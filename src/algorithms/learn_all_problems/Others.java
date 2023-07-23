package algorithms.learn_all_problems;

public class Others {

    // Count Primes
    // 核心算法是，数字从底到高，依次标记所有不是质数的位置  ===> 方法可以重构成多个子方法 !! 只有一个抽象逻辑层
    //          O(/n*loglogn) < O(n*n)  O(n)
    public int countPrimes(int n) {
        int count = 0;
        if (n < 2) {
            return count;
        }
        boolean[] notPrimes = new boolean[n]; // 初始默认都是质数 !!
        int lastLimit = (int) java.lang.Math.sqrt(n);
        for (int i = 2; i <= lastLimit; i++) {
            if (!notPrimes[i]) {
                for (int j = 2 * i; j < n; j += i) { // 标记所有这个数的倍数，都不是质数 !!
                    notPrimes[j] = true;
                }
            }
        }
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                count++;
            }
        }
        return count;
    }

    // Power of Three
    // Could you solve it without loops/recursion? 不用循环和递归都能实现
    public boolean isPowerOfThree(int n) {
        // 测试理解：判断一个值是否能够被3完全整除 !!
        //         O(log3(n))   O(1)
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) { // 是3的倍数  ==>  并不代表是3个阶乘 !!
            n /= 3;
        }
        return n == 1; // 最后的基数是1，从1*3的任何次方
        //  return (Math.log10(n) / Math.log10(3)) % 1 == 0; 数学公式计算log3(n)
    }

    // Kick Start 2021
    // For the first day K, next day K+1, K+2 ...
    // At the N day, the sum must equal G value         一旦生产的数量总和超过G, 则结束, 可以相等
    // K + (K+1) + .. (2K+day-1) ... = (2K+day-1)*day / 2 = G  在生产到第n天的"求和公式", 必须严格相等
    private static int test(int g) {
        // 测试理解：1. 结合等差数列的求和公式，推导出所有的可能 O(G*G^0.5)  O(1)
        //            至少有一种方式，是第一天直接生产g个数量
        int count = 1;
        for (int k = 1; k < (g + 1) / 2; k++) {     // 这里可以取到二分之一的位置, 超过中间再double则必然超出 !!
            int day = 1;                            // k < (g + 1) / 2 或者 k <= g / 2
            int sum = k;                            // 起使点的第一天是正数
            while (sum < g) {
                day++;
                sum = ((2 * k + day - 1) * day) / 2;// 1<= G <=10^4 根据G的约束，这里不可能溢出 !!
            }
            if (sum == g) count++;
        }
        return count;
    }

    // Generate Random Number
    // Design a algo to generate a int number between 0 and bound
    // 0 <= ? < bound 什么样的逻辑能够使得数字均匀的生成 ??
    public int getRandomNumber(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("bound must be positive");
        }
        if ((bound & -bound) == bound)  // i.e., bound is a power of 2
            return bound / 2;
        // return (int)((bound * (long)next(31)) >> 31);
        int bits, val;
        do {
            // bits = next(31);
            bits = 10;
            val = bits % bound;
        } while (bits - val + (bound - 1) < 0);
        return val;
    }
}
