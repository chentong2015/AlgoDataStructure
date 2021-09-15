package com.base_system_design.timed_task_scheduler;

import java.util.concurrent.DelayQueue;

public class TaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> queue;

    public TaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    // 消费者不需要关心时间差，直接在while(true)里不断take()
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
