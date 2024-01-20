package questions.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingSearching {

    // Merge Sorted Array
    // Merge nums2 into nums1 as one sorted array
    // nums1 has a size equal to m + n such that it has enough space to hold additional elements from nums2
    // nums1 = [1,2,3,0,0,0], m = 3   -> [1,2,2,3,5,6]
    // nums2 = [2,5,6],       n = 3
    //
    // 充分利用m, n的信息控制元素位置的移动，从后往前进行判断，依次提取要填入的值
    // O(m+n) 最佳的复杂度 O(1)
    public void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;
        int right1 = m - 1;
        int right2 = n - 1;
        if (m == 0) {
            while (right2 >= 0) {
                nums1[last--] = nums2[right2--];
            }
        }

        while (right1 >= 0 && right2 >= 0) {
            if (nums1[right1] < nums2[right2]) {
                nums1[last--] = nums2[right2--];
                // 由于nums2数组更短，当right2移动到最左端时候，nums1中存储的数据就是结果
            } else {
                nums1[last--] = nums1[right1--];
                // 当nums1中数据移动完成后，将nums2中的数据整体迁移
                if (right1 < 0) {
                    while (right2 >= 0) {
                        nums1[last--] = nums2[right2--];
                    }
                }
            }
        }
    }

    // Find K Pairs with Smallest Sums
    // Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    // Output: [[1,2],[1,4],[1,6]]
    //
    // nums1 = [1,2',4,5,6], nums2 = [3',5,7,9]
    // Output = [[1,3],[2,3],[1,5]]  OK
    //          [[1,3],[2,3],[4,3]]  KO
    //
    // 使用双指针的计算方式会造成"结果对"的遗漏
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
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
