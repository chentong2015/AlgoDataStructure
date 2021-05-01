package com.leetcode.top_interview_questions.medium_collection;

import java.util.ArrayList;
import java.util.List;

public class MediumArrayStrings1 {

    // 3Sum: return all the triplets [nums[i], nums[j], nums[k]]
    // Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
    // solution set must not contain duplicate triplets 不能出现重复的结果集
    public List<List<Integer>> threeSum(int[] nums) {
        // 测试理解: 在复杂度最低的情况下，找到所有的可能 !!

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        } else {
            
        }
        return result;
    }
}
