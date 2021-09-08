package com.base_system_design.producer_consumer.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingConsumer implements Runnable {

    private String color;
    private ArrayBlockingQueue<String> queue;

    public BlockingConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.queue = buffer;
        this.color = color;
    }

    /**
     * queue.peek()报错NullPointerException
     * 当两个线程同时判断queue非空时，然后其中一个线程调用peek()已经将element从queue中取出来
     * 另外一个线程再调用peek()进行取的时候，已经是空指针 !!
     */
    @Override
    public void run() {
        while (true) {
            // synchronized (queue) {
            if (queue.isEmpty()) {
                continue;
            }
            if (queue.peek().equals("EOF")) {
                System.out.printf(color + "Existing..");
                break;
            } else {
                try {
                    System.out.println(color + "removed " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //  }
        }
    }
}
