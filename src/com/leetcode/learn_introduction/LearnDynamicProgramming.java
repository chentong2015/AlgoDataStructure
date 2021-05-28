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
    //      12345678
    // O(nlog(n)) 时间复杂度看成是log(n)层 * 每一层merge的时间复杂度O(n) !!
    // O(n)       空间复杂度来自于每一层排序时的sublist, 最大情况时O(n)的临时存储空间 ==> 在递归divide Single Element过程中，总O(n)
    public int[] merge_sort(int[] input) {
        if (input.length <= 1) {
            return input;
        }
        int pivot = input.length / 2;
        int[] left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot));
        int[] right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.length));
        return merge(left_list, right_list);
    }

    // 标准解法：合并两个排序的数组，使用两个index位置游标 + 结果表的index
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
}
