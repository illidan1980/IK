package IK.LinkedListsQueuesStacks;

/**
 * Created by HIMANSHU on 5/20/2016.
 */
public class ReverseEveryKNodes {
    private static class LinkedListNode {
        int data;
        LinkedListNode next;
        public LinkedListNode(int d){
            data = d;
            next = null;
        }
    }
    static LinkedListNode swapNodes(LinkedListNode pList, int iK) {
        if(pList == null || pList.next == null) return pList;
        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next = pList;
        LinkedListNode slow = dummy;
        LinkedListNode fast = dummy;
        // go forth to iKth node
        for(int i = 1; i < iK; i++){
            fast = fast.next;
            if(fast == null){
                return pList;
            }
        }
        LinkedListNode replace = fast;
        // create the 1 extra shift
        fast = fast.next;
        if(fast == null){
            return pList; //fix this.
        }
        fast = fast.next;
        //go to the end
        while(fast != null ){
            fast = fast.next;
            slow = slow.next;
        }

        LinkedListNode temp = replace.next;
        replace.next = slow.next;
        slow.next = temp;
        temp = temp.next;
        slow.next.next = replace.next.next;
        replace.next.next = temp;
        return dummy.next;
    }
    public static void main(String[] args){
        LinkedListNode head = new LinkedListNode(0);
        LinkedListNode tracer = head;
        for(int i: new int[]{1,2,3,4,5,6,7}) {
            tracer.next = new LinkedListNode(i);
            tracer = tracer.next;
        }
        head = head.next;
        tracer = head;
        while(tracer != null){
            System.out.print((tracer != null ? tracer.data : "NULL" ) + " ");
            tracer = tracer.next;
        }
        System.out.println();
        head = reverseEveryK(head, 3);
        tracer = head;
        while(tracer != null){
            System.out.print((tracer != null ? tracer.data : "NULL" ) + " ");
            tracer = tracer.next;
        }
    }

    private static LinkedListNode reverseEveryK(LinkedListNode head, int iK) {
        // go forth every k
        if(head == null || head.next == null) return head;
        LinkedListNode front = head;
        for(int i = 1; i < iK; i++){
            front = front.next;
            if(front == null)
                break;
        }
        LinkedListNode nextList = front.next;
        front.next = null;
        reverseList(head);
        head.next = reverseEveryK(nextList, iK);;
        return front;
    }

    private static void reverseList(LinkedListNode head) {
        LinkedListNode next = null;
        LinkedListNode current = head;
        LinkedListNode prev;
        while(current != null){
            prev = current.next;
            current.next = next;
            next = current;
            current = prev;
        }
    }
}

