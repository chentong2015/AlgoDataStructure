package com.leetcode.learn_introduction.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class LearnBFS {

    // Open the Lock
    // 4 circular wheels. Each wheel has 10 slots (0-9)(9-0) 数字循环，在相邻的两个数字可以自由移动
    // The lock initially starts at '0000', can not reach "deadends"
    // Given a target representing the value of the wheels that will unlock the lock,
    // return the minimum total number of turns, -1 if it's impossible
    //      0101   0102
    //     1212      2002
    //    0201        '0202'
    // "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202" 在数字变化的过程中，需要使用到char和int的转换

    // 0001  0010  0100  1000
    //          0000           -> 每一个结点可以延展开8个位置，每一个位置又能恢复成原来的位置，造成循环 !!
    // 0009  0090  0900  9000  -> 每变化一次，作为是一层的展开，记作一步统计 !!
    public int openLock(String[] deadends, String target) {
        // 测试理解：1. 逆向推导，从目标的值恢复成0000，避开死锁的情况下最快需要几步
        //            使用BFS算法，将该字符移动一步可以达到的相关位置都入队列，然后从第二层移动到第三层，最后判断结果是否能够到0000
        if (target.equals("0000")) return 1;
        HashSet<String> setDeadEnds = new HashSet<>();
        for (String end : deadends) setDeadEnds.add(end);

        HashSet<String> set = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(target);
        while (!queue.isEmpty()) {
            int value1 = target.charAt(0) - '0';
            int value2 = target.charAt(1) - '0';
            int value3 = target.charAt(2) - '0';
            int value4 = target.charAt(3) - '0';
            // 可以使用StringBuilder来优化字符的处理
            // 依次变化每一个字符，添加到队列和Set中，直到队列为空，或者到达了"0000"
            if (value1 == 0) {
                String newValue1 = "9" + value2 + value3 + value4;
                if (!setDeadEnds.contains(newValue1) && !set.contains(newValue1)) {
                    queue.add(newValue1);
                    set.add(newValue1);
                }
            } else {
                String newValue2 = String.valueOf(value1 - 1) + value2 + value3 + value4;
            }
            String newValue3 = String.valueOf(value1 + 1) + value2 + value3 + value4;
        }
        return -1; // It is impossible
    }

    // TODO: BFS通用广度优先遍历，结合Dynamic Programming动态编程 / 递归写法 ??
    // Perfect Squares
    // Given an integer n, return the least number of perfect square numbers that sum to n
    // A perfect square is an integer that is the square of an integer: 1 4 9 16 25 ...
    // Input: n = 12 -> 12 = 4 + 4 + 4 -> 3 最少需要3个perfect square数值组成
    //            12
    //      9           4        1   数值展开，记录增加的层级，同时对应不同的差值，在继续迭代(12-9), (12-4), (12-1)
    //  9   4    1     4  1      1   下层展开，计算(12-9-9)...(12-4-4)...
    // ..  4 1   1    4 1  1     1
    public int numSquares(int n) {
        // 测试理解：1. 展开的形式是一个任意的tree树，最小perfect square的数目，也就是树的最低深度 !!
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return dpCount(n, dp);
    }

    private int dpCount(int n, int[] dp) {
        if (dp[n] != -1) return dp[n]; // 如果指定的数值已经推导出结果，则直接返回，避免循环
        if (n == 0) return 0;          // 说明上一层的(n-index*index)刚好完全
        int min = Integer.MAX_VALUE;   // 使用index平方而不是index，能充分的降低时间复杂度
        for (int index = 1; index * index <= n; index++) {
            int currentLevel = 1 + dpCount(n - index * index, dp);
            min = Math.min(min, currentLevel);
        }
        return dp[n] = min;            // 动态记录第n个位置的结果
    }
}
