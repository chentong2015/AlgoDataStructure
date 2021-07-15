package com.leetcode.basic_theory_introduction.array_list_linked;

import java.util.LinkedList;

/**
 * Linked List 链表
 * 1. 数据查询比较慢
 * 2. 增删中间节点的数据很快 !!
 */
// Linked List类型: 单向链表, 双向链表(Doubly Linked List), 循环链表(Cycle Linked List)
// class LinkedList<E>
//       extends AbstractSequentialList<E>
//       implements List<E>, Deque<E> (extends Queue<E>)
public class LearnLinkedList1 {


    // 1. Doubly-linked list implementation of the List and Deque interfaces
    // 2. 可以从开始和结尾双向索引
    // 3. 非线程安全的，多线程访问的时候，需要在外部设置同步锁 !!
    private void testJavaLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0, 10);                  // 插入在指定的index位置，后面的元素会因此移位 !!
        list.add(10);                                  // 指定在末尾加入
        int lastValue = list.get(list.size() - 1);     // 获得最后一个元素
        for (int num : list) {
            System.out.println(num);
        }
    }
}
