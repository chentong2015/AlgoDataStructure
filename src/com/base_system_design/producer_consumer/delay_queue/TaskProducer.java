package com.base_system_design.producer_consumer.delay_queue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.DelayQueue;

public class TaskProducer implements Runnable {

    private final Random random = new Random();
    private DelayQueue<DelayedTask> queue;

    public TaskProducer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                produceRandomTasks();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 生产一些时间随机的任务，添加到队列中
    private void produceRandomTasks() {
        int delay = random.nextInt(10000);
        String randomName = UUID.randomUUID().toString();
        DelayedTask task = new DelayedTask(randomName, delay);
        queue.put(task);
    }
}
