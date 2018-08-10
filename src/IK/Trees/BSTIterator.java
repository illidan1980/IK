package IK.Trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by HIMANSHU on 5/20/2016.
 */
public class BSTIterator {
    private static class Node {
        int data;
        Node left;
        Node right;
        public Node(int d){
            data = d;
        }
    }
    Stack<Node> stack = new Stack<>();
    Node current = null;
    Node root = null;
    public BSTIterator(Node root){
        this.root = root;
        current = root;
        while(current != null){
            stack.push(current);
            current = current.left;
        }
        current = stack.peek();
    }

    public Node next() {
        if(stack.empty())
            return null;
        Node ret = stack.pop();
        Node tracer = ret;
        if(tracer.right != null){
            tracer = tracer.right;
            while(tracer != null){
                stack.push(tracer);
                tracer = tracer.left;
            }
        }
        return ret;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
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

    static void printPostOrderIter(Node root){
        //first fill the stack without popping
        // since we need the parents at the end
        if(root == null) return;
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(root);
        Node tracer = root;
        Node latestPrintedNode = tracer; // needed a dummy other than null here.
        while(!nodeStack.isEmpty()) {
            tracer = nodeStack.peek();
            // breaking the repeated additions.
            while(latestPrintedNode != tracer.left && latestPrintedNode != tracer.right) {
                // breaking the first addition.
                if(tracer.left == null && tracer.right == null)
                    break;
                if(tracer.right != null){
                    nodeStack.push(tracer.right);
                }
                if(tracer.left != null){
                    nodeStack.push(tracer.left);
                }
                tracer = nodeStack.peek();
            }

            latestPrintedNode = nodeStack.pop();
            System.out.print(latestPrintedNode.data + " ");
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
        printPostOrderIter(root);
        BSTIterator iterator = new BSTIterator(root);
        while((iterator.hasNext())){
            System.out.print("--> " + iterator.next().data);
        }
    }
}
