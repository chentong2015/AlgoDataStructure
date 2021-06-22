package com.leetcode.learn_introduction.hash_table;

// 衡量Hash表优劣的两个维度
// 1. 性能高，接近O(1)的查询复杂度
// 2. 良好的分布性，出现冲突的可能性少(在添加key->value键值对的时候)
public class BaseHashTableImplementation {

    // The Principle of Hash Table 哈希表的核心和实现原理
    // 1. Use a hash function to map keys to buckets 核心原理是使用hash方法映射key到buckets
    // 2. Insert key: "hash function" decide which bucket key should be assigned and key will be stored in that bucket
    // 3. Search key: "same hash function" to find the corresponding bucket and search only in the specific bucket
    //                只需要检索(map映射到)对应的bucket中的数据，因此时间复杂度为O(1)，避免遍历所有数据 !!

    // Keys to Design a Hash Table 哈希表的设计原则 (How to implement a HashMap<> ?)
    // 1. Hash Function: depend on the range of key values and the number of buckets 理想情况是one-one mapping
    // 2. Collision Resolution: 当无法做到一一对应的时候，则需要处理冲突
    //    2.1 如何在bucket中存储相同的值 ?
    //        2.1.1 如果key键值数目是Constant, 使用array数组来作为bucket
    //        2.1.2 如果key键值数目是变量或很大, 使用height-balanced BST作为bucket
    //    2.2 当一个bucket中值过多怎么办 ?
    //    2.3 如何搜索某bucket中的指定值 ?
    //        2.3.1 根据其中使用的数据结构确定搜索算法

    // Hash Table key的基本设计原则：在原始的信息(一组信息)和hash table key之间构建映射关系
    // 1. All values belong to the same group will be mapped in the same group
    // 2. Values which needed to be separated into different groups will not be mapped into the same group
    //    Function可能多值映射到同一个bucket
    // 3. 设计实战
    //    sorted string/array as the key            排序字符串之后作为key       (x0,x3,x1,x2) -> (x0,x1,x2,x3) 考虑字符的位置和频率
    //    use offset of the first value             通过偏移量来构建key         (x0,x1,x2,x3) -> (x0,x1-x0,x2-x0,x3-x0)
    //    use the TreeNode as key sometimes         直接使用树的结点作为key
    //    use serialization of the subtree          使用子树的序列化值           3(2(4,1),6)
    //    use row or column index as key            数组中使用index坐标位
    //    combine row and column index              结合行列坐标确定位置block    (i,j) -> (i/3)*3 + j/3 确定数独9*9中3*3的区域
    //    aggregate values in same diagonal line    级联所有对角线的index        (i,j) -> (i+j) )斜对角  (i,j) -> (i-j)反斜对角
}
