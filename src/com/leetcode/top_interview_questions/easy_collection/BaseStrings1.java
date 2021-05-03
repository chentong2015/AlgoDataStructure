package com.leetcode.top_interview_questions.easy_collection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseStrings1 {

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
    // s都是小写字符，返回其中第一个非重复字符的位置
    // Input: s = "loveleetcode"  ->  2
    public static int firstUniqueChar(String s) {
        // 测试理解：取出第一个位置上的具有指定特征的字符
        //          1. 使用Map来辅助判断, 依次存储然后删除前面已经重复的添加，最后剩的第一个pair.value位置就是结果
        //             Map添加和输出的顺序不一致，同时可以多次添加同一个key值的Pair

        // 正确理解: 1. 利用HashMap的put方法来统计指定的Key出现的次数
        //          2. 使用定长字符数组int[26], 小写字母位置计算s.charAt(i)-'a', 计算每一个字符出现的次数
        //          3. Space complexity : O(1) because English alphabet contains 26 letters.
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) { // 直接遍历字符串的每一个char字符 !!
            char keyChar = s.charAt(i);
            int oldCount = maps.getOrDefault(keyChar, 0);
            maps.put(keyChar, oldCount + 1);   // put可以直接堆原来的value进行修改, 不需要设置特定的值 !!
        }
        for (int j = 0; j < s.length(); j++) {
            if (maps.get(s.charAt(j)) == 1) {
                return j;
            }
        }
        return -1;
    }

    // Valid Anagram 字符中包含同样多的字符
    // Sorting both strings will result in two identical strings
    public boolean isAnagram(String s, String t) {
        // 正确理解: 1. String -> CharArray 通过调用Array的静态方法来实现 !!  Time complexity: O(nlogn)  Space complexity: O(1)
        //          2. 使用定长字符数组int[26], 统计每个出现字符的数量 !! 由于这的数组是定长的，所以额外开出来的空间复杂度是O(1)
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        //
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray(); // 这里会将string复制一份出来，时间复制度O(n)
        char[] tChars = t.toCharArray(); // 这和语言的实现有关，将提供的参数改成char[]便可以去掉这个复杂度
        Arrays.sort(sChars);             // 时间复杂度决定于这里的实现方法
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }
}
