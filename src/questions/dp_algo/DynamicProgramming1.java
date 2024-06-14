package questions.dp_algo;

import java.util.Arrays;

public class DynamicProgramming1 {

    // TODO. 虽然是“连续”的子区间，但是滑动窗口无法移动
    //   题目更偏向于“新添加一个值，能够在过去的统计值上叠加”，并返回过程中统计的最大值
    // Maximum Subarray
    // Find the contiguous subarray (containing at least one number) which has the largest sum
    // nums = [-2,1,-3,4,-1,2,1,-5,4] -> [4,-1,2,1] -> max = 6
    //
    // dp = [-2, 1, -2,4, 3,5,6, 1,5] 只需要利用前面一步统计的值即可
    public static int getMaxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int index=1; index<nums.length; index++){
            if (dp[index-1] > 0) {
                dp[index] = dp[index-1] + nums[index];
            } else {
                dp[index] = nums[index];
            }
            max = Math.max(max, dp[index]);
        }
        return max;
    }

    // TODO. 如果可以之间在原始数组操作，则无需创建DP数组 ！
    // 即使无法修改原始数组，也只需要创建一个变量sum来累计运算，避免空间复杂度的开销
    public static int getMaxSubArray2(int[] nums) {
        int max = nums[0];
        for (int index = 1; index < nums.length; index++){
            if (nums[index-1] > 0) {
                nums[index] += nums[index-1];
            }
            max = Math.max(max, nums[index]);
        }
        return max;
    }

    // House Robber
    // 找到一组数中能够获得的最大值，不能取相邻的两个值
    // [1,2,3,1] -> 1 + 3 = 4
    // [2,1,1,2] -> 2 + 2 = 4 中间的间隔位置不一定
    // [2,7,9,3,1] -> 2 + 9 + 1 = 12
    // [2,10,5,6,20]
    //
    // 1 <= nums.length <= 100, 0 <= nums[i] <= 400
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
        int max = Math.max(nums[1], nums[2]);
        for (int index = 3; index < nums.length; index++) {
            nums[index] += Math.max(nums[index - 3], nums[index - 2]);
            max = Math.max(max, nums[index]);
        }
        return max;
    }

    // TODO: DP典型实例, 保留前面计算过的值, 在此基础上更新和判断
    // 最长连续增长子序列，可以演变为最长满足条件的增长
    // Longest Increasing Subsequence
    // An integer array nums, return the length of the longest strictly increasing subsequence
    // nums  = [10,9,2,5,3,7,101,18] -> [2,3,7,101] 最长连续增长子序列, 数字之间可以不连续
    // steps = [1, 1,1,2,2,3,4  ,4]
    //
    // 使用等长数组保存"每一步所积累的'它前面的'最长的到达步数"
    // O(n^2) O(n)
    public int lengthOfLIS(int[] nums) {
        // 必须将之前每一步的数据存储，只用一个变量是无法保存这些信息
        int[] steps = new int[nums.length];
        steps[0] = 1;
        int maxAnswer = 1;

        for (int i = 1; i < steps.length; i++) {
            // 找前面位置更低的数目的(已经存在的)最长的序列数目
            // 判断当前位置前面的所有更小的值，获取它记录的累计长度 !!
            int maxStepBefore = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxStepBefore = Math.max(maxStepBefore, steps[j]);
                }
            }

            // 在"前面子序列最长的"那个点的基础上再移动一步，如果没有遍历到，则初始第一步为1
            steps[i] = maxStepBefore + 1;
            // 更新记录的最大值, 因为可能出现几个不同的增长序列, 需要求总的
            maxAnswer = Math.max(maxAnswer, steps[i]);
        }
        return maxAnswer;
    }

    // Dynamic Programming with Binary Search: O(nlog(n)) O(n)
    //
    // Store increasing subsequence formed by including currently encountered element
    // 这个数组需要预留足够的长度
    // input: [0, 8, 4, 12, 2]
    //    dp: [0]
    //    dp: [0, 8]
    //    dp: [0, 4]
    //    dp: [0, 4, 12]
    //    dp: [0, 2, 12] 这里存储的不是正确的排序值，但是数组的长度是最终的答案
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            // Returns index of search key, Return (-(insertion point) - 1) if not found
            int index = Arrays.binarySearch(dp, 0, length, num);
            if (index < 0) {
                index = -(index + 1); // 根据查找的index，重新计算要插入的点，可能直接追加到最后
            }
            dp[index] = num;          // 将读取的每个值添加到dp数组中指定的位置"insertion point"
            if (index == length) {
                length++;             // 使用length来统计插入到的位置长度，如果是插入到最后，则需要延申长度
            }
        }
        return length; // length比插入的index坐标位置要多1
    }
}
