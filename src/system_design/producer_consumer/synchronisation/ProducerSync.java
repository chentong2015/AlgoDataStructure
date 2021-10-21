package system_design.producer_consumer.synchronisation;

import java.util.List;
import java.util.Random;

public class ProducerSync implements Runnable {

    private List<String> buffer;

    public ProducerSync(List<String> buffer) {
        this.buffer = buffer;
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
                System.out.println("Adding.." + num);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}
