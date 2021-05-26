package com.leetcode.top_interview_questions.easy_collection;

import com.leetcode.top_interview_questions.base.ListNode;

public class BaseLinkedList {

    // Remove Nth Node From End of List
    // Given the head of a linked list, remove the nth node from the end of the list and return its head.
    // head = [1,2,3,4,5], n = 2  ->  [1,2,3,5] 1位置就是head位置
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 正确理解: 1. 可以在head前面添加一个dummy的节点，用来方便操作head
        //            dummy -> head -> first -> second -> third -> ...
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int length = 0;
        ListNode node = head;
        while (node != null) { //通过遍历统计节点的数目
            length++;
            node = node.next;
        }

        length = length - n;   // 这个是要删除节点的前一个位置, 正确的位置是(L - n + 1)
        node = dummy;
        while (length > 0) {   // 从前往后移动，当length=0就移动到了要删除节点的前一个位置 !!
            length--;
            node = node.next;
        }
        node.next = node.next.next; // 执行删除的操作，释放掉指定位置的节点
        return dummy.next;   // 最后返回dummy节点的后一个，作为正式的结果
    }

    // Reverse Linked List 两种算法：迭代法和递归法
    // Given the head of a singly linked list, reverse the list, and return the reversed list
    public ListNode reverseLinkedListIteratively(ListNode head) {
        // 测试理解：1. 迭代遍历一遍，将原来的节点的连接的顺序颠倒, 注意设置原来head节点下一个节点为null

        // 正确理解: 1. 需要使用"前中后"三个节点的指向，核心是将<当前指向的下一个节点改成指向它原来的前一个节点>
        //            O(n) O(1)
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next; // 保留后一个节点，以便于往后移动
            current.next = pre;
            pre = current;       // 使用pre每次记录最后出来的新head节点
            current = next;
        }
        return pre; // 因为current节点为null
    }

    // 正确理解: 2. 递归算法是"从后往前"开始做两个节点之间的执行的颠倒; 而不是从前往后换
    //             A <- B <- C 在递归的过程中，newHead是原来的最后一个
    //             O(n) O(n) 会造成n层的嵌套调用，造成额外的栈空间开销 !!
    public ListNode reverseLinkedListRecursively(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode newHead = reverseLinkedListRecursively(head.next);
            head.next.next = head; // 反向: 递归方法结束之后，往后执行 !!
            head.next = null;
            return newHead;
        }
    }

    // Merge Two Linked List
    // Merge two sorted linked lists and return it as a sorted list 返回排序好的组合链表
    // Both l1 and l2 are sorted in non-decreasing order 注意原始的链表是否排序 !!
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 测试理解：1. 同时遍历两个链表中的数据，交替添加值到新的链表中
        //            O(n+m)  O(n+m) 多出相同的内存空间给新的结果表

        // 正确理解: 1. 循环两个链表，将另外一个链表的值，合并到另一个链表
        //            O(n*m)  O(1) 时间度会增加，但空间复杂度比较小  ===> 可能造成循环链表 !!
        ListNode dummy = new ListNode(0); // 留住新链表的起使位置 !!
        ListNode node = dummy;
        while (l1 != null || l2 != null) { // 只要还有值，就继续往后读取
            if (l1 == null) {
                node.next = l2;
                break;
            } else if (l2 == null) {
                node.next = l1;
                break;
            } else {
                if (l1.value < l2.value) {
                    node.next = l1;
                    node = node.next;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    node = node.next;
                    l2 = l2.next;
                }
            }
        }
        return dummy.next;
    }

    public ListNode insertNewNode(ListNode head, ListNode addNode) {
        if (head == null) {
            return addNode;
        }
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            if (addNode.value > current.value) {
                pre = current;
                current = current.next;
            } else {
                if (pre == null) {      // 说明是插在head上面
                    addNode.next = head;
                    return addNode;
                } else {
                    pre.next = addNode; // 在插入的位置，修改指定的前后节点 !!
                    addNode.next = current;
                    break;
                }
            }
        }
        return head;
    }

    // Linked List Cycle
    // Given the head of a linked list, determine if the linked list has a cycle in it
    // pos is -1 or a valid index in the linked-list 使用pos表示链表尾部节点的下一个节点指向的位置(0-indexed)!!
    // head = [3,2,0,-4], pos = 1 -> true
    public boolean hasCycle(ListNode head) { // pos并不是参数
        // 正确理解: 1. "龟兔赛跑原理"，如果链表中出现循环，则必然跑得快的，会追上跑得慢的，完成套圈 !!
        //            O(n) O(1)
        ListNode node = head;
        while (head != null && head.next != null) { // 判断前面有node可以遍历
            node = node.next;
            head = head.next.next;
            if (head != null && head.equals(node)) {
                return true;
            }
        }
        return false;
    }
}
