package questions.sorting;

import java.util.*;

public class SortingSearching2 {

    // TODO: 找一組数据中前K个数的算法
    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    public int[] topKFrequent(int[] nums, int k) {
        // TODO: 将最频繁出现的前k个元素构建成Heap堆, 然后提取成数组输出
        // O(Nlogk) if k < N and O(N) in the particular case of N = k
        // O(N+k) to store the hash map with not featured N elements and a heap with k elements
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

    // Merge Intervals 合并特征区间所使用的数据结构
    // Given an array of intervals where intervals[i] = [start, end], start <= end 整合区间
    // intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
    public int[][] merge(int[][] intervals) {
        // 测试理解：1. 每添加一个新区间，就更新前面所存储的区间段 O(n²)  O(n)

        // 正确理解：1. 将所有区间按照start值排序，然后按照下面的处理方式运算 O(nlog(n)) < O(n²) ===> 优先考虑使用排序 !!
        //            使用LinkedList保证添加的区间从低到高，没有交叉
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {  // 如果不需要合并区间，则直接添加
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]); // 判断是否更新区间的上边界
            }
        }
        return merged.toArray(new int[merged.size()][]);  // 直接从LinkedList到Array转换，提供数组的声明，不需要从List中逐个提取
    }
}





