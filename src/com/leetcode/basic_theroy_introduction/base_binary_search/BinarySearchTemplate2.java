package com.leetcode.basic_theroy_introduction.base_binary_search;

// TODO: Binary Search Template II 模板
// Search for an element or condition which requires accessing the current index and
// its immediate right neighbor's index in the array
// 需要访问index和它前或后index(或相关的index/right)，通过while(left<right)循环来逼近最终的值
public class BinarySearchTemplate2 {

    // 1. int right = nums.length; 初始范围在数组的下标范围之外
    // 2. if(left != nums.length)  结束循环之后会做判断处理
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // End Condition: left == right
        if (left != nums.length && nums[left] == target) return left;
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // First Bad Version
    // n versions [1, 2, ..., n] and find out the first bad one, which causes all the following ones to be bad
    // 找到一组产品中第一次次品：该index位置是次品，而index-1前面相邻的位置是正品
    // 1 2 3 4 0 0 0 0
    public int firstBadVersion(int n) {
        // 正确理解：1. 在二分法查找的过程中，必须判断两个index位置，来确定最后的移动方向
        int low = 1;
        int high = n;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (isBadVersion(middle)) {
                if (!isBadVersion(middle - 1)) return middle;
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return low; // 出循环条件: low=high, (low-1)位置正品 < low第一个次品, high位置次品 ... 都是次品
    }

    private boolean isBadVersion(int n) {
        return n >= 4;
    }

    // TODO: 注意"Recursive Binary Search"递归二分法会带来额外的空间复杂度的开销 !!
    // Find Peak Element 提取"波峰"元素
    // A peak element is an element that is strictly greater than its neighbors
    // nums[-1] = nums[n] = -∞ 开头和末尾始终是最小值，可以返回答案中任何一个index
    // nums[i] != nums[i + 1]  约束任意相邻的两个值都不相等
    // left = 0; right = nums.length - 1;
    public int findPeakElementRecursive(int[] nums, int left, int right) {
        // 正确理解：1. 递归的算法逻辑是每一次只取剩下长度的二分之一  O(log2(n)) O(log2(n))
        if (left == right) return left;
        int middle = left + (right - left) / 2;
        if (nums[middle] > nums[middle + 1]) {
            return findPeakElementRecursive(nums, left, middle); // 如果是下降趋势，则取左边的部分继续查找
        }
        return findPeakElementRecursive(nums, middle + 1, right);
    }

    // 正确理解：2. Iterative Binary Search
    // nums = [1,2,1,3,5,6,4] -> index = 1, index = 5 共有2个波峰的位置
    public int findPeakElementBinarySearch(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < nums[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left; // 出循环条件: left=right当两个位置坐标相遇，说明left-1 < (left = right) > right -1 确定中间位置是Peak !!
    }

    // Find Minimum in Rotated Sorted Array
    // nums = [0,1,2,4,5,6,7] -> [4,5,6,7,0,1,2] if it was rotated 4 times
    // Given the sorted rotated array nums of unique elements, return the minimum element of this array
    // nums = [4,5,6,7,0,1,2] -> 0
    public static int findMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return nums[left]; // 出循环条件: left=right当两个位置坐标相遇, 说明前面一个位置是大值，后面一个位置也是大值 !!
    }
}
