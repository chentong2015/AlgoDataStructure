package com.leetcode.learn_introduction.linked_list;

import java.util.ArrayList;
import java.util.List;

/***
 * // List 列表：动态的数组
 * 1. Super interface:
 *    Collection<E> extends Iterable<E> 上一级的接口：泛型集合可被迭代
 * 2. public interface List<E> 泛型接口 size()               ===> C#: Class List<T> "Count", Insert(index, value), list[index]
 * 3. Implementing Classes:
 *    AbstractList, "ArrayList", CopyOnWriteList, "LinkedList", Stack栈, Vector向量
 */

/**
 * 使用列表的优缺点：
 * 1. 查找数据的速度快 (直接使用下标索引值)  ===> 存储的地址是连续的 !! 计算偏移量
 * 2. 在中间插入新的元素速度很慢 (需用移动后面所有位置的值)
 */
public class LearnArrayList {

    private ArrayList<String> myList = new ArrayList<>();

    public void addItemToList(String item) {
        myList.add(item);
    }

    // size() 作为列表的长度
    // get() 直接取指定位置的长度
    public void displayListItems() {
        System.out.println("You have " + myList.size() + " items ");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }

    // set() 设置指定的位置的值
    public void modifyListItem(int position, String newItem) {
        if (position >= 0 && position < myList.size()) {   // 判断是否是有效的position值 !! 或者是否有找到Item对应的Position
            myList.set(position, newItem);
        }
    }

    // remove() 移除指定位置的值，其他的值会自动的组合 !!!
    public void removeListItem(int position) {
        if (position >= 0 && position < myList.size()) {
            String removeItem = myList.get(position);
            myList.remove(position);
        }
    }

    // contains() 判断是否存在指定的Item
    // indexOf() 判断指定Item的位置Position
    public String findItem(String searchItem) {
        boolean isExist = myList.contains(searchItem);
        int position = myList.indexOf(searchItem);
        if (position >= 0) {
            return myList.get(position);
        }
        return "";
    }

    /**
     * ArrayList的复制：核心方法 System.arraycopy(elementData, 0, a, 0, size);
     * 1. addAll() 直接复制ArrayList中的所有值
     * 2. new ArrayList<>() 直接在初始化成指定的ArrayList中的值 !!!
     * 3. 使用循环，依次复制
     */
    public void copyArrayList() {
        ArrayList<String> nextArray = new ArrayList<>(myList);

        ArrayList<String> newArray = new ArrayList<>();
        newArray.addAll(myList);
    }

    private void convertListToArray() {
        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray); // 将要转换成的数组作为参数传递 !!!

        String[] myArray02 = (String[]) myList.toArray(); // Object[] -> String[]
    }

    public void testInsertItemToArrayList() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(4);
        intList.add(1, 2); // 导致index=1往后的值都会移动，以完成列表长度的自动扩充 !!
        intList.remove(1); // 后面位置的值自全部向前一位填充
    }

    /**
     * List<ReferenceType> baseArray = new ArrayList<>();
     * 添加数据到baseArray中
     * 1. List<ReferenceType> cloneArray = baseArray;                      ======> 直接赋值引用的方式，一定是Shadow Copy !!!
     * 2. List<ReferenceType> cloneArray = new ArrayList<>(baseArray);     ======> 参数@NotNull
     * 3. List<ReferenceType> cloneArray = new ArrayList<>();
     * cloneArray.addAll(baseArray);
     * 通过cloneArray修改存储数据
     * 结果如下:
     * 1. 如果ReferenceType是不可变类型 (String, Integer); 则体现为Deep Copy的效果
     * 2. 如果ReferenceType是可变类型 (自定义Class); 则体现为Shadow Copy的效果
     * 3. 对原始的列表追加新的元素，则不属于拷贝的内容
     */
    private void testShadowCopyAndDeepCopy() {
        // 背后实现机制：对象的克隆方法默认提供的是浅拷贝 !!!
        // Object.Clone(): this method performs a "shallow copy" of this object, not a "deep copy" operation.
    }
}
