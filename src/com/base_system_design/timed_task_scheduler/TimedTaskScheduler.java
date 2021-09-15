package com.base_system_design.timed_task_scheduler;

import java.util.concurrent.DelayQueue;

// 定时任务调度器:
// 大量任务，每个任务都有一个时间戳，任务会在该时间点开始执行
// 如何指定的时间执行任务，而在尽量的减少等待的时间差，同时避免对CPU的过渡消耗
// 1. PriorityBlockingQueue + Polling轮询: 时间间隔不好控制
// 2. PriorityBlockingQueue + 时间差      : 时间差会造成任务处理不及时
// 3. DelayQueue 延迟阻塞队列
public class TimedTaskScheduler {

    public void testTimedTaskScheduler() {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}
