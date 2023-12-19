package data_structure.base_data_structures.hash_table.hash_table;

import java.util.HashMap;
import java.util.Hashtable;

// TODO. HashMap和HashTable区别:
// 1. HashMap中可存储null值，HashTable的key和value均不能设置成null
// 2. HashMap数据的存储是非同步的，Hashtable数据的存储和获取都是同步的，会对性能造成影响

// Hashtable不常用: 性能差, 加锁的范围太大/力度太大, 造成在没有冲突的情况下则会收到来自加锁的影响(等锁)
// 以整个Hashtable对象为锁，来实现线程的安全性
// public synchronized V get(Object key) {}
// public synchronized V put(K key, V value) {}
public class BaseHashTable {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", null);
        map.put(null, "value");
        System.out.println(map.get(null));

        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(null, "value");
        System.out.println(hashtable.get(null));
    }
}
