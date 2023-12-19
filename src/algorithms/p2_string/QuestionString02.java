package algorithms.p2_string;

public class QuestionString02 {

    // Remove All Occurrences of a Substring
    // Given two strings s and part, perform the following operation on s
    // until all occurrences of the substring part are removed:
    // Find the leftmost occurrence of the substring part and remove it from s
    //
    // Input: s = "daabcbaabcbc", part = "abc"
    // Output: "dab"
    // Explanation: The following operations are done:
    // - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
    // - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
    // - s = "dababc", remove "abc" starting at index 3, so s = "dab".
    // Now s has no occurrences of "abc".
    //
    // 代码层面的最简短写法 ~O(N)*O(N)
    public String removeOccurrences(String str, String part) {
        while (str.contains(part)) {
            str = str.replaceFirst(part, "");
        }
        return str;
    }

    // TODO: 使用.subString5()方法提供更快的字符串处理
    public String removeOccurrences2(String s, String part) {
        while (s.indexOf(part) != -1) {
            int idx = s.indexOf(part);
            s = s.substring(0, idx) + s.substring(idx + part.length());
        }
        return s;
    }

    // TODO: 使用StringBuilder类型(可变字符串)提供最快的字符串处理 !!
    public String removeOccurrences3(String s, String part) {
        int len = part.length();
        StringBuilder sb = new StringBuilder(s);
        do {
            int i = sb.indexOf(part);
            if (i == -1) break;
            sb.replace(i, i + len, "");
        } while (true);
        return sb.toString();
    }

    // 自定义实现算法的逻辑
    public static String removeOccurrences4(String str, String part) {
        char[] charsStr = str.toCharArray();
        char[] charsPart = part.toCharArray();
        int index = 0;
        int indexRight = charsStr.length;
        while (index < indexRight) {
            boolean isFound = false;
            if (charsStr[index] == charsPart[0]) {
                isFound = containsPartStr(charsStr, charsPart, index, indexRight);
            }
            if (isFound) {
                replacePartStr(charsStr, charsPart, index);
                indexRight -= charsPart.length;
                index = 0;
            } else {
                index++;
            }
        }
        return generateResultString(charsStr, indexRight);
    }

    // 从指定位置开始逐个比较part字符串, 必须比较part字符串的每一个字符
    // 注意主字符串数组移动的边界问题
    private static boolean containsPartStr(char[] charsStr, char[] charsPart, int startIndex, int rightIndex) {
        boolean isFound = true;
        int indexOffset = 0;
        while (startIndex + indexOffset < rightIndex && indexOffset < charsPart.length) {
            if (charsStr[startIndex + indexOffset] != charsPart[indexOffset]) {
                isFound = false;
                break;
            }
            indexOffset++;
        }

        return isFound && indexOffset == charsPart.length;
    }

    // 移除指定位置找到的sub string字符串, 返回从后往前移动的字符的个数
    private static void replacePartStr(char[] charsStr, char[] charsPart, int startIndex) {
        int length = charsPart.length;
        for (int i = startIndex + length; i < charsStr.length; i++) {
            charsStr[i - length] = charsStr[i];
        }
    }

    // Char Array -> StringBuilder -> String
    private static String generateResultString(char[] charsStr, int indexRight) {
        StringBuilder resultStr = new StringBuilder();
        for (int index = 0; index < indexRight; index++) {
            resultStr.append(charsStr[index]);
        }
        return resultStr.toString();
    }
}
