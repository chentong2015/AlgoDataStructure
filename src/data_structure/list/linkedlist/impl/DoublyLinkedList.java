package data_structure.list.linkedlist.impl;

import data_structure.list.linkedlist.beans.BaseListNode;
import data_structure.list.linkedlist.beans.ILinkedList;

// 双向链表节点的添加和删除
public class DoublyLinkedList implements ILinkedList {

    private BaseListNode root;

    public DoublyLinkedList(BaseListNode root) {
        this.root = root;
    }

    @Override
    public BaseListNode getRoot() {
        return this.root;
    }

    // 添加的节点的时候，需要按照顺序添加
    @Override
    public boolean addNode(BaseListNode newNode) {
        if (this.root == null) {
            this.root = newNode;
            return true;
        }
        BaseListNode currentNode = this.root;
        // currentNode不可能为null，在设置之前都测试了非空 !!!!
        while (currentNode != null) {
            int comparison = currentNode.compareTo(newNode);
            if (comparison < 0) {
                if (currentNode.next() != null) {
                    currentNode.next();  // 继续往后面移动，知道找到位置为止
                } else {
                    currentNode.setNext(newNode);   // Insert at end of list 注意设置双方向 !!!
                    newNode.setPrevious(currentNode);
                    return true;
                }
            } else if (comparison > 0) {
                if (currentNode.previous() == null) {
                    this.root = newNode;
                } else {
                    currentNode.previous().setNext(newNode);
                    newNode.setPrevious(currentNode.previous());
                }
                newNode.setNext(currentNode);
                currentNode.setPrevious(newNode);
                return true;
            } else {
                System.out.println("Can not add the same node");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean removeNode(BaseListNode node) {
        if (node == null) {
            System.out.println("Can not delete node null");
        }
        BaseListNode currentNode = this.root;
        while (currentNode != null) {
            int comparison = currentNode.compareTo(node);
            if (comparison == 0) {
                if (currentNode == this.root) {
                    this.root = currentNode.next();
                } else {
                    if (currentNode.next() == null) {
                        currentNode.previous().setNext(null);
                    } else {
                        // 删掉中间的一个Node，然后链接前后
                        currentNode.previous().setNext(currentNode.next());
                        currentNode.next().setPrevious(currentNode.previous());
                    }
                }
            } else if (comparison < 0) {
                currentNode = currentNode.next();
            } else {
                // 注意：链表按照顺序排列的
                System.out.println("Can not find the node to delete ...");
            }
        }
        return false;
    }

    // 遍历全部的节点
    // 这个方法不适合使用迭代的方式来遍历 ==> 遍历的层级过高 !!! 区别tree的层级
    @Override
    public void traverse(BaseListNode root) {
        if (root == null) {
            System.out.println("The linked list is null");
        } else {
            while (root != null) {
                System.out.println(root.getValue());
                root = root.next();
            }
        }
    }
}
