package com.base_system_design.producer_consumer.base_sync_lock;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LockConsumer implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock reentrantLock;

    public LockConsumer(List<String> buffer, String color, ReentrantLock reentrantLock) {
        this.buffer = buffer;
        this.color = color;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            if (buffer.isEmpty()) {
                reentrantLock.unlock(); // 必须释放，否则没有办法执行到后面的unlock();
                continue;  // 返回whine继续执行时，没有将bufferLock释放掉，导致producer线程没有办法获得lock, list状态不会改变 !!
            }
            if (buffer.get(0).equals("EOF")) {
                System.out.printf(color + "Existing..");
                reentrantLock.unlock(); // 必须释放，否则没有办法执行到后面的unlock();
                break;
            } else {
                System.out.println(color + "removed " + buffer.remove(0));
            }
            reentrantLock.unlock();
        }
    }
}
