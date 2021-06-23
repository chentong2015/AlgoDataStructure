package com.leetcode.interview_questions.top_hard_collection;

public class SortingSearching {

    // TODO: 双重二分法
    // Median of Two Sorted Arrays 求中间值(有奇偶之分, 和两个数组的大小有关)，不是平均值 !!
    // Given two sorted arrays nums1 and nums2, return the median of the two sorted arrays
    // nums1=[1,2], nums2=[3,4] -> 2.50000
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 正确理解：必须满足时间复杂度 O(log(m+n)) ==> 注意：log(m)+log(n)=log(m*n)
        //         并不需要合并两个数组，只需要根据数组元素的大小比较位置，即可找到排序在整个数组中的中间值(可以需要取两个值的平均) !!
        //         1  3  5
        //         2  6  8  9
        //         1 2 3 5 6 8 9 ==> 5 取总排序下的最中间值，如何找到?
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int half = (nums1.length + nums2.length + 1) / 2;
        int low = 0;
        int high = nums1.length;
        int mid1 = 0, mid2 = 0;
        while (low <= high) {
            mid1 = low + (high - low) / 2; // 取第一个数组的中间位置, 避免int类型的值溢出 !!
            mid2 = half - mid1;
            int left1 = mid1 == 0 ? Integer.MAX_VALUE : nums1[mid1 - 1];
            int right1 = mid1 == nums1.length ? Integer.MAX_VALUE : nums1[mid1];
            int left2 = mid2 == 0 ? Integer.MAX_VALUE : nums2[mid2 - 1];
            int right2 = mid2 == nums2.length ? Integer.MAX_VALUE : nums2[mid2];
            if (right1 < left2) {        // 如果左边的最大值还小于右边的最小值，则上升左边的二分位置
                low = mid1 + 1;
            } else if (right2 < left1) { // 如果右边的最大值值小于左边的最小值，则下降右边的二分位置
                high = mid1 - 1;
            } else {
                break;
            }
        }
        int l1 = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
        int r1 = mid1 == nums1.length ? Integer.MAX_VALUE : nums1[mid1];
        int l2 = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
        int r2 = mid2 == nums2.length ? Integer.MAX_VALUE : nums2[mid2];
        if ((nums1.length + nums2.length) % 2 == 0) {
            return ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2; // 左边取最大，后边取最小
        } else {
            return Math.max(l1, l2);
        }
    }

}
