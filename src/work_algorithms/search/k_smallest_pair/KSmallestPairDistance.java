package work_algorithms.search.k_smallest_pair;

import java.util.Arrays;

public class KSmallestPairDistance {

    // TODO: 对数据差值的在距离进行二分法，并且在二分法结束之后再返回结果
    // Find K-th Smallest Pair Distance
    // Given an integer array nums and an integer k
    // The distance of a pair of integers a and b is defined as the absolute difference between a and b
    // Return the kth smallest distance among all the pairs nums[i] and nums[j]
    // where 0 <= i < j < nums.length
    //
    // nums = [1,3,1], k = 1  -> {0,2,2}         -> 0 第k个最小距离
    // nums = [1,3,5,6,9,10]  -> (0->9)          -> 1 第3个最小距离
    //      -> 0' 1 1 2 2 3 3 4 4 4 5 5 6 7 8 9 距离范围
    //
    // O(nlog(n) + nlog(m)) n为数组的长度，m为最大的差值距离
    // O(1)
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);  // 排序时间复杂度nlog(n)
        int lowDis = 0;
        int highDis = nums[nums.length - 1] - nums[0];
        while (lowDis < highDis) { // 二分法时间复杂度log(m)
            int midDis = lowDis + (highDis - lowDis) / 2;
            int count = countDistanceLessThanMid(nums, midDis);
            if (count >= k) {
                highDis = midDis;
            } else {
                lowDis = midDis + 1;
            }
        }
        // End condition: lowDis = highDis;
        // low值前面有(k-1)个更小的距离
        return lowDis;
    }

    // TODO. 参考滑动窗口统计距离长度 // 统计复杂度O(n)
    private int countDistanceLessThanMid(int[] nums, int midDis) {
        return 1;
    }
}
