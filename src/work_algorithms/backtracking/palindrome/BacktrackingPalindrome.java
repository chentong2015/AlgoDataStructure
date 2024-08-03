package work_algorithms.backtracking.palindrome;

import java.util.ArrayList;
import java.util.List;

// Palindrome Partitioning
// Given a string s, partition s such that every substring of the partition is a palindrome
// Return all possible palindrome partitioning of s 返回所有的划分组合的可能
// s = "aab"  -> [["a","a","b"],["aa","b"]]
// s = "aabb" -> [["a","a","b","b"],["aa","b","b"],["a","a","bb"],["aa","bb"]]
public class BacktrackingPalindrome {

    // O(n^2*2^n) 外层具有O(n)的复杂度，递归的内存也具有O(n)级别的复杂度，内层递归树结点的数目是2^n ??
    // O(n)       存储递归栈的空间Maximum depth of the recursive call stack is N, 每次只取一个字符形成最大深度
    public List<List<String>> partitionTest(String str) {
        List<List<String>> results = new ArrayList<>();
        partitionDFS(0, str, results, new ArrayList<>());
        return results;
    }
    
    // DFS到底之后，将结果转移到新的ArrayList并添加到最后结果集中
    void partitionDFS(int start, String str, List<List<String>> results, List<String> currentList) {
        if (start >= str.length()) {
            results.add(new ArrayList<>(currentList));
        }
        for (int id = start; id < str.length(); id++) {
            if (isPalindrome(str, start, id)) {
                String subStrPalindrome = str.substring(start, id + 1); // 这里的子字符串将是有效的结果
                currentList.add(subStrPalindrome);

                partitionDFS(id + 1, str, results, currentList);

                // 回溯的核心：回退一步到调用该方法的上一层去添加新的组合
                int lastSubStrIndex = currentList.size() - 1;
                currentList.remove(lastSubStrIndex);
            }
        }
    }

    // 判断是否满足palindrome条件
    private boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
