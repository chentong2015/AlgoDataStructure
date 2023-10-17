package data_structure.greedy_dynamic_programming;

public class DynamicProgramming0 {
    
    // Maximum Subarray
    // Find the contiguous subarray (containing at least one number) which has the largest sum
    // nums = [-2,1,-3,4,-1,2,1,-5,4] -> [4,-1,2,1] -> max = 6
    public int maxSubArray(int[] nums) {
        // 测试理解：1. 常规理解：每个位置都累计追加一个新的值来求和，判断这些求和片段中最大的那个值 !!
        //            O(n*n) O(n)
        int maxSum = nums[0];
        int[] temps = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temps[i] = nums[i];
            for (int j = 0; j < i; j++) {
                temps[j] += nums[i];
                maxSum = Math.max(maxSum, temps[j]);
            }
            maxSum = Math.max(maxSum, temps[i]);
        }
        return maxSum;
    }

    // TODO: DP编程典型实例，使用运算过程中的临时存储值来得出最终结果 !!
    // 正确理解：1. 循环的核心在于"始终有一个中间存储的值，判断是否累计，还是直接用index位置值更新"
    // 如果在"累计片段"的过程中，加出来的结果是负数，则这个片段直接舍弃
    // 只有正数的"片断累计"才是有效值，才有可能是和最大的子数组 !!
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    // House Robber
    // nums = [1,2,3,1] -> 1 + 3 = 4 找到一组数中能够获得的最大值，不能取相邻的两个值
    // nums = [2,1,1,2] -> 2 + 2 = 4
    public int robNums(int[] nums, int n) {
        // 测试理解：1. "递归逻辑", 相邻不能取, 可以间隔一个或者两个，不能间隔三个不取，否则得不到最大值 !!
        //            O(n^3) 递归调用造成的栈内存空间的开销, 理论上时间复杂度太高 O(1)
        if (n == nums.length - 1) {
            return nums[n];
        } else if (n == nums.length - 2) {
            return Math.max(nums[n], nums[n + 1]);
        } else if (n == nums.length - 3) {
            return Math.max(nums[n] + nums[n + 2], nums[n + 1]);
        } else {
            int firstRob = nums[n] + robNums(nums, n + 2);
            int secondRob = nums[n + 1] + robNums(nums, n + 3);
            int thirdRob = nums[n] + robNums(nums, n + 3);
            int max = Math.max(firstRob, secondRob);
            return Math.max(max, thirdRob);
        }
    }

    // TODO: DP编程典型实例，累计记录前面每一步的有效结果
    // 正确理解：1. 利用增加的空间复杂度, 依次以用前面已有的累计结果, 得出新的判断结果 O(n) O(n)
    // nums = [2,7,9,3,1]
    // 2
    // 2 7
    // 2 7 11
    // 2 7 11 (2+3)?(7+3)=10
    // 2 7 11 10 (7+1)?(11+1)=12 比较这个位置和前面2个3个位置和的大小
    public int rob(int[] nums) {
        if (nums == null && nums.length == 0) return 0;
        int[] temp = new int[nums.length];
        int max = nums[0];
        for (int index = 0; index < nums.length; index++) {
            if (index == 0) {
                temp[0] = nums[0];
            } else if (index == 1) {
                temp[1] = Math.max(temp[0], nums[1]);
            } else if (index == 2) {
                temp[2] = Math.max(temp[0] + nums[2], temp[1]);
            } else {
                // 核心比较：结合当前的位置和前面位置，共同决定取较大的结果值
                temp[index] = Math.max(temp[index - 3] + nums[index], temp[index - 2] + nums[index]);
            }
            max = Math.max(temp[index], max); // 记录下累计数组中的最大值
        }
        return max;
    }
}
