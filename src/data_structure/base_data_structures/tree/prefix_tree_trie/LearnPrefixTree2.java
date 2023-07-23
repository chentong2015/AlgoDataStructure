package data_structure.base_data_structures.tree.prefix_tree_trie;

import java.util.HashMap;

// TODO: Prefix Hashmap 将前缀树和HashMap结合使用，优化具有共同Prefix的key值的存储
// 1 <= key.length, prefix.length <= 50
// key and prefix consist of only lowercase English letters
// "apple"->3, "app"->2, "code"->1
// a      0+3+2    c     0+1    sum("app")=3+2=5 存储的是具有公共前缀的字符串的值总和
// ap     0+3+2    co    0+1    get("app")={"apple", "app"} 获取具有公共前缀的所有字符串(key->list<String>)
// app    0+3+2    cod   0+1
// appl   0+3      code  0+1
// apple  0+3
public class LearnPrefixTree2 {

    private HashMap<String, Integer> map;
    private HashMap<String, Integer> prefixTreeMap;

    public LearnPrefixTree2() {
        map = new HashMap<>();
        prefixTreeMap = new HashMap<>();
    }

    // O(L) 和key字符串的长度有关
    // Inserts the key-val pair into the map 如果存在则直接修改，如果不存在则添加
    // If the key already existed, the original key-value pair will be overridden to the new one
    public void insert(String key, int val) {
        int value;
        if (map.containsKey(key)) {
            value = val - map.get(key);
        } else {
            value = val;
        }
        StringBuilder stringBuilder = new StringBuilder();  // 使用StringBuilder减少字符串拼接造成的时间复杂度 !!
        for (int index = 0; index < key.length(); index++) {
            stringBuilder.append(key.charAt(index));
            String newKey = stringBuilder.toString();
            int baseValue = prefixTreeMap.getOrDefault(newKey, 0);
            prefixTreeMap.put(newKey, baseValue + value);
        }
        map.put(key, val);
    }

    // Returns the sum of all the pairs' value whose key starts with the prefix
    // 如果没有这样的公共Prefix，则返回0值
    public int sum(String prefix) {
        return prefixTreeMap.getOrDefault(prefix, 0);
    }
}
