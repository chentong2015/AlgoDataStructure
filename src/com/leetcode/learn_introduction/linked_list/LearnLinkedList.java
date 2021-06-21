package com.leetcode.learn_introduction.linked_list;

import com.leetcode.top_interview_questions.base.ListNode;

/**
 * TODO: List可以直接根据index的位置来增删数据，list.add(index, element); list.remove(index);
 * 1. 链表特点: 数据查询比较慢, 增删中间节点的数据很快 !!
 * 2. 链表类型: 单向链表, 双向链表, 循环链表(Cycle Linked List)
 * 3. 常见技术:
 * >  迭代法 & 递归法
 * >  在链表前面添加DummyHead作为依托
 * >  充分利用节点值做判断，按顺序插入节点
 * >  操作和遍历链表的时候，直接判断Node是否为null
 * >  Collections.swap(list, i, j) 交换list中指定两个位置的值
 */
public class LearnLinkedList {

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
