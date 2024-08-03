package data_structure.map.group_anagrams;

import java.util.*;

public class CriteoQuestions {

    // O(M)  O(N + 26*N) 需要遍历所有的字符
    // - N is number of strings
    // - M is number of letters
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, Integer> mapKeys = new HashMap<>();
        List<List<String>> results = new ArrayList<>();
        for(String str: strs) {
            String keyString = generateStringKey(str);
            if (mapKeys.containsKey(keyString)) {
                results.get(mapKeys.get(keyString)).add(str);
            } else {
                List<String> subList = new ArrayList<>();
                subList.add(str);
                results.add(subList);
                // Save the new key and its index
                mapKeys.put(keyString, results.size() - 1);
            }
        }
        return results;
    }

    // TODO. 注意：这里数子组合出来的key string可能会冲突，必须确保key唯一
    // 010100000000000000000000000
    // Generate string key based on count of letters
    private static String generateStringKey(String str) {
        int[] counts = new int[26]; // only 26 lettres
        for(char c: str.toCharArray()) {
            counts[c - 'a'] += 1;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i: counts) {
            stringBuilder.append(i);
            stringBuilder.append(',');
        }
        return stringBuilder.toString();
    }

    // TODO. 使用字符串排序比较会造成大的时间复杂度
    private boolean isAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] sChars = str1.toCharArray(); // 这里会将string复制一份出来，时间复制度O(n)
        char[] tChars = str2.toCharArray(); // 这和语言的实现有关，将提供的参数改成char[]便可以去掉这个复杂度
        Arrays.sort(sChars);                // 时间复杂度决定于这里的实现方法 O(nlog(n))
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }
}
