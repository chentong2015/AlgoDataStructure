package com.leetcode.learn_introduction.array_list_linked;

import com.leetcode.interview_questions.base.ListNode;

/**
 * Linked List高级解法：
 * 1. 两种常规解法：迭代法 & 递归法(简单直接)
 * 2. 之间判断node是否为null，而不是node.next !!
 * 3. 构建dummyHead作用head的前面结点，作为保留
 */
public class LearnLinkedList2 {

    // TODO: 典型链表递归算法
    // Swap Nodes in Pairs
    // Swap every two adjacent nodes and return its head
    // Solve the problem without modifying the values in the list's nodes 不能通过修改值来交换
    // head = [1,2,3,4] -> [2,1,4,3]
    public ListNode swapPairs(ListNode head) {
        // 测试理解: 1. 每交换两个节点之后，后面的节点由递归去实现交换，每调用一次交换两个位置的前后
        //             O(n) 递归调用造成栈空间的开销 O(1)
        if (head == null || head.next == null) return head;
        ListNode nextNode = head.next;
        head.next = swapPairs(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }

    // Remove Duplicates from Sorted Linked List
    // 保证升序排列的链表，其中的int类型的值可正可负
    // head = [1,2,3,3,4,4,5] -> [1,2,3,4,5]
    public ListNode removeDuplicated(ListNode head) {
        // Test: O(n) 最多递归n-1次的造成的时间复杂度 O(1)
        if (head == null || head.next == null) return head;
        ListNode node = head.next;
        // 确保当前结点的后面不会出现和它相同的结点
        while (node != null && head.value == node.value) {
            node = node.next;
        }
        head.next = removeDuplicated(node);
        return head;
    }

    // Remove Duplicates from Sorted Linked List
    // head = [1,2,3,3,4,4,5] -> [1,2,5] 只保留非重复的node结点
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.value != head.next.value) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            ListNode node = head.next;
            while (node != null && head.value == node.value) {
                node = node.next;
            }
            return deleteDuplicates(node);
        }
    }
}
