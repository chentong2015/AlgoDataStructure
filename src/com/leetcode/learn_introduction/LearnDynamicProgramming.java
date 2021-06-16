package com.leetcode.learn_introduction;

import java.util.Arrays;

/**
 * 动态编程的理解步骤
 * 1. Start with the recursive backtracking solution
 * .   > 使用递归拆分问题，递归解决
 * 2. Optimize by using a memoization table
 * .   > 开辟存储空间，记录迭代中每一步的结果
 * 3. Top-Down 自顶向下 & Bottom-Up 自底向上                 ===> Divide and Conquer 分而治之 + 归并算法
 * .   > 先将复杂问题差发成若干个小问题SubProblems              ===> Recursion 采用递归进行分解
 * .   > 重复且独立的解决每个小问题，最后再汇总结果解决原始问题
 * 4. Apply final tricks to reduce complexity
 * .   > 使用追踪记录结果信息，只判断关键点，减低时间空间复杂度
 */
public class LearnDynamicProgramming {

    // TODO: DP金典算法 Divide and Conquer  ==> Merge Sort 归并排序
    // Top-Down 自顶向下
    // 1  5  3  2  8  7  6  4
    //   1532       8764
    //  15  32    87   64
    // 1  5  3  2  8  7  6  4  将大问题拆分成单元的小问题
    //  15  23    78   46      逐步解决小问题
    //    1235      4678       使用同样的逻辑，直到最终问题解决
    //       12345678
    // O(nlog(n)) 时间复杂度看成是log(n)层 * 每一层merge的时间复杂度O(n) !!
    // O(n)       空间复杂度来自于每一层排序时的sublist, 最大情况时O(n)的临时存储空间 ==> 在递归divide Single Element过程中，总O(n)
    public int[] merge_sort(int[] input) {
        if (input.length <= 1) {
            return input;
        }
        int pivot = input.length / 2;
        int[] left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot));  // Arrays复制片段的时候，复制到最后一个位置前 !!
        int[] right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.length));
        return merge(left_list, right_list);  // 最后回退到第一层的调用，则merge左右两个半的子数组
    }

    // 标准解法：合并两个排序的数组，使用两个index位置游标 + 结果表的index(ret_cursor)
    //         当一个游标结束后，直接添加另一个剩下的结果
    public int[] merge(int[] left_list, int[] right_list) {
        int[] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0;
        int right_cursor = 0;
        int ret_cursor = 0;
        while (left_cursor < left_list.length && right_cursor < right_list.length) {
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }
        while (left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }
        while (right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }

    // TODO: Dynamic Programming动态编程展开树 !!
    // Perfect Squares
    // Given an integer n, return the least number of perfect square numbers that sum to n
    // A perfect square is an integer that is the square of an integer: 1 4 9 16 25 ...
    // Input: n = 12 -> 12 = 4 + 4 + 4 -> 3 最少需要3个perfect square数值组成
    //            12
    //      9           4        1   数值展开，记录增加的层级，同时对应不同的差值，在继续迭代(12-9), (12-4), (12-1)
    //  9   4    1     4  1      1   下层展开，计算(12-9-9)...(12-4-4)...
    // ..  4 1   1    4 1  1     1
    public int numSquares(int n) {
        // 测试理解：1. 展开的形式是一个任意的tree树，最小perfect square的数目，也就是树的最低深度 !!
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dpCount(n, dp);
    }

    private int dpCount(int n, int[] dp) {
        if (dp[n] != -1) return dp[n]; // 如果指定的数值已经推导出结果，则直接返回，避免循环
        if (n == 0) return 0;          // 说明上一层的(n-index*index)刚好完全
        int min = Integer.MAX_VALUE;   // 使用index平方而不是index，能充分的降低时间复杂度
        for (int index = 1; index * index <= n; index++) {
            int currentLevel = 1 + dpCount(n - index * index, dp);
            min = Math.min(min, currentLevel);
        }
        return dp[n] = min;            // 动态记录第n个位置的结果
    }
}
