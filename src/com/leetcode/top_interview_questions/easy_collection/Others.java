package com.leetcode.top_interview_questions.easy_collection;

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
}
