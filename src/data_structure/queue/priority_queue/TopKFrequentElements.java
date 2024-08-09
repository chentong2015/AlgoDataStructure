package data_structure.queue.priority_queue;

import java.util.*;

// TODO. PriorityQueue金典运用: 对元素出现的频率进行优先级排序，然后取TOP k频率
public class TopKFrequentElements {

    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    //
    // O(Nlogk) if k < N and O(N) in the particular case of N = k
    // O(N+k) to store the hash map with not featured N elements and a heap with k elements
    //
    // O(nlog(k)) 生成优先队列时需要逐个排序
    // O(n+k+k)   开辟三个数据的存储空间
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == k) {
            return nums;
        }
        HashMap<Integer, Integer> results = new HashMap<>();
        for (int num : nums) {
            int baseCount = results.getOrDefault(num, 0);
            results.put(num, baseCount + 1);
        }

        // 根据Hashmap中统计的频率进行排序
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(results::get));
        for (int n : results.keySet()) {  // O(nlog(k))
            // 插入新的数据，如果队列中超过k个结果数据，则移除频率低的数据
            // 通过减少队列中的元素来降低优先队列排序的时间
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 从队列中取出最后的结果数据
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            top[i] = heap.poll();
        }
        return top;
    }
}





