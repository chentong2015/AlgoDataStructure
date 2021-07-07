package com.leetcode.basic_theroy_introduction.array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Iterable<E> ==> Collection<E> ==> List<E> 继承和实现层级
 * AbstractList, "ArrayList", CopyOnWriteList, "LinkedList", Stack, Vector
 * .
 * ArrayList 列表(动态数组)，长度可变
 * 1. 查找数据的速度比较快，直接通过下标index索引即可访问     ===> 数据存储的地址是连续的，可以直接计算偏移量 !!
 * 2. 在数据中间插入元素的速度比较慢，会影响插入位置后面的元素
 */
public class LearnArrayList1 {

    // TODO: 数组和list列表之间的相互转换
    private void testConvertArrayAndList() {
        Integer[] nums = {1, 2, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(nums)); // 这里必须提供<T>泛型中的reference type

        List<String> myList = new ArrayList<>();
        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray);               // 将要转换成的数组作为参数传递

        String[] myArray1 = (String[]) myList.toArray(); // .toArray()之后强制类型转换
    }

    // Array List 基本使用方法
    public void testArrayList() {
        List<Integer> v0 = new ArrayList<>();
        List<Integer> v1 = v0;                    // another reference to v1
        List<Integer> v3 = new ArrayList<>(v0);   // make an actual copy of v1 (存储的是值类型)深度拷贝

        v1.add(-1);
        v1.add(1, 6);
        v1.add(1, 2);     // 导致index=1往后的值都会移动，以完成列表长度的自动扩充 ==> 造成时间复杂度 !!
        v1.remove(1);             // 后面位置的值自全部向前一位填充
        v1.remove(v1.size() - 1); // 删除最后一个元素
        v3.set(0, -1);

        int size = v1.size();
        int firstItem = v1.get(0);
        int position = v1.indexOf(0);
        for (int i = 0; i < v1.size(); ++i) {
            System.out.print(" " + v1.get(i));
        }
        for (int item : v1) {
            System.out.print(" " + item);
        }
    }

    // 利用Collections的静态方法
    private void testCollections() {
        List<Integer> list = new ArrayList<>();
        Collections.sort(list);
        Collections.swap(list, 1, 2);
    }

    /**
     * ArrayList复制：System.arraycopy(elementData, 0, a, 0, size);
     * 1. 直接复制ArrayList中的所有值 addAll()
     * 2. 直接在初始化成指定的ArrayList中的值 new ArrayList<>(list)
     * 3. 使用循环，依次复制每一个index位置的值
     */
    public void copyArrayList() {
        List<String> myList = new ArrayList<>();

        ArrayList<String> copyList1 = new ArrayList<>(myList);
        ArrayList<String> copyList2 = new ArrayList<>();
        copyList2.addAll(myList);
    }
}
