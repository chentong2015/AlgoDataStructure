package data_structure.master_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingSubsets {

    // TODO. 算法核心: 最后的结果组合中每个位置的值可能出现，可能不出现
    //  使用额外的同等长度的数组来在对于的index位置做标识，针对每一轮的排序进行统计
    // Subsets
    // Given an integer array nums of unique elements, return all possible subsets (the power set) 返回所有的组合形式
    // The solution set must not contain duplicate subsets
    // nums = [1,2,3] -> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]] nums中所有值都唯一
    public List<List<Integer>> subsets(int[] nums) {
        // 测试理解：1. 每一个位置上的值，都有两种可能，要么出现，要么不出现 -> 结果的数目是2^n个

        // 正确理解：1. 根据每个具体位置的不同可能, 多重"递归", 当递归到最后一个位置，再提取值到结果中 !!
        //  O(n*2^n) 递归调用方法的数目*最后的取值
        //  O(n+n*2^n)*n)=O(n*2^n) 空间复杂度用来存储所有的可能结果, 一共有2^n中可能，其中最大存储空间是全部的n个数
        List<List<Integer>> results = new ArrayList<>();
        int[] list = new int[nums.length];
        getSubsets(-1, list, nums, results);
        return results;
    }

    private void getSubsets(int index, int[] list, int[] nums, List<List<Integer>> results) {
        index++;
        // 使用clone出来的列表，不会造成数据的共享差错
        int[] cloneList = list.clone();
        if (index == nums.length) {
            // 递归的最后，还需要遍历N来取出所有的结果，增加时间复杂度
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (list[i] == 1) {
                    result.add(nums[i]);
                }
            }
            // 统计当前轮次统计出来的结果
            results.add(result);
        } else {
            // 回溯的核心: 改变设置递归出不同的结果, 0表示不出现, 1表示出现
            cloneList[index] = 0;
            getSubsets(index, cloneList, nums, results);

            cloneList[index] = 1;
            getSubsets(index, cloneList, nums, results);
        }
    }

    // TODO. Subsets的标准回溯算法
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue; // skip duplicates
            }
            
            tempList.add(nums[i]);

            // i+1 当前index位置使用之后，移动到下一个位置
            backtrack(list, tempList, nums, i + 1);

            tempList.remove(tempList.size() - 1);
        }
    }
}
