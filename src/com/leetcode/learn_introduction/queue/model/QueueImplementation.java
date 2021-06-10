package com.leetcode.learn_introduction.queue.model;

import java.util.ArrayList;
import java.util.List;

// 如何自定义实现一个"队列"类型
// 1. 使用动态的数组，支持不断的往队列中扩充元素
// 2. 需要指针始终指向出队列的那个元素
public class QueueImplementation {

    private int startIndex;
    private List<Integer> data;

    public QueueImplementation() {
        startIndex = 0;
        data = new ArrayList<>();
    }

    public boolean enQueue(int item) {
        data.add(item);
        return true;
    }

    public boolean deQueue() {
        if (startIndex < data.size()) {
            startIndex++;
            return true;
        }
        return false;
    }

    // 拿到应该出栈的那个元素
    public int front() {
        return data.get(startIndex);
    }

    public boolean isEmpty() {
        return startIndex >= data.size();
    }
}
