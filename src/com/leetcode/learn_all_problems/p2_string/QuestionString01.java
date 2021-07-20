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

    public static void main(String[] args) {
        System.out.println(reverseString("gk"));
    }
}
