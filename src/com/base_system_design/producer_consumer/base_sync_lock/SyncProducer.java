package com.base_system_design.producer_consumer.base_sync_lock;

import java.util.List;
import java.util.Random;

public class SyncProducer implements Runnable {

    private List<String> buffer;
    private String color;

    public SyncProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = {"1", "2", "3", "4"};
        for (String num : numbers) {
            try {
                synchronized (buffer) {
                    buffer.add(num);
                }
                System.out.println(color + "Adding.." + num);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (buffer) {
            buffer.add("EOF"); // "EOF": End of file String
        }
    }
}
