package algorithms.learn_all_problems.p1_array.pair_sum;

import java.util.Arrays;

public class BasePairSum {

    // Minimize Maximum Pair Sum in Array
    // n == nums.length
    // 2 <= n <= 10^5
    // n is even 数量必须是偶数
    // 1 <= nums[i] <= 10^5
    //
    // nums = [3,5,4,2,4,6]
    // sort -> 2 3 4 4 5 6 -> 8
    // 本质上只需要找到所有成组的对数中的和的最大值
    // 计算规律，最大值一定和最小的值进行组合成对，以降低pair和的最大值
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxPairSum = 0;
        int tempSum = 0;
        int length = nums.length - 1;
        for (int index = 0; index < nums.length / 2; index++) {
            tempSum = nums[index] + nums[length - index];
            if (maxPairSum < tempSum) {
                maxPairSum = tempSum;
            }
        }
        return maxPairSum;
    }
}
