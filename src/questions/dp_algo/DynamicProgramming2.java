package questions.dp_algo;

import beans.PatternResult;

// Regular Expression Matching ==> Java底层正则表达式的实现
// Implement regular expression matching with support for '.' 任意多个字符 and '*' 前字符重复0到n次
// The matching should cover the entire input string (not partial)
// s = "ab", p = ".*"      -> true
// s = "aab", p = "c*a*b"  -> true 第一个c字符有可能重复0次
public class DynamicProgramming2 {

    // TODO. 1 Recursive backtracking
    // 从第一个字符和第二个字符开始递归，每次往后截取text的子字符串，并且带上前一轮判断的结果
    // <复杂度和text & pattern字符串长度均有关>
    public boolean isMatchRecursive(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        // 先确定第一步开头是匹配的，再在此基础上列举出递归的匹配条件
        boolean firstMatch = false;
        if (!text.isEmpty()) {
            firstMatch = text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.';
        }

        // text比较完第一个字符之后，需要往后截取     (*>0, *将会继续作用在开头的字符，一直匹配到不能再匹配为止)
        // 或者不截取text, 从第3个字符开始截取pattern(*==0)
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return firstMatch && isMatchRecursive(text.substring(1), pattern) || isMatchRecursive(text, pattern.substring(2));
        } else {
            return firstMatch && isMatchRecursive(text.substring(1), pattern.substring(1));
        }
    }

    // TODO 2. Up-Bottom Variation 自顶向下
    // 分而治之，拆分问题，使用二维数组来存储判断的结果值，避免递归 O(TP) O(TP)
    public boolean isMatchUpBottom(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    // TODO 3. Bottom-Up Variation 自底向上
    private PatternResult[][] memo;

    public boolean isMatchBottomUp(String text, String pattern) {
        memo = new PatternResult[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == PatternResult.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? PatternResult.TRUE : PatternResult.FALSE;
        return ans;
    }
}
