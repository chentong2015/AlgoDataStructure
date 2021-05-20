package com.leetcode.top_interview_questions.easy_collection;

import java.util.Arrays;

public class BaseArray2 {

    // Move Zeroes
    // Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
    // nums = [0,1,0,3,12] -> [1,3,12,0,0] 只能在原始的数组上操作
    //         1 3 12
    public void moveZeroes(int[] nums) {
        // 测试理解：1. 将非0的值依次排列在数组的开头，最后留下的位置就是0的值(统计数目)
        //            O(n) O(1)
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            } else {
                count++;
            }
        }
        for (int i = 0; i < count; i++) { // 最后补充结尾的0位置
            nums[nums.length - 1 - i] = 0;
        }
    }

    // Two Sum
    // Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    // 数组中的元素只能使用一次，有且仅有一个解
    // nums = [3,2,4], target = 9 -> [1,2]
    // nums = [3,3],   target = 6 -> [0,1]
    public int[] twoSum(int[] nums, int target) {
        // 测试理解：1. 将原始的数组进行排序(造成数字的位置index丢失?)，然后从两边往中间读取确定具体的位置
        //            O(nlog(n)) O(1)
        //         2. 使用HashMap<>存储元素和对应的index位置，直接判断key和target-key同时存在 ==> 原始数组中元素不能重复 !!
        //            O(n) O(n)
        int[] result = new int[2];
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(temp);
        int left = 0;
        int right = temp.length - 1;
        while (left < right) {
            if (temp[left] + temp[right] < target) {
                left++;
            } else if (temp[left] + temp[right] > target) {
                right--;
            } else {
                break; // 每个位置的值只能使用一次，并且在一定有解 !!
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == temp[left] && result[0] == 0) { // 如果temp[left]和temp[right]相同，则需要取两个位置
                result[0] = i;
            } else if (nums[i] == temp[right]) {
                result[1] = i;
            }
            if (result[0] != 0 && result[1] != 0) break;
        }
        return result;
    }

    // Rotate Image
    // You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
    // You have to rotate the image in-place 必须在原始矩阵上面改
    public void rotate(int[][] matrix) {
        // 测试理解：1. 每次移动一个值，构建循环的路径，直到移动的位置回到原点
        //            从矩阵的斜角位置进行遍历(0,0) -> (1,1) -> (2, 2)

        // 正确理解：1. 不需要内部的while()循环，本质是在旋转的时候，只有4个点(东南西北)位置的坐标值需要依次交换
        int n = matrix.length;                                      // n = 5
        for (int i = 0; i < (n + 1) / 2; i++) {                     // i = 0
            for (int j = 0; j < n / 2; j++) {                       // j = 1
                int temp = matrix[n - 1 - j][i];                    // 3,0  上下左右四个位置依次轮换计算 !!
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];// 4,3  数学推导
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];// 1,4
                matrix[j][n - 1 - i] = matrix[i][j];                // 0,1
                matrix[i][j] = temp;
            }
        }
    }
}
