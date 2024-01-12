package data_structure.tree;

import java.util.*;

public class HardTrees {

    // TODO: BBFS(Bidirectional BFS) https://leetcode.com/problems/word-ladder/solution/
    // Word Ladder
    // Given two words, beginWord and endWord, and a dictionary wordList, 
    // return the number of words in the shortest transformation sequence from beginWord to endWord, 
    // or 0 if no such sequence exists
    // beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    // -> "hit" -> "hot" -> "dot" -> "dog" -> cog" -> 5
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 测试理解: 如何判断两个字符串只有一个字符不同? 如何选择下一个字符串? 如何排序取出最短的路径?

        // 正确理解：1. 使用Graph图(结构)来解决最短线路和路径问题，最短的路径是构成图的levels中间的距离(在能够联通的情况下)
        Set<String> set = new HashSet<>(wordList); // 从List转成HashSet, 去除重复单词并快速查找
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                char[] current = queue.poll().toCharArray(); // 出队列
                for (int j = 0; j < current.length; j++) {
                    char temp = current[j];                  // 每次只能变化一个位置上的单词
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);   // 依次替换每个位置上的字符，构建新的字符串，查找是否具有构建出来的单词
                        if (set.contains(next)) {
                            if (next.equals(endWord)) {      // 当有路径到达list中的单词，并且是结尾单词，则结束 !!
                                return count + 1;
                            }
                            queue.add(next);  // 添加到队列中，作为下一个查找的单词的起点
                            set.remove(next); // 移除掉给的单词列表中的单词，并且count已经添加步数
                        }
                    }
                    current[j] = temp;
                }
            }
            count++; // Queue中一个单词查找完，换下一个新的单词，增加步数
        }
        return 0;
    }
}
