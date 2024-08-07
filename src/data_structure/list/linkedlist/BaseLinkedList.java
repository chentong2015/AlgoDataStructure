package data_structure.list.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BaseLinkedList {

    public static void main(String[] args) {
        List<String> myLinkedList = new LinkedList<>();
        myLinkedList.add("item 01");
        myLinkedList.add("item 02");
        myLinkedList.add("item 03");
        myLinkedList.add(1, "add item"); // 在指定的位置添加节点数据 !!
        printLinkedList(myLinkedList);
        myLinkedList.remove(1);       // 移动指定位置的值
        String value = myLinkedList.get(1); // 取指定位置的值

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1, 10);  // TODO: 插入在指定的index位置，后面的元素会因此移位
        list.add(10);                  // 默认追加到末尾的位置
        int item = list.get(1);        // 使用index位置来获取元素(和list操作一致)
        int lastElement = list.get(list.size() - 1);
    }

    private static void printLinkedList(List<String> linkedList) {
        Iterator<String> item = linkedList.iterator(); // iterator 迭代: 只能往前，无法返回前一个节点 !!!
        // .hasNext() 是否item有指向能读取的element
        while (item.hasNext()) {
            System.out.println("Show the current value: " + item.next()); // 返回当前节点的值，然后移动到下一个节点 !!!
        }
    }

    // 方法问题: 在改变LinkedList中数据的同时，还返回了值 !!
    private static boolean addInOrder(LinkedList<String> linkedList, String newItem) {
        ListIterator<String> stringListIterator = linkedList.listIterator(); // 使用list iterator才能找到前一个节点 !!!
        // .hasNext()
        // 初始的时候，必须使用.next()拿到第一个节点的值 !!
        while (stringListIterator.hasNext()) {
            // next(); 使用next会自动的移到下一个节去，不在当前处理的节点上 !!
            int comparison = stringListIterator.next().compareTo(newItem);
            if (comparison == 0) {
                System.out.println("Find item exist !");
                return false;
            } else if (comparison > 0) {
                System.out.println("New Item should be before the current item (Next)");
                stringListIterator.previous();   // 由于不在当前的节点，所以需要找前一个节点
                stringListIterator.add(newItem); // ... -> previousItem -> newItem -> nextItem -> ...
                return true;
            } else {
                System.out.println("New Item should be after the current item (Next)");
            }
        }
        stringListIterator.add(newItem); // 添加在最后
        return true;
    }
}
