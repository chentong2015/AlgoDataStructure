package com.leetcode;

import java.util.HashMap;
import java.util.List;

// 企业题库  https://codetop.cc/#/home              Login: tong / TCHong15
// 题目全集  https://leetcode.com/problemset/all/   Login: chen2015tong@gmail.com / TCHong15&
// 算法总结  https://longwang2.gitbooks.io/lintcode/content/substringlei-xing-wen-ti/minimum-size-subarray-sum.html
// 1. Easy   ：只包含1个基础的核心逻辑，一个思想
// 2. Medium : 包含1个较难的逻辑，或者包含2到3个(构建在基础题目上面的)逻辑和思想
// 3. Hard   : 包含4个或者更换多的判断和逻辑，一般是一些小游戏的逻辑或是小工具软件的组成部分
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
    // 重点考虑数组中值的范围和特点，寻找可能的极限解法
    // 1. 排序之后判断每个元素所占的长度区间  O(nlog(n)) O(1)
    // 2. 使用HashMap<>来存储每个元素的频率 O(n)       O(n)
    public int findMostFrequencyElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            if (count > nums.length / 2) return num;
            map.put(num, count);
        }
        return -1;
    }

    // Microsoft Interview Question
    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // Return the smallest index (left, right) 如果没有这层限制，则使用DP
    public static int findClosestSumIndex(int[] nums, int m) {
        // 1. 使用Sliding Windows框出一部分数据，判断特征
        // if (nums == null || nums.length == 0) return null;
        // MyPair pair = new MyPair();
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
                // pair.left = left;
                // pair.right = index;
            }
        }
        // if (checkSum == 0) return null;
        return 0;
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
    // http://www.noteanddata.com/leetcode-301-Remove-Invalid-Parentheses-java-solution-note2.html
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
