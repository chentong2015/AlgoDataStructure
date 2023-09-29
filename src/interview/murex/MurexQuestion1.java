package interview.murex;

import interview.murex.model.Word;

import java.util.HashMap;
import java.util.Map;

public class MurexQuestion1 {

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

    // TODO: BFS-DFS，使用深度优先遍历，直到找到最深的那个目标终点 -- 递归思想
    // 道路连接的问题: 找出所有可能的通路，有且只有一条可能的通路
    // A通过，E阻断，D终点: 更新经过的标记
    // (0,0)
    // A   E   A   E
    // A   A   A   E
    // A   A   E   E
    // E   A   A   D (n*m)
    // 传入n*m的二维数组数据，返回唯一一条通路的步数，如果没有通路，则返回-1
    // O(n*m) O(1)
    // TODO: 选择不同的BFS遍历策略，会造成时间复杂度的不稳定性
    private static int stepCount;

    public static int transmissionLength(char[][] planetsMap) {
        stepCount = 0;
        findNextStep(planetsMap, 0, 0);
        return stepCount == 0 ? -1 : stepCount;
    }

    // 最优的BFS遍历方式: 顺时针方法进行遍历
    private static boolean findNextStep(char[][] planetsMap, int i, int j) {
        if (i < 0 || j < 0 || i >= planetsMap.length || j >= planetsMap[0].length)
            return false;
        if (planetsMap[i][j] == 'B' || planetsMap[i][j] == 'E') {
            return false;
        }
        if (planetsMap[i][j] == 'D')
            return true; // 如果找到目标，则直接返回

        // 遍历过的位置需要做修改值的处理，避免无限递归造成StackOverflow
        planetsMap[i][j] = 'B';

        boolean upStep = findNextStep(planetsMap, i - 1, j);
        boolean rightStep = findNextStep(planetsMap, i, j + 1);
        boolean downStep = findNextStep(planetsMap, i + 1, j);
        boolean leftStep = findNextStep(planetsMap, i, j - 1);

        // 只有在当前的位置，成功的往后走"有效的"一步，才统计
        // 反之不会纳入统计，因为没有找到最终的'D'，没有true这结果返回 !!!
        if (upStep || downStep || leftStep || rightStep) {
            System.out.println(i + " ++ " + j);
            stepCount++;
            return true;
        }
        return false;
    }
}
