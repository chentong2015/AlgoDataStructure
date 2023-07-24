package data_structure.array_string;

import java.util.*;

public class LearnString5 {

    // TODO: Hash Table "Aggregate by Key" 通过键值来聚合同类数据
    // Group Anagrams
    // Given an array of strings strs, group the anagrams together. Return answer in any order
    // strs = ["eat","tea","tan","ate","nat","bat"] -> [["bat"],["nat","tan"],["ate","eat","tea"]]
    public List<List<String>> groupAnagrams(String[] strs) {
        // 测试理解: 1. 暴力解法：直接遍历，然后归类添加到结果列表中  O(n*n*mlog(m))   O(n*m)

        // 正确理解: Group分组的本质：将具有特定特征的数据放到一组中，这个特定的特征就是分组的"键值key"
        // HashMap<Key, Value> 采用Map存储，最终返回Map中的所有Values，不直接使用List<List<T>> !!
        // 1. 将字符串中的字符排序之后，作为一组值的Key: O(n*mlog(m)) O(n*m)
        HashMap<String, List<String>> results = new HashMap<>();
        if (strs.length == 0) return new ArrayList();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);                          // 排序造成O(mlog(m))的复杂度 !!

            String key = new String(chars);              // Create Mapkey From String 完全Copy出chars数组中的字符
            if (results.containsKey(key)) {              // Add item by group
                results.get(key).add(strs[i]);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                results.put(key, newList);
            }
        }
        return new ArrayList(results.values()); // 通过.values()来构造出List
    }

    // 正确理解2. 将字符串中的字符依次统计，用统计数值(int -> String)作为Key
    //           O(n*m)  O(n*m)
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> results = new HashMap<>();
        int[] count = new int[26];
        for (String str : strs) {
            // 通过统计字符的数量，生成指定的Map的Key=#1#2#1...
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) {  // 循环内的复杂度是O(m) < O(mlog(m))
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            // ....
            // 使用hash table来做键值的判断
            // ....
        }
        return new ArrayList(results.values());
    }
}
