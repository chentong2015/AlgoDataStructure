package data_structure.greedy_dynamic_pro;

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
}
