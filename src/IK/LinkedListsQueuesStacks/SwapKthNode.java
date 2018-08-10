package IK.LinkedListsQueuesStacks;

/**
 * Created by HIMANSHU on 5/20/2016.
 */
public class SwapKthNode {
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
        for(int i: new int[]{3,1,2,0}) {
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
        head = swapNodes(head, 1);
        tracer = head;
        while(tracer != null){
            System.out.print((tracer != null ? tracer.data : "NULL" ) + " ");
            tracer = tracer.next;
        }

    }
}
