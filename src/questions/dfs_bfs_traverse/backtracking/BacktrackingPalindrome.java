package questions.dfs_bfs_traverse.backtracking;

import java.util.ArrayList;
import java.util.List;

public class BacktrackingPalindrome {

    // Palindrome Partitioning
    // Given a string s, partition s such that every substring of the partition is a palindrome
    // Return all possible palindrome partitioning of s 返回所有的划分组合的可能
    // s = "aab"  -> [["a","a","b"],["aa","b"]]
    // s = "aabb" -> [["a","a","b","b"],["aa","b","b"],["a","a","bb"],["aa","bb"]]
    public List<List<String>> partitionTest(String str) {
        // 测试理解: 1. 所有能拆分出来的子字符串组合，判断有"多少个组合的子字符串"全部满足palindrome条件
        //             3: 1 + 1 + 1; 1 +2 ; 2+1
        //             4: 1+1+1+1; 1+2+1; 1+3; 2+1+1; 2+2; 3+1
        List<List<String>> results = new ArrayList<>();
        partitionDFS(0, str, results, new ArrayList<>());
        return results;
    }

    // O(n^2*2^n) 外层具有O(n)的复杂度，递归的内存也具有O(n)级别的复杂度，内层递归树结点的数目是2^n ??
    // O(n)       存储递归栈的空间Maximum depth of the recursive call stack is N, 每次只取一个字符形成最大深度
    void partitionDFS(int start, String str, List<List<String>> results, List<String> currentList) {
        if (start >= str.length()) {
            results.add(new ArrayList<>(currentList));           // DFS到底，添加有效结果成为结果之一
        }
        for (int index = start; index < str.length(); index++) { // 每次切分字符串的一个部分来判断，如果是则继续往内存递归
            if (isPalindrome(str, start, index)) {
                currentList.add(str.substring(start, index + 1));
                partitionDFS(index + 1, str, results, currentList);
                currentList.remove(currentList.size() - 1); // 回溯的核心：回退一步到调用该方法的上一层去添加新的组合 !!
            }
        }
    }

    private boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
