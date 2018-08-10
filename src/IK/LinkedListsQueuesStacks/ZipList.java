package IK.LinkedListsQueuesStacks;

/**
 * Created by HIMANSHU on 5/19/2016.
 */
public class ZipList {
    static class ListNode{
        int data;
        ListNode next;
        public ListNode (int d){
            data = d;
            next = null;
        }
    }
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode tracer = head;
        for(int i = 2; i <= 2; i++){
            tracer.next = new ListNode(i);
            tracer = tracer.next;
        }
        zipList(head);
        tracer = head;
        while(tracer != null){
            System.out.print("--> " + tracer.data);
            tracer = tracer.next;
        }
    }

    private static void zipList(ListNode head) {
        // split the list
        ListNode slowTracer = head;
        ListNode fastTracer = head;
        while(fastTracer != null){
            fastTracer = fastTracer.next;

            if(fastTracer != null){
                fastTracer = fastTracer.next;
                slowTracer = slowTracer.next;
            }
        }

        ListNode secondList = slowTracer.next;
        slowTracer.next = null;

        // reverse the second list
        ListNode next = null;
        ListNode current = secondList;
        //return if second list is empty
        if(current == null) return;
        ListNode prev = current.next;
        while(prev != null) {
             prev = current.next;
            current.next = next;
            next = current;
            current = prev;
        }
        secondList = next;

        // zip the final list
        // i.e. merge the two or interleave

        ListNode firstList = head;
        while(firstList != null && secondList != null){
            ListNode temp = firstList.next;
            firstList.next = secondList;
            ListNode temp2 = secondList.next;
            secondList.next = temp;
            firstList = temp;
            secondList = temp2;
        }

    }
}
