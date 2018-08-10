package Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HIMANSHU on 5/3/2016.
 */
public class Multithreading {
    public static void main(String[] args){
        Processor p = new Processor();
        p.start();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        p.shutdown();

        SyncAtomic s = new SyncAtomic();
        s.doWork();

        Sync s1 = new Sync();
        s1.doWork();
    }
}

class Processor extends Thread{
    private volatile boolean running = true;
    public void run(){
        while(running)
            System.out.println("Hello! ");
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void shutdown(){
        running = false;
    }
}

class SyncAtomic {
    private AtomicInteger count = new AtomicInteger(0);

    public void doWork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    count.addAndGet(1);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    count.addAndGet(1);
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

}

class Sync {
    private int count = 0;

    private synchronized void increment(){
        count++;
    }
    public void doWork(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    increment();
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }

}