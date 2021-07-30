package com.leetcode.learn_all_problems.p2_string;

public class QuestionString01 {

    // Reverse a string using recursive 源码实现stringBuilder.reverse();
    // 1. 根据坐标index位置，从两端往中间进行递归
    // 2. 转换成数组问题，直接从左右两端遍历交换
    public static String reverseString(String str) {
        if (str == null || str.length() < 1) return null;
        return recursive(str.toCharArray(), 1);
    }

    private static String recursive(char[] chars, int index) {
        int length = chars.length;
        if (index < length / 2 + 1) {
            char temp = chars[index - 1];
            int swapIndex = length - index;
            chars[index - 1] = chars[swapIndex];
            chars[swapIndex] = temp;
            recursive(chars, index + 1);
        }
        return new String(chars);
    }

    // Repeated String Match
    // Given two strings a and b, return the minimum number of times you should repeat string a
    // so that string b is a substring of it.
    // If it is impossible for b to be a substring of a after repeating it, return -1
    // Input: a = "abcd", b = "cdabcdab" -> "abcdabcdabcd" -> 3 将A重复3次之后则包含字符串B
    // Input: a = "a", b = "aa"          -> "aa"           -> 2
    public int repeatedStringMatch(String a, String b) {
        if (a == null || a.length() < 1) return -1;
        if (b == null || b.length() < 1) return -1;
        StringBuilder strBuilder = new StringBuilder(a);
        int countRepeatTimes = 0;
        while (strBuilder.length() < b.length()) {  // A字符串重复的长度如果超过B，则跳出循环，最多还可以再重复一次Ａ
            countRepeatTimes++;
            strBuilder.append(a);
        }
        if (strBuilder.toString().contains(b)) return countRepeatTimes;
        if (strBuilder.append(a).toString().contains(b)) return countRepeatTimes + 1;
        return -1;
    }
}
