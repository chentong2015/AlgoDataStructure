package com.leetcode.basic_theory_introduction.base_binary_search;

// TODO: Binary Search Template I 模板
// Standard Binary Search Template
// Search for an element or condition which can be determined by accessing a single index
// 标准二分法, 只需要访问单一的index就能确定, 不需要考虑相邻或者相关的index
public class BinarySearchTemplate1 {

    // 1. 注意起使位置的设置，确定范围                   ==> 根据数组或者数字范围确定
    // 2. 注意while循环的结束条件                      ==> 判断是否能相等，是否需要间隔，结束的条件是什么
    // 3. TODO: 计算中间位置时，注意避免值的溢出overflow  ==> 固定写法
    // 4. 注意左右index位置的更新 !!                   ==> 在left < right循环中必须更新
    public int binarySearch1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {                  // 相等条件, 能确定到搜索同一个位置, 后面的left，right其中之一必须移动
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {            // 常规的模板是：左边left右边right均可以上下移动 !!
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // End Condition: left > right
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Guess Number Higher or Lower
    // I pick a number from 1 to n. You have to guess which number I picked
    // Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess
    // Input: n = 10, pick = 6  -> 6 通过猜测找出提前选择的值
    public int guessNumber(int n) {
        if (n == 1) return 1;
        int low = 1;
        int high = n;
        while (low < high) {                      // 这里不需要间隔位置，可以相差一个值
            int middle = low + (high - low) / 2;
            if (guess(middle) == 0) return middle;
            if (guess(middle) < 0) {
                high = middle;                    // 当while(low<=high)条件是，这里可以使用high = middle - 1;
            } else {
                low = middle + 1;                 // 必须将low低位往前进，避免下次计算middle时，回到原来的low值，造成循环 !!
            }
        }
        return low; // 出循环条件: low=high 说明置于统一位置，可以确定
    }

    private int guess(int num) {
        // pre-defined API
        // -1: The number I picked is lower than your guess (i.e. pick < num).
        // 1: The number I picked is higher than your guess (i.e. pick > num).
        // 0: The number I picked is equal to your guess (i.e. pick == num).
        return 0;
    }

    // TODO: 如果有两个有序排列的数据，那么就使用两次二分搜索 O(2log(n))
    // Search in Rotated Sorted Array
    // [nums[k], nums[k+1], ..., nums[n-1],  nums[0], nums[1], ..., nums[k-1]] (0-indexed)
    // [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2]
    // Given the array nums after the rotation and an integer target,return the index, or -1 if it is not in nums
    // nums = [4,5,6,7, 0,1,2], target = 0 -> 4
    // nums = [4,5,6,7, 0,1,2], target = 3 -> -1
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int minIndex = BinarySearchTemplate2.findMinIndex(nums);
        if (nums[minIndex] == target) return minIndex;
        int low = nums[minIndex] < target ? minIndex : 0;                    // 只有两种可能区间[0, minIndex-1] & [minIndex, length-1]
        int high = nums[minIndex] < target ? nums.length - 1 : minIndex - 1; // 中间minIndex作为划分的分水岭，左边一个是最大值 !
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (nums[middle] == target) return middle;
            if (nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return -1;
    }

    // TODO: 如果需要找两个位置"起点和结尾"，左右分开找，使用两次二分搜索将区间位置找出来 !!
    // Search for a Range
    // Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
    // If target is not found in the array, return [-1, -1]
    // nums = [5,7,7,8,8,10], target = 8  -> [3,4] 如果找到，则开始index和结束index一定是相邻连续的，可能连续有好几个值 !!
    // nums = [1,2,3],        target = 2  -> [1,1] 开始和结束的位置可能重合
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;
        result[0] = findStartPosition(nums, target);
        result[1] = findEndPosition(nums, target);
        return result;
    }

    // 查看start位置左边时候还有target，把right设置在左边，下一轮计算middle的时候会下移动一个位置
    // 只有在找到target的时候，start值才会被修改，否则为初始值
    public int findStartPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int start = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                start = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return start;
    }

    // 查看end位置右边的位置，把left右移，下一轮计算middle的时候会上移动一个位置
    // 只有在找到target的时候，end值才会倍修改，否则为初始值
    public int findEndPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int end = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                end = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return end;
    }
}
