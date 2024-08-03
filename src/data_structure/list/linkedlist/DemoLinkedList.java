package data_structure.list.linkedlist;

import beans.ListNode;

import java.util.LinkedList;

public class DemoLinkedList {

    private void testJavaLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1, 10);  // TODO: 插入在指定的index位置，后面的元素会因此移位
        list.add(10);                  // 默认追加到末尾的位置
        int item = list.get(1);        // 使用index位置来获取元素(和list操作一致)
        int lastElement = list.get(list.size() - 1);
        for (int num : list) {
            System.out.println(num);
        }
    }

    // Swap Nodes in Pairs
    // Swap every two adjacent nodes and return its head
    // Solve the problem without modifying the values in the list's nodes 不能通过修改值来交换
    // head = [1,2,3,4] -> [2,1,4,3]
    //
    // 直接使用递归，每次递归都为它的调用函数返回Pair中的首节点
    // O(n) 递归调用造成栈空间的开销 O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递归往后进行交换
        ListNode nextNode = head.next;
        head.next = swapPairs(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }

    // Remove Duplicates from Sorted Linked List
    // 保证升序排列的链表，其中的int类型的值可正可负
    // head = [1,2,3,3,4,4,5] -> [1,2,3,4,5]
    // O(n) 最多递归n-1次的造成的时间复杂度 O(1)
    public ListNode removeDuplicated(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        // 确保当前结点的后面不会出现和它相同的结点
        while (node != null && head.value == node.value) {
            node = node.next;
        }

        // 跳出while循环的条件，遍历到最后或者找到了新的node，递归执行同样的操作
        head.next = removeDuplicated(node);
        return head;
    }

    // Remove Duplicates from Sorted Linked List
    // head = [1,2,3,3,4,4,5] -> [1,2,5] 只保留非重复的node结点
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.value != head.next.value) {
            head.next = deleteDuplicates(head.next);
            return head;
        }

        // 如果一直相等，则遍历到一个新的结点返回(确保是非重复的结点)
        ListNode node = head.next;
        while (node != null && head.value == node.value) {
            node = node.next;
        }
        return deleteDuplicates(node);
    }
}
