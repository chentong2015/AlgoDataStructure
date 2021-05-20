package com.leetcode;

// TODO: 补充视频算法研究
public class AlgoDataStructure {

    // 对算法和数据结构的认识
    /***
     * 0. 特殊数据结构对应指定的算法
     * 1. 用最低的时间复杂度处理数据    ：避免一切不必要的多余的遍历, 部分遍历, 递归调用方法...
     * 2. 用最少的空间复杂度存储计算信息 : 不使用一个多余变量, 直接修改源数据结构
     */

    // 对算法和数据结构的误区
    /**
     * 1. 关于递归法的时间复杂度   ：O(n), O(2^n), O(n^n) ...都有可能
     * 2. 关于递归法的用法与场景   ：数列，二叉树的遍历，动态编程，回溯算法 ...
     * 3. "Dynamic Programming": 动态编程的核心在于保留每一步迭代信息
     */

    // 金典算法的使用
    // 1. 二分法查找：常用在搜索和动态编程
    //    Arrays.binarySearch(array, item)
    //    Collections.binarySearch(list, item)
    //    自定义使用left, right -> middle两个标识符

    // About Tree:
    // N-Ary Tree
    // Binary Tree
    // Binary Search Tree => Balanced & Unbalanced BTS有平衡之分
    // Complete Binary Tree 完全二叉树 / BTS / 堆(Heap)

    // 关于Tree的高级数据结构
    // 1. Segment Tree 片段树
    // 2. Binary Indexed Tree 二叉索引树
    // 3. Prefix Tree 前缀树(含有多个子节点的树数据结构)

    // 金典排序算法:
    // 1. 插入排序  O(n²)      O(1)       每读取一个值, 将它插入到前面有序的序列中
    // 2. 选择排序  O(n²)      O(1)       每次遍历找最大或者最小值, 放到开头或者末尾
    // 3. 冒泡排序  O(n²)      O(1)       两两比较交换, 依次交换
    // 4. 快速排序  O(nlog(n)) O(log(n))  Quick Sort, 设置pivot基准, 然后进行partition分区
    // 5. 归并排序  O(nlog(n)) O(n)       Divide and Conquer分治法, 将长度切分成n/2个子序列, 两两进行归并, 最后合并,缺点是空间复杂度高
    // 6. 用堆排序  O(nlog(n)) O(1)       Heap完全二叉树, 先构建堆, 然后依次将root节点和最后一个元素Rn交换, 剩下R1-Rn-1个无序区, 再找第二大的
    // 7. 计数排序  O(n+k)     O(n+k)     n个0到k之间整数, 当k不是很大并且序列比较集中时, 考虑使用计数排序, 先统计再依次输出
}
