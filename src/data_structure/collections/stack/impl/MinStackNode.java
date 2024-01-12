package data_structure.collections.stack.impl;

public class MinStackNode {

    public int val;
    public int min;
    public MinStackNode next;

    // 构造器的正确使用方式：一个构造器只能调用一个this()或者是super() !!
    public MinStackNode(int val, int min) {
        this(val, min, null);
    }

    // 始终只使用一个完整的构造器来初始化成员属性 !! ==> 避免代码重复性
    public MinStackNode(int val, int min, MinStackNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
