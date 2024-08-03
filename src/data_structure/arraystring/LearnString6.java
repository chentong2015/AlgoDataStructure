package data_structure.arraystring;

import java.util.ArrayList;
import java.util.List;

public class LearnString6 {

    // TODO: KMP Pattern Matching(Substring Search) 金典子字符串的查找
    //  取消对pattern子字符串的遍历，将时间复杂度从O(n*m)降低为O(n+m)
    // 将要查找的子字符串的信息提前提取到数组中，提前标识子字符串中存在的"自子字符串"
    // pattern: a b c a b y  对于y字符位置而言，它的前面是ab，整个子字符串的开头也是ab
    //          0 1 2 3 4 5  如果在y位置出现字符匹配不一致，那么它前面的ab字符一定已经比较过，再次比较的时候只需要从c位置开始比较
    //          0 0 0 1 2 0  找y位置的前一个字符所计算出来的数组位置上的下标值，进行跳转到指定位置，直接取值string.charAt(index);
    //          left 当x和c不等时，后退一步，然后再确定到a的位置
    // str    : a b x a b c a b c a b y
    //              index 当x和c不等时，后退一步，前面的字符串都当作是比较过了 !!
    public static boolean testKMPPatternMatching(String pattern, String str) {
        // 测试基本的字符串条件
        int left = 0;
        int right = 1;
        int[] indexs = new int[pattern.length()];
        while (right < pattern.length()) {
            if (pattern.charAt(left) == pattern.charAt(right)) {
                indexs[right] = indexs[right - 1] + 1;
                left++;
            }
            right++;
        }
        left = 0;
        int index = 0;
        while (index < str.length()) {
            if (pattern.charAt(left) == str.charAt(index)) {
                left++;
                if (left == pattern.length()) return true;
            } else {
                // 取该位置的前位置所定位的(在数组中的)位置, 同时index位置后退一步再做比较
                // 如果left=0为起点位置，则index不能后退，否则会出现死循环
                if (left > 0) {
                    left = indexs[left - 1];
                    index--;
                }
            }
            index++;
        }
        return false;
    }

    // Repeated String Match
    // Given two strings a and b, return the minimum number of times you should repeat string a
    // so that string b is a substring of it.
    // If it is impossible for b to be a substring of a after repeating it, return -1
    // Input: a = "abcd", b = "cdabcdab" -> "abcdabcdabcd" -> 3 将A重复3次之后则包含字符串B
    // Input: a = "a", b = "aa"          -> "aa"           -> 2
    public int repeatedStringMatch(String a, String b) {
        if (a == null || a.length() < 1) return -1;
        if (b == null || b.length() < 1) return -1;
        StringBuilder strBuilder = new StringBuilder(a);
        int countRepeatTimes = 0;
        while (strBuilder.length() < b.length()) {
            countRepeatTimes++;
            strBuilder.append(a);
        }
        // 跳出while循环的两个条件: > b.length() 或者 = b.length()，基于两种情况做两次判断
        if (strBuilder.toString().contains(b)) return countRepeatTimes;
        if (strBuilder.append(a).toString().contains(b)) return countRepeatTimes + 1;
        return -1;
    }

    // Rearrange Words in a Sentence
    // Given a sentence text (A sentence is a string of space-separated words) in the following format:
    // 1. First letter is in upper case.
    // 2. Each word in text are separated by a single space.
    // Rearrange the words in text such that all words are rearranged in an increasing order of their lengths
    // 如果两个单词的长度相同，则按照原来的顺序返回
    // text = "Leetcode is cool"      -> "Is cool core_algo" 结果句子的首字母需要大写
    // text = "Keep calm and code on" -> "On and keep calm code"
    // 1. 1 <= text.length <= 10^5
    // 2. 句子只包含字母和空格
    // O(nlog(n)) O(n) n为word单词的数量
    public static String arrangeWords(String text) {
        if (text == null || !text.contains(" ")) return text;
        List<String> words = new ArrayList<>(List.of(text.split(" ")));
        // TODO: 直接调用List.sort(Comparator<? super E> c)方法，按照自定义的规则进行排序
        //       或者直接使用Arrays.sort();
        //       Merge Sort稳定排序，算法复杂度可能优于nlog(n)
        words.sort((str1, str2) -> {
            if (str1.length() > str2.length()) {
                return 1;
            } else if (str1.length() == str2.length()) {
                return 0;
            } else {
                return -1;
            }
        });
        // String.join(delimiter, charSequence) 可以直接在字符序列中插入分割符号
        // 测试对字符串处理的准确程度
        StringBuilder result = new StringBuilder();
        for (String str : words) {
            if (result.length() == 0) {
                String firstChar = String.valueOf(str.charAt(0));
                result.append(firstChar.toUpperCase());
                result.append(str.substring(1));
            } else {
                result.append(" ");
                result.append(str.toLowerCase());
            }
        }
        return result.toString();
    }
}
