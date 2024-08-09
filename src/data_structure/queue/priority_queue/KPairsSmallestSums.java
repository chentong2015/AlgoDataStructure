package data_structure.queue.priority_queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// TODO. PriorityQueue金典运用: 对数据的某个特征进行优先级排序，然后取出结果
public class KPairsSmallestSums {

    // Find K Pairs with Smallest Sums
    // Given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
    // pair (u, v) consists of one element from first array and one element from second array.
    // Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
    //
    // Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    // Output: [[1,2],[1,4],[1,6]]
    //
    // O(k*log(n1)) 每一个结果数据都是经过log(n1)复杂度生成的特定位置
    // O(n1+k)      n1优先队列的空间 + k结果数据的空间
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // TODO. 优先队列在插入数据时会将数据排序并插入相应的位置
        // Priority queue to store pairs with the smallest sums, sorted by the sum
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Push the initial pairs into the priority queue
        // The sum and the index of the second element in nums2
        // 插入index是为了迭代计算下一个Pair组合值，添加值后再排序
        for (int x : nums1) {
            priorityQueue.offer(new int[]{x + nums2[0], 0});
        }

        while (k > 0 && !priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            int sum = pair[0]; // Get the smallest sum
            int index2 = pair[1]; // Get the index nums2

            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(sum - nums2[index2]);
            currentPair.add(nums2[index2]);
            result.add(currentPair);
            k--;

            // TODO. 每当取队列中的一个值，都能通过index移动计算下一个位置的值
            //  最后通过迭代入队，添加所有结果的排列
            if (index2 + 1 < nums2.length) {
                int newSum = sum - nums2[index2] + nums2[index2 + 1];
                priorityQueue.offer(new int[]{newSum, index2 + 1});
            }
        }
        return result;
    }
}
