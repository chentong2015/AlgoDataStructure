package com.base_system_design.producer_consumer.reentrantLock;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerLock implements Runnable {

    private List<String> buffer;
    private ReentrantLock reentrantLock;

    public ProducerLock(List<String> buffer, ReentrantLock reentrantLock) {
        this.buffer = buffer;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = {"1", "2", "3", "4"};
        for (String num : numbers) {
            try {
                reentrantLock.lock();
                buffer.add(num);
                reentrantLock.unlock();
                System.out.println("Adding.." + num);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        reentrantLock.lock();
        buffer.add("EOF"); // "EOF": End of file String
        reentrantLock.unlock();
    }
}
