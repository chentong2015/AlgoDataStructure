package data_structure.array.sliding_windows;

// TODO. 滑动窗口算法高阶版本: 在滑动的过程中抵消字符的统计变化
public class MinimumSubstringWindow {

    public static void main(String[] args) {
        System.out.println(minWindow("SADOBECPMEABCODEBANC", "ABC"));
    }

    // 找出最小的能够覆盖"另一个字符串所有字符"的窗口SubString
    // Minimum Window Substring
    // Given two strings s and t, return the min window in s which will contain all characters in t.
    // If there is no such window in s that covers all characters in t, return the empty string ""
    // s and t consist of uppercase and lowercase English letters.
    // 如果有结果则只有一个最小window substring
    //
    // s="SADOBECPMEABCODEBANC", t="ABC" -> "BANC"
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int startIndex =0; // 只需要起始点和长度将能获取子字符串
        int minLen = Integer.MAX_VALUE;

        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++; // 统计char字符的出现
        }

        int start = 0;
        int end = 0;
        int count = t.length();
        char[] chS = s.toCharArray();
        while (end < chS.length) {
            if (map[chS[end]] > 0) {
                map[chS[end]]--; // 抵消统计的char字符
                count--;
            }
            end++;
            while (count == 0) {
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }
                // Increment start and increase the frequency in map
                // until the window no longer contains all characters from t.
                if (map[chS[start++]]++ == 0) {
                    count++;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "": new String(chS, startIndex, minLen);
    }
}
