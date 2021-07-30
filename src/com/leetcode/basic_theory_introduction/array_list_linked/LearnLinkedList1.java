package com.leetcode.basic_theory_introduction.array_list_linked;

import java.util.LinkedList;
import java.util.ListIterator;

// class LinkedList<E> 比ArrayList多实现了一个双端队列Deque
//       extends AbstractSequentialList<E>
//       implements List<E>, Deque<E> (extends Queue<E>)

// LinkedList 特点: 数据查询比较慢(使用自定义的LinkedListNode结点), 增删中间节点的速度很快 !!
//                 非线程安全的，多线程访问的时候，需要在外部设置同步锁
// LinkedList 类型: 单向链表, 双向链表(Doubly Linked List), 循环链表(Cycle Linked List)
// Doubly-linked list implementation of the List and Deque interfaces
public class LearnLinkedList1 {

    private void testJavaLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1, 10);                  // TODO: 插入在指定的index位置，后面的元素会因此移位 !!
        list.add(10);                                  // 默认追加到末尾的位置
        int lastElement = list.get(list.size() - 1);   // 根据size()长度获得最后一个元素
        for (int num : list) {
            System.out.println(num);
        }
    }

    // 双向链表的遍历效果: 记录往前或者往后的标识
    // ... <-> previousItem <-> currentItem <-> nextItem <-> ...

    /**
     * 算法的设计是为了避免在元素之间形成无限的循环遍历效果 !!!
     * An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration,
     * and obtain the iterator current position in the list
     * A ListIterator has no current element; 没有当前的元素 !!!
     * its cursor position always lies between the element that would be returned by a call to previous() and next()
     * An iterator for a list of length n has n+1 possible cursor positions 光标的位置会比存储值的数目多一个 !
     */
    private static void displayLinkedList(LinkedList<String> linkedList) {
        ListIterator<String> stringListIterator = linkedList.listIterator();
        if (linkedList.isEmpty()) {
            System.out.println("No data in the linked list");
        } else {
            // 指示正在实行的遍历顺序，以便在换方向的时候，做遍历的更正(需要有一个节点的移动)
            boolean goForward = true;
            int moveAction = 1;
            switch (moveAction) {
                case 1:
                    // if 语句块用来恢复当前正在操作的节点位置 !!!
                    if (!goForward) {
                        if (stringListIterator.hasNext()) {
                            stringListIterator.next();
                        }
                        goForward = true;
                    }
                    if (stringListIterator.hasNext()) {
                        System.out.println("display the item: " + stringListIterator.next());
                    } else {
                        System.out.println("Find the end of the linked list");
                        goForward = false; // 只要唯一的一个查询方向
                    }
                case 2:
                    if (goForward) {
                        if (stringListIterator.hasPrevious()) {
                            stringListIterator.previous();
                        }
                        goForward = false;
                    }
                    if (stringListIterator.hasPrevious()) {
                        System.out.println("display the item: " + stringListIterator.previous());
                    } else {
                        System.out.println("Find the start of the linked list");
                        goForward = true;
                    }
                case 3:
                    // 移除当前的一个Item之后，应该自动跳到之后或者之前的Item，确保能够访问到 !!!
                    if (linkedList.size() > 0) {
                        stringListIterator.remove();
                    }
                default:
                    // 重复当前的Item, 确保是可以读取到的 !
                    if (goForward) {
                        System.out.println("Repeat the current item :" + stringListIterator.previous());
                    } else {
                        System.out.println("Repeat the current item :" + stringListIterator.next());
                    }
            }
        }
    }
}
