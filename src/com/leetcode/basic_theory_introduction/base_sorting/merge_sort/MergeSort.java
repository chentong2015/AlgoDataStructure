package com.leetcode.basic_theory_introduction.base_sorting.merge_sort;

import java.util.Arrays;

// Merge Sort(Divide and Conquer) 归并排序, 有时是最佳解
// O(nlog(n)) 时间复杂度看成是log(n)层高度 * 每一层merge的时间复杂度O(n)
// O(n)       空间复杂度来自于每一层排序时的sublist, 最大情况时O(n)的临时存储空间
public class MergeSort {

    // Top-Down 自顶向下(通过递归来实现)
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

    // TODO: 合并两个排序的数组"标准解法"，两个index位置游标 + 结果表的index(ret_cursor)
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

    // Count of Smaller Numbers After Self
    // Counts array has the property where counts[i] is the number of smaller elements to the right of nums[i]
    // nums = [5,2,6,1] -> counts = [2,1,1,0] 统计每个位置的后面有多少的数字是比当前这个数字小的
    // O(nlog(n)) O(n) 由于归并排序造成的复杂度

    // The smaller numbers on the right of a number are exactly those that jump from its right to its left during a stable sort
    // So I do merge sort with added tracking of those right-to-left jumps
    // 1. 统计对于每一数字，它右边的数字移动到它左边的数目，在merge sort的过程中记录
    // 2. 对于排序(后)的每一个数字，需要找到它原始index位置，以便在指定的位置上记录统计数目，如果数字不重复，则可以使用HashMap<>
    private int[] counts;
    private int[] indexes;

    public int[] countSmaller1(int[] nums) {
        int length = nums.length;
        counts = new int[length];
        indexes = new int[length];
        for (int i = 0; i < length; i++) indexes[i] = i;
        mergesort(nums, 0, length - 1);
        return counts;
    }

    private void mergesort(int[] nums, int start, int end) {
        if (end <= start) return;
        int mid = (start + end) / 2;
        mergesort(nums, start, mid);
        mergesort(nums, mid + 1, end);
        merge(nums, start, end);
    }

    private void merge(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        int leftIndex = start;
        int rightIndex = mid + 1;
        int countRightToLeft = 0;

        int sortIndex = 0;
        int[] newIndexes = new int[end - start + 1]; // 排序后存储的是index的值
        while (leftIndex <= mid && rightIndex <= end) {
            if (nums[indexes[rightIndex]] < nums[indexes[leftIndex]]) {
                newIndexes[sortIndex] = indexes[rightIndex++];
                countRightToLeft++;                 // 右边有一个，则会累加一个移动的数量
            } else {
                newIndexes[sortIndex] = indexes[leftIndex];
                counts[indexes[leftIndex]] += countRightToLeft; // 找到数字的原始位置，进行统计
                leftIndex++;
            }
            sortIndex++;
        }
        while (leftIndex <= mid) {
            newIndexes[sortIndex++] = indexes[leftIndex++];
            counts[indexes[leftIndex]] += countRightToLeft;
        }
        while (rightIndex <= end) {
            newIndexes[sortIndex++] = indexes[rightIndex++];
        }
        for (int i = start; i <= end; i++) {       // 将排序好的数字，设置到indexes[i]原始标识数组
            indexes[i] = newIndexes[i - start];
        }
    }

    // 使用自定义类型Pair，记录index-> value的对应关系
    public int[] countSmaller2(int[] nums) {
        NumPair[] arr = new NumPair[nums.length];
        int[] smaller = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new NumPair(i, nums[i]);
        }
        mergeSort(arr, smaller);
        return smaller;
    }

    private NumPair[] mergeSort(NumPair[] arr, int[] smaller) {
        if (arr.length <= 1) return arr;
        int pivot = arr.length / 2;
        NumPair[] left = mergeSort(Arrays.copyOfRange(arr, 0, pivot), smaller);
        NumPair[] right = mergeSort(Arrays.copyOfRange(arr, pivot, arr.length), smaller);
        for (int i = 0, j = 0; i < left.length || j < right.length; ) {
            if (i < left.length && j < right.length && left[i].val > right[j].val) {
                arr[i + j] = left[i];
                smaller[left[i].index] += j;
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        return arr;
    }
}
