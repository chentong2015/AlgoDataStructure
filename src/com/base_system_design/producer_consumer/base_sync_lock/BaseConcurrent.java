package com.base_system_design.producer_consumer.base_sync_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class BaseConcurrent {

    /**
     * Concurrency并发：支持"并发使用"设计的类型
     * 当多个线程在操该类型的同一个实例对象时，同一时刻，只有一个Thread能够在运行get()或者put()方法
     * 反之，如果两个并发的线程同时执行put()方法，会造成object对象(其中一个添加对象)的丢失
     */
    private Object boxContents;

    public synchronized Object get() {
        Object contents = boxContents;
        boxContents = null;
        return contents;
    }

    public synchronized boolean put(Object contents) {
        if (boxContents != null) {
            return false;
        }
        boxContents = contents;
        return true;
    }

    /**
     * Producer - Consumer模式：一个生产者，两个消费者(并发)的消费
     * 问题1. List中有数据的时候，两个Consumer同时发现，其中一个消费之后，另外一个将无法成功读取，抛出异常IndexOutOfBoundsException
     * 问题2. 两个Consumer同时移除同一个Item, 抛出异常
     * 问题3. 由于生产者add数据的时间随机，而造成的异常
     * 解决方案：Synchronisation of blocks 对list添加同步化; 使用list实例对象作为同步锁 !!!
     */
    public static void testConcurrentSynchronized() {
        List<String> buffer = new ArrayList<>(); // List is not synchronized 非线程安全的
        SyncProducer producer = new SyncProducer(buffer, "ThreadColor.ANSI_BLACK");
        SyncConsumer consumer1 = new SyncConsumer(buffer, "ThreadColor.ANSI_BLUE");
        SyncConsumer consumer2 = new SyncConsumer(buffer, "ThreadColor.ANSI_GREEN");
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }

    /**
     * Producer - Consumer模式：一个生产者，两个消费者(并发)的消费
     * 解决方案：使用ReentrantLock提供锁 implements Lock
     * 1. 如果一个线程已经拥有reentrantLock, 当遇到再次需要锁的时候，则可以直接继续执行，不用再次获取
     * 2. reentrantLock锁必须在lock之后释放掉unlock, jvm不会在运行结束之后自动释放 !!!
     * 3. 如果reentrantLock没有释放掉，则同一个线程可以多次获取锁，导致异常Exception: Maximum lock count exceeded !!
     * 4. 如果reentrantLock释放了一个没有获得锁，则会抛出异常Illegal monitor state exception !!
     */

    // 1. 优化获取方式：使用tryLock()尝试获取lock，如果没有获取成功，则可以执行另外的代码
    //    if(bufferLock.tryLock(1000, TimeUnit.MILLISECONDS)) {
    //       try ...
    //    } else {
    //        System.out.println("Could not get the lock");
    //    }
    //    尝试的次数可能极大，可指定timeout时间，避免不必要的try尝试
    // 2. 优化释放方式: 使用try - finally语句块, 确保一定会释放，且只释放一次
    //    bufferLock.lock();
    //    try {
    //      doSomething();
    //    } finally {
    //      bufferLock.unlock();
    //    }
    // 3. 可以判断在等待的线程队列的数目 getQueueLength()
    public static void testReentrantLockUnlock() {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();
        bufferLock.getQueueLength(); // 获取在等待获得锁的线程队列的thread的数量 !!!

        LockProducer producer = new LockProducer(buffer, "ThreadColor.ANSI_BLACK", bufferLock);
        LockConsumer consumer1 = new LockConsumer(buffer, "ThreadColor.ANSI_BLUE", bufferLock);
        LockConsumer consumer2 = new LockConsumer(buffer, "ThreadColor.ANSI_GREEN", bufferLock);
        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }

    /**
     * Re-entrant: 如果一个线程获得到同步锁之后，可以再继续执行需要该同步锁的代码块
     * Once a thread has acquired a lock, it is allowed to re-acquire it as many times as it pleases
     */
    private final String instanceObject = "lockObject";

    private void testReentrant() {
        synchronized (instanceObject) {
            synchronized (instanceObject) {
                System.out.println("Check it");
            }
        }
    }
}
