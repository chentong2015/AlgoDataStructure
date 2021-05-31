package com.leetcode.learn_introduction.binary_search;

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

    // Find K Closest Elements

}
