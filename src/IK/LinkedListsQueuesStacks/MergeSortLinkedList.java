package IK.LinkedListsQueuesStacks;

import java.util.Random;

/**
 * Created  on 5/19/2016.
 */
public class MergeSortLinkedList {
    private static class LinkedListNode {
        int data;
        LinkedListNode next;
        public LinkedListNode(int d){
            data = d;
            next = null;
        }
    }

    static LinkedListNode splitList(LinkedListNode first){
        if(first == null) return null;
        // split the list
        LinkedListNode slowTracer = first;
        LinkedListNode fastTracer = first;
        while(fastTracer.next != null){
            fastTracer = fastTracer.next;

            if(fastTracer.next != null){
                fastTracer = fastTracer.next;
                slowTracer = slowTracer.next;
            }
        }
        LinkedListNode temp = slowTracer.next;
        slowTracer.next = null;
        return temp;
    }
    static LinkedListNode mergeSortList(LinkedListNode pList) {
        if(pList == null) return null;
        if(pList.next == null) return pList;
        LinkedListNode first = pList;
        LinkedListNode second = splitList(first);
        first = mergeSortList(first);
        second = mergeSortList(second);
        return mergeSortedLists(first, second);
    }

    private static LinkedListNode mergeSortedLists1(LinkedListNode a, LinkedListNode b){
        LinkedListNode dummy = new LinkedListNode(0);
        LinkedListNode tracer = dummy;
        while(a != null && b != null ){
            if(a.data < b.data){
                tracer.next = a;
                a = a.next;
            } else {
                tracer.next = b;
                b = b.next;
            }
            tracer = tracer.next;
        }
        tracer.next = a == null ? b : a;
        return dummy.next;
    }
    private static LinkedListNode mergeSortedLists(LinkedListNode first, LinkedListNode second) {
        LinkedListNode ret = new LinkedListNode(0);
        LinkedListNode newNode = ret;
        while(first != null && second != null){
            if(first.data > second.data) {
                ret.next = second;
                second = second.next;
            } else {
                ret.next = first;
                first = first.next;
            }
            ret = ret.next;
        }
        if(first != null) {
            ret.next = first;
        }
        if(second != null){
            ret.next = second;
        }
        first = newNode.next;
        newNode.next = null;
        return first;
    }

    public static void main(String[] args) {
        //construct
        LinkedListNode head = new LinkedListNode(0);
        LinkedListNode tracer = head;
        Random rand = new Random();
        int[] arr = {11,4,6,7};
        for (int i = 1; i < 3; i++) {
            tracer.next = new LinkedListNode(rand.nextInt(Integer.SIZE - 1) % 15);
            tracer = tracer.next;
        }
        LinkedListNode head2 = new LinkedListNode(0);
        LinkedListNode tracer2 = head2;
        for (int i : arr) {
            tracer2.next = new LinkedListNode(i);
            tracer2 = tracer2.next;
        }
        head2 = head2.next;
        tracer = head;
        while(tracer != null){
            System.out.print(tracer.data + " ");
            tracer = tracer.next;
        }
        head2 = mergeSortList(head2);
        tracer = head2;
        while(tracer != null){
            System.out.print(tracer.data + " ");
            tracer = tracer.next;
        }
    }
}
