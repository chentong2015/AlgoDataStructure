package data_structure.collections.set;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

// Set<E>接口的实现: 存储元素不重复
// - HashSet<E>       不保证迭代的顺序，顺序不固定: 没有第一个和最后一个元素的概念
// - LinkedHashSet<E> 迭代顺序和插入映射中顺序一致
// - TreeSet<E>       排序SortedSet，通过Tree实现对key的自然排序
public class BaseHashSet {

    // TODO: HashSet<E>背后的基本实现:
    //  使用HashMap的key来实现(不重复), 只需要O(1)的时间复杂度即可判断元素是否存在
    //
    //  private transient HashMap<E,Object> map;
    //
    //  HashMap value值存储的Dummy对象
    //  Dummy value to associate with an Object in the backing Map
    //  private static final Object PRESENT = new Object();
    //
    //  public boolean contains(Object o) {
    //     return map.containsKey(o);
    //  }
    public static void main(String[] args) {
        Set<String> values = new HashSet<>();
        values.add("item1");
        values.add("item2");
        values.add("item3");
        values.add("item3"); // 重复的元素将不会被添加到set集合中
        for (String value : values) {
            System.out.println(value);
        }
    }
}
