package com.leetcode.learn_all_problems.p1_array;

import java.util.ArrayList;
import java.util.List;

public class QuestionArray01 {

    // TODO: 利用数组值的区间来找坐标index位置，利用数组存储空间完成值的交换 !!
    // Find All Duplicates in an Array
    // All the integers of nums are in the range [1, n] and each integer appears once or twice
    // Return an array of all the integers that appears twice
    // 1 <= nums.length <= 10^5
    // 1 <= nums[i] <= nums.length                 1. 数组中元素的值的范围不超过数组的长度范围
    // Each element in nums appears once or twice  1. 只可能出现过一次或者两次
    // nums = [4,3,2,7,8,2,3,1] -> [2,3]
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            int currentNum = nums[index];
            int findIndex = currentNum - 1;
            if (currentNum == 0 || currentNum == index + 1) // 如果该位置是0，或者值已经在正确的位置上面了
                continue;
            if (currentNum != nums[findIndex]) {
                swap(nums, index, findIndex);
            } else {
                result.add(currentNum); // 说明要找的那个位置(要交换的位置)的值出现重复了
                nums[index] = 0;
            }
            index--; // 无论是交换还是提取重复的值，都需要退回一步再做判断
        }
        return result;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    // TODO: 根据数据的特点，value<->index通过位置关联，设置数组中的值，记录特征点，不需要交换两者的值
    // [4,3,2,7,8,2,3,1]
    // [4,3,2,-7,8,2,3,1]  标记负数表示能够通过某个位置上的值"定位找到"这个位置上面来
    // [4,3,-2,7,8,2,3,1]  当再次找位置的时候，如果之前已经"被找到过"，那个这个数字就是第二次出现的
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            int findIndex = Math.abs(nums[index]) - 1;  // 通过求Math.abs()绝对值，忽略掉原来标记的值造成的影响 !!
            if (nums[findIndex] < 0) {
                result.add(findIndex + 1);              // 根据下标记录重复的数据值
            }
            nums[findIndex] = -nums[findIndex];         // 由于最多只能出现两次，如果从负数恢复到正数，则不造成任何影响 !!
        }
        return result;
    }
}
