package com.leetcode.top_interview_questions.easy_collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BaseArray {

    // Remove duplicates from sorted array
    // 1. The input array is passed in by reference
    // 2. It doesn't matter what you leave beyond the returned length
    // 2. Modifying the input array in-place with O(1) extra memory 空间复杂度 !!
    // 3. 时间复杂度上面O(n)
    // 4. For example: [0,0,1,1,1,2,2,3,3,4] -> [0,1,2,3,4]
    public int removeDuplicates(int[] nums) {
        // 测试理解：当出现重复的item时，不能将后面所有的items都往前面移动，复杂度不正确 !!

        // 正确理解：由于是排序排列的，因此只需要找到所有不同的item，从开始位置依次往后排即可
        //         从第二个位置开始，只在有不同的值出现的时候才交换一次位置 !!
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    // Best Time to Buy and Sell Stock II
    // An array prices where prices[i] is the price of a given stock on the ith day
    // Find the maximum profit you can achieve 使得卖出去的收益最大
    // You must sell the stock before you buy again 必须在指定的某天将股票卖了，然后再买
    // For example: prices = [7,1,5,3,6,4] -> (5-1) + (6-3) -> 7
    public int maxProfit(int[] prices) {
        // 测试理解：判断找到后面最大值的时候出手? 在这个过程中如何比较是否有更好的收益 ?
        //         如何判断在已经处于买或者卖的状态中 ?

        // 正确解法: 1. 穷举所有可能的transactions交易的可能，(递归法)提取出最大的值
        //          2. "峰谷法"：将所有的山峰和山谷之间的差值累加(数学公式)
        //          3. "Simple One Pass": 本质上收益的总值(极限值)是等于所有增长的部分的和
        int total = 0;
        boolean isBuy = false; // 根本不同考虑是否处于买入和卖出的状态
        if (prices.length == 0) return 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int baseProfit = 0;
            if (prices[i] < prices[i + 1]) {
                isBuy = true;
                baseProfit = prices[i + 1] - prices[i];
                // 嵌套一次迭代
            }
        }
        // 正确解法 3
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }

    // Rotate the array to the right by k steps, where k is non-negative.
    // Input: nums = [1,2,3,4,5,6,7], k = 3
    // Output: [5,6,7,1,2,3,4]
    public void rotateArray(int[] nums, int k) {
        // 测试理解：从后往前将顺序进行移位交换, 如何用最少的步骤实现交换 ?
        //         1. 将后面的子数组颠倒，然后拼接到原始数组的前面
        //         2. 循环k，一次处理一个，循环换值的位置        Time complexity: O(n×k), Space complexity: O(1)

        // 标准解法: 1. 使用额外的数组, 交换item后赋值回原来的数组 Time complexity: O(n), Space complexity: O(n)
        //          2. TODO 循环替换: 前后的会移动到后面，后面的超过数组的长度，求余数之后再移动到前面 ??
        //          3. Reverse完全颠倒数组，然后再颠倒部分 !! OK
        k = k % nums.length; // 求余：超过length的移动将回到原点
        for (int i = 0; i < nums.length; i++) {
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // Return true if any value appears at least twice in the array
    // nums = [1,2,3,1] -> true
    public boolean containsDuplicate(int[] nums) {
        // 测试理解: 乱序的数组，如何判断前面已经出现过的item值 ?
        //         1. 使用嵌套for循环 Time complexity: O(n2), Space complexity: O(1)
        //         2. 使用另外一个数组 Time complexity: O(n), Space complexity: O(n)

        // 正确解法: 1. 先对数组进行排序，然后之间判断相邻的两个值 Arrays.sort(nums) 排序复杂度O(nlog(n)) > O(n), Space complexity: O(1)
        //          2. Hash Tables使用Hash表 Time complexity: O(n), Space complexity: O(n)
        if (nums.length == 0) return false;
        Set<Integer> setNums = new HashSet<>(); // Set集中不包含相同的元素
        for (int i = 0; i < nums.length; i++) {
            // search() and insert() for n times and each operation takes constant time.
            if (setNums.contains(nums[i])) {
                return true;
            }
            setNums.add(nums[i]);
        }
        return false;
    }

    // Single Number
    // Non-empty array of integers nums, every element appears twice except for one. Find that single one.
    // 不能使用额外的内存空间，空间复杂度必须是O(1)
    // Input: nums = [4,1,2,1,2] -> 4  1 1 2 2 4
    public static int findSingleNumber(int[] nums) {
        // 测试理解: 乱序的数组，如何判断前面已经出现过的item值 ?
        //         1. 使用嵌套for循环 Time complexity: O(n2), Space complexity: O(1)

        // 正确解法: 1. 先对数组进行排序，然后之间判断相邻的两个值 Arrays.sort(nums) 排序复杂度O(nlog(n)) > O(n), Space complexity: O(1)
        //          2. 借助别的数组
        //          3. 借助HashSet<>, 通过求和来计算: 2∗(a+b+c)−(a+a+b+b+c)=c
        //          4. Bit Manipulation 比特位运算: a⊕b⊕a=(a⊕a)⊕b=0⊕b=b 将0和所有的值求XOR异或运算(相同的消去)，结果就是最中的单列的值 !!
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1]; // 如果前面都没有找出来，那么最后一个值就是单独的值
    }
}
