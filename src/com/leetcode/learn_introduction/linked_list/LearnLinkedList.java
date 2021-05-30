package com.leetcode.learn_introduction.linked_list;

import com.leetcode.top_interview_questions.base.ListNode;

/**
 * Linked List
 * 1. 链表的优缺点：数据查询比较慢, 增删中间节点的数据很快
 * 2. 链表的类型 ：单向链表，双向链表，循环链表(Cycle Linked List)
 * 3. 常见技术 + 特殊情况
 * >    操作和遍历链表的时候，直接判断Node是否为null
 * >    在链表前面添加DummyHead作为依托
 * >    迭代法 + 递归法
 * >    充分利用节点值做判断，按顺序插入节点
 */
public class LearnLinkedList {

    // TODO: 典型链表递归算法
    // Swap Nodes in Pairs
    // Swap every two adjacent nodes and return its head
    // Solve the problem without modifying the values in the list's nodes 不能通过修改值来交换
    // head = [1,2,3,4] -> [2,1,4,3]
    public ListNode swapPairs(ListNode head) {
        // 测试理解: 1. 没交换两个节点之后，后面的节点由递归去实现交换  O(n) 递归调用造成栈空间的开销 O(1)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
