package data_structure.hash_table;

import java.util.HashMap;

public class LearnHashTable5 {

    // TODO. 是否能够使用Stack来优化获取nums2中的结果?
    // Next Greater Element I
    // Two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
    // For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
    // and determine the next greater element of nums2[j] in nums2.
    //
    // Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    // Output: [-1,3,-1]
    // Explanation: The next greater element for each value of nums1 is as follows:
    // - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    // - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
    // - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    //
    // 注意前提条件，一定是离散的数据，且一定是子集的关系
    //    5 4 2 1 3 7 8  从后往前遍历的效果并不好
    // -> 7 7 3 3 7 8 -1 假如遍历的时候只找出比前面部分数据大的值，该如何设置 ?
    //
    // O(N*N)+O(M) O(N)+O(M) 只能够把查找index的复杂度优化，使用hashmap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];

        // TODO. <element, index> 为了可以更快的定位指定元素的答案
        // 1. Store num2's elements for searching faster by hash map
        HashMap<Integer, Integer> num2Map = new HashMap<>();
        for (int index = 0; index<nums2.length; index++) {
            num2Map.put(nums2[index], index);
        }

        // TODO. 只能通过O(N*N)的时间复杂度来找到Next Greater Element
        // 2. Compute the next greater element of nums2, replace the old values
        for (int index=0; index<nums2.length; index++) {
            boolean foundNextGreaterElement = false;
            for (int right=index+1; right<nums2.length; right++){
                if (nums2[index] < nums2[right]) {
                    foundNextGreaterElement = true;
                    nums2[index] = nums2[right];
                    break; // Find only the next greater element and break
                }
            }
            if (!foundNextGreaterElement) {
                nums2[index] = -1;
            }
        }

        // 3. Loop num1's elements to get answer
        for (int index = 0; index < nums1.length; index++) { // O(M)
            int indexNums2 = num2Map.get(nums1[index]); // O(1)
            ans[index] = nums2[indexNums2] ;
        }
        return ans;
    }
}
