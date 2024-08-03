package work_algorithms.backtracking;

public class BacktrackingSumTarget {

    // Target Sum
    // You are given an integer array nums and an integer target.
    // You want to build an expression out of nums
    // by adding one of the symbols '+' and '-' before each integer in nums
    // and then concatenate all the integers.
    // Return the number of different expressions that you can build,
    //
    // Input: nums = [1,1,1,1,1], target = 3
    // Output: There are 5 ways to assign symbols to make the sum of nums be target 3.
    // -1 + 1 + 1 + 1 + 1 = 3
    // +1 - 1 + 1 + 1 + 1 = 3
    // +1 + 1 - 1 + 1 + 1 = 3
    // +1 + 1 + 1 - 1 + 1 = 3
    // +1 + 1 + 1 + 1 - 1 = 3
    private int targetSum = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtracking(nums, 0,  target,0);
        return targetSum;
    }

    private void backtracking(int[] nums, int index, int target, int tempSum) {
        // 终止条件: 对于最后一个位置两种情况均需要统计
        if (index == nums.length - 1) {
            if (tempSum + nums[index] == target) {
                targetSum++;
            }
            if (tempSum - nums[index] == target) {
                targetSum++;
            }
            return;
        }

        tempSum += nums[index];
        backtracking(nums, index+1, target, tempSum);
        tempSum -= nums[index];

        tempSum -= nums[index];
        backtracking(nums, index+1,  target, tempSum);
    }
}
