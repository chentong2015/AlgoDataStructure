package com.leetcode.learn_introduction.array_string;

public class LearnString3 {

    // Reverse Words in a String
    // Given an input string s, reverse the order of the words
    // s may contain leading or trailing spaces or multiple spaces between two words 字符串的首尾和中间可能包含多个空格
    // Return a string of the words in reverse order concatenated by a single space
    // s = "the sky is blue" -> "blue is sky the"
    // s = "  hello world  " -> "world hello"
    public String reverseWords(String s) {
        // 测试理解：1. 常规解法，从后往前取每个单词，然后依次通过StringBuilder来构造最后的结果  O(n) O(n)
        //            TODO: 一般不能直接使用基础类库的方法 .split() .trim() !!
        if (!s.contains(" ")) return s;
        String[] arr = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = arr.length - 1; index >= 0; index--) {
            if (!arr[index].equals("")) {
                stringBuilder.append(arr[index]).append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

    // TODO: 数组的操作划分到每一个小步，逐步实现
    // 测试理解：1. 转变成字符数组进行处理，通过3个小步和小运算生成最后的结果，保持O(n)的时间复杂度
    public String reverseWords2(String s) {
        if (s == null) return null;
        char[] chars = s.toCharArray();
        int length = chars.length;
        reverse(chars, 0, length - 1);
        reverseWords(chars, length);
        return cleanSpaces(chars, length);
    }

    // 先整个字符数组颠倒，使用双标识 O(n) O(1)
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    // 使用双指针定位单词的首尾位置，[i, j-1]单词的区间 O(n) O(1)
    void reverseWords(char[] a, int n) {
        int i = 0;
        int j = 0;
        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++;  // 第一个非空的位置就是起使点
            while (j < i || j < n && a[j] != ' ') j++;  // 单词结尾处后一个为空，或者是到了数组的最后
            reverse(a, i, j - 1);
        }
    }

    // 将每个单词转移到从起使点index=0开始的位置，依次往后填充每一个单词，最后再截取处理好的长度index O(n)+O(n) O(1)
    String cleanSpaces(char[] a, int n) {
        int index = 0;
        int j = 0;
        while (j < n) {
            while (j < n && a[j] == ' ') j++;
            while (j < n && a[j] != ' ') a[index++] = a[j++];
            while (j < n && a[j] == ' ') j++;
            if (j < n) a[index++] = ' ';
        }
        return new String(a).substring(0, index); // 这里需要花费时间复制度
    }
}
