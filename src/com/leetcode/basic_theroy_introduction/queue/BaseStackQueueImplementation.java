package com.leetcode.basic_theroy_introduction.queue;

import java.util.Stack;

// Implement a first in first out (FIFO) queue using only two stacks
// 1. 使用Stack栈来自定义queue队列数据结构: 从"先入后出"到"先入先出"的转化
// 2. 需要使用两个栈，只能调用stack的标准方法
// 3. 使用两个栈来将存储的顺序颠倒，其中一个栈是作为出栈数据临时存储的地方
public class BaseStackQueueImplementation {

    private Stack<Integer> stack;
    private Stack<Integer> tempStack;

    public BaseStackQueueImplementation() {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }

    // O(n) O(n) 使用额外的数据结构作为转换，同时需要遍历出之前全部存储的数据
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
        } else {
            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }
            stack.push(x);
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }
    }

    // 如果在push方法中没有使用临时stack作为转换存储，则取出的时候，可以使用临时stack做倒换 !!
    public int peek() {
        return stack.peek();
    }

    public int pop() {
        return stack.pop();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
