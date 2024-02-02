package questions.greedy_dynamic_pro;

import core_model.enum_model.IndexMark;

import java.util.Arrays;

public class DpTwoDimensionBottomUp2 {

    // Jump Game
    // Positioned at the first index of the array 0 <= nums[i] <= 10^5
    // Each element in the array represents your maximum jump length at that position
    // nums = [2,3,1,1,4]   -> step 1 + step 3
    // nums = [3,2,1,0,4]   -> 始终只能跳到index=3
    // [3,  2,  1,  0,  4]  使用等长的数组来存储知否能够跳到的标记信息
    //          i       ?   在index位置和它能跳到的最远位置Math.min(i + nums[i], nums.length - 1)之间，如果有成功标记，则标记index位置 !!
    public static boolean canJumpBottomUp(int[] nums) {
        // O(n) 空间复杂度依赖dp数组所存储的标记信息，不会达到O(n^2)  O(n)
        IndexMark[] dp = new IndexMark[nums.length];
        Arrays.fill(dp, IndexMark.Unknown);
        dp[dp.length - 1] = IndexMark.Good;

        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);
            for (int j = i + 1; j <= furthestJump; j++) {
                if (dp[j] == IndexMark.Good) {
                    dp[i] = IndexMark.Good;
                    break;
                }
            }
        }
        return dp[0] == IndexMark.Good;
    }

    // TODO: 动态编程的最后一步，减少dp过程中记录的数据，使用最小存储来实现(替代数组)
    public static boolean canJumpTrick(int[] nums) {
        int lastMaxSteps = nums[0];
        int index = 1;
        while (index < nums.length) {
            // 在index位置，它前面的剩余的最大步数，至少要有1步，才能到当前这个位置
            if (lastMaxSteps < 1) return false;
            if (nums[index] == 0) {
                if (lastMaxSteps == 1) {
                    // 如果前面只剩最后一步，则跳一步之后，必须到结尾位置
                    return index == nums.length - 1;
                }
                lastMaxSteps--;
            } else {
                lastMaxSteps--;
                lastMaxSteps = Math.max(nums[index], lastMaxSteps);
            }
            index++;
        }
        return index == nums.length; // 成功跳出循环的条件
    }

    public boolean canJumpGreedy(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i; // 更新有效位置(可以到达的位置)
            }
        }
        return lastPos == 0;
    }
}
