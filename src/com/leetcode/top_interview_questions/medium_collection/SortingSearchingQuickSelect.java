package com.leetcode.top_interview_questions.medium_collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 正确理解：2. "QuickSelect: Hoare's selection algorithm", 专门查找数据中的前K个什么特征的数据 !!
//            One chooses a pivot and defines its position in a sorted array in a linear time using so-called partition algorithm.
//            按照frequency频率从左到右依次排序, 分成两个部分, 从N-k位置直到最后的值就是k个频率最高的值
//            If not, we can choose one more pivot and place it in its perfect position
public class SortingSearchingQuickSelect {

    private int[] unique;
    private int countElements;
    private Map<Integer, Integer> count;

    public int[] topKFrequent(int[] nums, int k) {
        countNumFrequencies(nums);
        addAllUniqueElements();
        quickSelect(0, countElements - 1, countElements - k);
        return Arrays.copyOfRange(unique, countElements - k, countElements);
    }

    private void countNumFrequencies(int[] nums) {
        count = new HashMap();
        for (int num : nums) {
            int baseCount = count.getOrDefault(num, 0);
            count.put(num, baseCount + 1);
        }
        countElements = count.size();
    }

    private void addAllUniqueElements() {
        int index = 0;
        unique = new int[count.size()];
        for (int num : count.keySet()) {
            unique[index] = num;
            index++;
        }
    }

    // kth top frequent element is (n - k)th less frequent. 第k的最大频率的值，就是第(n-k)个最小频率的值
    // "partial sort": from less to most frequent, till (n - k)th less frequent element takes its place (n - k) in a sorted array.
    // 最终要达到的结束点: 从左到右，从低到高，第Kth个最大频率的元素正好位于第(n-k)个位置上
    public void quickSelect(int left, int right, int k_smallest) {
        if (left == right) return;
        int pivot_index = generateRandomPivotIndex(left, right);
        pivot_index = partition(left, right, pivot_index);
        if (k_smallest == pivot_index) {
            return;
        } else if (k_smallest < pivot_index) {
            quickSelect(left, pivot_index - 1, k_smallest);
        } else {
            quickSelect(pivot_index + 1, right, k_smallest);
        }
    }

    private int generateRandomPivotIndex(int left, int right) {
        Random random_num = new Random();
        return left + random_num.nextInt(right - left);
    }

    // Find the pivot position in a sorted list
    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        swap(pivot_index, right);
        int store_index = left;
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {  // 将所有频率小的element往左边移动
                swap(store_index, i);
                store_index++;
            }
        }
        swap(store_index, right);
        return store_index;
    }

    public void swap(int index1, int index2) {
        int temp = unique[index1];
        unique[index1] = unique[index2];
        unique[index2] = temp;
    }
}
