package com.leetcode.top_interview_questions.medium_collection;

public class MediumArrayStrings2 {
    
    // Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s
    // s = "babad" -> "aba" 字符的位置满足什么样的条件是Palindrome, 中心对称分布
    public String longestPalindrome(String s) {
        // 正确理解：1. 遍历每个位置，然后从"中间位置"往两边扩散，直到扩散不动为止 !!
        //             避免遗漏掉相邻的两个字符是对称的情况，所以真正作为中心点的位置数目是"n+(n-1)"包含字符中间位置 !!
        //             O(n*n) O(1)
        if (s == null || s.length() < 1) return "";
        int start = 0;
        int end = 0;
        for (int index = 0; index < s.length(); index++) {
            int len1 = expandAroundCenter(s, index, index);           //  1 "a b b c"    1 "a b b c"
            int len2 = expandAroundCenter(s, index, index + 1); //   0 "a b b c"    2 "a b b c"
            int maxLength = Math.max(len1, len2);
            if (end - start < maxLength) {           // 综合情况：同时满足以一个位置和两个位置为中心的情况 !!
                start = index - (maxLength - 1) / 2; // 1 -(2-1)/2= 1 maxLength在超过2的时候才会左移动
                end = index + maxLength / 2;         // 1 + 2/2 = 2   maxLength在超过1的时候才会右移动，1表示往上一个位置
            }
        }
        return s.substring(start, end + 1); // 子字符串只能取到end位置的闭合区间点 !!
    }

    // 要实现从中间往两边扩展，需要使用两个index，分别为左右边的起使位置
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1; // 由于是对称扩展的，所以实际相隔的位置要-1
    }
}
