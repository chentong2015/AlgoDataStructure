package work_algorithms.search;

import java.util.Arrays;

public class LearnBinarySearch1 {

    // Peak Index in a Mountain Array
    // arr.length >= 3
    // An array arr is a mountain if the following properties hold
    // arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
    // return the index i in O(log(arr.length)) time complexity.
    //
    // Input: arr = [0,10,5,2] -> 1
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1;
        int right = arr.length - 2;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] > arr[middle-1] && arr[middle] > arr[middle+1]) {
                return middle;
            } else if (arr[middle] > arr[middle-1]) {
                left = middle + 1; // 这里一定要往后移动一位，否则会造成无限循环，middle的值不会改变
            } else {
                right = middle;
            }
        }
        return left;
    }

    // TODO: Binary Search + Sliding Windows 在0和最大距离之间使用二分法，和元素本身的值没有关系 !!
    // Find K-th Smallest Pair Distance
    // Given an integer array nums and an integer k
    // The distance of a pair of integers a and b is defined as the absolute difference between a and b
    // Return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length
    // nums = [1,3,1], k = 1  -> {0,2,2}         -> 0 第k个最小距离
    // nums = [1,3,5,6,9,10]  -> (0->9) 距离范围   -> 1 第3个最小距离
    //                            0' 1 1 2 2 3 3 4 4 4 5 5 6 7 8 9 基础算法O(n^2)可以将所有的距离穷举出来
    public int smallestDistancePair(int[] nums, int k) {
        // O(nlog(n) + nlog(m)) n为数组的长度，m为最大的差值距离

        // 排序是为了方便在使用Sliding Windows的时候计算方便
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countDistanceLessThanMid(nums, mid);
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // 这里的low值刚好是要找的距离值，它的前面有(k-1)个更小的距离
        return low; // End condition: low = high;
    }


    // number of pairs with distance <= mid 统计所有小于等于mid值的pairs对
    // 滑动时，右侧每增加一个数，产生的首位距离值是在上升的，从左侧拿掉一个值，首尾的距离值会降低 !!
    // [1,3,5,6,9,10]
    // 0         9
    //   (2) 4
    // l
    //    r
    private int countDistanceLessThanMid(int[] nums, int mid) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > mid) {
                left++;            // 滑动窗口从左侧出数据
            }
            count += right - left; // index的距离有多少，就有多少个更小的距离数 !!
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // TODO: Binary Search 在最大值和总值之间使用二分法, 结果一定是其中的一个sum值
    // Split Array Largest Sum
    // Given an array nums which consists of non-negative integers and an integer m
    // Split the array into m non-empty "continuous sub-arrays" 切分出来的数字必须是连续且非空的
    // Minimize the largest sum among these m sub-arrays
    // 在所有的划分可能中，每一种划分有m个子数组，其中有一个"元素和最大值"，求所有这些划分可能中的最小"最大值" !!
    // nums = [7,2,5,10,8], m = 2 -> [7,2,5] and [10,8] -> 18   共4种分法：1+4, 2+3, 3+2(最小的一种分法), 4+1
    // nums = [1,2,3,4,5],  m = 2 -> [1,2,3] and [4,5]  -> 9    TODO：通过数学方法来穷举所有的划分可能 !!
    // nums = [1,4,4],      m = 3 -> [1] [4] and [4]    -> 4
    public int splitArray(int[] nums, int numSplit) {
        // 由于数组元素固定，结果必须是连续的子字符串，所以不能对数组进行排序
        // O(nlog(M)) n是元素的个数，M是最大值和总值之间的距离 O(1)

        int sum = 0;  // sum的总和有可能超过int的最大值，使用长整型 !!
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
            sum += num;
        }
        if (numSplit == 1) return sum;

        int low = max;
        int high = sum;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (valid(mid, nums, numSplit)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low; // End condition: low > high
    }

    // Sliding Windows: 在当个最大值和总值之间进行二分，每一个值都是一个连续片段和的上限
    // 1. 以二分位置作为目标结果和，划分数组成指定的数量m，如果这个目标值做不到准确的划分，则目标值不对
    // 2. 由于要取划分出来的子数组中的最小和，则需要将每个划分"最大可能"的接近目标值，以保证平衡 !!
    // 1 4 4, 3
    // 4 - 9
    //   6 = target
    // 4 5
    // 4 4
    // 4 3
    // low > high
    public boolean valid(int targetSum, int[] nums, int numSplit) {
        int count = 1;
        int total = 0;
        for (int num : nums) {
            total += num;
            if (total > targetSum) {  // 如果超过了目标sum，则开始做下一次的划分
                count++;
                if (count > numSplit) return false;
                total = num;   // 前面的部分去掉，从当前这个数num重新开始累计总和sum
            }
        }
        return true;
    }
}
