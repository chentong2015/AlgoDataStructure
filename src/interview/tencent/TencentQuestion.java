package interview.tencent;

import java.util.ArrayList;
import java.util.List;

public class TencentQuestion {

    // Tencent Interview Question
    // Permutation in String 判断一个字符串是否包含另一个字符串(字符)的所有排列
    // 1 <= s1.length, s2.length <= 10^4  需要根据特殊情况做几个判断测试
    // s1 and s2 consist of lowercase English letters
    // str1 = "abc", str2="hskoebacdh" -> true
    // 测试理解 1: 将str1排序成mapKey，判断str2中是否有这key所在的区间 ==> 复杂度不好控制
    public boolean checkContains(String str1, String str2) {
        int[] counts = new int[26];
        for (char c : str1.toCharArray()) {
            counts[c - 'a']++;
        }

        StringBuilder subString = new StringBuilder();
        int countChars = 0;
        for (int index = 0; index < str2.length(); index++) {
            int findIndex = str2.charAt(index) - 'a';
            if (counts[findIndex] > 0) {
                countChars++;
                subString.append(str2.charAt(index));
                if (countChars == str1.length()) {
                    // 判断提取的subString是否符合counts数组统计出来的值...
                } else if (countChars > str1.length()) {
                    // 删除截取的开头字符，然后比较subString
                    subString.deleteCharAt(0);
                }
            } else {
                countChars = 0;
                // 清空已经提取的子字符串，重新截取
                if (subString.length() > 0) {
                    subString = new StringBuilder();
                }
            }
        }
        return false;
    }

    // TODO: 涉及到字符串截取或首尾增删，使用char数组进行统计，通过index在数组中定位，而不使用StringBuilder来过渡
    // 解法1. Sliding Window滑动窗口算法:
    //       将str1的每一个字符统计到数组中，然后在str2中依次框选指定的区间进行比较
    //       O(L1+26*(L2-L1))  O(1)
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        // TODO. [index i, index i + s1.length]区间构成滑动窗口
        // 从s2中截取指定的片段，每次移除一个字符，再添加一个字符的(位置)统计
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            // TODO. 这里matches String的循环次数是26常数，对时间复杂读不会造成根本的改变
            if (matches(s1map, s2map)) {
                return true;
            }
            s2map[s2.charAt(i) - 'a']--;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
        }
        // 判断最后一个添加的字符是否满足
        return matches(s1map, s2map);
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) return false;
        }
        return true;
    }

    // TODO: 在每次滑动一个char位置的时候，没有必要再次循环整个char数组，只需要判断差异的位置
    // 解法 2. Optimized Sliding Window滑动窗口进阶版本
    //        O(L1+(L2-L1))  O(1)
    public boolean checkInclusionOptimized(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        // 比较开头统计的是否满足: 先统计有多少的数组位置是相同的
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (s1map[i] == s2map[i]) count++;
        }
        // 如果两个数组26个位置均相同，则满足字符串包含的条件
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int left = s2.charAt(i) - 'a';
            int right = s2.charAt(i + s1.length()) - 'a';
            if (count == 26) return true;
            // 只需要定位到指定的位置，避免数组循环
            s2map[right]++;
            if (s2map[right] == s1map[right]) {
                count++; // 位置上原来不等，现在等，则+1
            } else if (s2map[right] == s1map[right] + 1) {
                count--; // 位置上原来等，现在不等，则-1
            }
            s2map[left]--;
            if (s2map[left] == s1map[left]) {
                count++;
            } else if (s2map[left] == s1map[left] - 1) {
                count--;
            }
        }
        return count == 26;
    }

    // Find All Anagrams in a String
    // Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    // You may return the answer in any order.
    // 1 <= s.length, p.length <= 3 * 104
    // s and p consist of lowercase English letters.
    // s = "cbaebabacd", p = "abc" -> [0,6] 在数组中找出包含字符串排序的所有位置
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        // 在上面判断 if (count == 26) return true;
        // 直接添加此时index的位置到list结果中
        return result;
    }
}
