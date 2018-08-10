package IK.Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HIMANSHU on 5/15/2016.
 */
public class AdjList<T> {
    List<Node<T>> list;
    public AdjList(int v) {
        list = new ArrayList<>(v);
    }
    public AdjList(){
        list = new ArrayList<>();
    }

    public Node addNode(Node<T> node){
        //this is the first ever time a node is added; initialize the neighbors list.
        node.neighbors = new ArrayList<>();
        list.add(node);
        return node;
    }

    public void addEdge(Node<T> nodeFrom, Node<T> nodeTo){
        list.get(list.indexOf(nodeFrom)).neighbors.add(nodeTo);
    }
}
