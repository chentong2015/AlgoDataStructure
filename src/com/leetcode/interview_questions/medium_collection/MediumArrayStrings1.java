package com.leetcode.interview_questions.medium_collection;

import java.util.*;

public class MediumArrayStrings1 {

    // TODO: 3Sum: return all the triplets [nums[i], nums[j], nums[k]]
    // Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
    // Solution set must not contain duplicate triplets 不能出现重复的结果集
    public List<List<Integer>> threeSum(int[] nums) {
        // 测试理解: 1. 移动3个指针, 排列组合问题，从N个数中提取出3个数字，和数字的顺序无关

        // 正确理解: 1. 降维处理，从3维降到2维的计算，固定一个值，然后找两个值的和是该值的相反值  O(n^3)  O(n)
        //         2. 使用值来构建Key, 通过HashMap来加快搜索，不改变原来的array中的值
        HashMap<String, List<Integer>> results = new HashMap<>();
        if (nums == null || nums.length < 3) return new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Integer[] keys = {nums[i], nums[j], nums[k]};
                        Arrays.sort(keys);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int m = 0; m < 3; m++) {
                            stringBuilder.append(keys[m]);
                        }
                        String key = stringBuilder.toString();
                        if (!results.containsKey(key)) {
                            results.put(key, Arrays.asList(keys));
                        }
                    }
                }
            }
        }
        return new ArrayList<>(results.values());
    }

    // Set Matrix Zeroes
    // Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
    public void setZeroes(int[][] matrix) {
        // 正确理解: 1. 使用两个Set集来存储记录到的行和列，最后根据记录的值来修改  Time O(m*n) / Space O(m+n)
        //          2. 用第一行和第一列来作为是否变化的标识符，根据设置0来更改   Time O(m*n) / Space O(1)
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                    matrix[0][j] = 0;  // 标记第一行和第一列作为判断
                    matrix[i][0] = 0;
                }
            }
        }
        // 设置除了第一行第一列的所有位置, 从row=1 & col=1开始往后
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后检查是否第一行和第一列需要设置
        if (firstRow) Arrays.fill(matrix[0], 0);
        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
        }
    }

    // TODO: Hash Table "Aggregate by Key" 通过键值来聚合同类数据
    // Group Anagrams
    // Given an array of strings strs, group the anagrams together. can return answer in any order
    // strs = ["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
    public List<List<String>> groupAnagrams(String[] strs) {
        // 测试理解: 1. 暴力解法：直接遍历，然后归类添加到结果列表中  O(n*n*mlog(m))   O(n*m)

        // 正确理解: Group分组的本质：将具有特定特征的数据放到一组中，这个特定的特征就是分组的"键值key"
        //          HashMap<Key, Value> 采用Map存储，最终返回Map中的所有Values，不直接使用List<List<T>> !!
        //          1. 将字符串中的字符排序之后，作为一组值的Key: O(n*mlog(m)) O(n*m)
        HashMap<String, List<String>> results = new HashMap<>();
        if (strs.length == 0) return new ArrayList();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);                                // 排序造成O(mlog(m))的复杂度 !!
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < chars.length; j++) {
                stringBuilder.append(chars[i]);
            }
            String key = stringBuilder.toString();              // Get map key from string
            if (results.containsKey(key)) {                     // Add item by group
                results.get(key).add(strs[i]);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                results.put(key, newList);                      // Put new item to results
            }
        }
        return new ArrayList(results.values()); // 通过.values()来构造出List
    }

    // 正确理解:  2. 将字符串中的字符依次统计，用统计数值(int -> String)作为Key
    //             O(n*m)  O(n*m)
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> results = new HashMap<>();
        int[] count = new int[26];
        for (String str : strs) {
            // 通过统计字符的数量，生成指定的Map的Key=#1#2#1...
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) {  // 循环内的复杂度是O(m) < O(mlog(m))
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            // ....
        }
        return new ArrayList(results.values());
    }
}
