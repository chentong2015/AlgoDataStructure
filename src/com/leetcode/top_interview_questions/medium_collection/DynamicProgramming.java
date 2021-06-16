package com.leetcode.top_interview_questions.medium_collection;

import com.leetcode.top_interview_questions.base.IndexMark;

import java.util.Arrays;

public class DynamicProgramming {

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

    // Coin Change 只用最少的零钱数目来凑出指定的值, 假设每种零钱的数目是足够的
    // An integer array coins representing coins, an integer amount representing a total amount of money
    // Return the fewest number of coins that you need to make up that amount
    public int coinChange(int[] coins, int amount) {
        // 测试理解：1. 先从币值最大的开始取, 从大到小, 如果没有结果则从第二小开始取, 可以使用递归算法一层一层的取
        //            n1*1+n2*2+n3*3...=X   n1 + n2 + n3  ...=Min

        // 正确理解：1. Dynamic programming(Top down) 所有可能最终构成一个递归树, 答案是这个递归树的最低高度层级
        // coins = {2, 5}       target = 6   ->  Answer: -1          Expected: 3 (2)
        // coins = {1, 3, 4, 5} target = 7   ->  Answer: 3 (5, 1, 1) Expected: 2 (3, 4)
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    // TODO: DP编程典型实例，连续增长的子序列
    // Longest Increasing Subsequence
    // An integer array nums, return the length of the longest strictly increasing subsequence
    // nums  = [10,9,2,5,3,7,101,18] -> [2,3,7,101] 最长连续增长子序列
    // steps = [1, 1,1,2,2,3,4  ,4]
    public int lengthOfLIS(int[] nums) {
        // 正确理解：1. Dynamic Programming 使用等长数组保存"每一步所积累的'它前面的'最长的到达步数"
        //            O(n²) O(n)
        if (nums.length == 0) return 0;
        int[] steps = new int[nums.length];
        steps[0] = 1;
        int maxAnswer = 1;
        for (int i = 1; i < steps.length; i++) {
            int maxValue = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {                     // 在前面位置低的基础上增加, 判断其中最大值步数
                    maxValue = Math.max(maxValue, steps[j]);
                }
            }
            steps[i] = maxValue + 1;                         // 在"前面子序列最长的"那个点的基础上再移动一步
            maxAnswer = Math.max(maxAnswer, steps[i]);       // 更新记录的最大值, 因为可能出现几个不同的增长序列, 需要求总的
        }
        return maxAnswer;
    }

    // 正确理解：2. Dynamic Programming with Binary Search  O(nlog(n)) O(n)
    public int lengthOfLIS2(int[] nums) {
        // input: [0, 8, 4, 12, 2]
        //    dp: [0]
        //    dp: [0, 8]
        //    dp: [0, 4]
        //    dp: [0, 4, 12]
        //    dp: [0, 2, 12] 这里存储的不是正确的排序值，但是数组的长度是最终的答案
        // Store increasing subsequence formed by including currently encountered element 这个数组需要预留足够的长度
        int[] dp = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            // Returns index of the search key if found, else return (-(insertion point) - 1)
            int index = Arrays.binarySearch(dp, 0, length, num); // 根据查找出来的index位置，确定插入点
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = num;         // 将读取的每个值添加到dp数组中指定的位置"insertion point"
            if (index == length) {   // 使用length来统计插入到的位置长度
                length++;
            }
        }
        return length;
    }
}
