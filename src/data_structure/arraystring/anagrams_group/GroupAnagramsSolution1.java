package data_structure.arraystring.anagrams_group;

import java.util.*;

// TODO. 如果不使用Hashmap则必须自定义分组的判断: 时间复杂度比较差，空间复杂度较好
public class GroupAnagramsSolution1 {

    // Group Anagrams
    // Given an array of strings strs, group the anagrams together. Return answer in any order
    // strs = ["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        for (String str : strs) {
            boolean hasFound = false;
            for (List<String> group : results) {
                if (isAnagrams(group.get(0), str)) {
                    group.add(str);
                    hasFound = true;
                    break;
                }
            }
            if (!hasFound) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(str);
                results.add(newGroup);
            }
        }
        return results;
    }

    // TODO. 每组Anagrams Key应该只计算一次，而非每次遍历比较时都计算并比较
    // 使用纯字符统计进行比较是否为同组数据
    private static boolean isAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] counts1 = new int[26]; // only 26 letters
        int[] counts2 = new int[26]; // only 26 letters
        for (int index = 0; index < str1.length(); index++) {
            counts1[str1.charAt(index) - 'a']++;
            counts2[str2.charAt(index) - 'a']++;
        }
        return Arrays.equals(counts1, counts2);
    }
}
