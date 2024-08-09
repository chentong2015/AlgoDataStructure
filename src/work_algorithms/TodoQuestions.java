package work_algorithms;

import java.util.*;

public class TodoQuestions {

    // TODO. 重新思考低算法复杂度的实现
    // Word Ladder
    // Given two words, beginWord and endWord, and a dictionary wordList,
    // return the number of words in the shortest transformation sequence from beginWord to endWord,
    // return 0 if no such sequence exists
    // beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    // -> "hit" -> "hot" -> "dot" -> "dog" -> cog" -> 5
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList); // 从List转成HashSet, 去除重复单词并快速查找
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                char[] current = queue.poll().toCharArray();
                for (int j = 0; j < current.length; j++) {
                    char temp = current[j];                  // 每次只能变化一个位置上的单词
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);   // 依次替换每个位置上的字符，构建新的字符串，查找是否具有构建出来的单词
                        if (set.contains(next)) {
                            if (next.equals(endWord)) {      // 当有路径到达list中的单词，并且是结尾单词，则结束 !!
                                return count + 1;
                            }
                            queue.add(next);  // 添加到队列中，作为下一个查找的单词的起点
                            set.remove(next); // 移除掉给的单词列表中的单词，并且count已经添加步数
                        }
                    }
                    current[j] = temp;
                }
            }
            count++; // Queue中一个单词查找完，换下一个新的单词，增加步数
        }
        return 0;
    }




    // How can we reuse a previously computed palindrome to compute a larger palindrome ?
    // https://leetcode.com/problems/longest-palindromic-substring/description/

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
