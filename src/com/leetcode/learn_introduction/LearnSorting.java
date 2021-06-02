package com.leetcode.learn_introduction;

// 金典排序算法:
// 1. 插入排序  O(n²)      O(1)       每读取一个值, 将它插入到前面有序的序列中
// 2. 选择排序  O(n²)      O(1)       每次遍历找最大或者最小值, 放到开头或者末尾
// 3. 冒泡排序  O(n²)      O(1)       两两比较交换, 依次交换
// 4. 快速排序  O(nlog(n)) O(log(n))  Quick Sort, 设置pivot基准, 然后进行partition分区
// 5. 归并排序  O(nlog(n)) O(n)       Divide and Conquer分治法, 将长度切分成n/2个子序列, 两两进行归并, 最后合并, 缺点是空间复杂度高
// 6. 用堆排序  O(nlog(n)) O(1)       Heap完全二叉树, 先构建堆, 然后依次将root节点和最后一个元素Rn交换, 剩下R1-Rn-1个无序区, 再找第二大的
// 7. 计数排序  O(n+k)     O(n+k)     n个0到k之间整数, 当k不是很大并且序列比较集中时, 考虑使用计数排序, 先统计再依次输出

// 原生语言自带的排序方法：Arrays.sort(array); Collections.sort(list, comparator); 可以自定义排序的比较器 !!
public class LearnSorting {

    /**
     * TODO: 金典算法: Quick Sort
     * 通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序
     * .
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）
     * 1. 从数列中挑出一个元素，称为“pivot基准”
     * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）
     * .  在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作
     * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
     */
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
