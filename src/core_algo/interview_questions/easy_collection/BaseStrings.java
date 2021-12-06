package core_algo.interview_questions.easy_collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseStrings {

    // Reverse String
    // Modifying the input array in-place with O(1) extra memory 必须之间修改源数组
    public static void reverseChars(char[] s) {
        // 测试理解：本质上是前后颠倒，交换各自位置上的值: 时间复杂度O(n) 空间复杂度O(1)

        // 正确理解: 使用两个位置index：left / right精确定位，同时减少运算
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

    // Valid Palindrome 判断字符串颠倒之后，是否和原来的字符串一致
    public boolean isPalindrome(String s) {
        // 正确理解: 1. 使用前后两个左边，逐个判断指定位置的字符: 时间复杂度O(n) 空间复杂度O(1)
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }

    // Reverse integer
    // return x with its digits reversed, return 0 if it's outside integer range
    // input -120 -> -21
    // -2^31 <= x <= 2^31 - 1
    public static int reverseInteger(int x) {
        // 测试理解：需要将int中包含的数字进行颠倒，形成新的值
        //          1. int -> String -> StringBuilder reverse -> new int

        // 正确理解: 1. 可以归结位数学问题，和公式计算 !!
        //          2. 通过StringBuilder可以实现其中的reverse字符操作，复杂度取决于reverse方法
        int value = x >= 0 ? x : -x;
        StringBuilder stringBuilder = new StringBuilder(Integer.toString(value));
        stringBuilder.reverse();
        if (stringBuilder.length() == 0) {
            return 0;
        } else {
            // 这里有可能因为值过大而无法发生转换 ? 使用更大的数据来承载 !!
            int result = Integer.parseInt(stringBuilder.toString());
            return x >= 0 ? result : -result;
        }
    }

    // First Unique Character in a String
    // Return the first non-repeating character in it and return its index
    // Input: "loveleetcode" 都是小写字符 ->  2 返回其中第一个非重复字符的位置
    public static int firstUniqueChar(String s) {
        // 正确理解: 1. 利用HashMap的put方法来统计指定的Key出现的次数               O(1)
        //          2. 使用定长字符数组int[26], 计算s.charAt(i)-'a', 统计字符次数 O(1)
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char keyChar = s.charAt(i);
            int oldCount = maps.getOrDefault(keyChar, 0);
            maps.put(keyChar, oldCount + 1);
        }
        for (int j = 0; j < s.length(); j++) {
            if (maps.get(s.charAt(j)) == 1) {
                return j;
            }
        }
        return -1;
    }

    // TODO: 利用字符串中的字符来做运算 s.charAt(i) - 'a'，将字符转换成int有效数值 !!
    // Valid Anagram 字符中包含同样多的字符
    // Sorting both strings will result in two identical strings
    public static boolean isAnagram(String s, String t) {
        // 正确理解: 1. 使用定长字符数组int[26], 统计每个出现字符的数量
        //             O(n)  O(1) 定长的数组，额外开辟出来的空间是一定的
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        // 正确理解: 2. String -> CharArray 通过调用Array的静态方法来实现 !!
        //             Time complexity: O(nlogn)  Space complexity: O(1)
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray(); // 将string复制一份出来, 时间复制度O(n), 和语言的实现有关，可将提供的参数改成char[]取消复杂度
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);             // 排序方法的时间复杂度 O(nlog(n)) > O(n)
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    // TODO: Longest Common Prefix
    // Write a function to find the longest common prefix string amongst an array of strings
    // If there is no common prefix, return an empty string ""
    // strs = ["flower","flow","flight"] -> "fl"
    public String longestCommonPrefix(String[] strs) {
        // 测试理解：1. 依次提取单词的每一个前缀字符串，判断后面的所有字符串是否具有相同的前缀  ==> 实际比较的次数过多，遍历一遍就必须出结果 !!
        //            O(m*n) m为公共前缀的长度 O(1)
        return null;
    }

    // 正确理解：1. 水平扫描：横向遍历，依次比较两个字符串的公共前缀，然后再和第三个进行比较，依次到最后    O(S) S为所有字符的长度和 O(1)
    //         2. 垂直扫描：纵向遍历字符串的每个位置上的字符(same character index of the strings) O(S) O(1)
    //         3. 高阶解法：使用Prefix Tree数据结构
    public String longestCommonPrefixHorizontalScanning(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0]; // 暂存的结果值，公共的前缀
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {                 // 公共prefix前缀的index开始位置必须是0
                prefix = prefix.substring(0, prefix.length() - 1); // 如果不是，则缩短prefix的长度，然后再判断是否是公共的
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public String longestCommonPrefixVerticalScanning(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // 遍历第一个位置的字符串的所有字符 ==> 避免重复检查前面已经比较过的index位置
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            // 判断后面的字符串在指定的index位置是否是指定的字符c
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) // .charAt(i) 每个指定的位置上只会读取到一次
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
