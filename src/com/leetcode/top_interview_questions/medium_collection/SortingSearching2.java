package com.leetcode.top_interview_questions.medium_collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 算法2：波峰的特征算法，二分法的使用(迭代和递归)
 * 算法3：合并特征区间所使用的数据结构
 */
public class SortingSearching2 {

    // Find Peak Element 提取"波峰"元素
    // A peak element is an element that is strictly greater than its neighbors
    // nums[-1] = nums[n] = -∞ 开头和末尾始终是最小值，可以返回答案中任何一个index
    // nums[i] != nums[i + 1] for all valid i 约束任意相邻的两个值都不相等
    public int findPeakElement(int[] nums) {
        // 测试理解: 1. 遍历数组中，找到第一个后面的位置是下降的点，如果前面没有下降的点，则最后一个位置就是波峰
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

    // 正确理解：1. "Recursive Binary Search" 递归2分法查找: Log级别的复杂度, 始终需要左右两个标识符做计算 !!
    //            递归的算法逻辑是每一次只取剩下长度的二分之一, 如果是上升趋势，则peak在后面，反之在前面
    //            O(log2(n))  O(log2(n)) 递归调用方法的深度，额外开辟的内存空间
    public int findPeakElementRecursive2(int[] nums, int left, int right) { // left = 0; right = nums.length - 1;
        if (left == right) {
            return left;
        }
        int middle = (left + right) / 2;
        if (nums[middle] > nums[middle]) {
            return findPeakElementRecursive2(nums, left, middle);
        }
        return findPeakElementRecursive2(nums, middle + 1, right); // middle+1 表示提取它的上面部分的数据
    }

    // 正确理解：2. "Iterative Binary Search" 迭代2分法查找, 典型的while(left<right)
    public int findPeakElementIterative(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] > nums[middle + 1]) { // 这里的判断不是为了将index直接找出来，而是为了逼近于要找的值 !!
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // Merge Intervals
    // Given an array of intervals where intervals[i] = [start, end], start <= end 整合区间
    // intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
    public int[][] merge(int[][] intervals) {
        // 测试理解：1. 每添加一个新区间，就更新前面所存储的区间段 O(n²)  O(n)

        // 正确理解：1. 将所有区间按照start值排序，然后按照下面的处理方式运算 O(nlog(n)) < O(n²) ===> 优先考虑使用排序 !!
        //            使用LinkedList保证添加的区间从低到高，没有交叉
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {  // 如果不需要合并区间，则直接添加
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]); // 判断是否更新区间的上边界
            }
        }
        return merged.toArray(new int[merged.size()][]);  // 直接从LinkedList到Array转换，提供数组的声明，不需要从List中逐个提取
    }
}
