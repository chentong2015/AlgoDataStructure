package com.leetcode.basic_theory_introduction.hash_table.hash_map;

// HashMap JDK 1.8 在1.7版上面进行了优化
public class BaseHashMapDataStructure {

    // key -> Node[] table 数组(Node<k,v>一个结点) / bucket桶位，槽位
    //     -> Entry<key,value> Entry类型的对象
    //                     -> Node -> Node ... 冲突的时候，需要挂链表  O(n)
    //                     -> 当链表长度大于8时,自动转成红黑树,优化查询   O(log(n))

    // 1. 数组初始容量(默认bucket 16数量)，最大容量，负载因子(存储的数据大于75%时，则需要扩容)
    // 2. 链表转红黑树的边界，红黑树转回链表边界
    // 3. 哈希桶数组 Node<K,V>[] table
    // 4. 实际存储元素数目 size

    // Node结点数据类型的实现
    // static class Node<K, V> implements Map.Entry<K, V> {
    //     final int hash; // 用来定位数组索引位置
    //     final K key;
    //     V value;
    //     HashMap.Node<K, V> next; // 指向下一个结点Node：挂载的链表或者是红黑树
    //     ....
    // }

    // 1. 使用哈希表存储：HashMap使用哈希表(hash table散列表)来存储数据，通过要key查找要找的值
    // 2. Java原始类型HashMap的哈希函数是如何实现的 ?
    //    key -> hash_function(key) -> bucket / Node<,>数组
    //                              -> 均匀存储/均匀分布，减少hash冲突
    static final int hash(Object key) { // 重新计算hash值
        int h;
        // h = key.hashCode() 取hashCode值
        // h ^ (h >>> 16)     异或, 无符号右移16位(高位全部补0), 高位参与运算
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    // 计算数组槽位(n-1) & hash确定在那个bucket桶中 !! 默认的槽位数n=16
    // h            = 1111 1111 1111 1111 1111 0000 1110 1010
    // h>>>16       = 0000 0000 0000 0000 1111 1111 1111 1111
    // h^(h>>>16)   = 1111 1111 1111 1111 0000 1111 0001 0101 ==> 让高16位参与运算，增大随机性，减少hash冲突
    // (n-1)        = 0000 0000 0000 0000 0000 0000 0000 1111 ==> 二进制表示15，低位置上都为1
    // (n-1) & hash = 0000 0000 0000 0000 0000 0000 0000 0101 ==> 5存储在第5个槽位上

    // 槽位数必须是2^n ? 为了使哈希后的结果更加的均匀
    //                 想要通过&算出index的必要条件是，约束数组的长度必须是2^n值
    // 如果是2^n, 则保证与运算的时候，保证低位上面都有1参与运算         ==> 相当于求15的模值
    // (n-1)        = 0000 0000 0000 0000 0000 0000 0001 0000 ==> 如果n=17，则和hash求与运算之后，落到的槽位不是0就是16  !! 不均匀

    // TODO: h&length 比 h%length 更有效率
    // 优化策略：任意数 % 2^n  ==> 任意数 ^ (2^n - 1)

    // 3. HashMap put方法流程
    //    if ((p = tab[i = (n - 1) & hash]) == null)    具体在Node数组中的槽位位置
    //       tab[i] = newNode(hash, key, value, null);  如果指定槽位没有值，则直接插入node  ==> TODO: 多线程场景下会出现并发的(判断)问题
    //    else
    //       if ((k = p.key) == key || (key != null && key.equals(k))) 如果有相同的key，则直接覆盖
    //       else if (p instanceof TreeNode)
    //            如果已经是红黑树，则在树中添加node结点，如果长度小于6，则转下面的链表 !!
    //       else
    //            挂链表的情况(遍历链表，一般插尾部)，如果长度大于8，转上面的红黑树 !!
    //    if (++size > threshold)       超过指定的长度，需要扩容size
    //       resize();

    // HashMap中equals()和hashCode()有什么作用?
    // HashMap添加，获取时需要通过key的hashCode()来进行hash运算，然后计算下标((n-1) & hash)从而获得要找的桶的位置
    // 当发生冲突(碰撞时)，使用"key.equals(k)"在链表或者红黑树中查找对应的结点

    // HashMap线程安全吗?
    // HashMap读写效率高，但是因为其时非同步的，既读写等操作都时没有锁保护的，在多线程场景下是不安全的，容易出现数据不一致的问题
}
