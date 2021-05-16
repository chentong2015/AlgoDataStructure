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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Sort List 自定义排序一个链表
    // Given the head of a linked list, return the list after sorting it in ascending order
    // head = [-1,5,3,4,0] -> [-1,0,3,4,5]
    public ListNode sortList(ListNode head) {
        // 正确理解：1. Top-Bottom 自顶向下算法：将长的链表切分成小的一到两个链表进行处理，最后再合成(递归回来执行merge)的每个小结果 !!
        //         2. TODO: Bottom-Up 自底向上算法, 从底往上开始恢复
        //           O(nlog(n)) O(1)
        if (head == null || head.next == null) { // 至少有两个节点，都在没有排序的必要
            return head;
        }
        ListNode midNode = getMidNode(head);
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(midNode);
        return merge(leftHead, rightHead);
    }

    // 将原始问题简化成合成两个有序链表的问题, 这里的算法将进行比较排序 !!
    // leftHead : -1 -> 5
    // rightHead:  0 -> 3 -> 4
    // node: -1 -> 0 -> 3 -> 4 -> 5
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;               // tail是合成出来的整个链条的最尾部的节点
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {     // 如果list1的值更小，则将小节点添加到总合成链表的最后，反之添加list2的节点 !!
                tail.next = list1;               // list1和list2各自的节点标识往后移动
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        tail.next = (list1 != null) ? list1 : list2; // 如果因为while循环导致其中一个链表读到最后，则将另一个链表剩余的部分接上 !!
        return dummyHead.next;
    }

    // -1 -> 5 -> 3 -> 4      => midNode = 5
    // -1 -> 5 -> 3 -> 4 -> 0 => midNode = 3
    private ListNode getMidNode(ListNode head) {
        ListNode midNode = null;
        while (head != null && head.next != null) {
            midNode = (midNode == null) ? head : midNode.next; // 中间Node每移动一步，head往前移动两步，最终取到中间的位置
            head = head.next.next;
        }
        ListNode middle = midNode.next; // 在midNode将整个链表切分成两段，后面一段的head就是midNode的下一个节点
        middle.next = null;             // 截断前面一个子链表
        return middle;
    }
}
