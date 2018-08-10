package IK.LinkedListsQueuesStacks;

import java.util.Random;

/**
 * Created  on 5/19/2016.
 */
public class CloneSpecialList {
    private static class LinkedListNode {
        int data;
        LinkedListNode next;
        LinkedListNode arbit;
        public LinkedListNode(int d){
            data = d;
            next = null;
            arbit = null;
        }
    }

    public static void main(String[] args){
        //construct
        LinkedListNode head = new LinkedListNode(0);
        LinkedListNode tracer = head;
        LinkedListNode[] arr = new LinkedListNode[15];
        arr[0] = head;
        for(int i = 1; i < 15; i++) {
            tracer.next = new LinkedListNode(i);
            tracer = tracer.next;
            arr[i] = tracer;
        }
        tracer = head;
        Random rand = new Random();
        for(int i = 0; i < 15; i++) {
            tracer.arbit = arr[rand.nextInt(Integer.SIZE - 1) % 15];
            tracer = tracer.next;
        }

        LinkedListNode clone = CloneList(head);
        tracer = head;
        while(tracer != null){
            System.out.print((tracer.arbit != null ? tracer.arbit.data : "NULL" ) + " ");
            tracer = tracer.next;
        }
        System.out.println();
        tracer = clone;
        while(tracer != null){
            System.out.print((tracer.arbit != null ? tracer.arbit.data : "NULL" ) + " ");
            tracer = tracer.next;
        }
    }

    private static LinkedListNode CloneList(LinkedListNode head) {
        if(head == null) return null;
        //clone original
        LinkedListNode tracer = head;
        LinkedListNode temp = null;
        while(tracer != null){
            temp = tracer.next;
            tracer.next = new LinkedListNode(tracer.data);
            tracer.next.next = temp;
            tracer = temp;
        }

        tracer = head;
        while(tracer != null){
            if(tracer.arbit != null){
                tracer.next.arbit = tracer.arbit.next;
            }
            tracer = tracer.next.next;
        }

        tracer = head;
        //seperate
        LinkedListNode second = head.next;
        LinkedListNode secondtracer = second;
        while(tracer != null){
            tracer.next = tracer.next.next;
            tracer = tracer.next;
            if(tracer != null){
                secondtracer.next = tracer.next;
                secondtracer = secondtracer.next;
            }
        }

        return second;
    }
}
