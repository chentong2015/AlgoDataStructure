package com.leetcode.learn_introduction.array_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BashArrayList {

    public void testArrayList() {
        List<Integer> v0 = new ArrayList<>();
        List<Integer> v1;                           // v1 == null

        Integer[] arr = {0, 1, 2, 3, 4};
        v1 = new ArrayList<>(Arrays.asList(arr));   // Convert Array to ArrayList

        List<Integer> v2 = v1;                      // another reference to v1
        List<Integer> v3 = new ArrayList<>(v1);     // make an actual copy of v1 (存储的是值类型)深度拷贝

        int size = v1.size();
        int firstItem = v1.get(0);
        for (int i = 0; i < v1.size(); ++i) {
            System.out.print(" " + v1.get(i));
        }
        for (int item : v1) {
            System.out.print(" " + item);
        }

        v2.set(0, 5);                                // modify v2 will actually modify v1
        System.out.println("1st item: " + v1.get(0));
        v3.set(0, -1);
        System.out.println("1st item: " + v1.get(0));

        Collections.sort(v1);

        v1.add(-1);
        v1.add(1, 6);
        v1.remove(v1.size() - 1);              // remove the last element
    }
}
