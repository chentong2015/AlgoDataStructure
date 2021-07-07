package com.leetcode.basic_theroy_introduction.base_binary_search;

import com.leetcode.basic_theroy_introduction.base_binary_search.model.ItemDoubleBS;

// Median of Two Sorted Arrays
// Given two sorted arrays nums1 and nums2, return the median of the two sorted arrays
// nums1=[1,2], nums2=[3,4] -> 2.50000
// 1. 中位数的出现位置index坐标一定在两个数组总长的一半位置(可能一个从一个数组中多取一点，另外一个少取一点)
// 2. 中位数的目的式将元素平衡的划分成两个部分，使得左边部分的最大值<=右边部分的最小值
public class LearnBinarySearch2 {

    // TODO: 二分法查找的变式，对一个数组的查找同时影响第二个数组的查找位置(位置的和为指定的特征值)
    // X1,X2,X3 .... Xn-2,Xn-1,Xn         (Xi-1, Xi)
    // 对第一数组进行二分，在某位置的(两个值)和第二个数组(指定位置的两个值)有交集，则要找的数据就在这4个值中
    // Y1,Y2,Y3 ....... Yn-2,Yn-1,Yn      (Yj-1, Yj)

    // 划分出来最后需要满足的条件，必须是均匀的两个部分，同时值的大小约束 !!
    //       left_part          |        right_part
    // A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
    // B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
    // 1. len(left_part) == len(right_part)
    // 2. max(left_part) <= min(right_part)
    private int[] nums1;
    private int[] nums2;

    public double findMedianSortedArrays() {
        int half = (nums1.length + nums2.length + 1) / 2;
        int low1 = 0;
        int high1 = nums1.length;
        ItemDoubleBS index1 = new ItemDoubleBS();
        ItemDoubleBS index2 = new ItemDoubleBS();
        while (low1 <= high1) {
            index1.middleIndex = low1 + (high1 - low1) / 2;
            index2.middleIndex = half - index1.middleIndex;
            setLeftMiddleValue(index1, nums1);
            setLeftMiddleValue(index2, nums2);
            if (index1.middleValue < index2.leftValue) {        // 如果左边的最大值 < 右边的最小值，则上升左边的二分位置
                low1 = index1.middleIndex + 1;
            } else if (index2.middleValue < index1.leftValue) { // 如果右边的最大值 < 左边的最小值，则下降右边的二分位置
                high1 = index1.middleIndex - 1;
            } else {                            // 当出现(left1,right1)和(left2,right2)区间交叉的时候，则满足条件
                break;
            }
        }
        return findMedianValue(index1, index2); // End condition: low1 > high1
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
