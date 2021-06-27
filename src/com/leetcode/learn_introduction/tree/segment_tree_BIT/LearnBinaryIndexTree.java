package com.leetcode.learn_introduction.tree.segment_tree_BIT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 专门用于解决"Range query problems"区间搜索问题
// TODO: Binary Indexed Tree: https://leetcode.com/tag/binary-indexed-tree/
public class LearnBinaryIndexTree {

    // Count of Smaller Numbers After Self
    // return a new counts array, where counts[i] is the number of smaller elements to the right of nums[i]
    // nums = [5,2,6,1] -> [2,1,1,0] 统计每个位置的右边小于该位置值的数目
    public List<Integer> countSmallerBIT(int[] nums) {
        int offset = 10000;       // offset negative to non-negative
        int size = 2 * 10000 + 2; // total possible values in nums plus one dummy
        int[] tree = new int[size];
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int shiftIndex = nums[i] + offset; //
            result.add(query(shiftIndex, tree));
            update(shiftIndex, tree, size);
        }
        Collections.reverse(result);
        return result;
    }

    // Query the number of elements in the BIT that are smaller than num
    private int query(int index, int[] tree) {
        int result = 0; // return sum of [0, index)
        while (index >= 1) {
            result += tree[index];
            index -= index & -index;
        }
        return result;
    }

    // Update the count of num in the Binary Index Tree
    private void update(int index, int[] tree, int size) {
        index++; // index in BIT is 1 more than the original index
        while (index < size) {
            tree[index] += 1;
            index += index & -index;
        }
    }
}
