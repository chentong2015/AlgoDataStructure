package com.leetcode.learn_introduction.binary_search;

import java.util.Arrays;

/**
 * 二分法搜索
 * 1. 二分区间，不需遍历所有值，时间复杂度为O(log(n))
 * 2. 适用于排序好的数据，如果是拼接好的排序数据，则对片段数据操作二分法 O(2log(n)) = O(log(n))
 * 2. 原生语言类库自带的搜索方法 Arrays.binarySearch(array, item) & Collections.binarySearch(list, item)
 */
public class LearnBinarySearch {

    // Search for a Range
    // Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
    // If target is not found in the array, return [-1, -1]
    // nums = [5,7,7,8,9,10], target = 8  -> [3,4]
    //         l          r
    //           m
    //         l r
    //         m
    // [2,2]
    // 0    2
    public int[] searchRange(int[] nums, int target) {
        int[] results = new int[2];
        Arrays.fill(results, -1);
        if (nums == null) return results;
        if (nums.length == 1 && nums[0] == target) {
            Arrays.fill(results, 0);
            return results;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                results[0] = middle;
                if (nums[middle - 1] == target) { //
                    results[1] = middle - 1;
                } else {
                    results[1] = middle + 1;
                }
                break;
            }
        }
        return results;
    }
}
