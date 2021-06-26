package com.leetcode.learn_introduction.base_sorting;

import com.leetcode.learn_introduction.base_sorting.model.ValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/explore/interview/card/top-interview-questions-hard/118/trees-and-graphs/851/
public class MergeSort_Master {

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        ValuePair[] arr = new ValuePair[nums.length];
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0);
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new ValuePair(i, nums[i]);
        }
        mergeSort(arr, smaller);
        res.addAll(Arrays.asList(smaller));
        return res;
    }

    private ValuePair[] mergeSort(ValuePair[] arr, Integer[] smaller) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        ValuePair[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid), smaller);
        ValuePair[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length), smaller);
        for (int i = 0, j = 0; i < left.length || j < right.length; ) {
            if (j == right.length || i < left.length && left[i].val <= right[j].val) {
                arr[i + j] = left[i];
                smaller[left[i].index] += j;
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        return arr;
    }
}
