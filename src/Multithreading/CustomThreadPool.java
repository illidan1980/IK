package Multithreading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HIMANSHU on 5/16/2016.
 */
public class CustomThreadPool {
    private BlockingQueue taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    public CustomThreadPool(int numThreads, int maxTasks){
        taskQueue = new BlockingQueue(maxTasks);

        for(int i = 0; i < numThreads; i++) {
            threads.add(new PoolThread(taskQueue));
        }
        for(PoolThread thread: threads){
            thread.start();
        }
    }

    public synchronized void execute(Runnable task) throws Exception{
        if(this.isStopped) throw new IllegalStateException("Threadpool is stopped.");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(PoolThread thread: threads){
            thread.doStop();
        }
    }
}

class PoolThread extends Thread{
    private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;

    public PoolThread(BlockingQueue queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}

class BlockingQueue {
    private List queue = new LinkedList();
    private int limit;

    public BlockingQueue(int limit){
        this.limit = limit;
    }

    public synchronized void enqueue(Object o) throws InterruptedException{
        while(this.queue.size() == this.limit){
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }

        this.queue.add(o);
    }

    public synchronized Object dequeue() throws InterruptedException {
        while(this.queue.size() == 0){
            wait();
        }

        if(this.queue.size() == this.limit){
            notify();
        }

        return this.queue.remove(0);
    }
}