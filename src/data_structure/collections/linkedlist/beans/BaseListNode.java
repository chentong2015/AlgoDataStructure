package data_structure.collections.linkedlist.beans;

// 注意：该抽象类位于不同的package中，在使用的时候需要注意开闭性 !!!
public abstract class BaseListNode {

    protected Object value;  // Object能够最大限度的解耦 !
    protected BaseListNode leftLink = null;
    protected BaseListNode rightLink = null;

    public BaseListNode(Object value) {
        this.value = value;
    }

    // 这里的next可以看成是二叉树的右边Right
    public abstract BaseListNode next();

    public abstract void setNext(BaseListNode node);

    // 这里的previous可以看成是二叉树的右边Left
    public abstract BaseListNode previous();

    public abstract void setPrevious(BaseListNode node);

    // 可以实现泛型接口中方法 public interface Comparable<T> !!!
    public abstract int compareTo(BaseListNode node);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}