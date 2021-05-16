package com.leetcode.top_interview_questions.medium_collection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 算法1：找一組数据中前K个数的算法
 */
public class SortingSearching1 {

    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    public int[] topKFrequent(int[] nums, int k) {
        // 测试理解：1. 使用HashMap统计每一个值出现的次数，排序之后取出前K个值, 如何排序是关键
        //            不能组合key和value来排序，由于会出现负数，导致排序出错 1#-3

        // 正确理解：1. TODO: 将最频繁出现的前k个元素构建成Heap堆, 然后提取成数组输出
        //            O(Nlogk) if k < N and O(N) in the particular case of N = k
        //            O(N+k) to store the hash map with not featured N elements and a heap with k elements
        if (nums.length == k) {
            return nums;
        }
        HashMap<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int baseCount = results.getOrDefault(nums[i], 0); // 直接添加或者修改原来的key所对应的值 !!
            results.put(nums[i], baseCount + 1);
        }
        // 创建Queue的容量就是HashMap中键值的数量，添加node提供的比较器是通过key->Value来比较
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(results::get));
        for (int n : results.keySet()) {  // O(nlog(k)) < O(nlog(n))
            heap.add(n);
            if (heap.size() > k) {
                heap.poll(); // Retrieves and removes the head of this queue 提取并移除头部的值
            }
        }
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; i--) { // O(klog(k))
            top[i] = heap.poll();
        }
        return top;
    }

    // Kth Largest Element in an Array 
    // The kth largest element in the sorted order, not the kth distinct element
    // 使用QuickSelect算法来解决
    public int findKthLargestElement() {
        return 0;
    }

}





