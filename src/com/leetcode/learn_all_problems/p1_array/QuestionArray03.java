package com.leetcode.learn_all_problems.p1_array;

public class QuestionArray03 {

    // TODO: 理论事实：所有的数据要相等，移动到median中位数的总步数的是最小的，而不是平均数 !!
    // Minimum Moves to Equal Array Elements II
    // Given an integer array nums of size n
    // Return the minimum number of moves required to make all array elements equal
    // In one move, you can increment or decrement an element of the array by 1
    // nums = [1,2,3]     -> 2
    // nums = [1,0,0,8,6] -> 14
    // Algo: 如何找到中位数? 通过Quick Select快速选择算法定位Pivot  O(n*n)    O(1) 根据pivot的选择，会造成不同的复杂度 !!
    //       如果不找中位数? 将数据进行排序，首尾判断移动步数         O(nlog(n)) O(1)
    public int minMoveSteps(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            count += nums[right] - nums[left];
            right--;
            left++;
        }
        return count;
    }

    public int minMoveSteps2(int[] nums) {
        int length = nums.length;
        int median = quickSelect(nums, length / 2, 0, length - 1);
        int sumSteps = 0;
        for (int num : nums) sumSteps += Math.abs(num - median);
        return sumSteps;
    }

    // 目标index位置：该位置的值前面有指定的数目是小于这个位置上面的值的，该值便是中位数 !! 知道index位置为targetIndex(要找的排在第几个位置的值)
    // 1 0 0 8 6
    // 1 0 6 8 0 pivotValue
    //     index 确定找到的targetIndex的位置
    private int quickSelect(int[] nums, int targetIndex, int leftIndex, int rightIndex) {
        // 假设这里挑选中间位置就是中位数的位置 (由于是乱序的数组，所以最差情况是一次选不到中位数的)
        int pivot = leftIndex + (rightIndex - leftIndex) / 2;
        int index = leftIndex;
        swap(nums, pivot, rightIndex);
        for (int i = leftIndex; i <= rightIndex; i++) {
            if (nums[i] < nums[rightIndex]) {
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index, rightIndex);
        if (index == targetIndex) return nums[index];
        if (index < targetIndex) return quickSelect(nums, targetIndex, index + 1, rightIndex);
        return quickSelect(nums, targetIndex, leftIndex, index - 1);
    }

    public void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    // TODO: 如何转换问题，观察数据的特点
    //       在什么条件下结果值可能最大 ? 要么有两个最小的值(负数)*最大值，或者直接是数据中最大的3个值的乘积 !!
    // Maximum Product of Three Numbers
    // Given an integer array nums, find three numbers whose product is maximum and return the maximum product
    // [0, 1, 2, 3]           -> 6
    // [-12, -7, -1, 11, 17]  -> (-12)*(-7)*17 
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies between max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies between max2 and max3
                max3 = n;
            }
        }
        // 假设这里的乘积结果不会越界
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    // TODO: 从特殊性到一般性的过渡，满足同一个逻辑条件的成立 ==> 数据统计的相互抵消性质，一定是出现次数多的那个数据占优势(被留下来) !!
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 2,2,2,2,2,1,3,3,6  假如有一个数字出现的次数超过一半，通过count统计最后留下来的mostFrequencyValue一定是这个数字
    // 1,2,2,3,3,2,6,2,2  数据打乱之后依然能保留到这个数字，原因是因为在count统计的过程中数据会呈现相互抵消的效果 !!
    public int findMostFrequencyElement2(int[] gifts, int n) {
        if (gifts.length < n || gifts.length == 0) return 0;
        int count = 0;
        int mostFrequencyValue = gifts[0];
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                mostFrequencyValue = gifts[i]; // 只有当count被累积清空之后，才会变动记录的数字mostFrequencyValue
                count = 1;
            } else {
                if (mostFrequencyValue == gifts[i]) count++;
                else count--;
            }
        }
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (gifts[i] == mostFrequencyValue) size++;
        }
        return (size > n / 2) ? mostFrequencyValue : 0;
    }
}
