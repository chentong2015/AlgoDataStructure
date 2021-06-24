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
    // 1  5  3  2  8  7  6  4  将大问题拆分成单元的小问题, 逐步解决小问题
    //  15  23    78   46      使用同样的逻辑，直到最终问题解决
    //    1235      4678
    //       12345678
    public int[] merge_sort(int[] input) {
        if (input.length <= 1) return input;
        int pivot = input.length / 2;
        int[] left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot));
        int[] right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.length));
        return merge(left_list, right_list);  // 最后回退到第一层的调用，则merge左右两个半的子数组
    }

    // 标准解法：合并两个排序的数组，使用两个index位置游标 + 结果表的index(ret_cursor)
    //         当一个游标结束后，直接添加另一个剩下的结果
    public int[] merge(int[] left_list, int[] right_list) {
        int left_cursor = 0;
        int right_cursor = 0;
        int ret_cursor = 0;
        int[] ret = new int[left_list.length + right_list.length];
        while (left_cursor < left_list.length && right_cursor < right_list.length) {
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }
        // 补充完最后一部分的值，排序好的值
        while (left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }
        while (right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }
}
