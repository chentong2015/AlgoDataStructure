package data_structure.arraystring.windows;

// TODO. 滑动窗口加强版
// - 需要根据条件，反复的变化窗口的大小
// - 将字符串问题转换成字符数组来解决，存储滑动窗口每一步的结果值
public class MinimumWindowSubstring {

    // Minimum Window Substring
    // Given two strings s and t, return the min window in s which will contain all characters in t.
    // If there is no such window in s that covers all characters in t, return the empty string ""
    // s and t consist of uppercase and lowercase English letters. 全部使用英文字符
    //
    // s="SADOBECPMEABCODEBANC", t="ABC" -> "BANC" 如果有结果则只有一个最小window substring
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int start = 0; // 滑动窗口的双指针
        int end = 0;
        int startIndex =0; // 只需要起始点和长度将能获取子字符串
        int minLen = Integer.MAX_VALUE;

        int count = t.length();
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }

        char[] chS = s.toCharArray();
        while (end < chS.length) {
            if (map[chS[end++]]-- > 0) {
                count--;
            }
            while (count == 0) {
                // 当t中的所有字符全部被抵消之后，记录符合条件的substring位置信息
                if (end - start < minLen) {
                    startIndex = start;
                    minLen = end - start;
                }

                // 移动左侧的index坐标，重新统计到map数组中
                // 只有当后续添加的字符将其抵消后，才满足count==0
                if (map[chS[start++]]++ == 0) {
                    count++;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "": new String(chS, startIndex, minLen);
    }
}
