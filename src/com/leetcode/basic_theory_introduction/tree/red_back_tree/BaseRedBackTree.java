package com.leetcode.basic_theory_introduction.tree.red_back_tree;

// 插入和查询复杂度: log(n)
// 红黑树使用场景：
//   TreeMap，TreeSet
//   JDK1.8 Hashmap + ConcurrentHashMap
//   Linux 内核中完全公平调度
//   高精度计时器
//   ext3文件系统
public class BaseRedBackTree {

    // 逻辑约束：黑结点平衡
    // 1. 根结点一定是黑色，其余结点红色或者黑色
    // 2. 红结点的两个子结点都是黑色
    // 3. 新插入的结点默认是红色
    // 4. 叶子结点(null node)是黑色的
    // ...

    // 平衡措施：变色 & 自旋

    // 红黑树的生成
    // https://www.bilibili.com/video/BV13q4y1n7oC?p=43

    // 红黑树的插入

    // 红黑树的查询

    // 红黑树的删除 ==> 代码和场景复杂
}