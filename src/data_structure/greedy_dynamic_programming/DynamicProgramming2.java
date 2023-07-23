package data_structure.greedy_dynamic_programming;

import algorithms.datamodel.enum_model.IndexMark;

import java.util.Arrays;

public class DynamicProgramming2 {

    // TODO: DP Top-Down 自顶向下的设计 (包含递归)
    // Coin Change 只用最少的零钱数目来凑出指定的值, 假设每种零钱的数目是足够的
    // An integer array coins representing coins, an integer amount representing a total amount of money
    // Return the fewest number of coins that you need to make up that amount
    // coins = {2, 5}       target = 6   ->  Answer: -1          Expected: 3 (2)
    // coins = {1, 3, 4, 5} target = 7   ->  Answer: 3 (5, 1, 1) Expected: 2 (3, 4)
    public int coinChange(int[] coins, int amount) {
        // 正确理解：1. Dynamic programming(Top down) 所有可能最终构成一个递归树, 答案是这个递归树的最低高度层级
        //            O(S*n) S是amount, n是coin的种类  O(S) 需要一个额外的数组来存储中间转换的剩余数
        if (amount < 1) return 0;
        int[] counts = new int[amount];
        return coinChange(coins, amount, counts);
    }

    //                 6 {1,2,3}
    //             (5   4   3)
    //    (4 3 2)   (3 2 1) (2 1 0)
    // ...                       计算到0的时候返回，完全展开含有多子结点的树
    private int coinChange(int[] coins, int remainder, int[] count) {
        if (remainder < 0) return -1;
        if (remainder == 0) return 0;
        // 如果某一个位置已经是最优的零钱分配，则直接返回它分配出来的最小种类的数目
        if (count[remainder - 1] != 0) return count[remainder - 1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 判断分配不同零钱之后的结果(分配出来的最少零钱的种类数目)
            int fewestNumber = coinChange(coins, remainder - coin, count);
            if (0 <= fewestNumber && fewestNumber < min) {
                min = fewestNumber + 1;
            }
        }
        count[remainder - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[remainder - 1];
    }

    // TODO: DP Bottom Up 自底向上的设计 (从底部开始向上展开)
    // F(3) = min{F(3−c1),F(3−c2),F(3−c3)}+1
    //      = min{F(3−1),F(3−2),F(3−3)}+1
    //      = min{F(2),F(1),F(0)}+1
    //      = min{1,1,0}+1
    //      = 1
    public int coinChange2(int[] coins, int amount) {
        // O(S*n) O(S)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // 设置一个最大值，用于最小值的判断
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 判断i位置数目能否被coins中coin划分，如果可以，划分出来的结果又是否能够被划分(利用之前的结果)
                int coin = coins[j];
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); // dp[1] = 1 再依次往后推
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // TODO: Bottom Up 自底向上的设计
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
