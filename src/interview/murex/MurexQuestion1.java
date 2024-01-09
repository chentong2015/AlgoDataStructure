package interview.murex;

import algorithms.datamodel.TreeNode;
import interview.murex.model.Word;

import java.util.HashMap;
import java.util.Map;

public class MurexQuestion1 {

    // LCA二叉树中最小的公共前继
    // 输入二叉树的root结点，以及两个结点的值，返回最小公共node
    public static int findLCA(TreeNode root, int p, int q) {
        // 判断初始条件
        // 递归判断左右子树
        // 按照找到的结果进行返回
        return 0;
    }

    // 对字符串进行指定规则的加工，添加指定的后缀??
    // this asa out test
    // ihts?? asa?? uot?? sett??
    // 至少有一个单词，并且单词之间使用空格隔开
    // O(N) O(N) N为字符串中字符的数目
    public static String toMurextLatin(String suffix, String message) {
        StringBuilder result = new StringBuilder();
        for (String word : message.split(" ")) {
            String formattedWord = formatWord(suffix, word);
            if (result.length() > 0) result.append(" ");
            result.append(formattedWord);
        }
        return result.toString();
    }

    private static String formatWord(String suffix, String word) {
        StringBuilder tempWord = new StringBuilder(word.toLowerCase());
        tempWord.reverse();
        char tempChar = tempWord.charAt(0);
        if (tempChar != 'a' && tempChar != 'e' && tempChar != 'i') {
            tempWord.deleteCharAt(0).append(tempChar);
        }
        return tempWord.append(suffix).toString();
    }

    // 查找第一个出现指定次数的单词，和另一个出现在最右边的最多次数的单词(如果存在的话)
    // 排序统计(map中插入键值时会涉及到字符排序)，忽略单词的大小写比较
    // 返回知道的两个可能的结果
    // O(N) O(N)
    public static Word findTheRepeatedWord(String sentence, int n) {
        boolean hasFoundLeftMostWord = false; // 标记是否已经知道第一个指定数目的单词
        int rightMostCount = 0; // 标记最大的单词统计数目
        Word result = new Word();
        Map<String, Integer> wordCounts = countWords(sentence);
        for (Map.Entry<String, Integer> item : wordCounts.entrySet()) {
            if (!hasFoundLeftMostWord && item.getValue() == n) {
                hasFoundLeftMostWord = true;
                result.leftMost = item.getKey();
            }
            // 如果相等，仍然需要纪录最右边的单词
            if (item.getValue() >= rightMostCount) {
                rightMostCount = item.getValue();
                result.rightMost = item.getKey();
            }
        }
        if (result.leftMost.equals(result.rightMost)) {
            result.rightMost = null;
        }
        return result;
    }

    private static Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : sentence.split(" ")) {
            String lowerCaseWord = word.toLowerCase();
            int wordCount = 1;
            if (wordCounts.containsKey(lowerCaseWord)) {
                wordCount = wordCounts.get(lowerCaseWord) + 1;
            }
            wordCounts.put(lowerCaseWord, wordCount);
        }
        return wordCounts;
    }
}
