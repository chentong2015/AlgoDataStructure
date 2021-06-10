package com.leetcode.learn_introduction.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Queue队列：严格按照指定的访问顺序来process data
 * 1. Queue: First-in-first-out
 */
public class LearnQueue {

    // 直接使用LinkedList来作为队列使用 !!
    public void testQueue() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(10);
        q.offer(5);
        System.out.println("The first element is: " + q.peek()); // 查看，但是不会移除
        q.offer(13);
        q.offer(8);
        q.offer(6);
        if (!q.isEmpty()) {
            int value = q.poll(); // 取出第一个出队列的数
        }
        int size = q.size();
    }

    // TODO: 使用队列来实现"图和树"的BFS广度优先遍历
    //       "图"是在树的基础上增加了一些节点的连接线路，构成图形网络 !!
}
