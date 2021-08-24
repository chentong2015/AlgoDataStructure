package com.leetcode.basic_theory_introduction.array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ArrayList 列表(动态数组)，长度可变
// 1. 查找数据的速度比较快，直接通过下标index索引即可访问(数据存储的地址是连续的，可以直接计算偏移量) !!
// 2. 在数据中间插入元素的速度比较慢，会影响插入位置后面的元素

// Collections.sort(list);
// Collections.swap(list, 1, 2);
// List<Integer> array = Collections.synchronizedList(list); TODO: 通过Collections来构造出线程安全的List列表多对象
public class LearnArrayList1 {

    /**
     * Iterable<E> ==> Collection<E> ==> List<E> 继承和实现层级
     * AbstractList, "ArrayList", CopyOnWriteList, "LinkedList", Stack, Vector
     */
    // TODO: 数组和list列表之间的相互转换
    private void testConvertArrayAndList() {
        Integer[] nums = {1, 2, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(nums)); // 这里必须提供<T>泛型中的reference type

        List<String> myList = new ArrayList<>();
        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray);            // 将要转换成的数组作为参数传递

        myArray = (String[]) myList.toArray();        // .toArray()之后强制类型转换
    }

    // Array List 基本使用方法
    public void testArrayList() {
        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = list0;                    // another reference to list1
        List<Integer> list3 = new ArrayList<>(list0);   // make an actual copy of list1 (存储的是值类型)深度拷贝

        list1.add(-1);
        list1.add(1, 6);
        list1.add(1, 2);     // 导致index=1往后的值都会移动，以完成列表长度的自动扩充 ==> 造成时间复杂度 !!
        list1.remove(1);             // 后面位置的值自全部向前一位填充
        list1.remove(list1.size() - 1); // 删除最后一个元素
        list3.set(0, -1);

        int size = list1.size();
        int firstItem = list1.get(0);
        int position = list1.indexOf(0);
        for (int i = 0; i < list1.size(); ++i) {
            System.out.print(" " + list1.get(i));
        }
        for (int item : list1) {
            System.out.print(" " + item);
        }
    }

    /**
     * ArrayList复制：System.arraycopy(elementData, 0, a, 0, size);
     * 1. 在创建动态数组对象时，作为初始化的参数 new ArrayList<>(list)
     * 2. 直接复制添加列表所有值到目标列表      arrays.addAll()
     * 3. 循环依次复制每一个index位置的值
     */
    public void copyArrayList() {
        List<String> myList = new ArrayList<>();
        ArrayList<String> copyList1 = new ArrayList<>(myList);
        ArrayList<String> copyList2 = new ArrayList<>();
        copyList2.addAll(myList);
    }
}
