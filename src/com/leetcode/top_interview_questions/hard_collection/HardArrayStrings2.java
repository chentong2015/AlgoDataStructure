package com.leetcode.top_interview_questions.hard_collection;

import java.util.*;

public class HardArrayStrings2 {

    // TODO: First Missing Positive
    // Given an unsorted integer array nums, find the smallest missing positive integer
    // You must implement an algorithm that runs in O(n) time and uses constant extra space 不能使用排序，不能超过O(n)的时间复杂度
    public int firstMissingPositive(int[] nums) {
        // 测试理解：1. 如何确定第一个最小的正数? 忽略重复的值和负数的值, 需要遍历两遍数组 O(2n)=O(n)
        // 6 0 9 2 3 1 -1 -> 4
        int baseValue = 1;
        return 1;
    }

    // Basic Calculator II
    // Given a string s which represents an expression, evaluate this expression and return its value
    // s represents a valid expression: integers and operators('+','-','*','/') 都是正数，且保证值的范围不会溢出 !!
    // Char和Integer之间的转换会根据unicode码值来对应 --> 运算currentChar-'0'或者char-'a'转成对应的整数位置int值
    public int calculate(String s) {
        // 测试理解：1. 如何根据操作符来拆分字符串，获得其中的操作数，必须先算乘除，然后加减
        //            使用栈来记录和统计操作数 O(n) O(n)
        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        int currentNumber = 0;
        char operation = '+'; // 初始第一个数必然是加
        Stack<Integer> stack = new Stack<>();
        s = s.replace(" ", "");
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);     // 将读取的字符合成Integer
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');  // 将数字全部组合起来成int类型的值
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber); // 将减法用负数来存储，最后出栈是统一计算加法 !!
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    // 正确理解：1. 在连续操作的过程中，如果是乘除则立刻计算，如果是加减则暂存一个值，然后结合前面的值进行计算，如此迭代，则不需要stack来存储
    //            O(n) O(1)
    public int calculate2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {  // 遇到加减，则可以更新前面的总计算结果 !! 切断往后
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;  // 用上一个操作数来更新上一个操作数，直到把"乘除运算"算完
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar; // 更新操作符号
                currentNumber = 0;       // 清空记录的当前操作数
            }
        }
        result += lastNumber; // "1+2*3+6*5" 添加最后一个计算结果30
        return result;
    }

    // Minimum Window Substring
    // Given two strings s/m and t/n, return the minimum window in s which will contain all the characters in t.
    // If there is no such window in s that covers all characters in t, return the empty string ""
    // s="ADOBECPMEABCODEBANC", t="ABC" -> "BANC" 如果有结果，那么只有一个最小window substring
    public static String minWindow(String s, String t) {
        // 测试理解：1. 最小子字符串起点必须是t中的字符, 如何保证s和t只被遍历了一遍 ?
        //          2. 如果t中的字符有重复，会有什么影响 ? 意味着只只需要从s中提取一个字符就能满足"包含" ?
        int[] nums = new int[s.length()];
        for (int i = 0; i < t.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (t.charAt(i) == s.charAt(j)) {
                    nums[j] = i + 1;
                }
            }
        }
        int startIndex = 0;
        int minLength = s.length() + 1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                int index = i;
                set.clear();
                while (index < nums.length) {
                    if (nums[index] > 0) {
                        set.add(nums[index]);
                        if (set.size() == t.length()) {
                            if (index - i < minLength) {
                                startIndex = i;
                                minLength = index - i;
                            }
                        }
                    }
                    index++;
                }
            }
        }
        if (minLength < s.length() + 1) {
            return s.substring(startIndex, startIndex + minLength + 1);
        }
        return "";
    }

    // 正确理解: 1.
    //  S = "ABCDDDDDDEEAFFBC" T = "ABC"
    //  filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
    public static String minWindow2(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        Map<Character, Integer> dictT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }

        int required = dictT.size();
        List<Map.Entry<Integer, Character>> filteredS = new ArrayList<>(); // 将t在s中出现的字符及位置提取到列表中
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (dictT.containsKey(c)) {
                filteredS.add(new AbstractMap.SimpleEntry<>(i, c));  // 保留index定位实是为了计算子字符串的间隔 !!
            }
        }

        int left = 0, right = 0, formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        int[] ans = {-1, 0, 0};
        while (right < filteredS.size()) {
            char c = filteredS.get(right).getValue();
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            while (left <= right && formed == required) {
                c = filteredS.get(left).getValue();
                int end = filteredS.get(right).getKey();
                int start = filteredS.get(left).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
