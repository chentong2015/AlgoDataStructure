package questions.greedy_dynamic_pro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DynamicProgramming3 {

    // TODO: 动态编程的标准用法，归避掉由于暴力破解带来的原始时间复杂度(数字上的可能情况)
    //  1. 首先使用递归拆分，解决问题
    //  2. 使用存储空间memoization table，记录迭代的结果
    // Word Break
    // Given a string s and a dictionary of strings wordDict (all strings are unique)
    // Return true if s can be segmented into a space-separated sequence of one or more dictionary words
    // s = "cars"         wordDict = ["car","ca","rs"]             -> true
    // s = "aaaaaaaaaaab" wordDict = ["a","aa","aaa","aaaa"]       -> false
    public boolean wordBreak(String str, List<String> wordDict) {
        // 测试理解：1. 从原始字符串出发，在每个位置截取，然后递归判断两个部分的字符串是否能够在wordDict中找到
        //            O(2^n) 可以拆分成的子字符串的数目，每个位置可能有，可能没有
        //            O(n)   根据拆分的子字符串，最多可能递归的(深度)复杂度，栈空间的开销
        if (wordDict.contains(str)) return true;
        for (int i = 0; i < str.length() - 1; i++) {
            boolean result = false;
            if (wordDict.contains(str.substring(0, i + 1))) {
                result = wordBreak(str.substring(i + 1), wordDict);
            }
            if (result) return true;
        }
        return false;
    }

    // 1. 对于字符串的任意位置index, 如果能够通过前面的子字符串的组合(以某种方式到达index位置)
    //    那么index位置就是true, 借助前面的记录推导到最后str.length()结束位置 !!
    //    O(n*n*n) .substring()方法会造成一定的时间复杂度
    //    O(n)     空间复杂度由Hash Table和dp数组构成
    public boolean wordBreakDP(String str, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);  // 直接用List构建Hash Table
        boolean[] dp = new boolean[str.length() + 1];       // 默认length+1里面的初始值均为false
        dp[0] = true;
        for (int index = 1; index <= str.length(); index++) {           // 利用DP数组来完成迭代和累计
            // 判断该index位置是否能够通过前面某个位置到达，做好标记
            for (int left = 0; left < index; left++) {
                // 只有当dp[left]为真的时候，才计算后面的判断.substring()，可以减低时间复杂度
                if (dp[left] && wordDictSet.contains(str.substring(left, index))) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[str.length()];
    }

    // Word Break II
    // Given a string s and a dictionary of strings wordDict
    // Add spaces in s to construct a sentence where each word is a valid dictionary word
    // Return all such possible sentences in any order                 找出不止一种，返回所有的可能，需要一种存储的机制
    // s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]  提供的数量有一定的约束，单词可以多次使用，只包含小写字母
    // ->  ["cats and dog", "cat sand dog"]
    // s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"] wordDict里面提供的单词是不重复的
    // ->  ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
    public List<String> wordBreak2(String str, List<String> wordDict) {
        // 本质上是在字符串的每个字符区间位置，添加或者不添加空格 !! O(2^n)种可能
        // 1. 自顶向下，使用递归找出所有的组合可能，每一种连接通过StringBuilder来连接  ==> 复杂度过高
        // 2. 自底向上，存储没有位置能够拆分出来的情况                              ==> 如何存储所有的情况 ?
        // 3. 使用DP二维空间

        return null;
    }
}
