package work_algorithms.dfs.work_ladder;

import java.util.*;

// TODO. DFS + Backtracking综合解法: 自顶到底找到最短长度
public class WordLadderDFS {

    // Word Ladder 字符梯子
    // Given two words, beginWord and endWord, and a dictionary wordList,
    // Every adjacent pair of words differs by a single letter.
    // return the number of words in the shortest transformation sequence from beginWord to endWord,
    // return 0 if no such sequence exists
    //
    // Constraints: 约束部分是值得讨论的话题
    //  1 <= beginWord.length <= 10
    //  endWord.length == beginWord.length
    //  1 <= wordList.length <= 5000
    //  wordList[i].length == beginWord.length
    //  beginWord, endWord, and wordList[i] consist of lowercase English letters.
    //  beginWord != endWord
    //  All the words in wordList are unique.

    private int minLength = Integer.MAX_VALUE;

    // 进入backtracking回溯前需要先判断第一个能够到达的字符串
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int[] visited = new int[wordList.size()];
        for (int index=0; index < wordList.size(); index++) {
            if (canTransform(beginWord, wordList.get(index))) {
                visited[index] = 1;
                backtracking(wordList, visited, 1, index, endWord);
                visited[index] = 0;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0: minLength;
    }

    // 每一层的时间复杂度: 剩余的str数目 * str的字符数目
    private void backtracking(List<String> wordList, int[] visited, int steps, int visitedIndex, String endWord) {
        String visitedString = wordList.get(visitedIndex);
        if (Objects.equals(visitedString, endWord)) {
            minLength = Math.min(steps + 1, minLength);
            return; // 判断DFS终点后需要return返回
        }
        for (int index=0; index < wordList.size(); index++) {
            if (visited[index] == 0 && canTransform(visitedString, wordList.get(index))) {
                visited[index] = 1;
                backtracking(wordList, visited, steps + 1, index, endWord);
                visited[index] = 0;
            }
        }
    }

    // TODO. 快速判断两个字符串是否是相差一个字符, 注意0和1统计的计数
    // startS=00000200001000010001..
    // endStr=00000100001000010001..
    //     => 00000100000000000000 抵消完成后必须只剩一个字符位置1
    private boolean canTransform(String startStr, String endStr) {
        int[] counts = new int[26];
        int leftCount = 0;
        for (char c: startStr.toCharArray()) {
            counts[c - 'a']++;
            leftCount++;
        }
        for (char c: endStr.toCharArray()) {
            if (counts[c - 'a'] > 0) {
                counts[c - 'a']--;
                leftCount--;
            }
        }
        return leftCount==1;
    }

    public static void main(String[] args) {
        WordLadderDFS todoQuestions = new WordLadderDFS();
        List<String> list = List.of("lest","leet","lose","code","lode","robe","lost");
        System.out.println(todoQuestions.ladderLength("leet", "code", list));
    }
}
