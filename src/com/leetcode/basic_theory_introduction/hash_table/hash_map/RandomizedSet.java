package com.leetcode.basic_theory_introduction.hash_table.hash_map;

import java.util.*;

// 实现一个能够随机取出一个元素的HashSet类型 
// 确保插入，删除和随机取一个值都是以O(1)的时间复杂度来完成
// 1. List列表 用于动态存储数据，根据index快速的查找删除，根据随机产生的一个整数index，返回随机元素
// 2. HashMap 用于存储值和index的对应关系，快速定位到值的index位置
//            在插入和移除的时候，需要快速判断值是否存在，需要将value作为HashMap的key，做快速判断
public class RandomizedSet {

    private List<Integer> values = new ArrayList<>();
    private Map<Integer, Integer> map = new HashMap<>();

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        values.add(val);
        map.put(val, values.size() - 1); // list的下标是从0开始的
        return true;
    }

    // 确保下标index的连续性，以便根据index做随机的提取
    // 1 6 9  -> map 1 6 9
    // 0 1 2         0 2 3 -> 减少value所对应的index的修改 !!
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        int index = map.get(val);
        int lastIndex = values.size() - 1;
        int lastValue = values.get(lastIndex);
        Collections.swap(values, index, lastIndex); // 将要删除的元素，放置到最后位置，改变index
        values.remove(lastIndex);  // 将移除到最后要删除的值进行删除
        map.put(lastValue, index); // 改变移动的前面的值所对应的index位置
        map.remove(val);
        return true;
    }

    // 确保每个位置都能够被均匀的访问到
    // 1. Each element must have the same probability of being returned
    // 2. There will be at least one element in the data structure when getRandom is called
    public int getRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(map.size()); // 产生一个随机数字0<=?<map.size()左闭右开的区间 !!
        return values.get(randomIndex); // 直接通过index下标取值
    }
}
