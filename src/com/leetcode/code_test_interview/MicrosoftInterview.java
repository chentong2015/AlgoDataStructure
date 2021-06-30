package com.leetcode.code_test_interview;

import com.leetcode.code_test_interview.model.MyPair;

/**
 * 1. 面试的问题不一定有标准答案，有的甚至没有正确答案，把面试官当做客户
 * 2. 沟通好问题是什么，问题的边界，假设条件，边缘场景，异常场景，技术架构，如何测试，技术限制等
 * 3. 动手答题前，请留两分钟思考，抽象算法，整理逻辑
 * 4. 问对的问题，做正确的决策，在众多答案中寻找最优解
 * .
 * 5. 如果题目特殊，可以请求一定的提示，思考后解决
 * 6. 如果测试的题目需要写的代码量过大，则考虑换一种思路，寻找最优解
 */
// 1. 至少保证有一门语言不被问倒
// 2. Coding的速度和code的整洁性 !!
// 3. 三轮算法(必须在45分钟内) + 一个系统设计 + 一个behavioral行为态度
// 4. 不是只追求做对题目的“机器” + 扎实的基本功 + 优秀的思维能力 + 出众的沟通能力
public class MicrosoftInterview {

    // Microsoft 实战题目
    // 1. https://codeshare.io/ 共享代码界面, 白板写代码
    // 2. Microsoft Teams       视频面试, 共享桌面, 运行测试
    // http://microsoft-hire.me/pages/problems/leetcode.html
    // https://www.careercup.com/page?pid=microsoft-interview-questions

    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // return the smallest index (left, right) 如果没有这层限制，则使用DP
    public static MyPair test(int[] nums, int m) {
        // 1. 使用Sliding Windows框出一部分数据，判断特征
        if (nums == null || nums.length == 0) return null;
        MyPair pair = new MyPair();
        int checkSum = 0;
        int sum = 0;
        int left = 0;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            while (sum > m) {
                sum -= nums[left];
                left++;
            }
            if (sum > checkSum) {
                checkSum = sum;
                pair.left = left;
                pair.right = index;
            }
        }
        if (checkSum == 0) return null;
        return pair;
    }
}
