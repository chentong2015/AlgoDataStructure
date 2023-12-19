package data_structure.base_data_structures.collections.queue.impl;

// TODO: 使用常规方法实现的队列会造成出栈后空间没有释放，从而没有办法在指定的长度范围下再添加新的item
//                     head = 1
// Circular Queue  |  | 3 | 5 | 6 | 9 | 15 |  count=5
//                                      tail = 5 循环的队列可以有效的节省长度，从开头再开始入新的元素到队列
// 1. 使用定长的数组，可能处于满队列的情况
// 2. 使用双指针来标记起使位置和结束位置
public class CircularQueueImplementation {

    private int[] queue;
    private int head;  // 通过head & tail没有办法严格的判断是否是空和满的状态 !!
    private int tail;
    private int count; // 使用count来统计数组中的数目，用于判断空和满的状态

    public CircularQueueImplementation(int length) {
        queue = new int[length];
        head = 0;  // head从0开始标记出队的位置
        tail = -1; // tail标识的始终是新值的左边位置
        count = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        tail = (tail + 1) % queue.length;
        queue[tail] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = (head + 1) % queue.length;
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return queue[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return queue[tail];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == queue.length;
    }
}
