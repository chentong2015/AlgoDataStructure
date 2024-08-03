package data_structure.list.linkedlist.beans;

public class ListNode extends BaseListNode {

    public ListNode(String value) {
        super(value);
    }

    @Override
    public BaseListNode next() {
        return this.rightLink;
    }

    @Override
    public void setNext(BaseListNode node) {
        // set方法可以返回新添加的Item，可有利于双向链表的设置
        this.rightLink = node;
    }

    @Override
    public BaseListNode previous() {
        return this.leftLink;
    }

    @Override
    public void setPrevious(BaseListNode node) {
        this.leftLink = node;
    }

    @Override
    public int compareTo(BaseListNode node) {
        if (node != null) {
            // Object类型没有compareTo()方法，使用String类型来做测试 !!!
            String currentValue = (String) super.getValue();
            String compareValue = (String) node.getValue();
            return currentValue.compareTo(compareValue);
        }
        return -1;
    }
}
