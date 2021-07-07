package com.leetcode.basic_theroy_introduction.array;

import java.util.Arrays;

// TODO: 数组统一相似问题的研究, "bit位运算" & "求和解法"
// 1. 找数组中唯一出现一次的数字
// 2. 找(连续)数组中唯一缺失的数字
// 3. 找(连续)数组中唯一重复的数字
public class LearnArray4 {

    // Single Number
    // Non-empty array of integers nums, every element appears twice except for one. Find that single one.
    // Input: nums = [4,1,2,1,2] -> 4  1 1 2 2 4
    public static int findSingleNumber(int[] nums) {
        // 测试理解: 1. 先对数组进行排序, 然后之间判断相邻的两个值 Arrays.sort(nums) O(nlog(n)), O(1)
        //         2. 借助HashSet<>, 通过求和来计算: 2∗(a+b+c)−(a+a+b+b+c)=c   O(n), O(n)
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1]; // 如果前面都没有找出来，那么最后一个值就是单独的值
    }

    // 正确解法: 1. Bit Manipulation 比特位运算    O(n) O(1)
    //            a⊕b⊕a=(a⊕a)⊕b=0⊕b=b 将0和所有的值求XOR异或运算(相同的消去)，结果为出现一次的那个值
    //            0^x=x 和0运算之后会保持原来的值不变
    public static int findSingleNumber2(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }

    // Missing Number
    // Given an array nums containing n distinct numbers in the range [0, n], all the numbers of nums are unique
    // Return the only number in the range that is missing from the array
    // nums = [3,0,1] -> 2
    // nums = [0,1]   -> 2 从0到2的数的闭合区间范围 !!
    public int missingNumber(int[] nums) {
        // 测试理解：1. 理论上，用[0, n]的总值减去nums的总值，差值就是缺失的那个值，sumNums的最大有效值是n，不会溢出
        //            O(n) O(1)
        int sumNums = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sumNums += i - nums[i];  // 在累计index位置值的同时，减去改位置上的值
        }
        return sumNums;
    }

    // 正确理解：1. Gauss Formula 将上述的计算抽象出来，直接使用length计算总和sum，然后减去数组中值的总和
    //         2. Bit Manipulation 使用位运算，XOR异或计算之后，最后剩下的是出现一次的那个(空缺的那个值) !!
    //            missing =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4) = 2 除了数字2意外，其他的数字都会最后"两两相消"
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    // Find the Duplicate Number 改版
    // n(n>=1)个值在[1,n+1]数组中，数字乱序，连续分布，其中有且仅有一个值重复
    public int findDuplicate(int[] nums) {
        // 测试理解：1. 通过数组中值的总和和位置坐标的总和，比较计算出重复值(在什么位置)
        //            [1,3,4,2,2+3] => 12    值重复的位置 + 到最大时的间距 = n+1
        //             1 2 3 4 5    => 15    5-2 = 15-12
        int sum = 0;
        int length = nums.length;
        int total = ((1 + length) * length) / 2;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
        }
        return length - (total - sum);
    }

    // Find the Duplicate Number
    // Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive
    // 有且仅有一个重复值，可以重复多次
    public int findDuplicate2(int[] nums) {
        // 正确理解：1. 联想到1-n数字和数组的下标index有关，通过下标转换造成Linked List
        //            > 为什么会有Cycle出现 ? 通过f(x)=nums[x],x=nums[x]构建的Linked List链表可以看到重复的值是循环入口点
        //            > 约束条件：数组中值的范围必须是[1,n],不能是[0,n-1],否则第一个位置如果是0,则算法无法往后查找
        //            O(n) O(1)
        int tortoise = nums[0];        // 根据位置的值确定下一个移动的位置坐标
        int hare = nums[0];
        do {
            hare = nums[nums[hare]];
            tortoise = nums[tortoise]; // 一个超前一倍前进，当两者相遇说明(跑前面的已经在循环中将后者套圈了，此时为第一次相遇的点)
        } while (tortoise != hare);    // 要相遇必然出现圈，并且hare必须在圈内

        tortoise = nums[0];            // 将其中一个退回到起使点，然后一步一步开始
        while (tortoise != hare) {     // 直到两者第一次遇到，则为循环圈的入口点，重复位置的开始点 !!
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return hare;
    }
}
