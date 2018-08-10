package IK.Graphs;

import java.util.List;

/**
 * Created by HIMANSHU on 5/15/2016.
 */
public class Node<T> {
    T data;
    List<Node<T>> neighbors;

    public Node(T n){
        data = n;
    }
}
