package work_algorithms;

import java.util.*;

// TODO. DFS + Backtracking的综合解法
public class TodoQuestions {

    public static void main(String[] args) {
        TodoQuestions todoQuestions = new TodoQuestions();
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");

        System.out.println(todoQuestions.ladderLength("hit", "cog", list));
    }

    // TODO. 本质上是一个recursion的DFS算法，从顶到底找到最短长度
    // Word Ladder 字符梯子
    // Given two words, beginWord and endWord, and a dictionary wordList,
    // Every adjacent pair of words differs by a single letter.
    // return the number of words in the shortest transformation sequence from beginWord to endWord,
    // return 0 if no such sequence exists
    //
    // Constraints:
    //   1 <= beginWord.length <= 10
    //   endWord.length == beginWord.length
    //   1 <= wordList.length <= 5000
    //   wordList[i].length == beginWord.length
    //   beginWord, endWord, and wordList[i] consist of lowercase English letters.
    //   beginWord != endWord
    //   All the words in wordList are unique.
    //
    // beginWord = "hit", endWord = "cog",
    // wordList = ["hot","dot","dog","lot","log","cog"] -> boolean
    //             hit
    //             hot
    //       dot          lot
    //   dog    lot     dot  log
    // cog log  ...
    //
    // O(n*M) 判断的次数以及递归的深度与给定的wordList有关
    // O()

    private int minLength = 0;
    private HashMap<String, Boolean> hashMap = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String str: wordList) {
            hashMap.put(str, false);
        }
        for (int index=0; index<wordList.size(); index++) {
            String nextStr = wordList.get(index);
            if (canTransform(beginWord, nextStr)) {
                hashMap.put(nextStr, true);
                backtracking(wordList, 1, index, endWord);
                hashMap.put(nextStr, false);
            }
        }
        return minLength;
    }

    private void backtracking(List<String> wordList, int steps, int currentIndex, String endWord) {
        // 判断临界条件后需要返回，避免继续递归
        if (wordList.get(currentIndex) == endWord) {
            minLength = Math.min(steps + 1, minLength);
            return;
        }

        for (int index=0; index < wordList.size(); index++) {
            String nextStr = wordList.get(index);
            if (index != currentIndex && !hashMap.get(nextStr)) {
               if (canTransform(wordList.get(currentIndex), nextStr)) {
                   if (nextStr == endWord) {
                       // 如果在循环的时候已经找到，则无需进入回溯DFS
                       minLength = Math.min(steps + 1, minLength);
                   } else {
                       hashMap.put(nextStr, true);
                       backtracking(wordList, steps + 1, index, endWord);
                       hashMap.put(nextStr, false);
                   }
               }
            }
        }
    }

    // TODO. 快速判断两个字符串是否是相差一个字符
    // startStr=00000200001000010001..
    // endStr=00000100000000020001...
    private boolean canTransform(String startStr, String endStr) {
        int[] counts = new int[26];
        for (char c: startStr.toCharArray()) {
            counts[c - 'a']++;
        }
        for (char c: endStr.toCharArray()) {
            counts[c - 'a']--;
        }
        // 抵消完成后必须剩下两个不同位置的1
        int countLeft = 0;
        for (int index=0; index<26; index++) {
            if (counts[index] == 1) {
                countLeft++;
            }
        }
        return countLeft==2;
    }
}
