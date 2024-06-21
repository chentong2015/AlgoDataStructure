package data_structure.collections.arraylist.impl;

import java.util.ArrayList;

// 1. ArrayList中可以存放对象(自定义对象)，需要特化泛型的类型
// 2. ArrayList是一种class，需要调用()初始化器 !!!
// 3. 使用List接口可以进一步的解耦 !!
public class MyArrayList {
   
    private ArrayList<String> myList;

    // 一般在构造中初始化
    public MyArrayList() {
        myList = new ArrayList<>();
    }

    public void addItemToList(String item) {
        myList.add(item);
    }

    public void displayListItems() {
        System.out.println("You have " + myList.size() + " items ");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }

    // 判断是否是有效的position值 !! 或者是否有找到Item对应的Position
    public void modifyListItem(int position, String newItem) {
        if (position >= 0 && position < myList.size()) {
            myList.set(position, newItem);
        }
    }

    public void removeListItem(int position) {
        if (position >= 0 && position < myList.size()) {
            String removeItem = myList.get(position);
            myList.remove(position);  // 位置后面的元素会自动的向前移动
        }
    }

    public String findItem(String searchItem) {
        boolean isExist = myList.contains(searchItem);
        int position = myList.indexOf(searchItem);
        if (position >= 0) {
            return myList.get(position);
        }
        return "";
    }
}
