package Multithreading;

import java.util.Scanner;

/**
 * Created by HIMANSHU on 5/4/2016.
 */
public class ProducerConsumer {
    public static void main(String[] args){
        final ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        pc.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        pc.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("producer thread running");

            wait();
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(1000);

        synchronized (this){
            System.out.println("waiting for the key");
            scanner.nextLine();
            System.out.println("return key pressed.");
            notify();
        }
    }
}
