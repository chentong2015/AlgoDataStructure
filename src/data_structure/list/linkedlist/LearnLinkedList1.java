package data_structure.list.linkedlist;

import beans.ListNode;

public class LearnLinkedList1 {

    // Swap Nodes in Pairs
    // Swap every two adjacent nodes and return its head
    // Solve the problem without modifying the values in the list's nodes
    // head = [1,2,3,4] -> [2,1,4,3]
    //
    // 直接使用递归，每次递归都为它的调用函数返回Pair中的首节点
    // O(n) 递归调用造成栈空间的开销
    // O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        head.next = swapPairs(nextNode.next);
        nextNode.next = head;
        // 返回新的头部Node
        return nextNode;
    }

    // Remove Duplicates from Sorted Linked List
    // 保证升序排列的链表，其中的int类型的值可正可负
    // head = [1,2,3,3,4,4,5] -> [1,2,3,4,5]
    // O(n) 最多递归n-1次的造成的时间复杂度
    // O(1)
    public ListNode removeDuplicated(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        while (node != null && head.value == node.value) {
            node = node.next;
        }
        head.next = removeDuplicated(node);
        return head;
    }

    // TODO. 在链表头部添加dummy节点，方便后续的移动操作
    //  dummy -> head -> first -> second -> third -> ...
    // Remove Nth Node From End of List
    // Given the head of a linked list, remove the nth node from the end of the list and return its head.
    // head = [1,2,3,4,5], n = 2  ->  [1,2,3,5] 1位置就是head位置
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // TODO. 需要统计node节点数目才能确定正向移动的距离
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // 正确的位置是(L - n + 1)
        length = length - n;
        node = dummy;
        while (length > 0) {
            node = node.next;
            length--;
        }
        // 执行删除节点的操作，并返回正确的head头部节点
        node.next = node.next.next;
        return dummy.next;
    }

    // Reverse Linked List 两种算法：迭代法和递归法
    // Given the head of a singly linked list, reverse the list, and return the reversed list
    //        1 -> 3 -> 6 -> 7 -> 10
    // pre  current next
    public ListNode reverseLinkedListIteratively(ListNode head) {
        // 测试理解：1. 迭代遍历一遍，将原来的节点的连接的顺序颠倒, 注意设置原来head节点下一个节点为null
        //
        // 正确理解: 1. 需要使用"前中后"三个节点的指向，核心是将<当前指向的下一个节点改成指向它原来的前一个节点>
        // O(n) O(1)
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null) {
            next = current.next; // 保留后一个节点，以便于往后移动
            current.next = pre;

            pre = current;       // 使用pre每次记录最后出来的新head节点
            current = next;
        }
        // 由于跳出while循环的条件是current==null，必须使用临时节点来保留head节点位置 !!
        return pre;
    }

    // 正确理解: 2. 递归算法是"从后往前"开始做两个节点之间的执行的颠倒; 而不是从前往后换
    // A <- B <- C 在递归的过程中，newHead是原来的最后一个
    // O(n) O(n) 会造成n层的嵌套调用，造成额外的栈空间开销 !!
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
    // 1 -> 4 -> 8 -> 9
    // 2 -> 5 -> 20
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 测试理解：1. 同时遍历两个链表中的数据，交替添加值到新的链表中
        //            O(n+m)  O(n+m) 多出相同的内存空间给新的结果表
        // 正确理解: 1. 循环两个链表，将另外一个链表的值，合并到另一个链表
        //            O(n*m)  O(1) 时间度会增加，但空间复杂度比较小  ===> 可能造成循环链表 !!

        // dummy留住新链表的起使位置，作为创建出来的暂存节点
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;

        // 哪个链表的值小便移动哪个链表，知道遍历完其中一个链表
        while (l1 != null || l2 != null) {
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
