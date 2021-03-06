package IK.Trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created  on 5/21/2016.
 */
public class PopulateSiblingPointer {
    private static class Node{
        int data;
        Node left;
        Node right;
        Node nextRight;
        public Node(int d){
            data = d;
            nextRight = null;
        }
    }
    public static Node constructTree(int[] iInOrderArray, int[] iPreOrderArray) {
        if(iInOrderArray.length != iPreOrderArray.length) return null;
        Node root = constructTreeRecur(iInOrderArray, iPreOrderArray,0, 0, iInOrderArray.length - 1);
        printTreeLevelOrder(root);
        return root;
    }

    private static void printTreeLevelOrder(Node node) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(node);
        Node flag = new Node(0);
        deque.addLast(flag);
        while(!deque.isEmpty()){
            Node current = deque.pop();
            if(current == flag){
                System.out.println(); if(!deque.isEmpty())deque.addLast(current); continue;
            }
            if(current.left != null){
                deque.addLast(current.left);
            }
            if(current.right != null){
                deque.addLast(current.right);
            }
            System.out.print(current.data + "-->" + (current.nextRight == null ? "null" : current.nextRight.data));
            if(!deque.isEmpty() && deque.peek() != flag)
                System.out.print(",");
        }
    }

    private static Node constructTreeRecur(int[] iInOrderArray, int[] iPreOrderArray, int preOrderIndex, int start, int end) {
        if(start > end) return null;
        Node root = new Node(iPreOrderArray[preOrderIndex]);
        if(start == end) return root;
        int indexOfRootinIn = start;
        // find this in inorder array
        while(iInOrderArray[indexOfRootinIn] != root.data) indexOfRootinIn++;
        root.left = constructTreeRecur(iInOrderArray, iPreOrderArray, preOrderIndex + 1, start, indexOfRootinIn - 1);
        root.right = constructTreeRecur(iInOrderArray, iPreOrderArray, preOrderIndex + 1 + (indexOfRootinIn - start), indexOfRootinIn + 1, end);
        return root;
    }

    public static void main(String[] args){
        int[] inOrder = {3,2,4,1,6,5,7};
        int[] preOrder = {1,2,3,4,5,6,7};
        Node root = constructTree(inOrder, preOrder);
        setRightSibling(root);
        printTreeLevelOrder(root);
    }

    private static void setRightSibling(Node root) {
        setRightSiblingRecur(root, null);
    }

    private static void setRightSiblingRecur(Node root, Node rightSibling) {
        if(root == null) return;
        root.nextRight = rightSibling;
        setRightSiblingRecur(root.left, root.right);
        setRightSiblingRecur(root.right, rightSibling == null ? null : rightSibling.left);
    }
}

