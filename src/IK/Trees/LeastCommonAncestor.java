package IK.Trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created  on 5/20/2016.
 */
public class LeastCommonAncestor {
    private static class Node{
        int data;
        Node left;
        Node right;
        public Node(int d){
            data = d;
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
            System.out.print(current.data);
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
        int[] inOrder = {3,4,2,7,1,8,9,6};
        int[] preOrder = {1,2,3,4,7,6,8,9};
        Node root = constructTree(inOrder, preOrder);
        Node first = findNode(root, 2);
        Node second = new Node(0);//findNode(root, 2);
        System.out.println("LCA " + first.data + " " + second.data + " " + findLCA(root, first, second).data);
    }

    private static Node findLCA(Node root, Node first, Node second) {
        if(root == null) return root;
        if(root == first) return first;
        if(root == second) return second;
        Node left = findLCA(root.left, first, second);
        Node right = findLCA(root.right, first, second);
        if(left != null && right != null)
            return root;
        if(left != null)
            return left;
        return right;
    }

    private static Node findNode(Node root, int i) {
        if(root == null) return root;
        if(root.data == i)
            return root;
        Node node = findNode(root.left, i);
        if(node == null)
            node = findNode(root.right, i);
        return node;
    }
}
