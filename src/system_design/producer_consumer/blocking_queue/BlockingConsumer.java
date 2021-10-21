package system_design.producer_consumer.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingConsumer implements Runnable {

    private ArrayBlockingQueue<String> queue;

    public BlockingConsumer(ArrayBlockingQueue<String> buffer) {
        this.queue = buffer;
    }

    @Override
    public void run() {
        while (true) {
            if (queue.isEmpty()) continue;
            if (queue.peek().equals("EOF")) {
                System.out.println("Existing..");
                break;
            } else {
                try {
                    System.out.println("removed " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
