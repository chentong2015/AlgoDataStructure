package com.leetcode.code_test_interview;

import java.util.HashMap;

/**
 * 如何面试答题 ===> 和平时工作一致，明确需求
 * 1. 面试的问题不一定有标准答案，有的甚至没有正确答案，把面试官当做客户 !!
 * 2. 沟通好问题是什么，问题的边界，假设条件，边缘场景，异常场景，技术架构、如何测试、技术限制等
 * 3. 动手答题前，请留两分钟思考，抽象算法和逻辑
 * 4. 如果题目特殊，可以请求一定的提示，思考后解决
 * 5. Ask the right question 提正确的问题，make decision在多种解法中找到最优化的解法
 * 6. 如果测试的题目需要写的代码量过大，则考虑换一种思路，寻找最优解 !!
 */
public class DemoInterview {

    // Microsoft实战题目
    // http://microsoft-hire.me/pages/problems/leetcode.html
    // https://www.careercup.com/page?pid=microsoft-interview-questions
    // 补充实战题库 https://codetop.cc/#/home

    // Twitter算法面试题: 计算每个位置的柱子上方还能储水的量，和该柱子的前后柱子没有关系，只和它前后元素中的最大高度柱子有关系 !!
    // 解法1: 暴力破解
    // 解法2: Dynamic programming
    // 解法3: Using stack
    // 解法4: Two pointers
    public static int testSaveWater(int[] arr) {
        // 测试理解：1. 对于任何一个柱子，如果它"左边位置的最大值"和"右边位置的最大值"都比该位置的高度高，则柱子上方还有空间存水 !!
        //            O(n*n) 由于每个位置都会前后遍历，所以复杂度不可取 O(1)

        // 正确理解: 1. 通过左右两个标识完成左右最大值的更新，依次往中间移动，一次遍历 O(n) O(1)
        if (arr == null || arr.length < 3) return 0;
        int sum = 0;
        int left = 0;
        int right = arr.length - 1;
        int largestLeft = arr[left];
        int largestRight = arr[right];
        while (left < right) {
            if (largestLeft > largestRight) {        // 如果右边的最大高度更低, 则往右标识往左移动, 保证整个左边的元素的最大值不变 !!
                sum += largestRight - arr[right--];  // 此处的计算不可能为负数
                largestRight = Math.max(arr[right], largestRight);
            } else {
                sum += largestLeft - arr[left++];
                largestLeft = Math.max(arr[left], largestLeft);
            }
        }
        return sum;
    }

    // Alibaba Interview Question
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2]  ->  2
    public int findMostFrequencyElement(int[] nums) {
        // 测试理解：1. 是否能够在不使用别的数据结构的情况下，通过依次遍历将结果判断出来 !!
        //         2. 排序之后判断每个元素所占的长度区间  O(nlog(n)) O(1)
        //         3. 使用HashMap<>来存储每个元素的频率 O(n) O(n)
        if (nums == null || nums.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            if (count > nums.length / 2) {
                return num;
            }
            map.put(num, count);
        }
        return -1;
    }
}
