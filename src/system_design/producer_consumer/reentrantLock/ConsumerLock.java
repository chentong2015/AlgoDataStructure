package system_design.producer_consumer.reentrantLock;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerLock implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock reentrantLock;

    public ConsumerLock(List<String> buffer, ReentrantLock reentrantLock) {
        this.buffer = buffer;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            if (buffer.isEmpty()) {
                reentrantLock.unlock();
                continue;
            }
            if (buffer.get(0).equals("EOF")) {
                System.out.printf("Existing..");
                reentrantLock.unlock();
                break;
            } else {
                System.out.println("removed " + buffer.remove(0));
            }
            reentrantLock.unlock();
        }
    }
}
