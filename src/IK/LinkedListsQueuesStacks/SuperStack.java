package IK.LinkedListsQueuesStacks;

/**
 * Created  on 5/19/2016.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SuperStack {
    public static void main(String[] argv) {

        String[] cmds = null;

        String cmd = null;

        ArrayList<Integer> stk = new ArrayList<>();
        int here = -1;
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        cmds = new String[Integer.parseInt(scan.nextLine().toString())];
        for( int i = 0; i < cmds.length; i++ ) {
           cmds[i] = scan.nextLine().toString();
        }
        for( int i = 0; i < cmds.length; i++ ) {
            StringTokenizer st = new StringTokenizer( cmds[i], " " );
            cmd = st.nextToken();

            if( "pop".compareTo(cmd) == 0 ) {
                if( here == 0 ) {
                    here--; // Do POP
                    System.out.println("EMPTY");
                }
                else {
                    here--; // Do POP
                    System.out.println(stk.get(here));
                }
            } else if( "push".compareTo(cmd) == 0 ) {
                stk.set(++here, Integer.parseInt(st.nextToken())); // Do PUSH
                System.out.println(stk.get(here));
            } else if( "inc".compareTo(cmd) == 0 ) {
                int t = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                stk.trimToSize();
                for( int k=0; k< Math.min(t, stk.size()); k++ ) {
                    stk.set(k, stk.get(k) + d);
                }
                System.out.println(stk.get(here));
            }
        }
        System.out.println("-Top---------------------------");
        for( int i=here; i>=0; i-- ) {
            System.out.println(stk.get(i));
        }
        System.out.println("-Bottom------------------------");
    }
}
