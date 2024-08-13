package data_structure.arraystring.anagrams;

import java.util.ArrayList;
import java.util.List;

// TODO. 判断是否一个字符串的子字符串包含另一个字符串的全部字符
//  构建判断标准的数组 + 使用数组来做Substring的截取 ！！
//  本质上是在处理SubString，处理String的一个片段Windows
public class SubStringAnagrams2 {

    // Find All Anagrams in a String
    // Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    // You may return the answer in any order.
    // 1 <= s.length, p.length <= 3 * 104
    // str1 and p consist of lowercase English letters.
    // str1 = "cbaebabacd", str2 = "abc" -> [0,6] 在数组中找出包含字符串排序的所有位置
    //
    // O(N + M) N is num of Str chars, M is num of subStr chars
    // O(N)     最糟糕的情况是会产生N个长度的结果，但是int[26]数组不会造成空间复杂度 ！！
    public List<Integer> findAnagrams(String str, String subStr) {
        List<Integer> result = new ArrayList<>();

        // TODO. 用于比较的字符统计数组只需要计算一次
        int[] subStrCounts = new int[26];
        for (char c : subStr.toCharArray()) {
            subStrCounts[c - 'a']++;
        }

        // TODO. 初始化Sliding Window Counts
        // 统计str字符串开头subStr.length()长度的字符
        char[] chars = str.toCharArray();
        int[] strCounts = new int[26];
        for (int index = 0; index < subStr.length(); index++) {
            strCounts[chars[index] - 'a']++;
        }
        if (compareCharArray(strCounts, subStrCounts)) {
            result.add(0);
        }

        // TODO. Sliding Window Index窗口移动
        int index = subStr.length();
        while (index < str.length()) {
            int startIndex = index - subStr.length();
            strCounts[chars[startIndex] - 'a']--;
            strCounts[chars[index] - 'a']++;
            if (compareCharArray(strCounts, subStrCounts)) {
                // 窗口滑动后，统计的SubString的起始点Index应该+1
                result.add(startIndex + 1);
            }
            index++;
        }
        return result;
    }

    // TODO. 每次比较Anagrams时只需要循环26个有限次数, 不造成时间复杂度
    private static boolean compareCharArray(int[] strCounts, int[] subStrCounts) {
        for (int index = 0; index < 26; index++) {
            if (strCounts[index] != subStrCounts[index]) {
                return false;
            }
        }
        return true;
    }
}
