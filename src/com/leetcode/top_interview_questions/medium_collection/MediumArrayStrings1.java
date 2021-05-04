package com.leetcode.top_interview_questions.medium_collection;

import com.leetcode.top_interview_questions.easy_collection.BaseStrings1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediumArrayStrings1 {

    // TODO: 3Sum: return all the triplets [nums[i], nums[j], nums[k]]
    // Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
    // Solution set must not contain duplicate triplets 不能出现重复的结果集
    public List<List<Integer>> threeSum(int[] nums) {
        // 测试理解: 1. 移动3个指针, 在复杂度最低的情况下，找到所有的可能 !!

        // 正确理解: 1. 排列组合问题，从N个数中提取出3个数字，和数字的顺序无关
        //         2. 降维处理，从3维降到2维的计算，固定一个值，然后找两个值的和是该值的相反值
        //         3. 使用hash map来加快搜索，不改变原来的array中的值
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> item;
        if (nums == null || nums.length < 3) {
            return results;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        item = new ArrayList<>();
                        item.add(nums[i]);
                        item.add(nums[j]);
                        item.add(nums[k]);
                        if (!results.contains(item)) { // 判断是否已经存在这样的组合
                            results.add(item);
                        }
                    }
                }
            }
        }
        return results;
    }

    // Set Matrix Zeroes
    // Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
    public void setZeroes(int[][] matrix) {
        // 测试理解：1. 遍历每一个位置，如果是0，则标记横向和纵向的位置(原始是0的位置不做标记，同一个位置不被标记两次)
        //            使用额外的O(mn)或者O(m+n)空间进行标记，但不是最优的解法 !!

        // 正确理解: 1. 使用两个Set集来存储记录到的行和列，最后根据记录的值来修改
        //          2. 尝试使用第一行和第一列来作为是否变化的标识符，最后根据标记来更改 ?? Space(1)不存在
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<>();  // Space O(m+n)
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < R; i++) {         // Time O(m+n)
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Group Anagrams
    // Given an array of strings strs, group the anagrams together. can return answer in any order
    public List<List<String>> groupAnagrams(String[] strs) {
        // 测试理解: 1. 暴力解法：直接遍历，然后归类添加到结果列表中  O(n*n*mlog(m))   O(n*m)
        List<List<String>> results = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] != ".") {
                List<String> newItem = new ArrayList<>();
                if (results.size() == 0) {
                    newItem.add(strs[i]);
                    results.add(newItem);
                } else {
                    int index = 0;
                    for (List<String> item : results) {
                        if (BaseStrings1.isAnagram(item.get(0), strs[i])) {
                            results.get(index).add(strs[i]);
                            break;
                        }
                        index++;
                    }
                    if (index == results.size()) {
                        newItem.add(strs[i]);
                        results.add(newItem);
                    }
                }
            }
        }
        return results;
    }

    // 正确理解: Group什么是分组，将具有特定特征的数据放到一组中，这个特定的特征就是分组的"键值key"
    //          1.
    //          2.
}
