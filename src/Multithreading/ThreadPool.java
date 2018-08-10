package Multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by HIMANSHU on 5/4/2016.
 */
 class ThreadPoolThread implements Runnable {
    private int id;
    public ThreadPoolThread(int i){
        this.id = i;
    }
    @Override
    public void run() {
        System.out.println("starting..." + id);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished!");
    }
}

public class ThreadPool {
    public static void main(String[] args){
        ExecutorService myTPool = Executors.newFixedThreadPool(2);

        for(int i = 0;i < 5; i++){
            myTPool.submit(new ThreadPoolThread(i));
        }

        myTPool.shutdown();

        System.out.println("all tasks submitted.");

        try {
            myTPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all tasks completed.!");

        System.out.println("countdown latches start");

        ExecutorService service = Executors.newFixedThreadPool(5);
        final CountDownLatch latch = new CountDownLatch(5);

        for(int x = 0; x < 5; x++) {
            service.submit(new Runnable() {
                public void run() {
                    // do something
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.println("done waating on countdown latches");
    }
}
