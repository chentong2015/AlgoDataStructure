package questions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingTemplate1 {

    // TODO. 标准回溯算法的模板：记录一组数据排列出来的所有可能 ！！
    // Given an integer array nums of unique elements, return all possible subsets (the power set)
    // The solution set must not contain duplicate subsets
    // nums = [1,2,3] -> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]] nums中所有值都唯一
    // nums = [2,2,3] -> [[], [2], [3], [2, 3]] 原数组中存在重复的值，只考虑一次
    //
    // n + (n-1) + Mathematical formula 算法的复杂度和数学公式有关(组合形式)
    public List<List<Integer>> getSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    // 将最后的结果带到方法的参数中，传递reference并修改结果
    // for循环每次递归到最深处，直到Tree的叶子节点
    //    1  /  2   /  3
    //   2  /  3   /
    // 3   /      /
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        // TODO. 将每次回溯的临时结果tempList添加到最终结果中
        result.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            // 跳过所有重复的值，直到找到第一个新的数字
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 将有效数字添加到临时list中
            tempList.add(nums[i]);

            // 移动到下一个index位置作为开头
            backtrack(result, tempList, nums, i + 1);

            // 移除当前index添加的数字，以便排序出所有可能的组合
            int lastNumIndex = tempList.size() - 1;
            tempList.remove(lastNumIndex);
        }
    }

    // TODO. 其它自定义的解法，可能造成算法的复杂度不好计算
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
}
