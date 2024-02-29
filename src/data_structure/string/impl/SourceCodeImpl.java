package data_structure.string.impl;

// 通过自定义算法实现Java源码方法
public class SourceCodeImpl {

    // 1. 实现stringBuilder.reverse()源码
    // 转换成字符数组，根据坐标index位置，从两端往中间进行递归
    public static String reverseString(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        return recursive(str.toCharArray(), 1);
    }

    private static String recursive(char[] chars, int index) {
        int length = chars.length;
        if (index < length / 2 + 1) {
            // 将index指定下标位置的char和它对称位置进行交换
            char tempChar = chars[index - 1];
            int swapIndex = length - index;
            chars[index - 1] = chars[swapIndex];
            chars[swapIndex] = tempChar;

            // 交换完成后，再递归给先一个index位置交换char
            recursive(chars, index + 1);
        }
        // 最后根据结果的char[]构建生成反转后的字符串
        return new String(chars);
    }

    // 2. 实现String.replace(subStr, targetStr)源码效果
    // 使用StringBuilder + Sliding Windows来实现全部替换子字符串成自定的字符串
    // aaaaa aaa bb -> bbaa 符合条件的子字符串只会替换首次出现的那个
    public static String replaceSubStr(String sourceStr, String subStr, String targetStr) {
        // 首先需要判断是否满足替换的条件
        int left = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder windowStr = new StringBuilder();
        for (int index = 0; index < sourceStr.length(); index++) {
            // 新的字符添加到滑动窗口的末尾
            windowStr.append(sourceStr.charAt(index));
            if (windowStr.length() < subStr.length())
                continue;
            if (windowStr.toString().equals(subStr)) {
                result.append(targetStr);
                // 移动下标，开启新的一个窗口
                left = index + 1;
                windowStr = new StringBuilder();
            } else {
                // 滑动窗口在移动的过程中，如果判断不等，则将第一个字符删除
                // 第一个字符也就left index所在的位置
                windowStr.deleteCharAt(0);
                result.append(sourceStr.charAt(left));
                left++;
            }
        }
        return result.toString();
    }

    // 3. Occurrences of substring in a string
    // 3.1 使用StringBuilder + Sliding Windows
    // 3.2 使用Java原生方法: 通过全部替换子字符串之后，通过字符长度的变化来判断替换了几次 !!
    public static int count(String str, String target) {
        int lenStr = str.length();
        int lenReplacedStr = str.replace(target, "").length();
        return (lenStr - lenReplacedStr) / target.length();
    }

    // 4. 提取两个指定分割符之间的子字符串
    public static String getMiddleName(String name) {
        int firstIndex = name.indexOf("/");
        int lastIndex = name.lastIndexOf("/");
        if (firstIndex > -1) {
            if (lastIndex > firstIndex) {
                return name.substring(firstIndex, lastIndex);
            }
            return name.substring(lastIndex);
        }
        return name;
    }
}
