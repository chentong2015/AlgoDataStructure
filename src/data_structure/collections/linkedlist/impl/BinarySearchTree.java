package data_structure.collections.linkedlist.impl;

import data_structure.collections.linkedlist.beans.BaseListNode;
import data_structure.collections.linkedlist.beans.ILinkedList;

// 二叉树: 添加和删除节点的算法
// 1. 迭代的算法 ===> 递归带来的遍历层级会优化很多 !!
// 2. 没有指向父节点的方式 !!
public class BinarySearchTree implements ILinkedList {

    private BaseListNode root;

    public BinarySearchTree(BaseListNode root) {
        this.root = root;
    }

    @Override
    public BaseListNode getRoot() {
        return null;
    }

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
                    currentNode.next();   // Go to right part of tree
                } else {
                    currentNode.setNext(newNode);  // 成功设置之后，返回
                    return true;
                }
            } else if (comparison > 0) {
                if (currentNode.previous() != null) {
                    currentNode = currentNode.previous();
                } else {
                    currentNode.setPrevious(newNode);
                    return true;
                }
            } else {
                System.out.println("Can not add the same node");
                return false;
            }
        }
        return false;
    }

    // 删除节点的算法，需要零时保存Parent Node节点
    @Override
    public boolean removeNode(BaseListNode node) {
        if (node == null) {
            System.out.println("Can not delete node null");
        }
        BaseListNode currentNode = this.root;
        BaseListNode parentNode = currentNode;
        while (currentNode != null) {
            int comparison = currentNode.compareTo(node);
            if (comparison < 0) {
                parentNode = currentNode;
                currentNode = currentNode.next();
            } else if (comparison > 0) {
                parentNode = currentNode;
                currentNode = currentNode.previous();
            } else {
                // 问题在于：需要删除的当前的节点是属于父节点的左还是右节点，同时需要判断删除节点的左右节点
                preformRemoval(parentNode, currentNode);
                return true;
            }
        }
        return false;
    }

    // 在数据库的背后实现逻辑中：
    // 1. 针对大量的数据中的节点删除，通常可以通过Tag标记节点来做"暂时"移除 ===> 优化性能，在Query的时候忽略特定的值 !!!
    // 2. 在数据库刷新的时候，对数据进行重整
    private void preformRemoval(BaseListNode parentNode, BaseListNode currentNode) {
        if (currentNode.next() == null && currentNode.previous() == null) {
            // 直接删除叶子节点
            if (parentNode.previous() == currentNode) {
                parentNode.setPrevious(null);
            } else if (parentNode.next() == currentNode) {
                parentNode.setNext(null);
            } else {
                this.root = null;
            }
        } else if (currentNode.next() == null) {
            // 只提取currentNode左边的所有节点
            if (parentNode.previous() == currentNode) {
                parentNode.setPrevious(currentNode.previous());
            } else if (parentNode.next() == currentNode) {
                parentNode.setNext(currentNode.previous());
            } else {
                this.root = currentNode.previous();
            }
        } else if (currentNode.previous() == null) {
            // 只提取currentNode右边的所有节点
            if (parentNode.previous() == currentNode) {
                parentNode.setPrevious(currentNode.next());
            } else if (parentNode.next() == currentNode) {
                parentNode.setNext(currentNode.next());
            } else {
                this.root = currentNode.next();
            }
        } else {
            // 因为是通过值的替换来等效删除的效果，所以不需要判断父节点和子节点的关系 !
            // 当currentNode左边和右边的节点同时存在时  ===> 提取左边最大值或者是右边最小值作为删除节点的新值 !!!!
            BaseListNode rightMixParentNode = currentNode;
            BaseListNode tempNode = currentNode.next();
            while (tempNode.previous() != null) {  // 当tempNode左边节点为空时候结束，确保是最小值
                rightMixParentNode = tempNode;
                tempNode = tempNode.previous();
            }
            currentNode.setValue(tempNode.getValue());  // 一定是设置找到的那个最小值 !!
            if (rightMixParentNode.previous() == tempNode) {
                // 说明currentNode右边节点下面有左节点 !!!
                // 清除掉提取出来的那个节点
                rightMixParentNode.setPrevious(tempNode.next()); // 遍历完之后，tempNode一定没有左边的节点，所以直接赋右节点的值 !!!
            } else {
                // currentNode.next()就是右边的最小节点 !!!
                currentNode.setNext(tempNode.next());
            }
        }
    }

    // 二叉树的遍历算法 !!
    // 1. 先序、中序、后序, 针对"中间节点的位置"的遍历方式 !!!  ===> 都是DFS(深度优先遍历)
    // 2. 层次遍历, 从左到右, 从上到下 !!!                      ===> BFS广度优先遍历
    // 3. 非递归遍历, 使用Stack栈来暂存
    @Override
    public void traverse(BaseListNode root) {
        if (root != null) {
            // PreOrder 先序遍历
            System.out.println("Node value: " + root.getValue());
            traverse(root.previous());
            traverse(root.next());

            // InOrder 中序遍历
            traverse(root.previous());
            System.out.println("Node value: " + root.getValue());
            traverse(root.next());

            // PostOrder 后序遍历
            traverse(root.previous());
            traverse(root.next());
            System.out.println("Node value: " + root.getValue());
        }
    }
}
