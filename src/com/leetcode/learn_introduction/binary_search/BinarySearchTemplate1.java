package com.leetcode.learn_introduction.binary_search;

// TODO: Binary Search Template I 模板
// Standard Binary Search Template
// Search for an element or condition which can be determined by accessing a single index
// 标准二分法，只需要访问单一的index就能确定
public class BinarySearchTemplate1 {

    // Template 模板 01
    // 1. 注意起使位置的设置，确定范围                   ==> 根据数组或者数字范围确定
    // 2. 注意while循环的结束条件                      ==> 判断是否能相等，是否需要间隔，结束的条件是什么
    // 3. TODO: 计算中间位置时，注意避免值的溢出overflow  ==> 固定写法
    // 4. 注意左右index位置的更新 !!                   ==> 在left<right循环中必须更新
    public int binarySearch1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {                  // 相等条件, 能确定到搜索同一个位置, 后面的left，right其中之一必须移动
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;                  // 常规的模板是：左边left右边right均可以上下移动 !!
            } else {
                right = mid - 1;
            }
        }
        return -1; // End Condition: left > right
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Sqrt(x)  0<=x<=2^31-1
    // Given a non-negative integer x, compute and return the square root of x
    // 通过二分法求平方根，只保留正数的部分
    // 1. x = 8 -> 2.82842 -> 2     必须去掉正数后面的小数
    // 2. 2147395599       -> 46339 对于大数, 需要考虑乘积是否会出现值的溢出, 直接比较会出现值的截取, 判断不准 !!
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

    // TODO: 如果有两个有序排列的数据，那么就使用两次二分搜索，并不影响时间复杂度 !!
    // Search in Rotated Sorted Array
    // [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed)
    // [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2]
    // Given the array nums after the rotation and an integer target,return the index , or -1 if it is not in nums
    // nums = [4,5,6,7, 0,1,2], target = 0 -> 4
    // nums = [4,5,6,7, 0,1,2], target = 3 -> -1
    // [1,3]  3
    //  0 1
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int minIndex = findMinIndex(nums);
        if (nums[minIndex] == target) return minIndex;
        int low = nums[minIndex] < target ? minIndex : 0; // 只有两种可能区间[0, minIndex-1] & [minIndex, length-1]
        int high = nums[minIndex] < target ? nums.length - 1 : minIndex - 1; // 中间minIndex作为划分的分水岭，左边一个是最大值 !
        while (low <= high) {                             // 这里可以取相等位置，测试搜索到指定的值
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

    private int findMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {            // 当中间的值大于最右边的值，说明前面一段都是大数，最小值出现在后面
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;  // 出循环条件: low=high, 则该位置必然是最小值
    }
}
