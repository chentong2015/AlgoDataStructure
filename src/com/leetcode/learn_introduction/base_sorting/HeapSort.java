package com.leetcode.learn_introduction.base_sorting;

// Heap Sort 堆排序，不是最佳解
// 平均时间复杂度	O(nlog(n))
// 最坏时间复杂度	O(nlog(n))
// 最优时间复杂度	O(nlog(n))
// 空间复杂度	    O(n) total, O(1) auxiliary
public class HeapSort {

    /*
     * 第一步：将数组堆化
     * beginIndex = 第一个非叶子节点
     * 从第一个非叶子节点开始即可, 无需从最后一个叶子节点开始。
     * 叶子节点可以看作已符合堆要求的节点, 根节点就是它自己且自己以下值为最大
     *
     * 第二步：对堆化数据排序
     * 每次都是移出最顶层的根节点A[0], 与最尾部节点位置调换, 同时遍历长度 - 1
     * 然后从新整理被换到根节点的末尾元素, 使其符合堆的特性。
     * 直至未排序的堆长度为0
     */
    private int[] arr = new int[100];

    public void sort() {
        int len = arr.length - 1;
        int beginIndex = (arr.length >> 1) - 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len);
        }
        for (int i = len; i > 0; i--) {
            swap(0, i);
            maxHeapify(0, i - 1);
        }
    }

    /*
     * 调整索引为 index 处的数据，使其符合堆的特性。
     * index 需要堆化处理的数据的索引
     * len   未排序的堆（数组）的长度
     */
    private void maxHeapify(int index, int len) {
        int li = (index << 1) + 1; // 左子节点索引
        int ri = li + 1;           // 右子节点索引
        int cMax = li;             // 子节点值最大索引，默认左子节点。
        if (li > len) return;      // 左子节点索引超出计算范围，直接返回。
        if (ri <= len && arr[ri] > arr[li]) // 先判断左右子节点，哪个较大。
            cMax = ri;
        if (arr[cMax] > arr[index]) {
            swap(cMax, index);      // 如果父节点被子节点调换，
            maxHeapify(cMax, len);  // 则需要继续判断换下后的父节点是否符合堆的特性。
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


