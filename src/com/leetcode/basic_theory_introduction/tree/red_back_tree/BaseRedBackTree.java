package com.leetcode.basic_theory_introduction.tree.red_back_tree;

// 红黑树使用场景：ConcurrentHashMap, TreeMap
// 逻辑约束：
//   1. 根结点一定是黑色，其余结点红色或者黑色
//   2. 红结点的两个子结点都是黑色
//   3. 新插入的结点默认是红色
//   ...
// 平衡措施：
//   1. 变色
//   2. 自旋
public class BaseRedBackTree {
}
