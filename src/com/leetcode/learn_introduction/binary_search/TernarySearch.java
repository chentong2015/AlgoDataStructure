package com.leetcode.learn_introduction.binary_search;

// Ternary Search 三元搜索: 二分法搜索进阶
// 1. 将二分的区间进行三分，使用两个middle位置进行判断
// 2. 在某种场景下，能够将时间复杂度从O(log2(n))缩小到O(log3(n)) ==> 但是继续提高分区可能造成时间复杂度的降低 !!
public class TernarySearch {

    // Guess Number Higher or Lower
    // I pick a number from 1 to n. You have to guess which number I picked
    // Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0) return mid1;
            if (res2 == 0) {
                return mid2;
            } else if (res1 < 0) {
                high = mid1 - 1;  // 取三分区间的第一个区间
            } else if (res2 > 0) {
                low = mid2 + 1;   // 取三分区间的第三个区间
            } else {
                low = mid1 + 1;   // 取中间
                high = mid2 - 1;
            }
        }
        return -1;
    }

    private int guess(int num) {
        return 0;
    }
}
