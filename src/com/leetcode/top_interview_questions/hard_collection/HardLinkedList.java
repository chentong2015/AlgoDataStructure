package com.leetcode.top_interview_questions.hard_collection;

import com.leetcode.top_interview_questions.base.ListNode;

public class HardLinkedList {

    // Merge k Sorted Lists
    // An array of k linked-lists lists, each linked-list is sorted in ascending order
    // Merge all the linked-lists into one sorted linked-list and return it
    // lists = [[1,4,5],[1,3,4],[2,6],[7]] -> [1,1,2,3,4,4,5,6,7] 每一个子链表都是严格递增
    public ListNode mergeKLists(ListNode[] lists) {
        // 测试理解：1. 依次读取所有的链表节点，找到每一个节点的位置，然后对应插入
        //            O(N²) 每添加一个节点都会在前面找节点的添加位置 O(N)

        // 正确理解：1. 将所有的节点统一添加O(N), 然后排序O(nlog(n)), 然后根据顺序创建新的链表O(N)+O(N)空间复杂度
        //         2. 将所有的子链表两两合并，依次合并到最后 O(nlog(k)) < O(n*k)
        ListNode dummy = new ListNode(0);
        for (ListNode root : lists) {
            ListNode node = root;
            while (node != null) {
                dummy.next = insertNewSortNode(dummy.next, new ListNode(node.value)); // 将Node取值断开添加，否则会造成循环 !!
                node = node.next;
            }
        }
        return dummy.next;
    }

    private ListNode insertNewSortNode(ListNode root, ListNode addNode) {
        if (root == null) return addNode; // 针对第一次插入节点
        ListNode pre = root;
        ListNode after = pre;
        while (pre != null) {
            if (pre.value > addNode.value) {
                if (after == pre) {
                    addNode.next = pre;   // 插入在原来的root节点的前面
                    root = addNode;
                } else {
                    after.next = addNode; // 在添加的时候,如果after==pre,则会造成cycle循环链表 !!
                    addNode.next = pre;
                }
                break;
            }
            after = pre;
            pre = pre.next;
        }
        if (pre == null) after.next = addNode;// 如果移动到最后，则直接将新的节点添加到最后
        return root;
    }

    // Sort List 自定义排序一个链表
    // Given the head of a linked list, return the list after sorting it in ascending order
    // 目标：O(nlog(n)) O(1)
    // head = [-1,5,3,4,0] -> [-1,0,3,4,5]
    public ListNode sortList(ListNode head) {
        // 测试理解：1. 
        

        return null;
    }
}
