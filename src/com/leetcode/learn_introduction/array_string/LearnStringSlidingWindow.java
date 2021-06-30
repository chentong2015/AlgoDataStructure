package com.leetcode.learn_introduction.array_string;

// TODO: Sliding window technique 关于数组和字符串的滑动窗口算法
// 1. [i, j] 闭合的区间，用于框选一段数据的范围
// 2. 所框选的数据必须是连续的，不能间段
public class LearnStringSlidingWindow {

    // Minimum Size Subarray Sum
    // Given an array of positive integers nums and a positive integer target
    // Return the minimal length of a "contiguous subarray" [nums l, nums l+1, ..., nums r-1, nums r]
    // The sum is greater than or equal to target. If there is no such subarray, return 0
    // nums = [2,3,1,2,4,3], target = 7  -> [4,3] -> 2
    public int minSubArrayLen(int[] nums, int target) {
        // O(2n)=O(n) 最终可能left和right从左到右各自移动一遍 O(1)
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {                    // 从头开始减少，通过[j-i]来缩小区间
                min = Math.min(min, right - left + 1); // 保留移动过程中，有效最小窗口的宽度，注意长度 +1
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // Longest Substring
    // Given a string s, find the length of the longest substring without repeating characters
    // s consists of English letters, digits, symbols and spaces.
    // s = "abcabcbb" -> "abc" -> 3
    // int[128] for ASCII (该码值为8个bit位置), int[256] for Extended ASCII
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int res = 0;
        int[] chars = new int[128];
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;
            while (chars[r] > 1) {       // 一步一步的移动，直到windows窗口的区间中，没有重复的char字符 !!
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    // TODO: 滑动窗口算法的优化版本, left坐标位置不需要逐步移动，而是一步跳跃式的移动到指定位置
    // "abcdeafbdgcbb"
    // a: 6    字符始终记录出现的最后一个位置的index，每次更新位置     chars[r] = right;
    // b: 12   当发现字符出现，取出它的位置，并从它的下一个位置开始截断  left = index + 1;
    // c: 11   同时每一步更新结果                                  res = Math.max(,)
    // d: 9
    // ...
    public int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int right = 0;
        int res = 0;
        Integer[] chars = new Integer[128]; // char -> int 根据ASCII码值表对应十进制的值，作为数组的下标
        while (right < s.length()) {
            char r = s.charAt(right);
            Integer index = chars[r];       // 使用引用类型来做null空的判断，避免之间判断index的范围 !!
            if (index != null && left <= index && index < right) {
                left = index + 1;
            }
            res = Math.max(res, right - left + 1); // 需要在移动过程中记录结果
            chars[r] = right;
            right++;
        }
        return res;
    }
}
