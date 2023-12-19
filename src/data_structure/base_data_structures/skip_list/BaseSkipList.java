package data_structure.base_data_structures.skip_list;

import java.util.concurrent.ConcurrentSkipListSet;

// 把有序链表修改成支持"二分查找"的结构，以内存空间换时间
// 每个位置的结点具有向下和向右的指针
// 1         ->           25           ->          100 索引层(冗余元素)
// 1 ->      12    ->     25        -> 50    ->    100
// 1 -> 6 -> 12  -> 18 -> 25  -> 32 -> 50 -> 80 -> 100 最底层包含所有的元素
public class BaseSkipList {

    // 1. 搜索算法: 从上往下，从左往右
    // 2. 插入算法:
    //    随机确定元素要占据的层数K，将元素在指定的位置上添加K层
    //    如果K大于链表的层数，则要添加新的层
    // 3. 删除算法: 在各个层中找到包含x的节点，使用标准"链表删除结点"的方法
    // 4. 跳表的高度分析: 跳表的高度等于这n次随机数K中的最大值
    public void test() {
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
        // 源码会调用 ConcurrentSkipListMap.putIfAbsent(K key, V value)
        set.add(10);
    }
}
