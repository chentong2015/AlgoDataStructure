package com.leetcode.learn_introduction.greedy_dp;

import com.leetcode.interview_questions.base.IndexMark;

import java.util.Arrays;

/**
 * Greedy Algorithm 贪婪算法
 */
public class LearnGreedyAlgo {

    // Jump Game 判断是否能够从开始跳到结尾
    // Positioned at the first index of the array 0 <= nums[i] <= 10^5
    // Each element in the array represents your maximum jump length at that position
    // nums = [2,3,1,1,4] -> step 1 + step 3
    // nums = [3,2,1,0,4] -> 始终只能跳到index=3
    public static boolean canJumpBottomUp(int[] nums) {
        // 测试理解：1. 使用递归，逐步判断每一步的跳法，优先从最大步数开始跳
        //            O(2^n) "数学推导复杂度"  O(n) Recursion requires additional memory for the stack frames.

        // 正确理解：1. Bottom-UP 自底向上的算法
        IndexMark[] memo = new IndexMark[nums.length];
        Arrays.fill(memo, IndexMark.Unknown);  // O(n) memo table 初始化暂存信息的表
        memo[memo.length - 1] = IndexMark.Good;
        for (int i = nums.length - 2; i >= 0; i--) {
            // 从i位置往后跳动nums[i]的位置, 将后面所有它能跳到的位置都做上标记
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (memo[j] == IndexMark.Good) {  // 如果后面要标记的位置到最后没有通路，则不在i点标记 !!
                    memo[i] = IndexMark.Good;
                    break;
                }
            }
        }
        return memo[0] == IndexMark.Good; // 最后从0出发到[memo.length - 1]有一条通路 !!
    }

    // 正确理解：2. 保留一个位置前面能跳到的最大步数，如果能够到达最后则成功 O(n) O(1)
    public static boolean canJumpTrick1(int[] nums) {
        int index = 1;
        int maxSteps = nums[0];
        while (index < nums.length) {
            if (maxSteps < 1) return false;          // 在index位置，它前面的剩余的最大步数，至少要有1步，才能到当前这个位置
            if (nums[index] == 0) {
                if (maxSteps < 2) {
                    return index == nums.length - 1; // 前面能到达最后一步，即使最后一个位置是0也是OK的
                }
                maxSteps--;                      // 在0这个位置对于maxStep所跳的最大步数没有帮助
            } else {
                maxSteps--;                          // 动态编程，交换比较前面剩余的最大步数
                maxSteps = Math.max(nums[index], maxSteps);
            }
            index++;
        }
        return index == nums.length;
    }

    // 正确理解：3. Greedy 贪心算法
    //            从后往前, 如果从index位置加上它移动步数能到到达后面标记位置，则切换后面记录的位置
    public boolean canJumpTrick2(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) { // 在index位置上面，要到达或者是越过后面的标记点
                lastPos = i;
            }
        }
        return lastPos == 0; // 最终判断是否是出发的起使点(index=0)，只需要有一种可能性即可
    }


    // TODO: 典型的Greedy算法的运用, 如何在一步一步的条件下逼近最小值 !!
    // Remove K Digits
    // Given string num representing a non-negative integer num, and an integer k
    // return the "smallest possible" integer after removing k digits from num
    // num = "1432219", k = 3 -> 1219
    // num = "10200",   k = 1 -> 200
    // num = "11200",   k = 1 -> 1100
    // 1 <= k <= num.length <= 10^5 严格的大小约束, num consists of only digits 是有效的数字，最高位不为0
    public String removeKdigits(String num, int k) {
        // O(n) O(n)
        char[] chars = num.toCharArray();
        int minIndex = 0;
        for (int index = 1; index < chars.length; index++) {
            if (k == 0) break;
            if (chars[minIndex] > chars[index]) {
                chars[minIndex] = ' ';
                minIndex = index;
                k--;
            } else if (chars[minIndex] < chars[index]) {
                chars[index] = ' ';
                k--;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) {
            if (c != ' ') {
                if (c == '0' && stringBuilder.length() == 0) continue; // 结果数字的最高位不能为0
                stringBuilder.append(c);
            }
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        }
        return "0"; // 最后的结果有可能全部清除，或者只剩下0
    }
}
