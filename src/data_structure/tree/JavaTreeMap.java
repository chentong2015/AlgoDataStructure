package data_structure.tree;

import java.util.SortedMap;

// TreeMap<K,V>: 底层使用红黑树来实现, 使用key来进行结点的比较
// 使用TreeMap来排序和根据区间来快速找寻(利用红黑树的查询效率)范围值
public class JavaTreeMap {

    private void testTreeMap() {
        java.util.TreeMap<Integer, Integer> treeMap = new java.util.TreeMap();
        treeMap.put(1, 1);
        treeMap.put(2, 1);
        treeMap.put(3, 1);
        treeMap.put(6, 1);
        treeMap.put(9, 1);
        // 返回树的"第一个结点"的key值(排序后最小的结点的值)
        int firstValue = treeMap.firstKey();

        // TODO: 返回大于等于某key值的子树结构(排序好序的map)
        SortedMap<Integer, Integer> sortedMap = treeMap.tailMap(4);
        int firstSortedValue = sortedMap.firstKey();
    }
}
