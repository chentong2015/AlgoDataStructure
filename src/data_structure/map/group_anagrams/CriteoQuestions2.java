package data_structure.map.group_anagrams;

import java.util.*;

public class CriteoQuestions2 {

    // O(M)  O(N + 26*N) 需要遍历所有的字符
    // - N is number of strings
    // - M is number of letters
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

    // Check the two Strings are in same group
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
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isAnagrams("eat", "tan"));
    }


    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str: strs) {
            char[] arr = new char[26];
            for(char cur: str.toCharArray()) {
                arr[cur-'a']++;
            }
            String key = String.valueOf(arr);
            if(map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
                ans.add(list);
            }
        }
        return ans;
    }
}
