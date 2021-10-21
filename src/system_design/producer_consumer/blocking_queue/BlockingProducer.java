package system_design.producer_consumer.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingProducer implements Runnable {

    private ArrayBlockingQueue<String> queue;

    public BlockingProducer(ArrayBlockingQueue<String> buffer) {
        this.queue = buffer;
    }

    @Override
    public void run() {
        String[] numbers = {"1", "2", "3", "4"};
        for (String num : numbers) {
            try {
                queue.put(num);
                System.out.println("Adding.." + num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("EOF");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
