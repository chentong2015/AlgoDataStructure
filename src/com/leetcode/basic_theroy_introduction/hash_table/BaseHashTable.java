package com.leetcode.basic_theroy_introduction.hash_table;

import java.util.*;

/**
 * Hash Table 使用Hash方法快速的插入和检索: 使用Standard Template Libraries
 * Data structure which organizes data using "hash functions" in order to support quick insertion and search
 */
// HashSet<>          对Set接口的实现，不包含重复的值: 不保证元素的迭代顺序，不保证顺序不变 !!
//   LinkedHashSet<>  确定迭代的顺序，必须是插入元素的顺序
//   TreeSet<>        对元素进行自然排序，或者提供Comparator进行对象的自定义比较

// HashMap<,>         对Map接口的实现，包含<key, value>键值对: 不保证元素的迭代顺序，不保证顺序不变 !!
//   LinkedHashMap<,> 确定迭代的顺序，必须是插入元素的顺序
//   TreeMap<>        对元素进行自然排序，或者提供Comparator
public class BaseHashTable {

    // 可以直接将List列表构造成HashSet<T>
    public void testHashSet(List<String> words) {
        Set<String> set = new HashSet<>(words);
    }

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
        if (hashmap.isEmpty()) {
            System.out.println("hash map is empty now!");
        }
        hashmap.clear();
    }

    // 通过TreeMap的排序之后，找到ceiling天花板元素或者floor地板元素
    private void testTreeMap() {
        NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();
        intervalMap.put(1, 2);
        intervalMap.put(2, 5);
        intervalMap.put(5, 10);
        Map.Entry<Integer, Integer> ceilingEntry = intervalMap.ceilingEntry(2);
        Map.Entry<Integer, Integer> floorEntry = intervalMap.floorEntry(2);
    }
}
