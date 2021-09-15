package com.base_system_design.timed_task_scheduler;

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

    private void produceRandomTasks() {
        int delay = random.nextInt(10000);
        String randomName = UUID.randomUUID().toString();
        DelayedTask task = new DelayedTask(randomName, delay);
        queue.put(task);
    }
}
