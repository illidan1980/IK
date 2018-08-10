package IK.HashTables;

/**
 * Created  on 6/1/2016.
 */

public class MyHashMap<K, V> {
    private static long SIZE = 7;
    private Node[] arr;
    private long entries = 0;
    private static class Node<K, V>{
        K key;
        V value;
        Node<K, V> next;
        public Node(K key, V val){
            this.key = key;
            value = val;
        }
    }

    public MyHashMap(){
        arr = new Node[(int) SIZE];
    }

    public synchronized void add(K key, V val){
        int index = (int) (key.hashCode() % SIZE);
        Node temp = arr[index];
        arr[index] = new Node(key, val);
        arr[index].next = temp;
        entries++;

        //REHASH logic!
        if(this.loadFactor() > 0.5D){
            this.rehash();
        }
    }

    public synchronized V get(K key){
        int index = (int) (key.hashCode() % SIZE);
        Node tracer = arr[index];
        while(tracer != null){
            if(tracer.key.equals(key))
                return (V)tracer.value;
            tracer = tracer.next;
        }
        return null;
    }

    public synchronized int size() {
        return (int) this.SIZE;
    }

    public synchronized boolean delete(K key){
        int index = (int) (key.hashCode() % SIZE);
        Node tracer = arr[index];
        if(tracer.key.equals(key)){
            this.arr[index] = this.arr[index].next;
        } else {
            while(tracer.next != null){
                if(tracer.next.key.equals(key)){
                    tracer.next = tracer.next.next;
                    entries--;
                    return true;
                }
                tracer = tracer.next;
            }
        }
        return false;
    }

    private synchronized double loadFactor(){
        return (entries / SIZE);
    }

    private synchronized void rehash(){
        int size = getNextPrime((int) SIZE);
        Node[] arr = new Node[size];
        //copy to the new location.
        for(int i = 0; i < this.SIZE; i++){
            Node tracer = this.arr[i];
            while(tracer != null){
                int index = tracer.key.hashCode() % size;
                Node temp = arr[index];
                arr[index] = this.arr[i];
                arr[index].next = temp;
                tracer = tracer.next;
            }
        }
        this.arr = arr;
        this.SIZE = size;
    }

    private int getNextPrime(int size) {
        int candidate = size * 2 + 1;
        while(!isPrime(candidate)){
            candidate += 2;
        }
        return candidate;
    }

    private boolean isPrime(long candidate) {
        // 1. is odd
        // 2. does not get divided by any odd between 2 and the number
        // 3. is not less than 2 or is not 2 or 3 or is not a multiple of 3
        if(candidate < 2) return false;
        if(candidate == 2 || candidate == 3 || candidate % 3 == 0) return false;
        if(candidate % 2 == 0)
            return false;
        // i square may overflow so sqrt the candidate
        int sqrt = (int) Math.sqrt(candidate);
        for(long i = 6L; i < sqrt; i += 6L){
            if(candidate % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        MyHashMap<String, Integer> map = new MyHashMap();
        for(int i = 0; i < 7; i++) {
            map.add((String.valueOf(i)), i);
        }

        for(int i = 0; i < map.size(); i++){
            System.out.println( i + " --> " + map.get(String.valueOf(i)));
        }
    }

    public static int stringHash(String str, int modulus) {
        int kMult = 997;
        int val = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            val = (val * kMult + c) % modulus;
        }
        return val;
    }
}
