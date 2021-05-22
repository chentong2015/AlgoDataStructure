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

    // Missing Number
    // Given an array nums containing n distinct numbers in the range [0, n], all the numbers of nums are unique
    // Return the only number in the range that is missing from the array
    // nums = [3,0,1] -> 2
    // nums = [0,1]   -> 2 从0到2的数的闭合区间范围 !!
    public int missingNumber(int[] nums) {
        // 测试理解：1. 理论上，用[0, n]的总值减去nums的总值，差值就是缺失的那个值 ===> 可能会造成int类型的值溢出，取决于数组中值的范围 !!

        // int sumRange = 0; 这个变量可以不用，直接在sumNums上面做加减法       ===> 减少了2MB内存的开销 !!
        int sumNums = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sumNums += i - nums[i];
        }
        return sumNums;
    }

    // 正确理解：1. Gauss Formula 将上述的计算抽象出来，直接使用length计算总和sum，然后减去数组中值的总和
    //         2. Bit Manipulation 使用位运算，XOR异或计算之后，留下来的就是最后的结果
    //            missing =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4) = 2 除了数字2意外，其他的数字都会最后"两两相消"
    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
