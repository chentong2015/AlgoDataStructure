package com.leetcode.top_interview_questions.easy_collection;

public class SortingSearching {

    // Merge Sorted Array
    // Merge nums2 into nums1 as one sorted array
    // nums1 has a size equal to m + n such that it has enough space to hold additional elements from nums2
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 测试理解：由于是从小到大排序的，所以可以使用两个index左边
        //         1. 每将num2中的值插入到num1中后，num1中插入位置后面的值需要往后移动 ===> 时间复杂度过高 !!

        // 正确理解：1. 在排序的数组中添加值，需要一档后面的值，必须控制移动最少的位置(操作的次数最少)
        //            O(n*m) 最坏的情况是所有第二个数组中的值都被添加到第一数组的开头  O(1)
        int index1 = 0;
        int index2 = 0;
        int count = m; // 统计已经添加到nums1中的数量值
        while (index2 < n) {
            while (index1 < count) {
                if (nums2[index2] <= nums1[index1]) {
                    for (int i = count; i > index1; i--) { // 移动的总的位置不要使用n+m
                        nums1[i] = nums1[i - 1];
                    }
                    nums1[index1] = nums2[index2];
                    index1++;
                    count++;
                    break;
                }
                index1++;
            }
            if (index1 == count) { // 如果是已经移动到末尾了，则需要直接将值添加到末尾
                nums1[index1] = nums2[index2];
                count++;
            }
            index2++;
        }
    }

    // First Bad Version 用最小的次数找到指定的目标
    public int firstBadVersion(int n) {
        // 测试理解：1. 第一个次品, 等效于找到前一个是正品，接着的后一个是次品 n=false, n+1=true

        // 正确理解：1. 标准二分法查找: 每次取剩下部分的一半，"当找到次品后，判断前一个是否是正品"，如果是则退出
        //         2. TODO: 注意overflow溢出, 两个int值的和可能超出int的最大值，导致计算出错, 除非使用python这种动态类型的语言 !!!
        int low = 1;
        int high = n;
        while (low < high) {                    // 当low=high，说明就是第一次品的位置
            int index = low + (high - low) / 2; // 改计算的结果一定小于high，所以不会溢出 !!
            if (isBadVersion(index)) {
                high = index;                   // 如果index是次品，那么有可能是第一个次品，所以不能减1
            } else {
                low = index + 1;                // 如果index位置是正品，那么第一个次品出现的位置一定在index后面
            }
        }
        return low;
    }

    private boolean isBadVersion(int n) {
        return n >= 4;
    }

}
