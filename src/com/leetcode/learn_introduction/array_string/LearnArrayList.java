package com.leetcode.learn_introduction.array_string;

/**
 * List
 * 1. 动态的数组，长度根据存储数据变化 ArrayList
 * 2. 动态列表转换成指定长度的数组    return list.toArray(new String[array.size()]);
 */
public class LearnArrayList {

    // Find Pivot Index
    // Given an array of integers nums, calculate the pivot index of this array
    // 数组的pivot左边和右边数的和相等，第一位置左边和最后一个位置的右边和都是0
    // Return the leftmost pivot index. If no such index exists, return -1
    // nums = [1,7,3,6,5,6] -> pivot = 3
    // nums = [2,1,-1]      -> pivot = 0
    public int pivotIndex(int[] nums) {
        // 测试理解：1. 一次遍历不行，不借助别的数据结构也不行 !!

        // 正确理解：1. 使用两次读取，借助另外一个copy出来的数组，分前后两次读取，判断是否该位置的左右和相等 !!
        //            因为要输出最小的index，所以先从右到左边计算，然后从左到右 
        //            O(2n)=O(n) O(n)
        int[] temp = new int[nums.length];
        int sum = 0;
        for (int index = nums.length - 1; index >= 0; index--) {
            temp[index] = sum;
            sum += nums[index];
        }
        sum = 0;
        for (int index = 0; index < nums.length; index++) {
            if (sum == temp[index]) return index;
            sum += nums[index];
        }
        return -1;
    }

    // TODO: 左边总值 + 中间值 + 右边总值 == 数组和的总值 !!
    // 正确理解: 2. 使用两遍循环，先计算出总值，右边的总和通过总值来计算 !! 去掉辅助的数组所开辟的空间复杂度
    //            O(n) O(1)
    public int pivotIndex2(int[] nums) {
        int sum = 0;
        int leftsum = 0;
        for (int x : nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }

    // Largest Number At Least Twice of Others
    // An integer array nums where the largest integer is unique
    // Whether the largest element in the array is at least twice as much as every other number in the array
    // If it is, return the index of the largest element, or return -1 otherwise
    // nums = [3,6,1,0] -> index = 1 判断最大的值，是否是其他任意值的两倍以上
    // nums = [1]       -> index = 0 1看做比其他的值(没有值)都大2倍以上
    public int dominantIndex(int[] nums) {
        // 测试理解：1. 使用2次遍历，第一次将最大值和他的index找出来，第二次判断是否都在两倍以上(除去index的其他位置)
        //            O(n) O(1)

        return -1;
    }
}
