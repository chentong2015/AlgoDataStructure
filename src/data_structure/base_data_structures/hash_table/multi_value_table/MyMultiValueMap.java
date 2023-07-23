package data_structure.base_data_structures.hash_table.multi_value_table;

import java.util.List;
import java.util.Map;

// MultiValueMap<K, V> extends Map<K, List<V>> 一个key对应多个value的数据结构
// MultiValueMap<String, String> 等效于 Map<String, List<String>>
public interface MyMultiValueMap<K, V> extends Map<K, List<V>> {

    // @Nullable 可以设置成可空
    V getFirst(K key);

    void add(K key, V value);

    void addAll(K key, List<? extends V> values);

    void addAll(MyMultiValueMap<K, V> values);

    default void addIfAbsent(K key, V value) {
        if (!this.containsKey(key)) {
            this.add(key, value);
        }
    }

    void set(K key, V value);

    void setAll(Map<K, V> values);

    Map<K, V> toSingleValueMap();
}
