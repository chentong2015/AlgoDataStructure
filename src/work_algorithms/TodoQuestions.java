package work_algorithms;

import java.util.*;

public class TodoQuestions {

    // Find K Pairs with Smallest Sums
    // Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    // Output: [[1,2],[1,4],[1,6]]
    //
    // nums1 = [1,2',4,5,6], nums2 = [3',5,7,9]
    // Output = [[1,3],[2,3],[1,5]]  OK
    //          [[1,3],[2,3],[4,3]]  KO
    //
    // 使用双指针的计算方式会造成"结果对"的遗漏
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int index1 = 0;
        int index2 = 0;
        int count = 0;
        List<List<Integer>> results = new ArrayList<>();
        // while (count < k) {
        //     List<Integer> sum = Arrays.asList(nums1[index1], nums2[index2]);
        //     results.add(sum);
        //     count++;
        //     if (index1 < nums1.length - 1 && index2 < nums2.length - 1) {
        //         if (nums1[index1 + 1] + nums2[0] < nums2[index2] + nums1[0]) {
        //             index1++;
        //         } else {
        //             index2++;
        //         }
        //     }
        // }
        return results;
    }

    // TODO. 重新思考低算法复杂度的实现
    // Word Ladder
    // Given two words, beginWord and endWord, and a dictionary wordList,
    // return the number of words in the shortest transformation sequence from beginWord to endWord,
    // return 0 if no such sequence exists
    // beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    // -> "hit" -> "hot" -> "dot" -> "dog" -> cog" -> 5
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList); // 从List转成HashSet, 去除重复单词并快速查找
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count = 1;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                char[] current = queue.poll().toCharArray();
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
