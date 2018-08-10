package IK.Trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created  on 5/21/2016.
 */
public class LargestBST {
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
        int[] inOrder = {1,3,3,4,6,5,4};
        int[] preOrder = {4,3,1,3,5,6,4};
        Node root = constructTree(inOrder, preOrder);
        System.out.println("LargestBST size: " + largestBSTSize(root));
    }

    private static int largestBSTSize(Node root) {
        if(root == null) return 0;
        int ret = 1;
        if(root.left != null && root.left.data < root.data){
            ret  += largestBSTSize(root.left);
        }
        if(root.right != null && root.right.data >= root.data){
            ret  += largestBSTSize(root.right);
        }
        return ret;
    }
}

