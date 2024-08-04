package data_structure.arraystring.anagrams;

import java.util.ArrayList;
import java.util.List;

// TODO. 使用int[]数组类累计SubString判断要比使用StringBuilder来截取转换要更高效
public class SubStringAnagrams {

    // TODO. 本质上获取SubString时每次只变化两个字符，因此不需要用StringBuilder将所有字符再刷一遍 ！！
    // 判断一个字符串是否包含另一个字符串(字符)的所有排列
    // Contain Permutation of SubString
    // s1 and s2 consist of lowercase English letters
    // 1 <= s1.length, s2.length <= 10^4
    //
    // str1="hskoebacdh", str2 = "abc", -> true
    //
    // O(N*M) 每循环一个字符都需要循环str2.length()的次数来生成key判断
    // O(M)   开辟了空间来存储Str2长度的数据，造成空间复杂度
    public static boolean containsPermutationOfSubstring(String str1, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index=0; index < str2.length(); index++) {
            stringBuilder.append(str1.charAt(index));
        }

        int right = str2.length() - 1;
        String str2Key = generateAnagramsKey(str2);
        while (right < str1.length()) {
            String str1Key = generateAnagramsKey(stringBuilder.toString());
            if (str1Key.equals(str2Key)) {
                return true;
            }

            right++;
            if (right == str1.length()) {
                return false;
            }
            stringBuilder.deleteCharAt(0);
            stringBuilder.append(str1.charAt(right));
        }
        return false;
    }

    // TODO. 每次生成key时都遍历str2.length()长度的字符并且将char[]转换成String，造成时间复杂度
    private static String generateAnagramsKey(String str) {
        char[] chars = new char[26]; // only 26 types of letters
        for (char c: str.toCharArray()) {
            chars[c - 'a']++;
        }
        return new String(chars);
    }

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

        // 统计str字符串开头subStr.length()长度的字符
        char[] chars = str.toCharArray();
        int[] strCounts = new int[26];
        for (int index = 0; index < subStr.length(); index++) {
            strCounts[chars[index] - 'a']++;
        }

        // 需要两个index坐标来控制字符的添加和删除
        int left = 0;
        int right = subStr.length() - 1;
        while (right < str.length()) {
            if (compareCharArray(strCounts, subStrCounts)) {
                result.add(left);
            }
            strCounts[chars[left] - 'a']--; // 从临时数组中移除左边位置坐标的统计

            left++;
            right++;
            if (right < str.length()) {  // 往后移动坐标，需要判断index位置是否处于溢出状态
                strCounts[chars[right] - 'a']++; // 同时增加新的字符统计
            }
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
