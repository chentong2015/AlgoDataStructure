package com.leetcode.learn_introduction.array_string;

/**
 * String 字符串研究
 * 1. A string is actually an array of unicode characters (16 bits unicode码值的数组)
 * 2. Use "==" to compares whether these two objects are the same object 只是比较是否是同一个对象
 * 3. TODO: Use .equals() 不仅比较是否是同于个对象，如果不是相同对象，则比较对象包含的值(每个字符)
 * 4. Immutable String 字符串具有不可变性, 如果要改变, 则需要创建一个新的String --> Java/C#不可变, C++可变
 * 5. 比Array多出的操作
 * > s1.indexOf('o')
 * > s1.lastIndexOf('o')        --> 都会造成O(n)的时间复杂度, 不可以忽略
 * > s1.substring(6, 11)
 * > String.copyValueOf(result)
 */
public class LearnString {

    // Immutable String - Problems & Solutions
    // TODO: String Concatenation in Java 注意String的级联造成巨大的时间复杂度 !!
    // 如何解决和更好的利用Java String不可变的特性 
    // 1. 转换成charArray()字符数组
    // 2. 使用StringBuilder数据结构，支持字符串的可变性
    public void testString(int n) {
        // First allocating enough space for the new string
        // Copy the contents from the old string and append to the new string
        // 5 + 5 × 2 + 5 × 3 + … + 5 × n = O(n^2) 操作会造成二次方的时间复杂度 !!
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "hello"; // 每次追加的新的字符串，都会造成额外的内存空间开辟，然后造成Copy的操作
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
        String result = str.toString();
    }

    // TODO：充分使用字符串中字符直接的运算 .charAt(index)-'0'，从char转化到int来计算 !!
    // Add Binary
    // Given two binary strings a and b, return their sum as a binary string
    // a and b consist only of '0' or '1' characters.
    // Each string does not contain leading zeros except for the zero itself.
    // Input: a = "1010", b = "1011" -> "10101"
    public String addBinary(String a, String b) {
        // 正确理解：1. 需要从char转换成int来计算 O(max(m, n)) O(max(m+n))
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;          // 从低位往高位读取，从后往前 !!
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {       // 不用比较那个字符串更长，统一读取，每次累加两个相同位置上面的值 !!
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);          // 求余数作为基础值
            carry = sum / 2;             // 求除数作为上升值
        }
        if (carry != 0) sb.append(carry);// 最后还要追加进位的数值 !!
        return sb.reverse().toString();  // 注意.reverse()复杂度
    }
}
