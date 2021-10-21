package data_structure.stack;

import java.util.ArrayDeque;
import java.util.Queue;

// Implement Stack using Queues
// 1. Implement a last in first out (LIFO) stack using only two queues
// 2. 使用队列数据结构来实现stack栈，只能使用队列的标准方法
// 3. 因为队列的出口是双端的，所以理论上只需要一个Queue则可以实现stack的全部功能 !!
public class BaseQueueStackImplementation {

    private Queue<Integer> queue;

    public BaseQueueStackImplementation() {
        queue = new ArrayDeque<>();
    }

    // 可以直接在push的时候将queue队列进行循环颠倒依次，和stack的出栈顺序一致 !!
    public void push(int x) {
        queue.add(x);
    }

    // 从队列开头开始出队列，然后再入队列，根据size()队列长度判断是否是最后一个元素 !!
    // O(n) O(1)
    public int peek() {
        recycleQueue();
        int value = queue.poll(); // 确定队列中一定是有值的
        queue.add(value);
        return value;
    }

    private void recycleQueue() {
        for (int index = 0; index < queue.size() - 1; index++) {
            queue.add(queue.poll());
        }
    }

    // O(n) O(1)
    public int pop() {
        recycleQueue();
        return queue.poll();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
