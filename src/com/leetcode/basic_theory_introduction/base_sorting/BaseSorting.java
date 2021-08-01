package com.leetcode.basic_theory_introduction.base_sorting;

// 金典排序算法(超过20种)
public class BaseSorting {

    // 比较排序
    // 1. 插入排序  O(n²)      O(1)       每读取一个值, 将它插入到前面有序的序列中
    // 2. 选择排序  O(n²)      O(1)       每次遍历找最大或者最小值, 放到开头或者末尾
    // 3. 冒泡排序  O(n²)      O(1)       两两比较交换, 依次交换

    // 4. 归并排序  O(nlog(n)) O(n)       Divide and Conquer分治法, 将长度切分成n/2个子序列, 两两进行归并, 最后合并
    // 5. 快速排序  O(nlog(n)) O(log(n))  Quick Sort, 设置pivot基准, 然后进行partition分区
    // 6. 用堆排序  O(nlog(n)) O(1)       Heap完全二叉树, 先构建堆然后将root节点和最后一个元素Rn交换, 剩下R1-Rn-1个无序区, 再找第二大的

    // 线性时间排序
    // 7. 计数排序  O(n+k)     O(n+k)     n个0到k之间整数, 当k不是很大并且序列比较集中时, 考虑使用计数排序, 先统计再依次输出

    // 拓扑排序: 图
}
