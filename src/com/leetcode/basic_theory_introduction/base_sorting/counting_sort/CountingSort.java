package com.leetcode.basic_theory_introduction.base_sorting.counting_sort;

// Counting Sort 计数排序：稳定的线性时间排序算法
// O(n+k) 当输入的元素是n个0到k之间的整数时
// O(n+k) 需要额外的统计元素频率的数组
public class CountingSort {

    // 计数排序是用来排序0到100之间的数字的最好的算法
    // 比如：10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位
    // 1. 找出待排序的数组中最大和最小的元素
    // 2. 统计数组中每个值为i的元素出现的次数，存入数组C的第i项
    // 3. 对所有的计数累加C中的第一个元素开始，每一项和前一项相加
    // 4. 反向填充目标数组：将每个元素i放在新数组的第C[i]}项，每放一个元素就将C[i]减去1

    // Input: 1, 4, 1, 2, 7, 5, 2
    //
    // Index: 0  1  2  3  4  5  6  7  8  9
    // Count: 0  2  2  0  1  1  0  1  0  0
    //
    // Index: 0  1  2  3  4  5  6  7  8  9
    // Count: 0  2  4  4  5  6  6  7  7  7
    //
    // Process the input data: 1, 4, 1, 2, 7, 5, 2.
    // Position of 1 is 2. 找到要放置的位置，在放置完成之后，降低位置，以便下一次放到前面
    // Put data 1 at index 2 in output. Decrease count by 1 to place, next data 1 at an index 1 smaller than this index.
    public static int[] countSort(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        for (int i : nums) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        // 优化解法，压缩统计数组的大小，减少使用的空间复杂度
        int[] counts = new int[max - min + 1];
        for (int num : nums) counts[num - min] += 1;
        for (int i = 1; i < counts.length; ++i) {
            counts[i] = counts[i] + counts[i - 1];
        }

        int[] numsSorted = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; --i) {
            numsSorted[counts[nums[i] - min]] = nums[i];
            counts[nums[i] - min]--;
        }
        return numsSorted;
    }

    // TODO: Counting Sort的测试解法，通项公式条件citations[index] >= length(citations) - index
    // H-Index
    // Given an array of integers citations
    // citations[i] is the number of citations a researcher received for their ith paper
    // return compute the researcher's h-index
    // If there are several possible values for h, the maximum one is taken as the h-index 提取最大的那一个h-index值
    // citations = [3,0,6,1,5] -> 5 papers -> 3 papers with at least 3 citations -> 3
    // citations = [1,3,1]     -> 3 papers -> 1 paper with at least 1 citation   -> 1
    // 查找h-index的分界位置标识，使数组中有h个元素至少有h个papers，其余n-h个元素拥有不超过h个paper
    // O(n) O(n) 牺牲掉一定的空间复杂度，将时间复杂度降低一个维度 !!
    public int findHIndex(int[] citations) {
        int len = citations.length;
        int[] counts = new int[len + 1];
        for (int citation : citations) {
            if (citation > len) counts[len]++;
            else counts[citation]++;
        }

        // 从后往前取并累加，优先考虑最大的zIndex !!
        int total = 0;
        for (int i = len; i >= 0; i--) {
            total += counts[i];
            if (total >= i) return i;
        }
        return 0;
    }

    // H-Index II
    // Citations is sorted in an ascending order, return compute the researcher's h-index
    // citations = [0,1,3,5,6] -> 3
    // citations = [0]         -> 0
    // citations = [0,0]       -> 0
    // O(nlog(n)) O(1) 将原本混乱的数据排序之后，可以去掉空间复杂度
    // O(n)       O(1) 最终的时间复杂度是O(2n)=O(n)，数组中的数据最多只能被遍历一遍
    public int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) return 0;
        int count = 0;
        int right = citations.length - 1;
        for (int index = citations.length; index >= 0; index--) {
            while (right >= 0 && citations[right] >= index) {
                count++;
                right--;
            }
            if (count >= index) return index;
        }
        return 0;
    }

    // TODO: 一般而言，具有排序的数据，都可以考虑使用二分法来查找一定的特征值(结果)
    //       金典二分法的3种条件判断，确定唯一移动方向 O(log(n) O(1)
    // 0 1 2 3 4 5 6 7 8 9   length=10
    // l       m         r
    public int hIndexBinarySearch(int[] citations) {
        int left = 0;
        int length = citations.length;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (length - mid == citations[mid]) {
                // (length - mid)表示该mid位置的右边有多少的数目(包括自身), mid是满足条件的zIndex(唯一的平衡点)
                return length - mid;
            } else if (length - mid > citations[mid]) {
                // (length - mid)右边的数目比该位置的值更大，则说明右边有一个平衡点，往上移动以找到最大的zIndex !!
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return length - left;
    }
}
