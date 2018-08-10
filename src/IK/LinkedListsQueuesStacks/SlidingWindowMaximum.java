package IK.LinkedListsQueuesStacks;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by HIMANSHU on 5/19/2016.
 */
public class SlidingWindowMaximum {

        static int[] maxSlidingWindow(int A[], int w) {
            int[] B = new int[A.length - w + 1];
            Deque<Integer> Q = new LinkedList<>();
            for (int i = 0; i < w; i++) {
                while (!Q.isEmpty() && A[i] >= A[Q.getLast()])
                    Q.removeLast();
                Q.addLast(i);
            }
            for (int i = w; i < A.length; i++) {
                B[i-w] = A[Q.getFirst()];
                while (!Q.isEmpty() && A[i] >= A[Q.getLast()])
                    Q.removeLast();
                while (!Q.isEmpty() && Q.getFirst() <= i - w)
                    Q.removeLast();
                Q.addLast(i);
            }
            B[A.length-w] = A[Q.getFirst()];
            return B;
        }
        public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
            int[] arr = {3,1,5};
            int[] arrb = null;

            for(int i: maxSlidingWindow(arr, 3))
                System.out.print(i + " ");
        }
    }
