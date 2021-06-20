package com.leetcode.top_interview_questions.hard_collection;

import java.util.*;

public class HardArrayStrings3 {

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

    // 正确理解: 1. S = "ABCDDDDDDEEAFFBC" T = "ABC"
    // filtered_S = [(0, 'A'), (1, 'B'), (2, 'C'), (11, 'A'), (14, 'B'), (15, 'C')]
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
