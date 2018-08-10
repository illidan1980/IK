package IK.Graphs;

import java.util.Random;

/**
 * Created by HIMANSHU on 5/16/2016.
 */


public class SkipList {
    private class Node
    {
        public Node[] Next;
        public int Value;

        public Node(int value, int level)
        {
            Value = value;
            Next = new Node[level];
        }
    }

    private Node _head = new Node(0, 33); // The max. number of levels is 33
    private Random _rand = new Random();
    private int _levels = 1;

    /// <summary>
    /// Inserts a value into the skip list.
    /// </summary>
    public void Insert(int value)
    {
        // Determine the level of the new node. Generate a random number R. The number of
        // 1-bits before we encounter the first 0-bit is the level of the node. Since R is
        // 32-bit, the level can be at most 32.
        int level = 0;
        for (int R = _rand.nextInt(); (R & 1) == 1; R >>= 1)
        {
            level++;
            if (level == _levels) { _levels++; break; }
        }

        // Insert this node into the skip list
        Node newNode = new Node(value, level + 1);
        Node cur = _head;
        for (int i = _levels - 1; i >= 0; i--)
        {
            for (; cur.Next[i] != null; cur = cur.Next[i])
            {
                if (cur.Next[i].Value > value) break;
            }

            if (i <= level) { newNode.Next[i] = cur.Next[i]; cur.Next[i] = newNode; }
        }
    }

    /// <summary>
    /// Returns whether a particular value already exists in the skip list
    /// </summary>
    public boolean Contains(int value)
    {
        Node cur = _head;
        for (int i = _levels - 1; i >= 0; i--)
        {
            for (; cur.Next[i] != null; cur = cur.Next[i])
            {
                if (cur.Next[i].Value > value) break;
                if (cur.Next[i].Value == value) return true;
            }
        }
        return false;
    }

    /// <summary>
    /// Attempts to remove one occurence of a particular value from the skip list. Returns
    /// whether the value was found in the skip list.
    /// </summary>
    public boolean Remove(int value) {
        Node cur = _head;

        boolean found = false;
        for (int i = _levels - 1; i >= 0; i--) {
            for (; cur.Next[i] != null; cur = cur.Next[i]) {
                if (cur.Next[i].Value == value) {
                    found = true;
                    cur.Next[i] = cur.Next[i].Next[i];
                    break;
                }

                if (cur.Next[i].Value > value) break;
            }
        }

        return found;
    }
}