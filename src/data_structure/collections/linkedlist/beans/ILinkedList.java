package data_structure.collections.linkedlist.beans;

import data_structure.collections.linkedlist.beans.BaseListNode;

// 抽象算法逻辑 (Can-Do的抽象)：支持不同类型的数据结构
// 支持数据结构的基本操作，数据结构的Node节点可以完全自定义
public interface ILinkedList {

    BaseListNode getRoot();

    boolean addNode(BaseListNode newNode);

    boolean removeNode(BaseListNode node);

    // 从根节点开始遍历所有的节点 !!
    void traverse(BaseListNode root);
}
