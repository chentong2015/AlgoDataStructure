package system_design.producer_consumer.synchronisation;

import java.util.List;

public class ConsumerSync implements Runnable {

    private List<String> buffer;

    public ConsumerSync(List<String> buffer) {
        this.buffer = buffer;
    }

    // TODO: 避免在while循环中不断synchronized(buffer)获取锁，对性能造成影响
    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;  // 返回whine继续执行时，JVM会自动释放掉synchronized同步的锁
                }
                if (buffer.get(0).equals("EOF")) {
                    System.out.printf("Existing..");
                    break;
                } else {
                    System.out.println("removed " + buffer.remove(0));
                }
            }
        }
    }
}
