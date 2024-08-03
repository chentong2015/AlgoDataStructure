package data_structure.queue;

import java.util.*;

public class LearnQueue1 {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.offer(5);
        int size = queue.size();
        Integer peekValue = queue.peek(); // 查看出队列元素但不移除，可能返回null，不能赋值给值类型 !!
        if (!queue.isEmpty()) {
            int value = queue.poll(); // 取出第一个出队列元素
        }
    }

    // 优先队列：根据元素的优先级(排序)，优先级高的先出队列
    // 定义优先队列中元素的Comparator排序规则
    // 输出顺序: 1.5 2.0 3.0 4.0
    // 反序输出: 4.0 3.0 2.0 1.5
    private void testPriorityQueue1() {
        Queue<Double> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.offer(3.0);
        queue.offer(1.5);
        queue.offer(2.0);
        queue.offer(4.0);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    // 输出顺序: A B C D
    private void testPriorityQueue2() {
        Queue<String> queue = new PriorityQueue<>();
        queue.offer("D");
        queue.offer("B");
        queue.offer("A");
        queue.offer("C");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
