package questions.iteration;

public class LearnIteration1 {

    // TODO. 典型迭代思想：直接在原始数组上迭代计算
    // 直接在原始的数组中更新每一步累计的历史值，无需创建DP数组
    // Maximum Subarray
    // Find the contiguous subarray (containing at least one number) which has the largest sum
    // nums= [-2,1,-3,4,-1,2,1,-5,4] -> [4,-1,2,1] -> max = 6
    //       [-2,1,-2,4,3, 5,6,1, 5]
    public static int getMaxSubArray(int[] nums) {
        int max = nums[0];
        for (int index = 1; index < nums.length; index++){
            // 只有当前面累计的是正值，才更新当前位置
            if (nums[index-1] > 0) {
                nums[index] += nums[index-1];
            }
            max = Math.max(max, nums[index]);
        }
        return max;
    }

    // TODO. 本质上是一个往后迭代判断结果的问题，无需开辟额外的空间
    // House Robber
    // 找到一组数中能够获得的最大值，不能取相邻的两个值
    // [1,2,3,1] -> 1 + 3 = 4
    // [2,1,1,2] -> 2 + 2 = 4 中间的间隔位置不一定
    // [2,7,9,3,1] -> 2 + 9 + 1 = 12
    public int robHouses(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (nums.length == 3) {
            return Math.max(nums[1], nums[0] + nums[2]);
        }

        // 注意max值的初始化，只有两种值的可能
        nums[2] += nums[0];
        int maxSum = Math.max(nums[1], nums[2]);
        for (int index = 3; index < nums.length; index++) {
            int maxBefore = Math.max(nums[index - 3], nums[index - 2]);
            nums[index] += maxBefore;
            maxSum = Math.max(maxSum, nums[index]);
        }
        return maxSum;
    }
}
