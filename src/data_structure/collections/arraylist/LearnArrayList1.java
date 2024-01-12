package data_structure.collections.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ArrayList 列表
// 1. 动态数组，长度可变
// 2. 查找速度比较快，直接通过index索引即可访问(数据存储的地址是连续的，可以直接计算偏移量)
// 3. 在数据中间插入元素的速度比较慢，会影响插入位置后面的元素

// 常见Collections集合API:
// Collections.sort(list)
// Collections.swap(list, 1, 2)
// List<Integer> array = Collections.synchronizedList(list) 线程安全的List列表
public class LearnArrayList1 {

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
    }

    // TODO. List列表可以指定index位置进行插入，并且通过位置进行查询数据
    public void testArrayList() {
        List<Integer> list0 = new ArrayList<>();
        List<Integer> list1 = list0; // another reference to list1
        // make an actual copy of list1 (存储的是值类型)深度拷贝
        List<Integer> list3 = new ArrayList<>(list0);

        list1.add(-1);
        list1.add(1, 6);
        list1.add(1, 2);         // 导致index=1往后的值都会移动，以完成列表长度的自动扩充，造成时间复杂度
        list1.remove(1);                // 后面位置的值自全部向前一位填充
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

    // TODO. 自定义从一个List拷贝数据到另外一个List
    // arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length)
    public void testCopyArrayList() {
        List<String> myList = new ArrayList<>();
        // 使用list来构建新的list
        ArrayList<String> copyList1 = new ArrayList<>(myList);
        // 直接将list中数据添加到新的list中 list.addAll()
        ArrayList<String> copyList2 = new ArrayList<>();
        copyList2.addAll(myList);
    }
}
