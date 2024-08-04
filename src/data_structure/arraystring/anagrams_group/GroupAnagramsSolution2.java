package data_structure.arraystring.anagrams_group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO. HashMap<Key,Value> 金典使用场景: 对数据进行分组
public class GroupAnagramsSolution2 {

    // TODO. Value值和Result结果共用同一个对象引用，避免重复开辟内层空间 !!
    // HashMap: Key->List<String> group
    //
    // O(M) M is number of letters
    // O(N) N is number of strings, generate new key for each string
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str: strs) {
            String key = generateAnagramsKey(str);
            if(map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
                results.add(list);
            }
        }
        return results;
        // Map中存储的键值就是最终的结果
        // return map.values().stream().toList();
        // return new ArrayList(map.values());
    }

    // TODO. 用于分组的key键值只需要计算一次，并且通过Hash能够快速匹配
    // 通过字符串的字符统计创建唯一的基于char的键值key
    private String generateAnagramsKey(String str) {
        char[] arr = new char[26];
        for(char cur: str.toCharArray()) {
            arr[cur-'a']++;
        }
        return String.valueOf(arr);
    }
}
