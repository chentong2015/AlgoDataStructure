package com.leetcode.learn_introduction.base_sorting;

import com.leetcode.learn_introduction.base_sorting.model.ValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Count of Smaller Numbers After Self
// he counts array has the property where counts[i] is the number of smaller elements to the right of nums[i]
// nums = [5,2,6,1] -> [2,1,1,0] 统计每个位置的后面右多少的数字是比当前这个数字小的
// O(nlog(n) O(n) 由于归并排序造成的复杂度
public class MergeSortCountSmaller {

    // The smaller numbers on the right of a number are exactly those that jump from its right to its left during a stable sort.
    // So I do mergesort with added tracking of those right-to-left jumps.
    // 1. 统计对于每一数字，它右边的数字移动到它左边的数目，在merge sort的过程中记录
    // 2. 对于排序(后)的每一个数字，需要直到它的原始index的位置，以便在指定的位置上记录统计数目 !! ==> 如果数字不重复，则可以使用HashMap<>

    private int[] count;
    private int[] indexes;

    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int length = nums.length;
        count = new int[length];
        indexes = new int[length];
        for (int i = 0; i < length; i++) indexes[i] = i;
        mergesort(nums, 0, length - 1);
        for (int i = 0; i < count.length; i++) res.add(count[i]);
        return res;
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
                count[indexes[leftIndex]] += countRightToLeft; // 找到数字的原始位置，进行统计
                leftIndex++;
            }
            sortIndex++;
        }
        while (leftIndex <= mid) {
            newIndexes[sortIndex++] = indexes[leftIndex++];
            count[indexes[leftIndex]] += countRightToLeft;
        }
        while (rightIndex <= end) {
            newIndexes[sortIndex++] = indexes[rightIndex++];
        }
        for (int i = start; i <= end; i++) {       // 将排序好的数字，设置到indexes[i]原始标识数组
            indexes[i] = newIndexes[i - start];
        }
    }

    // TODO: Merge Sort的优化算法
    // https://leetcode.com/explore/interview/card/top-interview-questions-hard/118/trees-and-graphs/851/discuss/76584/Mergesort-solution
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        ValuePair[] arr = new ValuePair[nums.length];
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0);
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new ValuePair(i, nums[i]);
        }
        mergeSort(arr, smaller);
        res.addAll(Arrays.asList(smaller));
        return res;
    }

    private ValuePair[] mergeSort(ValuePair[] arr, Integer[] smaller) {
        if (arr.length <= 1) return arr;
        int pivot = arr.length / 2;
        ValuePair[] left = mergeSort(Arrays.copyOfRange(arr, 0, pivot), smaller);
        ValuePair[] right = mergeSort(Arrays.copyOfRange(arr, pivot, arr.length), smaller);
        for (int i = 0, j = 0; i < left.length || j < right.length; ) {
            if (j < right.length && i < left.length && left[i].val > right[j].val) {
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
