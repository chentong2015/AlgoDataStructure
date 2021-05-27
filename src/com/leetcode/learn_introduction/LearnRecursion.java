package com.leetcode.learn_introduction;

import java.util.ArrayList;
import java.util.List;

/**
 * Recursion 递归法
 * 1. 时间复杂度：O(n), O(2^n), O(n^n) ...
 * 2. 用法与场景：数列，二叉树的遍历，动态编程，回溯算法 ...
 */
public class LearnRecursion {

    // Pascal's Triangle "Recurrence循环问题"
    //     1  0  0  0  0 ...
    //    1  1  0  0  0
    //   1 1+1 1  0  0
    //  1  3  3  1  0       level=3
    // 1  4  6  4  1        level=4 表示要算到第几个index的位置
    // Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle
    // Use only O(rowIndex) extra space 额外的空间复杂的约束
    public List<Integer> getRow(int level) {
        // 正确理解：1. 循环，在[1, index]区间从后往前累计计算，避免先计算的值对后值的累加造成影响(避免保存前一个位置值的历史值) !!
        List<Integer> results = new ArrayList<>();
        results.add(0, 1);
        for (int index = 1; index < level; index++) {
            for (int j = index - 1; j >= 1; j--) {  // 在index前面进行"Recurrence循环"计算
                int temp = results.get(j - 1) + results.get(j);
                results.add(j, temp);
            }
            results.add(index, 1);          // 每次在最index前追加右边值
        }
        return results;
    }
}
