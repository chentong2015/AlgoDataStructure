package data_structure.greedy_dynamic_pro.array;

public class DynamicProgrammingArray {

    // TODO. 动态编程核心，一次遍历，在每一个位置保持之前历史有效数据
    // 输入一个数组，可以在任意位置买入或者卖出，计算最大收益值 & 最少亏损值
    // 数组中均为整数，且至少有两个数
    // [3, 5, 4, 6, 3, 2, 9, 6, 9]  => 7
    private static int calculateMaxGain(int[] nums) {
        int minBefore = nums[0];
        int maxGain = Integer.MIN_VALUE;
        for (int index = 1; index < nums.length; index++) {
            // 统一计算，如果结果为负值，则表示为最小亏算
            maxGain = Math.max(maxGain, nums[index] - minBefore);
            if (nums[index] < minBefore) {
                minBefore = nums[index];
            }
        }
        return maxGain;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, -2, 2};
        // int[] nums = {3, 5, 4, 6, 3, 1, 9, 6, 9};
        System.out.println(calculateMaxGain(nums));
    }

    // TODO. 当允许多次买入和卖出时，该问题变成数学统计问题
    // Best Time to Buy and Sell Stock II
    // An array prices where prices[i] is the price of a given stock on the ith day
    // Find the maximum profit you can achieve，You must sell the stock before you buy again
    // For example: prices = [7,1,5,3,6,4] -> (5-1) + (6-3) -> 7
    //
    // 1. 穷举所有可能的transactions交易的可能，(递归法)提取出最大的值
    // 2. 峰谷法：将所有的山峰和山谷之间的差值累加(数学公式)
    // 3. "Simple One Pass": 本质上收益的总值(极限值)是等于所有增长的部分的和
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 本质上只要有上升就会有收益
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
