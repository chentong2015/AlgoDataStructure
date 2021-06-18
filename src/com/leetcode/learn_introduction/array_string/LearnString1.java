package com.leetcode.learn_introduction.array_string;

/**
 * String 字符串研究
 * 1. A string is actually an array of unicode characters (16 bits unicode码值的数组)
 * 2. Use "==" to compares whether these two objects are the same object 只是比较是否是同一个对象
 * 3. TODO: 使用.equals()不仅比较是否是同于个对象，如果不是相同对象，则比较对象包含的值(每个字符)
 * 4. Immutable String 字符串具有不可变性, 如果要改变, 则需要创建一个新的String --> Java/C#不可变, C++可变
 * 5. 比Array多出的操作
 * >  s1.indexOf('o')
 * >  s1.lastIndexOf('o')
 * >  s1.substring(6, 11)        --> 都会造成O(n)的时间复杂度, 不可以忽略
 * >  String.copyValueOf(result)
 * >  new String(charArray)      --> 直接通过字符数组构建String
 */
public class LearnString1 {

    // Immutable String - Problems & Solutions
    // TODO: String Concatenation in Java 注意String的级联造成巨大的时间复杂度 !!
    // 如何解决和更好的利用Java String不可变的特性 
    // 1. 转换成charArray()字符数组
    // 2. 使用StringBuilder数据结构，支持字符串的可变性，可以追加int类型
    public void testString(int n) {
        // First allocating enough space for the new string
        // Copy the contents from the old string and append to the new string
        // 5 + 5 × 2 + 5 × 3 + … + 5 × n = O(n^2) 操作会造成二次方的时间复杂度 !!
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "hello"; // 每次追加的新的字符串，都会造成额外的内存空间开辟，然后造成Copy的操作
        }

        StringBuilder str = new StringBuilder(s); // 用String来构建StringBuilder
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
        String result = str.toString();           // 将StringBuilder转换成String
    }

    // TODO：充分使用字符串中字符直接的运算 .charAt(index)-'0' 从char转换到int来计算 !!
    // Add Binary
    // Given two binary strings a and b, return their sum as a binary string
    // a and b consist only of '0' or '1' characters.
    // Each string does not contain leading zeros except for the zero itself.
    // Input: a = "1010", b = "1011" -> "10101"
    public String addBinary(String a, String b) {
        // 正确理解：1. 需要从char转换成int来计算 O(max(m, n)) O(max(m+n))
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;          // 从低位往高位读取，从后往前，必须考虑到数值的进位问题 !!
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {       // 不用比较那个字符串更长，统一读取，每次累加两个相同位置上面的值 !!
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);          // 求余数作为基础值
            carry = sum / 2;             // 求除数作为上升值
        }
        if (carry != 0) sb.append(carry);// 最后追加进位值
        return sb.reverse().toString();  // 注意.reverse()复杂度
    }

    // Implement strStr() / Java indexOf()
    // Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack
    // haystack = "hello", needle = "ll" -> index = 2
    public int strStr(String haystack, String needle) {
        // 测试理解：1. 在没有别的解法时，直接使用常规解法 O(n*m) O(1) !!
        if (needle == null || needle.isEmpty()) return 0;
        if (haystack == null || haystack.length() < needle.length()) return -1;
        for (int index = 0; index < haystack.length(); index++) {
            for (int j = 0; j < needle.length(); j++) {
                if (index + j == haystack.length()) return -1;
                if (haystack.charAt(index + j) != needle.charAt(j)) break;  // 在内部判断3种可能的情况
                if (j == needle.length() - 1) return index;
            }
        }
        return -1;
    }
}
