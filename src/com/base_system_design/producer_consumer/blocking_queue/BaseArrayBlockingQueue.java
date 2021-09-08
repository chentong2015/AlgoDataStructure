package com.base_system_design.producer_consumer.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BaseArrayBlockingQueue {

    /**
     * Producer - Consumer模式：一个生产者，两个消费者(并发)的消费
     * 1. ArrayBlockingQueue是线程安全的, 注意是否需要使用synchronized同步机制 !!
     * 2. 共享的数据本质上是一个队列，遵循FIFO的原则，可使用BlockingQueue
     */
    private static void testArrayBlockingQueue() {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);
        BlockingProducer producer = new BlockingProducer(buffer, "ThreadColor.ANSI_BLACK");
        BlockingConsumer consumer1 = new BlockingConsumer(buffer, "ThreadColor.ANSI_BLUE");
        BlockingConsumer consumer2 = new BlockingConsumer(buffer, "ThreadColor.ANSI_GREEN");
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
