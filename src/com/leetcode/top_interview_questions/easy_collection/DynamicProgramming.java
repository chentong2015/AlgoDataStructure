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

    // 正确理解: 1. Dynamic Programming: 利用前面已有的(保留的)值，得出下面的结果
    //          本质上, 到达第n个位置有两种可能, (n-1)位置+1或(n-2)位置+2过来: dp[i]=dp[i−1]+dp[i−2]
    //          O(n) O(n)
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
    // Find the contiguous subarray (containing at least one number) which has the largest sum
    public int maxSubArray(int[] nums) {
        // 测试理解: 1. "动态编程": 每次记录前面累计的结果，提取最大值 O(n*n) O(n)
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

    // 正确理解: 1. 如果存储? 可以去掉内部的嵌套循环，导致的时间复杂度过高
    //            在index位置，无论是什么值，如果它的前面是负数，则去掉，如果前面有和为非0的数据，则加上
    // [-1,1,3,4,-1,2,1,-5,4]
    // -2 1 3
    // -2 + 1 + 3
    public int maxSubarray(int[] nums) {
        int left = 0;
        int right = 1;
        int maxSum = nums[left];
        while (right < nums.length) {
            if (maxSum < 0) {
                left++;
                maxSum += nums[right];
            } else {

            }
            right++;
        }
        return 0;
    }

    // TODO: House Robber
    // nums = [1,2,3,1] -> 1 + 3 = 4 找到一组数中能够获得的最大值，不能取相邻的两个值
    // nums = [2,1,1,2] -> 2 + 2 = 4
    public int rob(int[] nums) {
        return robNums(nums, 0);
    }

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
