package com.leetcode.top_interview_questions.easy_collection;

public class DynamicProgramming {

    // Climbing Stairs 有多少种解法
    // It takes n steps to reach the top
    // Each time you can either climb 1 or 2 steps. how many distinct ways can you climb to the top?
    public int climbStairs(int n) {
        // 测试理解：每走一步，都对后面有联动的影响，每一步都有两种选择，穷举所有的可能值 !!
        //          将n步划分成多少个一步和多少个两步的和, 但是和顺序严格相关
        //          Time complexity : O(2^n). Size of recursion tree will be 2^n 栈开的嵌套方法的调用  !!
        //          Space complexity : O(n). The depth of the recursion tree can go upto n 递归造成分配给参数的栈空间，为二叉树的深度 !!
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    //  正确理解: 1. Dynamic Programming: 利用前面已有的(保留的)值，得出下面的结果
    //              本质上, 到达第n个位置有两种可能, 从n-1位置+1过来，或者从n-2位置+2过来  dp[i]=dp[i−1]+dp[i−2]
    //              O(n) O(n)
    public int climbStairsDynamicProgramming(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //  正确理解: 2. Fibonacci数列: Fib(n)=Fib(n−1)+Fib(n−2)
    //           前两个值分别是1和2, 递归每走一步的两种可能           ==> 区别在于没有保存前面每一步计算出来的值 !!!
    //           Time complexity : O(n)  Space complexity : O(1)
    public int climbStairsFib(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    // TODO: Maximum Subarray 找到连续的一组和最大的数字
    // find the contiguous subarray (containing at least one number) which has the largest sum
    public int maxSubArray(int[] nums) {
        // 测试理解:[-2,1,-3]  当读到第三个值的时候，判断是否需要将第一个值加入进来 !!
        //         [-2,1,-3,4,-1,2,1,-5,4] 找到这组数字的起点和结尾点, 如果是负数，会降低总和的值
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            int max = Math.max(nums[0], nums[1]);
            return Math.max(max, nums[0] + nums[1]);
        }
        int left = 0;
        int right = nums.length - 1;
        int sum = 0;
        while (left < right) {
        }
        return 0;
    }

    public int rob(int[] nums) {
        return robNums(nums, 0);
    }

    // TODO: House Robber
    // nums = [1,2,3,1] -> 1 + 3 = 4 找到一组数中能够获得的最大值，不能取相邻的两个值
    // nums = [2,1,1,2] -> 2 + 2 = 4 
    public int robNums(int[] nums, int n) {
        // 测试理解："相邻"不能取, 可以间隔一个，也可以间隔两个不取，不能间隔三个不取，否则得不到最大值 !!
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
}
