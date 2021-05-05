package com.leetcode.top_interview_questions.medium_collection;

public class MediumArrayStrings2 {

    // Longest Substring
    // Given a string s, find the length of the longest substring without repeating characters
    public int lengthOfLongestSubstring(String s) {
        // 测试理解：1. 使用两个index来标记subString的左端和右端位置
        //             O(n*n) 每追加一个字符都需要判断前面是否已经存在该字符 O(1)
        if (s.length() == 0) {
            return 0;
        }
        int left = 0;
        int maxLength = 1;
        for (int right = 1; right < s.toCharArray().length; right++) {
            for (int index = right - 1; index >= left; index--) { // 循环，需要从后往前判断，造成时间复杂度过高 !!
                if (s.charAt(index) == s.charAt(right)) {
                    left = index + 1;                         // 需要移动到index的前面一个位置
                    break;
                }
            }
            maxLength = Math.max(maxLength, right - left + 1); // 算上right右边的那个字符
        }
        return maxLength;
    }

    // 正确理解：1. \去掉内部的嵌套的for循环造成的复杂度\，动态的记录读取每一步的关键信息，判断下一步做出的改变
    //            "动态编程: 每新增一步，使用定长的字符数组，记录累积统计的过程"
    //            int[128] for ASCII, int[256] for Extended ASCII
    //            O(n)  O(m=128)
    public int lengthOfLongestSubstring2(String s) {
        Integer[] chars = new Integer[128]; // char -> int 根据unicode码值自动转换 !!
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            Integer index = chars[r];      // 使用char数组直接获得它的位置
            if (index != null && left <= index && index < right) { // 判断前面是否能找到字符
                left = index + 1;
            }
            res = Math.max(res, right - left + 1);
            chars[r] = right;              // 每读取一个字符，更新字符所在的新的位置，下次如果进来同样的字符，则需要从这后面开始移动
            right++;
        }
        return res;
    }

    // Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s
    // s = "babad" -> "aba" 字符的位置满足什么样的条件是Palindrome, 中心对称分布
    public String longestPalindrome(String s) {
        // 测试理解：1. 暴力算法：列举所有的子字符串，使用isPalindrome()方法逐个判断 !!
        //            O(n^3) O(1)

        // 正确理解：1. 遍历每个位置，然后从"中间位置"往两边扩散，直到扩散不动为止 !!
        //             避免遗漏掉相邻的两个字符是对称的情况，所以真正作为中心点的位置数目是"n+(n-1)"包含字符"中间位置" !!
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
