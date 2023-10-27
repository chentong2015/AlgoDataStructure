package data_structure.base_data_structures.collections.stack.impl;

// Min Stack实现:
// 借助链表的数据结构来实现具有特殊功能的Stack, 实时更新和保留stack中特殊数据的信息
// (value, min, next) -> (value, min, next) -> (value, min, next) ....
// 由于需要快速的返回stack中的最小值，所以在每个(head) node上都保存min的信息，方便及时读取 !!
public class MinStackImplementation {

    // 只需要一个指向链表的头标识, 从头部开始删除节点，和Stack先入后出的顺序一致
    private MinStackNode head;

    public void push(int x) {
        if (head == null) {
            head = new MinStackNode(x, x);
        } else {
            head = new MinStackNode(x, Math.min(x, head.min), head);
        }
    }

    public int top() {
        return head.val;
    }

    public void pop() {
        head = head.next;
    }

    public int getMin() {
        return head.min;
    }
}
