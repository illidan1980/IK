package IK.Graphs;

import java.util.*;


/**
 * Created by HIMANSHU on 5/15/2016.
 */
public class Graph<T> {
    int V;
    AdjList<T> list;
    public Graph(int v){
        V = v;
        list = new AdjList(v);
    }

    public Graph(){
        V = 0;
        list = new AdjList<>();
    }

    public void addEdge(Node<T> nodeFrom, Node<T> nodeTo){
        list.addEdge(nodeFrom, nodeTo);
    }

    public Node<T> addNode(Node<T> node){
        return this.list.addNode(node);
    }

    public static void main(String[] args){
        Graph g = new Graph();
        g.createIntegerGraph();
        for(Object i: g.DFS(1,2)){
            System.out.print(((Node<Integer>) i).data + "-->");
        }
        System.out.println();
        for(Object i: g.BFS(2,1)){
            System.out.print(((Node<Integer>) i).data + "-->");
        }

        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(g.findOrder(dict));
    }

    String findOrder(String[] strDict){
        Graph<Character> topoSortGraph = new Graph<>();
        HashMap<Character, Node<Character>> uniqueNodes = new HashMap<>();
        for(int i = 1; i < strDict.length; i++) {
            int index = 0;
            while(index < strDict[i - 1].length() && index < strDict[i].length()
                && strDict[i - 1].charAt(index) == strDict[i].charAt(index)) {
                index++;
            }
            //we could have gone past string lengths; if not, we have a graph edge
            if(index < strDict[i - 1].length() && index < strDict[i].length()) {
                //add node and update hashmap
                if(!uniqueNodes.containsKey(strDict[i-1].charAt(index))) {
                   uniqueNodes.put(strDict[i-1].charAt(index), topoSortGraph.addNode(new Node<Character>(strDict[i-1].charAt(index))));
                }
                // add second node and update hashmap
                if(!uniqueNodes.containsKey(strDict[i].charAt(index))) {
                    uniqueNodes.put(strDict[i].charAt(index), topoSortGraph.addNode(new Node<Character>(strDict[i].charAt(index))));
                }

                //add the edge
                topoSortGraph.addEdge(uniqueNodes.get(strDict[i-1].charAt(index)), uniqueNodes.get(strDict[i].charAt(index)));
            }
        }

        return topoSortDict(topoSortGraph);
    }

    private String topoSortDict(Graph<Character> topoSortGraph) {
        StringBuilder ret = new StringBuilder();
        Stack<Node<Character>> stack = new Stack<>();
        HashSet<Node<Character>> visited = new HashSet<>();
        //toposortgraph is a list of nodes, each of which have neighbors for edges
        // for every node, go through and do DFS from that node such that
        for(Object node: topoSortGraph.list.list) {
            if(!visited.contains(node)) {
                stack.push((Node<Character>) node);
                visited.add((Node<Character>) node);
            }
            while(!stack.isEmpty()) {
                boolean atLeastOneNewNeighbor = false;
                for(Object neighbor: stack.peek().neighbors){
                    if(!visited.contains(neighbor)) {
                        stack.push((Node<Character>) neighbor);
                        atLeastOneNewNeighbor = true;
                        visited.add((Node<Character>) neighbor);
                    }
                }
                if(!atLeastOneNewNeighbor){
                    Node<Character> current = stack.pop();
                    ret.append(current.data);
                }
            }
        }

        ret.reverse();
        return ret.toString();
    }

    public void createIntegerGraph(){
        Node<T> a = addNode(new Node(0));
        Node<T> b = addNode(new Node(1));
        Node<T> c = addNode(new Node(2));
        Node<T> d = addNode(new Node(3));

        addEdge(a, b);
        addEdge(a, c);
        addEdge(b, c);
        addEdge(c, d);
        addEdge(d, d);
        addEdge(c, a);
    }

    public ArrayList<Node<T>> DFS(int a, int b){
        Node from = null;
        Node to = null;
        for(Object n: this.list.list){
            if(((Node<Integer>) n).data== a){
                from = (Node<Integer>) n;
            }

            if(((Node<Integer>) n).data == b){
                to  = (Node<Integer>) n;
            }
            if(to != null && from != null){
                return this.DFS(from, to);
            }
        }
        return null;
    }

    public ArrayList<Node<T>> BFS(int a, int b){
        //find nodes with these values
        Node from = null;
        Node to = null;
        for(Object n: this.list.list){
            if(((Node<Integer>) n).data== a){
                from = (Node<Integer>) n;
            }

            if(((Node<Integer>) n).data == b){
                to  = (Node<Integer>) n;
            }
            if(to != null && from != null){
                return this.BFS(from, to);
            }
        }
        return null;
    }

    public ArrayList<Node<T>> DFS(Node<T> from, Node<T> to){
        ArrayList<Node<T>> ret = new ArrayList<>();
        HashMap<Node<T>, Node<T>> parentMap = new HashMap<>();
        HashSet<Node<T>> visited = new HashSet<>();
        Stack<Node<T>> running = new Stack<>();
        running.push(from);
        while(!running.isEmpty()){
            Node current = running.pop();
            if(current.equals(to)){
                break;
            }
            visited.add(current);
            for(Object n: current.neighbors){
                if(!visited.contains(n)){
                    running.add((Node) n);
                    parentMap.put((Node) n, current);
                }
            }
        }

        Node<T> temp = to;
        while(parentMap.get(temp) != from){
            ret.add(temp);
            temp = parentMap.get(temp);
        }
        ret.add(temp);
        ret.add(parentMap.get(temp));
        Collections.reverse(ret);
        return ret;
    }

    public ArrayList<Node<T>> BFS(Node<T> from, Node<T> to){
        ArrayList<Node<T>> ret = new ArrayList<>();
        HashMap<Node<T>, Node<T>> parentMap = new HashMap<>();
        HashSet<Node<T>> visited = new HashSet<>();
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.add(from);
        while(!queue.isEmpty()){
            Node current = queue.removeFirst();
            if(current.equals(to)){
                break;
            }
            visited.add(current);
            for(Object n: current.neighbors){
                if(!visited.contains(n)){
                    queue.add((Node<T>) n);
                    parentMap.put((Node<T>) n, current);
                }
            }
        }

        Node<T> temp = to;
        while(!temp.equals(from)){
            ret.add(temp);
            temp = parentMap.get(temp);
        }
        ret.add(temp);

        Collections.reverse(ret);
        return ret;
    }
}


