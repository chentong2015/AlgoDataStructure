package com.leetcode.learn_introduction.array_string;

import java.util.Arrays;

/**
 * String字符串问题的解法
 * 1. 使用2个标识符来定位，减少数据的遍历次数
 * 2. 两个标识符的移动速度可能不同，使用不同的策略 !!
 */
public class LearnString2 {

    // TODO: 判断什么情况下适合给数组排序，选择牺牲掉一定的复杂度 
    // Array Partition I
    // Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn)
    // The sum of min(ai, bi) for all i is maximized, Return the maximized sum
    // nums = [1,4,3,2] -> 4 备用数组的数据必须是双数，可以实现完全分组
    // (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
    // (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
    // (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4  ===> 所有分组可能中的最小值
    public int arrayPairSum(int[] nums) {
        // 测试理解: 1. 两两分组的可能性有多少，每一种可能性出来的最小值的和是否有特点? 一个值要么被选上，要么不被计算
        //            (a1, b1), (a2, b2), ..., (an, bn) 最优解来源于，每次都能留住最大值，bn最大，an次大，a1最小 ==> 结果最大
        //          2. 将数组排序，然后取所有奇数位置上面的和，就是最后的结果  O(nlog(n)) O(1)
        Arrays.sort(nums);
        int sum = 0;
        for (int index = 0; index < nums.length; index += 2) {
            sum += nums[index];
        }
        return sum;
    }

    // TODO: 有排序的数组，考虑使用双坐标，没有排序的数组考虑使用Hash Table暂存数据 !!
    // Two Sum II - Input array is sorted
    // Given an array of integers numbers that is already sorted in non-decreasing order
    // Find two numbers such that they add up to a specific target number
    // Return the indices of the two numbers (1-indexed) as an integer array answer of size 2
    // numbers = [2,7,11,15], target = 9 -> [1,2]
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return null;
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
