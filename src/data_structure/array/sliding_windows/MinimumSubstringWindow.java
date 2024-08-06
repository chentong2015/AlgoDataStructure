package data_structure.array.sliding_windows;

// TODO. 滑动窗口算法高阶版本: 在滑动的过程中抵消字符的统计变化
//  第一轮统计：   [0,0, 0, 0,,,,,1,2,0,2,0,,,]
//  第一轮窗口移动：[0,0,-1,-1,,,,,0,0,0,0,0,,,] 将统计的字符全部抵消成0，则SubString区间包含前字符
//     start滑动: [0,0, 0,-1,,,,,0,0,0,0,0,,,] 将无关的字符+1恢复
//     start滑动: [0,0, 0, 0,,,,,0,0,0,0,0,,,] 将无关的字符+1恢复
//     start滑动: [0,0, 0, 0,,,,,1,0,0,0,0,,,] 将查找的字符+1统计
//  第二轮窗口移动：[0,-1,0, 0,,,,,0,0,0,0,0,,,] 找到字符抵消count=0
//     start滑动: [0,0, 0, 0,,,,,0,0,0,0,0,,,] 将无关的字符+1回复
//     start继续: 缩短窗口的统计距离
public class MinimumSubstringWindow {


    // 找出最小的能够覆盖"另一个字符串所有字符"的窗口SubString
    // Minimum Window Substring
    // Given two strings s and t, return the min window in s which will contain all characters in t.
    // If there is no such window in s that covers all characters in t, return the empty string ""
    // s and t consist of uppercase and lowercase English letters.
    // 如果有结果则只有一个最小window substring
    //
    // s="SADOBEC PMEABCODEBANC", t="ABC" -> "BANC"
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() ||
                s.length() < t.length()) {
            return "";
        }
        int startIndex =0;
        int minLen = Integer.MAX_VALUE;

        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        int start = 0;
        int end = 0;
        int count = t.length();
        char[] chS = s.toCharArray();
        while (end < chS.length) {
            // 1. 每移动一个end，重新计算统计map，抵消target字符
            // 多次出现则只需要抵消target一次
            if (map[chS[end]] > 0) {
                count--;
            }
            map[chS[end]]--;
            end++;

            // 2. 滑动窗口从start往右滑动，每一个字符都是之前标记过的char
            while (count == 0) {
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }
                // 0说明为target字符，说明是被抵消到0的字符
                // 当滑动start index过程中出现重复的target字符时，只考虑第一次
                if (map[chS[start]] == 0) {
                    count++;
                }
                map[chS[start]]++;
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "": new String(chS, startIndex, minLen);
    }
}
