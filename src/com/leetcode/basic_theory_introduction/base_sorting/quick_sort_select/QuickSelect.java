package com.leetcode.basic_theory_introduction.base_sorting.quick_sort_select;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Quick Select: Hoare's selection algorithm 专门查找数据中的前K个什么特征的数据
 * 1. 统计每个元素(array或者list)和它出现的频率(hash table)
 * 2. TODO: 根据每个元素的频率，进行Quick Sort快速排序，从低频到高频
 * 3. 直到要取的前k的频率和pivot分区位置相等，则它右边的就是前k个最大频率 !!
 */
// 时间复杂度：O(n) 取决于分区的随机算法
// 空间复杂度：O(n)
public class QuickSelect {

    private int[] unique;
    private int countElements;
    private Map<Integer, Integer> mapCounts;

    public int[] topKFrequent(int[] nums, int k) {
        countNumFrequencies(nums);
        addAllUniqueElements();
        quickSelect(0, countElements - 1, countElements - k);
        return Arrays.copyOfRange(unique, countElements - k, countElements); // 只取右侧位置前k个元素，已经分区过的元素
    }

    private void countNumFrequencies(int[] nums) {
        mapCounts = new HashMap();
        for (int num : nums) {
            int baseCount = mapCounts.getOrDefault(num, 0);
            mapCounts.put(num, baseCount + 1);
        }
        countElements = mapCounts.size();
    }

    private void addAllUniqueElements() {
        int index = 0;
        unique = new int[mapCounts.size()];
        for (int num : mapCounts.keySet()) {
            unique[index] = num;
            index++;
        }
    }

    // 从左到右，从低到高，第Kth个最大频率的元素正好位于第(n-k)个位置上
    // 其余更左边的没有分区的，可以不用考虑，因为都是更小的值
    public void quickSelect(int left, int right, int kSmallest) {
        if (left == right) return;
        int pivot_index = getRandomPivot(left, right);
        pivot_index = partition(left, right, pivot_index);
        if (kSmallest == pivot_index) return;
        if (kSmallest < pivot_index) {
            quickSelect(left, pivot_index - 1, kSmallest);
        } else {
            quickSelect(pivot_index + 1, right, kSmallest);
        }
    }

    // 这里获取随机pivot index的算法影响时间复杂度
    private int getRandomPivot(int left, int right) {
        Random random_num = new Random();
        return left + random_num.nextInt(right - left);
    }

    // 注意pivot位置的设置
    // 最后将最右边存储的pivot移动到index,作为分区的中间位置 !!
    public int partition(int left, int right, int pivot) {
        swap(pivot, right);
        int index = left;
        for (int i = left; i <= right; i++) {
            if (mapCounts.get(unique[i]) < mapCounts.get(unique[pivot])) {
                swap(index, i);
                index++;
            }
        }
        swap(index, right);
        return index;
    }

    public void swap(int index1, int index2) {
        int temp = unique[index1];
        unique[index1] = unique[index2];
        unique[index2] = temp;
    }
}
