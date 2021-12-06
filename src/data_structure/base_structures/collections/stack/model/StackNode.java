package data_structure.base_structures.collections.stack.model;

public class StackNode {
    public int val;
    public int min;
    public StackNode next;

    // 构造器的正确使用方式：一个构造器只能调用一个this()或者是super() !!
    public StackNode(int val, int min) {
        this(val, min, null);
    }

    // 始终只使用一个完整的构造器来初始化成员属性 !! ==> 避免代码重复性
    public StackNode(int val, int min, StackNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
