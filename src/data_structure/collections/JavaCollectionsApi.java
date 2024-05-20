package data_structure.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO. 使用Collections.emptyList()来创建空list
public class JavaCollectionsApi {

    private final List<String> list = new ArrayList<>();

    public void testCollectionApi(int index, int lastIndex) {
        Collections.sort(list);
        Collections.swap(list, index, lastIndex);

        // 返回不可变的list
        List<String> unchangedList = Collections.unmodifiableList(list);

        // 返回线程安全的list
        List<String> synchronizedList = Collections.synchronizedList(list);
    }
}
