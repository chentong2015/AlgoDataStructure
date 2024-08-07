package work_algorithms;

// How can we reuse a previously computed palindrome to compute a larger palindrome ?
// https://leetcode.com/problems/longest-palindromic-substring/description/
public class LongestPalindromicSubsequence {

    // TODO. 使用DP编程：之前的计算结果对于后续的计算有何作用 ？
    // Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s
    // s = "babad" -> "aba" 字符的位置满足什么样的条件是Palindrome, 中心对称分布
    public String longestPalindromeDP(String str) {
        return null;
    }

    // 以下方法为暴力解法，时间复杂度不佳
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int index = 0; index < s.length(); index++) {
            // 避免遗漏掉相邻的两个字符是对称的情况，真正中心点位置数目是"n+(n-1)"包含字符中间位置
            int len1 = expands(s, index, index);          // 1 "a b b c"    1 "a b b c"
            int len2 = expands(s, index, index + 1); // 0 "a b b c"    2 "a b b c"
            int maxLength = Math.max(len1, len2);

            int currentLength = end - start;
            if (currentLength < maxLength) {         // 以index为中心，更新palindromic子字符串的起使和结束的位置
                start = index - (maxLength - 1) / 2; // 1 -(2-1)/2= 1 maxLength在超过2的时候才会左移动
                end = index + maxLength / 2;         // 1 + 2/2 = 2   maxLength在超过1的时候才会右移动，1表示往上一个位置
            }
        }
        return s.substring(start, end + 1);
    }

    // 从中心往两边延申，直到不在相等
    // 此时的Palindromic的长度是(right-left-2)+1
    private int expands(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
