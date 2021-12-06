package core_algo.data_model;

import java.util.Comparator;

// 自定义比较器，用于在Array数组排序时的元素之间比较
public class LargerNumberComparator implements Comparator<String> {

    // 直接比较两种字符串组合起来的大小，取更大的组合情况
    @Override
    public int compare(String str1, String str2) {
        String order1 = str1 + str2;
        String order2 = str2 + str1;
        return order2.compareTo(order1);
    }
}
