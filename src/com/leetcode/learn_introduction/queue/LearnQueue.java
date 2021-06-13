package com.leetcode.learn_introduction.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Queue队列：严格按照指定的访问顺序来process data
 * 1. Queue: First-in-first-out
 * 2. Queue: BFS 广度优先遍历
 */
public class LearnQueue {

    public void testQueue() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(10);
        q.offer(5);
        // 查看应该出队列的元素，但是不会移除，可能返回null，不能赋值给值类型 !!
        System.out.println("The first element is: " + q.peek());
        q.offer(13);
        q.offer(8);
        q.offer(6);
        if (!q.isEmpty()) {
            int value = q.poll(); // 取出第一个出队列的数
        }
        int size = q.size();
    }
}
