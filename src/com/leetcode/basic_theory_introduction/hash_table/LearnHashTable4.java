package com.leetcode.basic_theory_introduction.hash_table;

import java.util.HashSet;
import java.util.Set;

public class LearnHashTable4 {

    // Jewels and Stones
    // You're given strings jewels representing the types of stones that are jewels
    // Stones representing the stones you have. Each character in stones is a type of stone you have
    // You want to know how many of the stones you have are also jewels
    // Letters are case sensitive, All the characters of jewels are unique 明确约束条件
    // jewels = "aA", stones = "aAAbbbb" -> 3
    public int numJewelsInStones(String jewels, String stones) {
        // Test: 将是珠宝的石头类型读取到set中存储，然后判断所有的石头有那些是珠宝
        //       O(n+m) O(n) n<=m 空间复杂度
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c : jewels.toCharArray()) set.add(c);
        for (char c : stones.toCharArray()) {
            if (set.contains(c)) count++;
        }
        return count;
    }

    // 正确理解：
    // 1. return S.replaceAll("[^" + J + "]", "").length(); 现有方法的实现
    // 2. 不使用额外的存储空间来实现，利用约束条件和int<->char之间的转换
    public int numJewelsInStones2(String jewels, String stones) {
        int[] stoneNum = new int[58];    // 给定的长度有限制，因此使用定长的数组 !!
        for (char stone : jewels.toCharArray()) {
            stoneNum[stone - 65]++;      // ASCII码值 A -> 65
        }
        int sum = 0;
        for (char jewel : stones.toCharArray()) {
            sum += stoneNum[jewel - 65]; // 如果是珠宝，则指定index的位置上为1，进行累加 !!
        }
        return sum;
    }
}
