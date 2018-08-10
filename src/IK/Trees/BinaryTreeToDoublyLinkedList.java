package IK.Trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by HIMANSHU on 5/6/2016.
 */

public class BinaryTreeToDoublyLinkedList {

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
        Node head = convertToDoublyLinkedList(root);
        while(head != null){
            System.out.print( head.data + "--> ");
            head = head.right;
        }
    }

    private static Node convertToDoublyLinkedList(Node root) {
        return convertToDoublyLinkedListRecur(root, null, null, null);
    }

    private static Node convertToDoublyLinkedListRecur(Node root, Node leftParent, Node rightParent, Node head) {
        if(root == null) return null;

        if(root.left == null) {
            if (leftParent == null)
                head = root;
            else {
                root.left = leftParent;
                root.left.right = root;
            }
        }
        else
            head = convertToDoublyLinkedListRecur(root.left, leftParent, root, head);

        if(root.right == null) {
            if(rightParent != null) {
                root.right = rightParent;
                root.right.left = root;
            }
        }
        else
            head = convertToDoublyLinkedListRecur(root.right, root, rightParent, head);

        return head;
    }
}
