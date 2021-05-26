package com.leetcode.learn_introduction;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion 递归法
 * 1. 时间复杂度 ：O(n), O(2^n), O(n^n) ...
 * 2. 用法与场景 ：数列，二叉树的遍历，动态编程，回溯算法 ...
 */
public class LearnRecursion {

    // Pascal's Triangle II 递归问题
    //     1 0 0 0 0
    //    1 1 0 0 0
    //   1 2 1 0 0
    //  1 3 3 1 0      level=3
    // 1 4 6 4 1       level=4
    //         step
    public List<Integer> getRow(int level) {
        // 测试理解：1. 杨辉三角，将上层数据的和累计生成下层数据，形成数据金字塔，指定层数，确定数据
        int[] values = new int[level + 1];
        values[0] = 1;
        getRow(0, values);
        List<Integer> results = new ArrayList<>();
        for (int value : values) {
            results.add(value);
        }
        return results;
    }

    private void getRow(int step, int[] values) {
        if (step < values.length) {
            values[step] = 1;
            for (int i = 1; i <= step; i++) {
                int oldValue = values[i];
                values[i] = values[i - 1] + values[i];
            }
        }

        if (step < values.length) {
            values[step] = 1;
            getRow(step + 1, values);
        }
    }
}
