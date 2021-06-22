package com.leetcode.code_test_interview;

/**
 * 1. 面试的问题不一定有标准答案，有的甚至没有正确答案，把面试官当做客户 !!
 * 2. 沟通好问题是什么，问题的边界，假设条件，边缘场景，异常场景，技术架构、如何测试、技术限制等
 * 3. 动手答题前，请留两分钟思考，抽象算法和逻辑
 * 4. 如果题目特殊，可以请求一定的提示，思考后解决
 * 5. Ask the right question，make decision在多种解法中找到最优化的解法 !!
 * 6. 如果测试的题目需要写的代码量过大，则考虑换一种思路，寻找最优解 !!
 */
public class MicrosoftInterview {

    // Microsoft 实战题目
    // 1. https://codeshare.io/ 共享代码界面, 白板写代码
    // 2. Teams 视频面试, 共享桌面，运行测试
    // http://microsoft-hire.me/pages/problems/leetcode.html
    // https://www.careercup.com/page?pid=microsoft-interview-questions

    // Sort Colors
    // Given an array nums with n objects colored red, white, or blue
    // Sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue
    // Integers 0, 1, and 2 to represent the color red, white, and blue, respectively
    // nums = [2,0,2,1,1,0] -> [0,0,1,1,2,2]
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0;                // left位置的左边一定是0
        int right = nums.length - 1; // right位置的右边一定是2
        for (int index = 0; index <= right; index++) {
            if (nums[index] == 0 && left < index) {
                swap(nums, left, index);
                index--;             // 交换过后需要退一步位置，判断交换过来的值 !!
                left++;
            }
            if (nums[index] == 2) {
                swap(nums, index, right);
                index--;
                right--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
