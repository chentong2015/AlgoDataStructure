package data_structure.map.hash_map;

import java.util.*;

// TreeMap<K,V>: 底层使用红黑树来实现, 使用key来进行结点的比较
// 使用TreeMap来排序和根据区间来快速找寻(利用红黑树的查询效率)范围值
public class TreeMapDemo {

    // 在创建TreeMap<>()时传递自定义的类型比较器
    // private static Comparator<DemoClass>  createFieldComparator() {
    //     return (a, b) -> a.getValue() < b.getValue() ? -1 : 1;
    // }

    private void testTreeMap() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1, 1);
        treeMap.put(3, 1);
        treeMap.put(9, 1);
        // 返回树的"第一个结点"的key值(排序后最小的结点的值)
        int firstValue = treeMap.firstKey();

        // TODO: 返回大于等于某key值的子树结构(排序好序的map)
        SortedMap<Integer, Integer> sortedMap = treeMap.tailMap(4);
        int firstSortedValue = sortedMap.firstKey();
    }
}
