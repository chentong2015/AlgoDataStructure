package com.leetcode.learn_introduction.hash_table;

import java.util.*;

// 实现一个能够随机取出一个元素的Hash Set类型
// 确保插入，删除和随机取一个值都是以O(1)的时间复杂度来完成
public class BaseRandomizedSet {

    // 思考过程: 充分利用其他数据结构的特点来实现
    // 1. 什么样的数据结构能够满足值的快速插入和删除  ==> List链表，动态数组  ==> 数据的查找速度慢
    // 2. 什么样的数据结构能够满足快速判断值是否存在  ==> Hash Table        ==> 数据的查找速度快

    private List<Integer> values;
    private Map<Integer, Integer> map;

    public BaseRandomizedSet() {
        values = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        values.add(val);
        map.put(val, values.size() - 1); // list的下标是从0开始的
        return true;
    }

    // 确保下标index的连续性，以便根据index做随机的提取
    // 1 6 9  -> map 1 6 9
    // 0 1 2         0 2 3 -> 减少value所对应的index的修改 !!
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        int lastIndex = values.size() - 1;
        int lastValue = values.get(lastIndex);
        Collections.swap(values, index, lastIndex); // 将要删除的元素，放置到最后位置，改变index
        map.put(lastValue, index);                  // map中对应的index也要改变, 用于下次快速查找val的index
        map.remove(val);
        values.remove(lastIndex);
        return true;
    }

    // 确保每个位置都能够被均匀的访问到
    // 1. Each element must have the same probability of being returned
    // 2. There will be at least one element in the data structure when getRandom is called
    public int getRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(map.size()); // 产生一个随机数字 1 <= ? < map.size() 左闭右开的区间 !!
        return values.get(randomIndex);
    }
}
