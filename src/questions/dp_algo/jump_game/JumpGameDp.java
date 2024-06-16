package questions.dp_algo.jump_game;

import beans.IndexMark;

import java.util.Arrays;

// Jump Game
// Positioned at the first index of the array 0 <= nums[i] <= 10^5
// Each element in the array represents your maximum jump length at that position
// nums = [2,3,1,1,4]   -> step 1 + step 3
// nums = [3,2,1,0,4]   -> 始终只能跳到index=3
public class JumpGameDp {

    // TODO. 通过正向遍历记录跳跃的标记
    //  使用DP数组记录(存储每一步累计的历史记录)
    // 2,3,1,1,4
    // - - -
    //   - - - -
    //     - -
    //       - -
    //
    // 3, 2, 1, 0, 4
    // -  -  -  -
    //    -  -  -
    //       -  -
    //
    // Math.max(N, sum(nums0))  O(n) 循环的次数可能是所有步数的总和
    public static boolean testJumpBottomUp(int[] nums) {
        int length = nums.length;
        int[] marks = new int[length];
        Arrays.fill(marks, 0);

        // Make sure to reach the first step
        marks[0] = 1;
        for (int index=0; index<length; index++) {
            if (marks[index] != 1) {
                return false;
            }
            if (index + nums[index] >= length-1) {
                return true;
            }
            // Mark all the following steps 这里的循环到底其实没有必要
            for (int j= index + 1; j <= index + nums[index]; j++) {
                if (marks[j] != 1) {
                    marks[j] = 1;
                }
            }
        }
        return true;
    }

    // TODO. 从后往前判断跳跃是否能够保证连续性
    // [3,  2,  1,  0,  4]  使用等长的数组来存储知否能够跳到的标记信息
    //          i       ?
    // 在index位置和它能跳到的最远位置Math.min(i + nums[i], nums.length - 1)之间
    //
    // O(n)～O(n^2) 复杂度依赖dp数组所存储的标记信息  O(n)
    public static boolean canJumpBottomUp(int[] nums) {
        IndexMark[] dp = new IndexMark[nums.length];
        Arrays.fill(dp, IndexMark.Unknown);

        dp[dp.length - 1] = IndexMark.Good;
        for (int i = nums.length - 2; i >= 0; i--) {
            int furthestJump = Math.min(i + nums[i], nums.length - 1);

            // 在index+1和它跳出去的范围中间找，是否有Good
            // 从index位置跳出去，只需要跳到一个Good位置即可
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
}
