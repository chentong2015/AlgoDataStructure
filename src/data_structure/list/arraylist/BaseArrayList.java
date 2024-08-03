package data_structure.list.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ArrayList 列表
// 1. 动态数组，长度可变
// 2. 查找速度比较快，直接通过index索引即可访问(数据存储的地址是连续的，可以直接计算偏移量)
// 3. 在数据中间插入元素的速度比较慢，会影响插入位置后面的元素
public class BaseArrayList {

    // 数组和list列表之间的相互转换
    // Arrays.asList(array) <-> list.toArray()
    private void testConvertArrayAndList() {
        Integer[] nums = {1, 2, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(nums));

        List<String> myList = new ArrayList<>();
        String[] myArray = new String[myList.size()];
        // 返回的数组中只会包含list中有的数据
        myArray = myList.toArray(myArray);
        // .toArray() 必须提供强制类型转换
        myArray = (String[]) myList.toArray();

        // TODO. List.of()构建的列表是不可变的List
        List<Integer> needs = List.of(2,3);
    }

    // TODO. List列表可以指定index位置进行插入，并且通过位置进行查询数据
    public void testArrayList() {
        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = list0; // another reference to list1
        // make an actual copy of list1 (存储的是值类型)深度拷贝
        List<Integer> list3 = new ArrayList<>(list0);

        list1.add(-1);
        list1.add(1, 6); // TODO. 在指定位置插入元素
        list1.add(1, 2); // 导致index=1往后的值都会移动，以完成列表长度的自动扩充，造成时间复杂度
        list1.remove(1);         // 后面位置的值自全部向前一位填充
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

    // TODO. 拷贝List数据到另外List
    // arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length)
    // 1. 如果ReferenceType是不可变类型(String, Integer); 则体现为Deep Copy的效果
    // 2. 如果ReferenceType是可变类型(自定义Class); 则体现为Shadow Copy的效果
    // 3. 对原始的列表追加新的元素，则不属于拷贝的内容
    public void testCopyArrayList() {
        List<String> myList = new ArrayList<>();
        ArrayList<String> copyList1 = new ArrayList<>(myList);
        ArrayList<String> copyList2 = new ArrayList<>();
        copyList2.addAll(myList);
    }

    private void convertListToArray(List<String> myList) {
        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray); // 将要转换成的数组作为参数传递 !!!

        String[] myArray02 = (String[]) myList.toArray(); // Object[] -> String[]
    }

    public void testInsertItemToArrayList() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(1, 2); // 导致index=1往后的值都会移动，以完成列表长度的自动扩充 !!
        intList.remove(1); // 后面位置的值自全部向前一位填充
    }
}
