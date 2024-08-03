package data_structure.map.group_anagrams;

import java.util.*;

// TODO. 如果不使用Hashmap，则必须自定义分组的判断
//  时间复杂度比较差，空间复杂度较好 !!
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

    // TODO. 使用字符串排序API比较会造成大的时间复杂度
    private boolean isAnagrams2(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] sChars = str1.toCharArray(); // 时间复制度 O(n)
        char[] tChars = str2.toCharArray(); //
        Arrays.sort(sChars);                // 时间复杂度 O(nlog(n))
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars); // 时间复制度 O(n)
    }
}
