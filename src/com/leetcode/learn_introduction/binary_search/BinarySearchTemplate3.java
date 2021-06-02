package com.leetcode.learn_introduction.binary_search;

import java.util.ArrayList;
import java.util.List;

// TODO: Binary Search Template III 模板
// Search for an element or condition which requires
// accessing the current index and its immediate left and right neighbor's index in the array
// 需要访问index以及它的left和right相邻的位置，同时要考虑3个位置处的数据 
public class BinarySearchTemplate3 {

    // Template 模板 03
    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // End Condition: left + 1 == right 注意出循环的条件，left和right有间隔，需要做左右两边判断
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Sqrt(x)  0<=x<=2^31-1
    // Given a non-negative integer x, compute and return the square root of x
    // 通过二分法求平方根，只保留正数的部分
    // 1. x=8 -> 2.82 -> 2     必须去掉正数后面的小数
    // 2. 2147395599  -> 46339 对于大数, 需要考虑乘积是否会出现值的溢出, 直接比较会出现值的截取, 判断不准 !!
    public static int mySqrt(int x) {
        if (x <= 1) return x;
        int low = 1;
        int high = x;
        while (low + 1 < high) {                  // 注意这里的边界条件，需要间隔一个位置
            int middle = low + (high - low) / 2;
            if (middle == x / middle) {           // 先算除法，再来比较，避免乘法造成的值溢出 !!
                return middle;
            } else if (middle > x / middle) {
                high = middle;                    // 这里不需要浮动+1和-1，浮动之后很有可能错过要中的值 !!
            } else {
                low = middle;
            }
        }
        return low; // 出循环条件: low+1=high, 说明平方根在low和high之间
    }

    // TODO: 用二分法搜索K个相邻位置的区间，判断[index, index+k]li两端的临界条件 !!
    // Find K Closest Elements
    // Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array
    // The result should also be sorted in ascending order
    // |a-x|<|b-x| 或 |a-x|==|b-x| and a<b           两种情况说明a比b更加的接近x, 且x不一定在数组中存在
    // arr = [1,2,3,4,5], k = 4, x = 3 -> [1,2,3,4]  最接近3的4个数，并且有序
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 正确理解：1. 找k个元素区间的"左边起使位置"，在区间[0, arr.length - k]范围中，同时减少了二分法查找的时间复杂度 !!
        //            通过left & right标识无限的接近"左边起使位置"
        //            O(log(n-k)+k)  O(1)
        int left = 0;
        int right = arr.length - k;                // 左边起点的最大位置，给后面的k个位置留下区间
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) { // [mid] ~ [mid + k] 在这区间片段中，如果[mid + k]右边更接近，则把left位置移动上来
                left = mid + 1;                    // 反之如果[mid]左边更加接近，则[mid + k]后面的全部的数据都不用再考虑，则把right降低
            } else {
                right = mid;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    // 找到数组中一个最接近(有可能相等)某值的值(或位置)
    private static int findClosestValue(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) return nums[mid];
            if (nums[mid] < x) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (Math.abs(nums[low] - x) <= Math.abs(nums[high] - x)) {
            return nums[low];
        }
        return nums[high];
    }
}
