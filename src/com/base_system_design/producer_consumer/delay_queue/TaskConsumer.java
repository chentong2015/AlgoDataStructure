package com.base_system_design.producer_consumer.delay_queue;

import java.util.concurrent.DelayQueue;

public class TaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> queue;

    public TaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从队列中取出task，当队列中没有任务时，TaskConsumer会无限等待，直到被唤醒，因此它不会消耗CPU
                DelayedTask task = queue.take();
                System.out.println("Take " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
