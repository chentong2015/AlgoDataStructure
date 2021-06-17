package com.leetcode.learn_introduction.hash_table;

import java.util.HashMap;
import java.util.Map;

public class BaseHashMap {

    // TODO: Hash Table类型 new HashMap<>() + put(,) + get() + .entrySet()
    public void testHashMap() {
        Map<Integer, Integer> hashmap = new HashMap<>();
        hashmap.putIfAbsent(0, 0);
        hashmap.putIfAbsent(2, 3);

        hashmap.put(1, 1);
        hashmap.put(1, 2); // 更新已经存在的值
        System.out.println("The value of key 1 is: " + hashmap.get(1));

        hashmap.remove(2);
        // 直接判断键这值是否存在
        boolean foundKay = hashmap.containsKey(2);
        boolean foundValue = hashmap.containsValue(3);

        // 遍历所有的key的集合 hashmap.keySet()
        // 遍历所有的value集合 hashmap.values()
        // 使用Map.Entry<>来遍历每一对的键值.entrySet()
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + ") ");
        }

        System.out.println("The size : " + hashmap.size());
        hashmap.clear();
        if (hashmap.isEmpty()) {
            System.out.println("hash map is empty now!");
        }
    }
}
