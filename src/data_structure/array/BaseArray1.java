package data_structure.array;

public class BaseArray1 {

    // Remove Element
    // Given an array nums and a value val, remove all instances of that value in-place and return the new length
    // The order of elements can be changed. It doesn't matter what you leave beyond the new length
    //
    // nums = [0,1,2,2,3,0,4,2], val = 2 -> nums = [0,1,3,0,4]
    // 对于不是要移除的元素，全部将其移动到数组的左边，返回左边指定长度的结果数据
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != val) {
                nums[left] = nums[index];
                left++;
            }
        }
        return left;
    }

    // Move Zeroes
    // Given an integer array nums, move all 0's to the end of it while
    // maintaining the relative order of the non-zero elements.
    // nums = [0,1,0,3,12] -> [1,3,12,0,0] 只能在原始的数组上操作
    //         1 3 12
    public void moveZeroes(int[] nums) {
        // 测试理解：1. 将非0的值依次排列在数组的开头，最后留下的位置就是0的值(统计数目) O(n) O(1)
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            } else {
                count++;
            }
        }
        // 最后补充结尾的0位置，可以直接使用index来循环，不需要统计0的数目
        for (int i = 0; i < count; i++) {
            nums[nums.length - 1 - i] = 0;
        }
    }

    // Remove duplicates from sorted array
    // The input array is passed in by reference, it doesn't matter what you leave beyond the returned length
    // For example: [0,0,1,1,1,2,2,3,3,4] -> [0,1,2,3,4]
    public int removeDuplicates(int[] nums) {
        // 正确理解：由于是排序排列的，因此只需要找到所有不同的item，从开始位置依次往后排即可
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 从第二个位置开始，只在有不同的值出现的时候才交换一次位置 !!
        // 只找后面和left位置值不同的数据，往数组的左边移动
        int left = 0;
        for (int index = 1; index < nums.length; index++) {
            if (nums[left] != nums[index]) {
                nums[left] = nums[index];
                left++;
            }
        }
        return left + 1;
    }

    // Best Time to Buy and Sell Stock II
    // An array prices where prices[i] is the price of a given stock on the ith day
    // Find the maximum profit you can achieve 使得卖出去的收益最大
    // You must sell the stock before you buy again 必须在指定的某天将股票卖了，然后再买
    // For example: prices = [7,1,5,3,6,4] -> (5-1) + (6-3) -> 7
    public int maxProfit(int[] prices) {
        // 测试理解：判断找到后面最大值的时候出手? 在这个过程中如何比较是否有更好的收益 ?
        //         如何判断在已经处于买或者卖的状态中 ?

        // 正确解法: 1. 穷举所有可能的transactions交易的可能，(递归法)提取出最大的值
        //          2. "峰谷法"：将所有的山峰和山谷之间的差值累加(数学公式)
        //          3. "Simple One Pass": 本质上收益的总值(极限值)是等于所有增长的部分的和
        int total = 0;
        boolean isBuy = false; // 根本不同考虑是否处于买入和卖出的状态
        if (prices.length == 0) return 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int baseProfit = 0;
            if (prices[i] < prices[i + 1]) {
                isBuy = true;
                baseProfit = prices[i + 1] - prices[i];
                // 嵌套一次迭代
            }
        }
        // 正确解法 3
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }

    // Rotate Array
    // Rotate the array to the right by k steps, where k is non-negative.
    // Input: nums = [1,2,3,4,5,6,7], k = 3 -> [5,6,7,1,2,3,4]
    public void rotateArray(int[] nums, int k) {
        // 测试理解：1. 常规解法，每次移动一个位置的值，将数组中全部的值都移动 O(n×k), O(1)
        //         2. 使用额外的数组, 交换item后赋值回原来的数组          O(n), O(n)
        //
        // 正确理解: 1. 循环替换, 前面往后, 后面的超过长度往移动到开头 O(n), O(1)
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (current != start); // current循环回到start位置点的时候结束
        }
    }

    // 正确理解: 2. Reverse完全颠倒数组，然后再颠倒部分 O(n) O(1)
    // nums = [1,2,3,4,5,6,7]
    // nums = [7,6,5,4,3,2,1]
    //        [5, 6, 7, 4, 3, 2, 1]
    //        [5, 6, 7, 1, 2, 3, 4]
    public void rotateArray2(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
