package IK.Recursion;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Himanshu on 6/4/2016.
 */
/*Recursion
        QUESTION DESCRIPTION
        A Tower of Hanoi is a game, that consists of three rods, and a number of disks of
        different sizes which can slide onto any rod. The game starts with the disks in a neat
        stack in ascending order of size on one rod, the smallest at the top. The objective of
        the puzzle is to move the entire stack to another rod, obeying the following
        simple rules:
        1. Only one disk can be moved at a time.


        QUESTION 1

        Total Score
        0/150
        1. Only one disk can be moved at a time.
        2. Each move consists of taking the upper disk from one of the stacks and placing it
        on top of another stack i.e. a disk can only be moved if it is the uppermost disk on a
        stack.
        3. No disk may be placed on top of a smaller disk.
        Input: Number of disks in the first rod. e.g if the number is 5, you can assume that
        disks are arranged with 1 on top of 2, 2 on top of 3 and so on, until 4 on top of 5,
        with 5 at the bottom.
        Output: Series of steps which leads to all disks landing on the third rod. Each step can
        be represented by printing the number of disks on each rod after the step is taken.
        There are no test-cases set, because of the fluid nature of the output. Feel free to
        hard code the input and display the output on STDOUT.
        Solution: http://www.cs.cmu.edu/~cburch/survey/recurse/hanoiimpl.html
        (Suggested time: 45 minutes; maybe a little more if you are printing output nicely)*/
public class TowerOfHanoi {
    public static void main(String args[] ) throws Exception {
/* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner reader = new Scanner( System.in
        ).useDelimiter(Pattern.compile("\\D+", Pattern.MULTILINE));;
        int disks = reader.nextInt();
        int tower = reader.nextInt();
        System.out.println( "Number of disks = " + disks);
        System.out.println( "move to tower = " + tower);
        moveTowerofHanoi( disks, tower );
        System.out.println( "total moves: " + totalMoves);
    }
    static void moveTowerofHanoi( int numDisks, int tower ) {
        if( tower == 2 )
            moveTowerofHanoiRecur( numDisks, 1, 2, 3);
        else
            moveTowerofHanoiRecur( numDisks, 1, 3, 2);
    }
    static void moveTowerofHanoiRecur( int numDisks, int source, int target, int
            temp) {
        if(numDisks == 0) {
            return;
        }
        moveTowerofHanoiRecur( numDisks - 1, source, temp, target );
        moveDisk( numDisks, source, target);
        moveTowerofHanoiRecur( numDisks - 1, temp, target, source );
    }
    static int totalMoves = 0;
    static void moveDisk( int diskNum, int source, int target) {
        System.out.println( "Moved disk " + diskNum + " from " + source + " to " +
                target);
        totalMoves++;
    }
}

