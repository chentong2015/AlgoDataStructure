package com.leetcode.basic_theory_introduction.base_sorting.quick_sort_select;

// Quick Sort(分治法)快速排序, 有时是最佳解
// 时间复杂度 O(nlog(n)) ~ O(n²) 复杂度的推导需要严格的数学公式计算
// 通过基准的随机算法可以避免O(n²)的复杂度：完全颠倒的排序，每一递归一个方法，方法中遍历剩下的全部 => O(n*n)

// 空间复杂度 O(log(n)) ~ O(n)
// 每次都完全平分，复杂度由递归调用造成的栈内存空间的开销
public class QuickSort {

    // 快速排序使用分治法来把一个串list分为两个子串sub-lists
    // 1. 从数列中挑出一个元素，称为“pivot基准”
    // 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）
    // 3. 递归地把划分出来的分区都做相同的排序：挑选基准，然后左右放置大小值 !!
    public int[] quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            quickSort(nums, left, partitionIndex - 1); // 左边分区
            // partitionIndex位置一定是比左边分区的都大，比右边分区的都小
            quickSort(nums, partitionIndex + 1, right); // 右边分区
        }
        return nums;
    }

    // TODO: 注意这里选基准点的算法可能导致不同的时间复杂度
    // 1. 直接取第一个元素的值，作为pivot基准值，用来划分左右
    // 2. 在指定的范围中，随机选择一个值，用来划分左右
    public int partition(int[] nums, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, pivot, index - 1); // 将pivot移动到前段分区的最后一个位置
        return index - 1;                   //  index-1 才是pivot的位置点
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
