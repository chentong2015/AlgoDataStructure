package data_structure.stack.impl;

import java.util.ArrayList;
import java.util.List;

// Stack 栈的实现
// 1. 只需要一个动态的数组来存储，而无需考虑位置空间的浪费
// 2. 编程语言几乎都提供Stack栈实现，最常用API: push(), pop(), peek()
public class StackImplementation {

    private List<Integer> data;

    public StackImplementation() {
        data = new ArrayList<>();
    }

    public void push(int x) {
        data.add(x);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 拿到要出栈的那个元素(最后入栈的那个元素)
    public int top() {
        int lastIndex = data.size() - 1;
        return data.get(lastIndex);
    }

    // 将要出栈的那个的元素移除
    public boolean pop() {
        if (isEmpty()) return false;
        data.remove(data.size() - 1);
        return true;
    }

    // 这里取最小值的复杂度过高，需要优化设计 > Min Stack
    public int getMin() {
        int min = Integer.MIN_VALUE;
        for (int value : data) {
            min = Math.min(min, value);
        }
        return min;
    }
}
