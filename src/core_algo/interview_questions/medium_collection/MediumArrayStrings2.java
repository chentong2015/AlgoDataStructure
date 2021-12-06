package core_algo.interview_questions.medium_collection;

public class MediumArrayStrings2 {

    // TODO: 参照DP编程算法 "Longest Palindromic Subsequence" 能够判断最长的长度 !!
    // Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s
    // s = "babad" -> "aba" 字符的位置满足什么样的条件是Palindrome, 中心对称分布
    public String longestPalindrome(String s) {
        // 正确理解：1. 遍历每个位置，然后从"中间位置"往两边扩散，直到扩散不动为止 !!
        //            避免遗漏掉相邻的两个字符是对称的情况，所以真正作为中心点的位置数目是"n+(n-1)"包含字符中间位置 !!
        //            O(n*n) O(1)
        int start = 0;
        int end = 0;
        for (int index = 0; index < s.length(); index++) {
            int len1 = expandAroundCenter(s, index, index);          // 1 "a b b c"    1 "a b b c"
            int len2 = expandAroundCenter(s, index, index + 1); // 0 "a b b c"    2 "a b b c"
            int maxLength = Math.max(len1, len2);
            if (end - start < maxLength) {                           // 以index为中心，更新palindromic子字符串的起使和结束的位置
                start = index - (maxLength - 1) / 2;                 // 1 -(2-1)/2= 1 maxLength在超过2的时候才会左移动
                end = index + maxLength / 2;                         // 1 + 2/2 = 2   maxLength在超过1的时候才会右移动，1表示往上一个位置
            }
        }
        return s.substring(start, end + 1);
    }

    // 从中心往两边延申，直到不在相等，此时的Palindromic的长度是(right-left-2)+1
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
