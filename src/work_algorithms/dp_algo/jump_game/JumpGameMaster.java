package work_algorithms.dp_algo.jump_game;

// [2,3,1,1,4]
public class JumpGameMaster {

    // TODO. Jump Game游戏可以转换到Gas Station加油站模型
    // 最终的目标是从一边到另外一边，只要有足够的积累便能通过
    public boolean canJump(int[] nums) {
        int remainingSteps = 0;
        for (int num: nums) {
            if (remainingSteps < 0) {
                return false;
            }
            if (remainingSteps < num) {
                remainingSteps = num; // 如果剩余步数不如直接从当前位置出发走的远，则更换剩余步数
            }
            remainingSteps -= 1; // 每走一步便会消耗一步
        }
        return true; // 证明可以使用剩余步数走完全程
    }

    // 从后往前思考，记录最低必须从什么位置起跳
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
