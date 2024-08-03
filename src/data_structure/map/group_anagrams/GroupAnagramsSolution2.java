package data_structure.map.group_anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO. HashMap<Key,Value> 高阶使用场景:
//  Value值和Result结果共用同一个对象引用，避免重复开辟内层空间 !!
public class GroupAnagramsSolution2 {

    // 使用HashMap存储分组的Key键值，键值指向分组的结果集合List
    //
    // O(M) M is total number of letters
    // O(N) N is total number of strings
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str: strs) {
            // 1. 通过字符串的字符统计创建唯一的基于char的键值key
            char[] arr = new char[26];
            for(char cur: str.toCharArray()) {
                arr[cur-'a']++;
            }
            String key = String.valueOf(arr);

            // 2. 根据key来判断插入哪一个Group中，共享一个List
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
        // return new ArrayList(map.values()); // 通过.values()来构造出List
    }
}
