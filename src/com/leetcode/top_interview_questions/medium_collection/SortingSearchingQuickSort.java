package com.leetcode.top_interview_questions.medium_collection;

/**
 * 金典算法: Quick Sort
 * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序
 * .
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）
 * 1. 从数列中挑出一个元素，称为“pivot基准”
 * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）
 * .  在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作
 * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
 */
public class SortingSearchingQuickSort {

    // 时间复杂度 O(nlog(n)) ~ O(n²)
    // 复杂度的推导需要严格的数学公式计算
    // 通过基准的随机算法可以避免O(n²)的复杂度：完全颠倒的排序，每一递归一个方法，方法中遍历剩下的全部 => O(n*n)

    // 空间复杂度 O(log(n)) ~ O(n)
    // 每次都完全平分，复杂度由递归调用造成的栈内存空间的开销

    public static int[] quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            quickSort(nums, left, partitionIndex - 1); // 左边分区
            quickSort(nums, partitionIndex + 1, right); // 右边分区
        }
        return nums;
    }

    public static int partition(int[] nums, int left, int right) {
        int pivot = left;                   // 分区的基准可以从第一个位置的值开始
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, index);       // 将小于pivot的都放到pivot紧接的后边
                index++;
            }
        }
        swap(nums, pivot, index - 1);// 将pivot移动到前段分区的最后一个位置
        return index - 1;                   // 返回标记出来的分区位置
    }

    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
