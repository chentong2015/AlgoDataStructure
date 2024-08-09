package data_structure.heap;

import java.util.ArrayList;
import java.util.List;

public class LearnHeapQuestion {

    // Find K Pairs with Smallest Sums
    // Given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
    // pair (u, v) consists of one element from first array and one element from second array.
    // Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
    //
    // Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    // Output: [[1,2],[1,4],[1,6]]
    //
    // nums1 = [1,2,4,5,6], nums2 = [3,5,7,9]
    // Output = [[1,3],[2,3],[1,5]]  OK
    //          [[1,3],[2,3],[4,3]]  KO
    //
    // 由于是排序好的数据，因此无需遍历完所有的Pair组合
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int index1 = 0;
        int index2 = 0;
        int count = 0;

        int currentSum = nums1[index1] + nums2[index2];
        while (count < k) {
            if (nums1[index1] < nums2[index2]) {
                for (int i=0; i < index2; i++) {

                }
            }

        }


        return null;
    }


    // 使用双指针的计算方式会造成"结果对"的遗漏
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        List<List<Integer>> results = new ArrayList<>();
        // while (count < k) {
        //     List<Integer> sum = Arrays.asList(nums1[index1], nums2[index2]);
        //     results.add(sum);
        //     count++;
        //     if (index1 < nums1.length - 1 && index2 < nums2.length - 1) {
        //         if (nums1[index1 + 1] + nums2[0] < nums2[index2] + nums1[0]) {
        //             index1++;
        //         } else {
        //             index2++;
        //         }
        //     }
        // }
        return results;
    }

}
