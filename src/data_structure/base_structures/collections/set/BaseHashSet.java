package data_structure.base_structures.collections.set;

// Set<E>接口的实现:
// HashSet<E>       不保证迭代的顺序，顺序不固定，存储元素不重复
// LinkedHashSet<E> 迭代顺序和插入映射中顺序一致
// SortedSet<E>     严格排序
// TreeSet<E>       通过Tree实现对key的自然排序
public class BaseHashSet {

    // TODO: HashSet<E>背后的基本实现: 使用HashMap的key来实现(不重复)
    // private transient HashMap<E,Object> map;
    //
    // // HashMap value值存储的Dummy对象
    // // Dummy value to associate with an Object in the backing Map
    // private static final Object PRESENT = new Object();
    //
    // // 通过HashMap的key来判断是否存在 ==> 判断元素是否存在，只需O(1)的时间复杂度
    // public boolean contains(Object o) {
    //    return map.containsKey(o);
    // }
}
