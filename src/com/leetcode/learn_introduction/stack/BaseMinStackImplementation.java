package com.leetcode.learn_introduction.stack;

import com.leetcode.learn_introduction.stack.model.StackNode;

// Stack 特殊实现
// TODO: 借助链表的数据结构来实现具有特殊功能的Stack, 实时更新和保留stack中特殊数据的信息
// (value, min, next) -> (value, min, next) -> (value, min, next) ....
// 由于需要快速的返回stack中的最小值，所以在每个(head) node上都保存min的信息，方便及时读取 !!
public class BaseMinStackImplementation {

    private StackNode head;

    public void push(int x) {
        if (head == null) {
            head = new StackNode(x, x);
        } else {
            head = new StackNode(x, Math.min(x, head.min), head);
        }
    }

    public int top() {
        return head.val;
    }

    public void pop() {
        head = head.next;
    }
    
    public int getMin() {
        return head.min;
    }
}
