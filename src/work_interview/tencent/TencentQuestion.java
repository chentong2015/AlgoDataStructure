package work_interview.tencent;

import java.util.ArrayList;
import java.util.List;

// Tencent Interview Question
// Permutation in String 判断一个字符串是否包含另一个字符串(字符)的所有排列
// 1 <= s1.length, s2.length <= 10^4  需要根据特殊情况做几个判断测试
// s1 and s2 consist of lowercase English letters
// str1 = "abc", str2="hskoebacdh" -> true
public class TencentQuestion {

    // TODO. 利用char和int的映射关系，做char字符的统计，利用指针做移动进行遍历
    // O(n1+n2) O(1)
    public boolean checkContainsSubstring(String str1, String str2) {
        // 判断那个字符更长
        int[] counts = new int[26];
        for (char c : str1.toCharArray()) {
            counts[c - 'a']++;
        }

        char[] chars = str2.toCharArray();
        int[] tempCounts = new int[26];
        for (int index = 0; index < str1.length(); index++) {
            tempCounts[chars[index] - 'a']++;
        }

        int left = 0;
        int right = str1.length() - 1;
        while (right < str2.length()) {
            if (compareCharArray(counts, tempCounts)) {
                return true;
            }
            tempCounts[chars[left] - 'a']--;  // 从临时数组中移除左边位置坐标的统计
            left++;
            right++;
            if (right < str2.length()) {      // 往后移动坐标，需要判断index位置是否处于溢出状态
                tempCounts[chars[right] - 'a']++; // 同时增加新的字符统计
            }
        }
        return false;
    }

    private boolean compareCharArray(int[] char1, int[] char2) {
        for (int index = 0; index < 26; index++) {
            if (char1[index] != char2[index]) {
                return false;
            }
        }
        return true;
    }

    // Find All Anagrams in a String
    // Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    // You may return the answer in any order.
    // 1 <= s.length, p.length <= 3 * 104
    // s and p consist of lowercase English letters.
    // s = "cbaebabacd", p = "abc" -> [0,6] 在数组中找出包含字符串排序的所有位置
    public List<Integer> findAnagrams(String str2, String str1) {
        List<Integer> result = new ArrayList<>();

        int[] counts = new int[26];
        for (char c : str1.toCharArray()) {
            counts[c - 'a']++;
        }

        char[] chars = str2.toCharArray();
        int[] tempCounts = new int[26];
        for (int index = 0; index < str1.length(); index++) {
            tempCounts[chars[index] - 'a']++;
        }

        int left = 0;
        int right = str1.length() - 1;
        while (right < str2.length()) {
            if (compareCharArray(counts, tempCounts)) {
                result.add(left);             // 统计正确的子字符串的位置坐标
            }
            tempCounts[chars[left] - 'a']--;  // 从临时数组中移除左边位置坐标的统计
            left++;
            right++;
            if (right < str2.length()) {      // 往后移动坐标，需要判断index位置是否处于溢出状态
                tempCounts[chars[right] - 'a']++; // 同时增加新的字符统计
            }
        }
        return result;
    }
}
