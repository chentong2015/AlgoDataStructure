package com.leetcode.learn_introduction.hash_map_master;

public class HashMapDataStructure {

    // HashMap JDK 1.8
    // key -> Node[] table 数组(Node<k,v>一个结点) / bucket桶位，槽位
    //                     -> Node -> Node ... 冲突的时候，需要挂链表  O(n)
    //                     -> 当链表长度大于8时,自动转成红黑树,优化查询   O(log(n))
    // 1. 数组初始容量(默认16数量)，最大容量，负载因子(存储的数据大于75%时，则需要扩容)
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

    // 1. 使用哈希表存储
    // HashMap使用哈希表(hash table散列表)来存储数据，通过要key查找要找的值

    // 2. Java原始类型HashMap的哈希函数是如何实现的 ?
    // key -> hash_function(key) -> bucket / Node<,>数组
    //                           -> 均匀存储/均匀分布，减少hash冲突
    // 重新计算hash值
    static final int hash(Object key) {
        int h;
        // h = key.hashCode() 取hashCode值
        // h ^ (h >>> 16)     异或，无符号右移16位(高位全部补0) !! 高位参与运算
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    // 槽位数必须是2^n ?
    // 计算数组槽位 (n-1) & hash 确定在那个bucket桶中 !!
    // h            = 1111 1111 1111 1111 1111 0000 1110 1010
    // h>>>16       = 0000 0000 0000 0000 1111 1111 1111 1111
    // h^(h>>>16)   = 1111 1111 1111 1111 0000 1111 0001 0101 ==> 让高16位参与运算，增大随机性，减少hash冲突
    // (n-1) & hash = 0000 0000 0000 0000 0000 0000 0000 0101 ==> 5 存储在第5个槽位上
}
