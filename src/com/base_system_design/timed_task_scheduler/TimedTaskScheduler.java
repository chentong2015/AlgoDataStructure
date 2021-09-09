package com.base_system_design.timed_task_scheduler;

import java.util.concurrent.DelayQueue;

/**
 * "定时任务调度器":
 * 大量任务，每个任务都有一个时间戳，任务会在该时间点开始执行
 * 如何指定的时间执行任务，而在尽量的减少等待的时间差，同时避免对CPU的过渡消耗
 * 1. PriorityBlockingQueue + Polling: 时间间隔不好控制
 * 2. PriorityBlockingQueue + 时间差  : 时间差会造成任务处理不及时
 * 3. DelayQueue -> ScheduledThreadPoolExecutor 工作原理一致
 */
// HashedWheelTimer时间轮(循环队列): 循环队列中的每个Node，执行每个Node下面的应该被执行的任务(根据延迟时间依次)
// "分布式的定时任务调度器": Redis, RabbitMQ等
public class TimedTaskScheduler {

    /**
     * 1. DelayQueue是一个特化版的PriorityBlockingQueue
     * 它内部用了一个优先队列，所以插入和删除的时间复杂度都是log(n)
     * 2. 把计算时间差并让消费者等待该时间差的功能集成进了队列
     * 3. DelayQueue的设计其实是一个Leader/Follower模式，每个消费者线程只需要等待所需要的时间差，因此响应速度更快
     */
    public void testTimedTaskScheduler() {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        Thread producer = new Thread(new TaskProducer(queue), "Producer Thread");
        Thread consumer = new Thread(new TaskConsumer(queue), "Consumer Thread");
        producer.start();
        consumer.start();
    }
}
