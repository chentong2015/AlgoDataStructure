package work_interview.adobe;

// 自定义实现数据结构类型: 核心功能是能够快速的将数据提取并移动
// Design a queue-like data structure
// that moves the most recently used element to the end of the queue.
public class MRUQueue {

    // 1. Array数组定长度
    // 2. Array数组提取数据很快，重排数据很慢
    private final int[] values;

    // Constructs the MRUQueue with n elements: [1,2,3,...,n]
    public MRUQueue(int n) {
        values = new int[n];
        for (int i=1; i<= n; i++){
            values[i-1] = i;
        }
    }

    // Moves the kth element (1-indexed) to the end of the queue and returns it.
    // O(n) 移动数据的时间复杂度
    public int fetch(int k) {
        int size =  values.length;
        if (k < 1 || k > size) {
            return -1;
        }
        int value = values[k-1];
        for (int i=k; i<size; i++) {
            values[i-1] = values[i];
        }
        // Put the temp value to end of queue
        values[size-1] = value;
        return value;
    }

    // Check order of all values after fetching one item
    public void printValues() {
        for (int i: values) {
            System.out.print(i + " ");
        }
        System.out.println("-----");
    }

    public static void main(String[] args) {
        MRUQueue mruQueue = new MRUQueue(10);
        System.out.println(mruQueue.fetch(5));
        mruQueue.printValues();
        System.out.println(mruQueue.fetch(3));
        mruQueue.printValues();
    }
}
