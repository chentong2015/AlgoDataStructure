package data_structure.collections.linkedlist;

import beans.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LearnLinkedList2 {

    // Add Two Numbers
    // Two non-empty linked lists representing two non-negative integers
    // The digits are stored in reverse order, each node contains a single digit
    // Add the two numbers and return the sum as a linked list.
    // l1 = [2,4,3], l2 = [5,6,4] -> 342 + 465 = 807 -> [7,0,8] 链表的开头不为0, 其中的数字倒叙排列
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 测试理解：1. 从地位到高位依次累加对应位置上的值，如果超过10，则1会带到下一位的运算中
        //            O(max(N, M))  O(max(N, M)) 复杂度由最长的那个链表决定
        ListNode head = new ListNode();
        ListNode node = head;

        int offsetNum = 0; // 需要一个变量来存储偏移量，方便进位
        int sum = 0;
        while (l1 != null || l2 != null) { // 同时循环两个链表的方法: while非空循环
            if (l1 == null) {
                sum = l2.value + offsetNum;
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.value + offsetNum;
                l1 = l1.next;
            } else {
                sum = l1.value + l2.value + offsetNum;
                l1 = l1.next;
                l2 = l2.next;
            }
            offsetNum = sum / 10;

            node.next = new ListNode(sum % 10);
            node = node.next;
        }
        if (offsetNum == 1) {
            node.next = new ListNode(1);
        }
        return head.next;
    }

    // Odd Even Linked List
    // Given the head of a singly linked list, group all the nodes with odd indices together followed
    //   by the nodes with even indices, and return the reordered list
    // The first node is considered odd, and the second node is even
    // Could you solve it in O(nodes) time complexity and O(1) space complexity !!
    // head = [1,2,3,4,5] -> [1,3,5,2,4] 将一个链表的奇数和偶数前后分离，然后组成新的链表，空间复杂度必须是O(1)
    public ListNode oddEvenList(ListNode head) {
        // 测试理解：1. 遍历链表，将偶数位置的node提取到新的链表，删除偶数位置的node，将提出来的新链表接到原来的末尾 ==> O(n) O(n) > O(1)
        //         2. 先遍历找到链表的结尾，直接在源链表末尾依次追加偶数位置的节点，不使用额外的存储空间 ==> O(n) 不需要两次遍历链表 O(1)

        // 正确理解：1. 把偶数位置的节点排到后面，就是把奇数位置排到前面，每遇到一个奇数位置就往前提取 O(n) O(1)
        boolean isOdd = true;
        ListNode newHead = head;
        ListNode lastOddNode = newHead; // 奇数链表的最后一个节点
        ListNode beforeNode = head;     // 记录head的前一个节点，用来删除head节点
        ListNode tempNode;
        while (head != null) {
            if (isOdd && head != lastOddNode) {
                beforeNode.next = head.next;

                tempNode = lastOddNode.next;  // Add head node to the end of Odd Linked List
                lastOddNode.next = head;
                head.next = tempNode;
                lastOddNode = head;           // Move the lastOddNode to the last node 标记奇数链表的最后一个节点位置

                head = beforeNode.next;       // 重新回到原来的的节点顺序上面
            } else {
                beforeNode = head;
                head = head.next;
            }
            isOdd = !isOdd;
        }
        return newHead;
    }

    // 正确理解：1. 将原来的链表拆成奇偶两个片段, 最后再组合
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;  // 每次奇数位置后面接的是偶数后面的一个, 从even偶数下一个去取
            odd = odd.next;        // odd是奇数链表的末尾
            even.next = odd.next;  // 每次偶数位置的下一个是奇数后面一个, 从odd奇数下一个去取
            even = even.next;      // even是偶数链表的末尾
        }
        odd.next = evenHead;
        return head;
    }

    // Intersection of Two Linked Lists
    // Return nodes at which two lists intersect. If the two linked lists have no intersection, return null
    // There are no cycles anywhere in the entire linked structure 假设没有循环结构
    // Linked lists must retain their original structure after the function returns 原始链表不能够被破坏
    // The number of nodes of listA is in the m, listB is n
    // intersectVal == listA[skipA + 1] == listB[skipB + 1] if listA and listB intersect
    // 复杂度要求：O(n) O(1) memory
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 测试理解：1. 使用Set存储读取到的node, 在读取另外一个LinkedList时, 判断前面是否读过
        //            O(N+M) O(M) 两个链表均遍历一遍，并且只存其中一个链表
        Set<ListNode> nodesInB = new HashSet<>();
        while (headB != null) {
            nodesInB.add(headB);
            headB = headB.next;
        }
        while (headA != null) {
            if (nodesInB.contains(headA)) { // 共用同一个node对象，不是比较两个node包含的值相等 !!
                return headA;
            }
            headA = headA.next;
        }
        return null;
    }

    // 正确理解：1. Two Pointers 从比较的角度出发，如果出现公用节点，一定在右侧靠结尾的地方
    //            此时从"同一位置"往后判断到最后，则是用最少的步数找到第一个共用的节点
    //            为了找到"同一位置", 可以先将双链表拉升一遍，从头排
    //                5i 1 6 8 9
    //            3 2 5j 1 6 8 9
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        // 第一遍会将两个链表拉平，目的是让两个链表往右侧对齐，比较短的链表靠右，因为出现intersection一定在右端: 如果两个链表长度相同则只会遍历一遍
        while (pA != pB) {   // 当两个标识符从同样的位置出发，两个同时到末尾结束null，则会退出while循环
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }
}
