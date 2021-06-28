package com.leetcode.learn_introduction.base_binary_search;

import java.util.Arrays;

/**
 * 二分法搜索：3种使用模板
 * 1. 二分区间，不需遍历所有值，时间复杂度为O(log(n))
 * 2. 适用于排序好的数据，如果是拼接好的排序数据，则对片段数据操作二分法 O(2log(n)) = O(log(n))
 * 3. 原生数组二分搜索     Arrays.binarySearch(array, item)
 * 4. 原生List集合二分搜索 Collections.binarySearch(list, item)
 */
public class LearnBinarySearch1 {

    // TODO: Binary Search + Sliding Window 两种算法的结合
    // Find K-th Smallest Pair Distance
    // The distance of a pair of integers a and b is defined as the absolute difference between a and b
    // Given an integer array nums and an integer k
    // Return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length
    // nums = [1,3,1], k = 1 -> {0,2,2} -> 0 第k个最小的距离值
    // 1 3 5 6 9 10
    // 0 - 9        在0和最大距离之间使用二分法，
    //   4
    public int smallestDistancePair(int[] nums, int k) {
        // O(nlog(n) + nlog(m)) n为数组的长度，m为最大的差值距离
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countDistanceLessThanMid(nums, mid);
            if (count >= k) high = mid; // 根据k的大小，移动二分法的区间
            else low = mid + 1;
        }
        return low; // End condition: low = high;
    }

    // 通过Sliding Window来查找pairs
    // number of pairs with distance <= mid 统计所有小于等于mid值的pairs对
    private int countDistanceLessThanMid(int[] nums, int mid) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > mid) {
                left++;
            }
            count += right - left;
        }
        return count;
    }
}
