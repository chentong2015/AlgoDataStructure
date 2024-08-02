package questions.recursion;

public class LearnRecursion1 {

    // 使用迭代计算阶乘
    public static long factor(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factor(n - 1);
    }

    // 数列通项公式 fi(n) = fi(n-1) + fi(n-2)
    public static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Climbing Stairs 有多少种解法
    // It takes n steps to reach the top, each time you can either climb 1 or 2 steps.
    // how many distinct ways can you climb to the top ?
    public int climbStairs(int n) {
        // 测试理解：每走一步，都对后面有联动的影响，每一步都有两种选择，穷举所有的可能值 !!
        // 将n步划分成多少个一步和多少个两步的和, 但是和顺序严格相关
        // O(2^n) Size of recursion tree will be 2^n 栈开的嵌套方法的调用  !!
        // O(n)   The depth of the recursion tree can go upto n 递归造成分配给参数的栈空间，为二叉树的深度 !!
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    // Dynamic Programming: 利用前面已有的(保留的)值，得出下面的结果
    // 本质上, 到达第n个位置有两种可能, (n-1)位置+1或(n-2)位置+2过来: dp[i]=dp[i−1]+dp[i−2]
    // O(n) O(n)
    public int climbStairsDynamicProgramming(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // TODO: Fibonacci数列 Fib(n)=Fib(n−1)+Fib(n−2)，借助动态编程思想，所实现的最优解
    // 第一步有一种可能，第二步有两种可能，第三步的可能情况是前中情况的和(从第一步和第二步都能够走到第三步)
    // 根本不需要保留到每一步的数据，最后只需要返回到最后一步结果
    // O(n) O(1)
    public int climbStairsFib(int n) {
        if (n == 1) return 1;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
