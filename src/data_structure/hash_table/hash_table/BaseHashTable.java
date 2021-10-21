package data_structure.hash_table.hash_table;

// Hashtable解决HashMap线程安全的问题, 通过加锁来来实现
// Hashtable不常用: 性能差, 加锁的范围太大/力度太大, 造成在没有冲突的情况下则会收到来自加锁的影响(等锁)
public class BaseHashTable {

    public void testHashTable() {
        // 以整个Hashtable对象为锁，来实现线程的安全性
        // public synchronized V get(Object key) {}
        // public synchronized V put(K key, V value) {}
    }
}
