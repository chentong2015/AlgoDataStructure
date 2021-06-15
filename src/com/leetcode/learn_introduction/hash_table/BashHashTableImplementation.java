package com.leetcode.learn_introduction.hash_table;

// The Principle of Hash Table 哈希表的核心和实现原理
// 1. Use a hash function to map keys to buckets 核心原理是使用hash方法映射key到buckets
// 2. Insert key: "hash function" decide which bucket key should be assigned and key will be stored in that bucket
// 3. Search key: "same hash function" to find the corresponding bucket and search only in the specific bucket
//                只需要检索(map映射到)对应的bucket中的数据，因此时间复杂度为O(1)，避免遍历所有数据 !!

// Keys to Design a Hash Table 哈希表的设计原则 (How to implement a HashMap<> ?)                            ===> 测试没有标准答案 !!
// 1. Hash Function: depend on the range of key values and the number of buckets 理想情况是one-one mapping
// 2. Collision Resolution: 当无法做到一一对应的时候，则需要处理冲突
//    2.1 如何在bucket中存储相同的值 ?
//        2.1.1 如果key键值数目是Constant, 可使用array数组来作为bucket
//        2.1.2 如果key键值是变量或很大，则使用height-balanced binary search tree高度平衡的BST作为bucket
//    2.2 当一个bucket中值过多怎么办 ?
//    2.3 如何搜索某bucket中的指定值 ?
public class BashHashTableImplementation {
}
