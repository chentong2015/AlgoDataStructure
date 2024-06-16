package data_structure.collections.arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public class CollectionsList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("item 1");
        list.add("item 2");
        Collections.sort(list);
        Collections.swap(list, 0, 1);

        // Collections.EMPTY_LIST  返回不可变的空list
        List<String> emptyList = Collections.emptyList();
        emptyList.add("value");

        // 返回不可变的list
        List<String> unchangedList = Collections.unmodifiableList(list);

        // 返回线程安全的list
        List<String> synchronizedList = Collections.synchronizedList(list);
    }
}
