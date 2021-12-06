package data_structure.base_structures.collections.stack;

import java.util.ArrayList;
import java.util.List;

// Stack 栈的实现
// 1. 只需要一个动态的数组来存储，而无需考虑位置空间的浪费
// 2. 理论上大部分语言都是提供了Stack栈的实现，只需要使用两个最常用的方法 push(), pop(), peek()
public class BaseStackImplementation {

    private List<Integer> data;

    public BaseStackImplementation() {
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
        return data.get(data.size() - 1);
    }

    // 将要出栈的那个的元素移除
    public boolean pop() {
        if (isEmpty()) return false;
        data.remove(data.size() - 1);
        return true;
    }

    // 这里取最小值的复杂度过高，需要优化设计
    public int getMin() {
        int min = Integer.MIN_VALUE;
        for (int value : data) {
            min = Math.min(min, value);
        }
        return min;
    }
}
