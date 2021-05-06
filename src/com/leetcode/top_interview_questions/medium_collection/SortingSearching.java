package com.leetcode.top_interview_questions.medium_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SortingSearching {

    // TODO: QuickSelect 金典算法"find kth something": kth smallest, kth largest, kth most frequent, kth less frequent, etc.
    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    public int[] topKFrequent(int[] nums, int k) {
        // 测试理解：1. 使用HashMap统计每一个值出现的次数，排序之后取出前K个值
        //            关键是如何根据Map中的value来排序，最终按照顺序取得指定的key值
        //            不能组合key和value来排序，由于会出现负数，导致排序出错 1#-3

        // 正确理解：1. 通过Heap堆来实现排序和最后的提取
        // 3 0 1 0
        // 3 -> 1  -> 1#-3  -> 2
        // 0 -> 2  -> 2#0   -> 1
        // 1 -> 1  -> 1#1   -> 1
        HashMap<Integer, Integer> results = new HashMap<>(); // 自带排序的链表
        for (int i = 0; i < nums.length; i++) {
            results.put(nums[i], results.getOrDefault(nums[i], 0) + 1);  // 直接添加或者修改原来的key所对应的值 !!
        }

        List<Integer> list = new ArrayList<>(results.values());
        Collections.sort(list); // 默认是从低到高排序
        for (int i = list.size() - 1; i >= 0; i--) {  // 这里用二分法搜索，时间复杂度刚好是O(nlog(n))

        }

        int[] values = new int[k];
        int index = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (index == k) {
                break;
            }
            // values[index] = Integer.parseInt(list.get(i).split("#")[1]);
            index++;
        }
        return values;
    }

}
