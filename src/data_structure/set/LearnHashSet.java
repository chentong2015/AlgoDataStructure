package data_structure.set;

import java.util.HashSet;

public class LearnHashSet {


    // Happy Number
    // Starting with any positive integer, replace the number by the sum of the squares of its digits
    // Input: n = 19  -> true
    // 1^2 + 9^2 = 82 -> 8^2 + 2^2 = 68 -> 6^2 + 8^2 = 100 -> 1^2 + 0^2 + 0^2 = 1 直到结果迭代到1
    public boolean isHappy(int n) {
        // 正确理解：1. 需要一个额外的存储结构来判断计算的结果是否出现过，如果出现就表示循环，无解
        //            O(?) 时间复杂度没有统一的逻辑规律!! O(n)
        HashSet<Integer> set = new HashSet<>();
        int sum = calculateSum(n);
        set.add(sum);
        while (sum != 1) {
            sum = calculateSum(sum);
            if (set.contains(sum)) return false;
            set.add(sum);
        }
        return true;
    }

    private int calculateSum(int n) {
        int sum = 0;
        while (n / 10 > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        sum += n * n;
        return sum;
    }
}
