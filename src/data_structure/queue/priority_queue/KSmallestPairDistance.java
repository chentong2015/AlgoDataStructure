package data_structure.queue.priority_queue;

public class KSmallestPairDistance {

    // Find K-th Smallest Pair Distance
    // The distance of a pair of integers a and b is defined as the absolute difference between a and b.
    //
    // Given an integer array nums and an integer k, return the kth smallest distance
    // among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
    //
    // nums = [1,3,1], k = 1  -> the 1st smallest distance pair is (1,1), and its distance is 0.
    // (1,3) -> 2
    // (1,1) -> 0
    // (3,1) -> 2
    //
    // O(n*n*log(k))
    // O(n*n + k)
    public int smallestDistancePair(int[] nums, int k) {

        // 将所有组合的差值计算出来，然后对数据优先排序
        // 最后取第k个最小位置的结果
        return 0;
    }
}
