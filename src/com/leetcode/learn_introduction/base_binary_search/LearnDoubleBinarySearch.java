package com.leetcode.learn_introduction.base_binary_search;

import com.leetcode.learn_introduction.base_binary_search.model.ItemDoubleBS;

// TODO: 使用双重二分法进行查找
public class LearnDoubleBinarySearch {

    // Median of Two Sorted Arrays
    // Given two sorted arrays nums1 and nums2, return the median of the two sorted arrays
    // 求中位数(有奇偶之分, 和两个数组的大小有关)，不是平均值
    // nums1=[1,2], nums2=[3,4] -> 2.50000
    private int[] nums1; // TODO: 使用时，确保nums1的长度更短 !! 值需要对nums1进行二分法
    private int[] nums2;

    public double findMedianSortedArrays() {
        // 正确理解：必须满足时间复杂度 O(log(m+n)) ==> 注意：log(m)+log(n)=log(m*n)
        //         并不需要合并两个数组，只需要根据数组元素的大小比较位置，即可找到排序在整个数组中的中间值(可以需要取两个值的平均) !!
        int half = (nums1.length + nums2.length + 1) / 2;
        int low1 = 0;
        int high1 = nums1.length;
        ItemDoubleBS index1 = new ItemDoubleBS();
        ItemDoubleBS index2 = new ItemDoubleBS();
        while (low1 <= high1) {
            // mid1 + mid2 = half; 两个数组中取的二分的位置，和必须为指定的长度，在重排之后在half位置的值就是要找的值 !!
            index1.middleIndex = low1 + (high1 - low1) / 2;
            index2.middleIndex = half - index1.middleIndex;
            setLeftMiddleValue(index1, nums1);
            setLeftMiddleValue(index2, nums2);
            if (index1.middleValue < index2.leftValue) {        // 如果左边的最大值 < 右边的最小值，则上升左边的二分位置
                low1 = index1.middleIndex + 1;
            } else if (index2.middleValue < index1.leftValue) { // 如果右边的最大值 < 左边的最小值，则下降右边的二分位置
                high1 = index1.middleIndex - 1;
            } else {                                // 当出现(left1,right1)和(left2,right2)区间交叉的时候，则满足条件
                break;
            }
        }
        // TODO: 判断结束二分法的条件，对于nums1的数组搜索完成，根据middleIndex1, middleIndex2再次计算
        return findMedianValue(index1, index2);
    }

    private void setLeftMiddleValue(ItemDoubleBS index, int[] nums) {
        if (index.middleIndex == 0) {
            index.leftValue = Integer.MAX_VALUE;
        } else {
            index.leftValue = nums[index.middleIndex - 1];
        }
        if (index.middleIndex == nums.length) {
            index.middleValue = Integer.MAX_VALUE;
        } else {
            index.middleValue = nums[index.middleIndex];
        }
    }

    private double findMedianValue(ItemDoubleBS index1, ItemDoubleBS index2) {
        ItemDoubleBS resultIndex1 = new ItemDoubleBS(index1.middleIndex);
        setLeftMiddleValue(resultIndex1, nums1);
        ItemDoubleBS resultIndex2 = new ItemDoubleBS(index2.middleIndex);
        setLeftMiddleValue(resultIndex2, nums1);
        // 如果是总长度是偶数，则需要取两个值，左侧取大值，右侧取小值
        if ((nums1.length + nums2.length) % 2 == 0) {
            int leftValue = Math.max(resultIndex1.leftValue, resultIndex2.leftValue);
            int rightValue = Math.min(resultIndex1.middleValue, resultIndex2.middleValue);
            return ((double) leftValue + rightValue) / 2;
        } else {
            return Math.max(resultIndex1.leftValue, resultIndex2.leftValue);
        }
    }
}
