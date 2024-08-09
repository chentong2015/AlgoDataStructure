package data_structure.heap;

import java.util.*;

public class HeapSearching {

    // TODO: 找一組数据中前K个数的算法
    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    //
    // TODO: 将最频繁出现的前k个元素构建成Heap堆, 然后提取成数组输出
    // O(Nlogk) if k < N and O(N) in the particular case of N = k
    // O(N+k) to store the hash map with not featured N elements and a heap with k elements
    public int[] topKFrequent(int[] nums, int k) {
         if (nums.length == k) {
            return nums;
        }
        HashMap<Integer, Integer> results = new HashMap<>();
        for (int num : nums) {
            int baseCount = results.getOrDefault(num, 0);
            results.put(num, baseCount + 1);
        }

        // 创建Queue的容量就是HashMap中键值的数量，添加node提供的比较器是通过key->Value来比较
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(results::get));
        for (int n : results.keySet()) {  // O(nlog(k)) < O(nlog(n))
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; i--) { // O(klog(k))
            top[i] = heap.poll();
        }
        return top;
    }
}





