package com.leetcode.learn_introduction.base_binary_search;

import java.util.Arrays;

/**
 * 二分法搜索：不需遍历所有值，时间复杂度为O(log(n)) ==> Arrays.binarySearch(array, item) / Collections.binarySearch(list, item)
 * 1. 根据不同同情况使用三种不同模板 Templates
 * 2. 适用于排序好的数据，如果是拼接好的排序数据，则对片段数据操作二分法 O(2log(n)) = O(log(n))
 * 3. TODO: 二分法不止作用在元素本身上面，类作用在总值，距离差值上面 !!
 */
public class LearnBinarySearch1 {

    // TODO: Binary Search + Sliding Window 在0和最大距离之间使用二分法
    // Find K-th Smallest Pair Distance
    // The distance of a pair of integers a and b is defined as the absolute difference between a and b
    // Given an integer array nums and an integer k
    // Return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length
    // nums = [1,3,1], k = 1 -> {0,2,2} -> 0 第k个最小的距离值
    // 1 3 5 6 9 10
    // 0 - 9
    //   4
    public int smallestDistancePair(int[] nums, int k) {
        // O(nlog(n) + nlog(m)) n为数组的长度，m为最大的差值距离
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countDistanceLessThanMid(nums, mid);
            if (count >= k) {
                high = mid; // 根据k的大小，移动二分法的区间
            } else {
                low = mid + 1;
            }
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

    /////////////////////////////////////////////////////////////////////////////////

    // TODO: Binary Search 在最大值和总值之间使用二分法
    // Split Array Largest Sum
    // Given an array nums which consists of non-negative integers and an integer m
    // Split the array into m non-empty "continuous sub-arrays" 切分出来的数字必须是连续且非空的
    // Minimize the largest sum among these m sub-arrays        最小化所有子数组中的和
    // nums = [7,2,5,10,8], m = 2 -> [7,2,5] and [10,8] -> 18   共4种分法：1+4, 2+3, 3+2(最小的一种分法), 4+1
    // nums = [1,2,3,4,5],  m = 2 -> [1,2,3] and [4,5]  -> 9
    // nums = [1,4,4],      m = 3 -> [1] [4] and [4]    -> 4
    public int splitArray(int[] nums, int m) {
        // 数组的长度和m分成的子数组的个数，共同决定一共有多少分法
        int max = 0;
        int sum = 0;   // sum的总和有可能超过int的最大值，使用长整型 !!
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (m == 1) return sum;

        int low = max;
        int high = sum;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (valid(mid, nums, m)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low; // End condition: low > high
    }

    // 1 4 4, 3
    // 4 - 9
    //   6 = target
    // 4 5
    // 4 4
    // 4 3
    // low > high
    public boolean valid(int target, int[] nums, int m) {
        int count = 1;
        int total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = num;
                count++;
                if (count > m) return false;
            }
        }
        return true;
    }
}
