package data_structure.greedy_dynamic_pro;

import java.util.Arrays;

public class DynamicProgramming {

    // TODO: DP典型实例, 保留前面计算过的值, 在此基础上更新和判断
    // 最长连续增长子序列，可以演变为最长满足条件的增长
    // Longest Increasing Subsequence
    // An integer array nums, return the length of the longest strictly increasing subsequence
    // nums  = [10,9,2,5,3,7,101,18] -> [2,3,7,101] 最长连续增长子序列, 数字之间可以不连续
    // steps = [1, 1,1,2,2,3,4  ,4]
    public int lengthOfLIS(int[] nums) {
        // 正确理解：1. Dynamic Programming 使用等长数组保存"每一步所积累的'它前面的'最长的到达步数"
        // O(n^2) O(n)
        if (nums.length == 0) {
            return 0;
        }
        // 必须将之前每一步的数据存储，只用一个变量是无法保存这些信息
        int[] steps = new int[nums.length];
        steps[0] = 1;
        int maxAnswer = 1;

        for (int i = 1; i < steps.length; i++) {
            // 找前面位置更低的数目的(已经存在的)最长的序列数目
            // 判断当前位置前面的所有更小的值，获取它记录的累计长度 !!
            int maxStepBefore = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxStepBefore = Math.max(maxStepBefore, steps[j]);
                }
            }

            // 在"前面子序列最长的"那个点的基础上再移动一步，如果没有遍历到，则初始第一步为1
            steps[i] = maxStepBefore + 1;
            // 更新记录的最大值, 因为可能出现几个不同的增长序列, 需要求总的
            maxAnswer = Math.max(maxAnswer, steps[i]);
        }
        return maxAnswer;
    }

    // 正确理解：
    // Dynamic Programming with Binary Search: O(nlog(n)) O(n)
    //
    // input: [0, 8, 4, 12, 2]
    //    dp: [0]
    //    dp: [0, 8]
    //    dp: [0, 4]
    //    dp: [0, 4, 12]
    //    dp: [0, 2, 12] 这里存储的不是正确的排序值，但是数组的长度是最终的答案
    // Store increasing subsequence formed by including currently encountered element
    // 这个数组需要预留足够的长度
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            // Returns index of the search key if found
            // Return (-(insertion point) - 1) if not found
            int index = Arrays.binarySearch(dp, 0, length, num);
            if (index < 0) {
                index = -(index + 1); // 根据查找的index，重新计算要插入的点，可能直接追加到最后
            }
            dp[index] = num;          // 将读取的每个值添加到dp数组中指定的位置"insertion point"
            if (index == length) {
                length++;  // 使用length来统计插入到的位置长度，如果是插入到最后，则需要延申长度
            }
        }
        return length;
    }
}
