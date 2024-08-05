package data_structure.arraystring.anagrams_group;

import java.util.*;

// TODO. 如果不使用Hashmap则必须自定义分组的判断: 时间复杂度比较差，空间复杂度较好
public class GroupAnagramsSolution1 {

    // Group Anagrams
    // Given an array of strings strs, group the anagrams together. Return answer in any order
    // strs = ["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
    //
    // O(N * M * L) N为字符串数量，M为分组数量，L为分组key的字符数量
    // O(S)         S为总的字符串的空间，必须开辟的结果内存空间
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        for (String str : strs) {
            boolean hasFound = false;
            // 无法直接通过Str的key找到所属的组，造成额外的循环
            for (List<String> group : results) {
                // 由于那样保存用于分组的key，因此每次遍历都需要重新计算
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

    // TODO. 时间复杂度损失: 判断Anagrams又遍历一遍字符串的长度
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
