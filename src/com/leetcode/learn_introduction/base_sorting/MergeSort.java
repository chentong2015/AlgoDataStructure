package com.leetcode.learn_introduction.base_sorting;

import java.util.Arrays;

// 典型的Dynamic Programming
// Merge Sort(Divide and Conquer)归并排序, 有时是最佳解
// O(nlog(n)) 时间复杂度看成是log(n)层高度 * 每一层merge的时间复杂度O(n)
// O(n)       空间复杂度来自于每一层排序时的sublist, 最大情况时O(n)的临时存储空间
public class MergeSort {

    // Top-Down 自顶向下
    // 1  5  3  2  8  7  6  4
    //   1532       8764
    //  15  32    87   64
    // 1  5  3  2  8  7  6  4  将大问题拆分成单元的小问题, 逐步解决小问题, 直到最终问题解决
    //  15  23    78   46
    //    1235      4678
    //       12345678
    public int[] mergeSort(int[] input) {
        if (input.length <= 1) return input;
        int pivot = input.length / 2;
        int[] leftList = mergeSort(Arrays.copyOfRange(input, 0, pivot));
        int[] rightList = mergeSort(Arrays.copyOfRange(input, pivot, input.length));
        return merge(leftList, rightList);  // 最后回退到第一层的调用，则merge左右两个半的子数组
    }

    // 标准解法：合并两个排序的数组，使用两个index位置游标 + 结果表的index(ret_cursor)
    //         当一个游标结束后，直接添加另一个剩下的结果
    public int[] merge(int[] leftList, int[] rightList) {
        int leftCursor = 0;
        int rightCursor = 0;
        int retCursor = 0;
        int[] ret = new int[leftList.length + rightList.length];
        while (leftCursor < leftList.length && rightCursor < rightList.length) {
            if (leftList[leftCursor] < rightList[rightCursor]) {
                ret[retCursor++] = leftList[leftCursor++];
            } else {
                ret[retCursor++] = rightList[rightCursor++];
            }
        }
        // 补充完最后一部分的值，排序好的值
        while (leftCursor < leftList.length) {
            ret[retCursor++] = leftList[leftCursor++];
        }
        while (rightCursor < rightList.length) {
            ret[retCursor++] = rightList[rightCursor++];
        }
        return ret;
    }
}
