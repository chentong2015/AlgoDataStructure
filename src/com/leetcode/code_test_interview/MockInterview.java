package com.leetcode.code_test_interview;

import com.leetcode.code_test_interview.base.MyPair;

import java.util.HashMap;
import java.util.List;

/**
 * 1. 面试的问题不一定有标准答案，有的甚至没有正确答案，把面试官当做客户
 * 2. 沟通好问题是什么，问题的边界，假设条件，边缘场景，异常场景，技术架构，如何测试，技术限制等
 * 3. 动手答题前，请留两分钟思考，抽象算法，整理逻辑
 * 4. 问对的问题，做正确的决策，在众多答案中寻找最优解
 * 5. 如果题目特殊，可以请求一定的提示，思考后解决
 * 6. 如果测试的题目需要写的代码量过大，则考虑换一种思路，寻找最优解
 */
// 1. 至少保证有一门语言不被问倒
// 2. Coding的速度和code的整洁性
// 3. 三轮算法(必须在45分钟内)  + 一个系统设计 + 一个behavioral行为态度
// 4. 不是只追求做对题目的“机器” + 扎实的基本功 + 优秀的思维能力 + 出众的沟通能力
// https://codetop.cc/#/home  Login: tong / TCHong15
// https://codeshare.io/      共享代码界面, 白板写代码
public class MockInterview {

    // Twitter Interview Question
    // 计算每个位置的柱子上方还能储水的量，和该柱子的前后柱子没有关系，只和它前后元素中的最大高度柱子有关系
    // 对于任何一个柱子，如果它"左边位置的最大值"和"右边位置的最大值"都比该位置的高度高，则柱子上方还有空间存水 !!
    public static int testSaveWater(int[] arr) {
        // 正确理解: 通过左右两个标识完成左右最大值的更新，依次往中间移动，线性时间复杂度
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

    // Microsoft Interview Question
    // http://microsoft-hire.me/pages/problems/leetcode.html
    // https://www.careercup.com/page?pid=microsoft-interview-questions
    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // Return the smallest index (left, right) 如果没有这层限制，则使用DP
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

    // Google Interview Question
    // https://www.hackerrank.com/interview/interview-preparation-kit
    // 有一个n*n的棋盘，上面有m个糖果，最开始有一个人在棋盘左上角，他可以向左向右或者向下移动，但不能向上移动，
    // 问他最少需要多少步吃完所有糖果
    // ? 0 0 0   由于不能倒退，则必须保证每一行的必须吃完之后，才下降到下一行中
    // 0 0 1 0   进入下一行的时候，需要去找这一行有糖果的边界位置的的其中一个最近的位置
    // 1 0 0 1   在一行中横向移动
    // 0 1 0 0   直到结束...
    public int countSteps(int[][] nums, int m) {
        return 0;
    }

    // 2020 Facebook interview
    // TODO: https://leetcode.com/problems/remove-invalid-parentheses/solution/
    // Remove Invalid Parentheses
    // Remove the minimum number of invalid parentheses to make the input string valid
    // Return all the possible results in any order
    // s = "()())()"  -> ["(())()","()()()"]   去除最小的括号数(左右括号的数量差值), 使字符串有效
    // s = "(a)())()" -> ["(a())()","(a)()()"]
    public List<String> removeInvalidParentheses(String str) {
        // 测试理解: 1. )((( )))( 有效的字符串，左右括号数量必须一致/数量一致并不一定就有效，左括号必须第一字符，右括号必须最后字符
        //          2. 暴力破解，依次(全部可能)去除一定数目的字符，判断是否是有效字符串
        return null;
    }

    // Amazon Interview
    // Median of Two Sorted Arrays
    // TODO: https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn)))-solution-with-explanation
}
