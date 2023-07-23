package data_structure.sorting;

import java.util.Arrays;

// 金典排序算法 Arrays.sort()
// 10种算法 https://www.cnblogs.com/onepixel/articles/7674659.html
public class BaseSorting {

    // 1. 插入排序  O(n²)      O(1)       每读取一个值, 将它插入到前面有序的序列中
    // 2. 选择排序  O(n²)      O(1)       每次遍历找最大或者最小值, 放到开头或者末尾
    // 3. 冒泡排序  O(n²)      O(1)       两两比较交换, 依次交换
    //
    // 4. 归并排序  O(nlog(n)) O(n)       Divide and Conquer分治法, 将长度切分成n/2个子序列, 两两进行归并, 最后合并
    // 5. 快速排序  O(nlog(n)) O(log(n))  Quick Sort, 设置pivot基准, 然后进行partition分区
    // 6. 用堆排序  O(nlog(n)) O(1)       Heap完全二叉树, 先构建堆然后将root节点和最后一个元素Rn交换, 剩下R1-Rn-1个无序区, 再找第二大的
    //
    // 7. 计数排序  O(n+k)     O(n+k)     n个0到k之间整数, 当k不是很大并且序列比较集中时, 考虑使用计数排序, 先统计再依次输出
    //
    // 8. 拓扑排序: 图

    // 1. 冒泡法(每次冒泡的最值排到最后)
    private static int[] bubbleSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);  // 使用Java定义好的方法 !!!
        if (sortedArray.length > 1) {
            for (int i = 0; i < sortedArray.length - 1; i++) {
                for (int j = 0; j < sortedArray.length - 1 - i; j++) {
                    if (sortedArray[j] > sortedArray[j + 1]) {
                        swapValuesWithIndex(sortedArray, j, j + 1);
                    }
                }
            }
        }
        return sortedArray;
    }

    // 2. 反复循环(直到不再有顺序的交换为止, 复杂度为n*n)
    private static int[] simpleSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        boolean isAlreadySorted = false;
        while (!isAlreadySorted) {
            isAlreadySorted = sortArray(sortedArray);
        }
        return sortedArray;
    }

    // 以传引用的形式传递参数 !!!
    // 1. 方法中的修改的值，在方法外部也能看到
    // 2. 这里的array参数会拿到引用类型中存储的地址，成为又一个引用变量 !!!
    // 3. 如果array参数在方法中引用创建的新对象，则它的修改不再影响到外部 !!!
    public static boolean sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                swapValuesWithIndex(array, i, i + 1);
                return false;
            }
        }
        return true;
    }

    public static void swapValuesWithIndex(int[] array, int firstIndex, int secondIndex) {
        int tempValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tempValue;
    }

    // 数组反转的算法：只需要遍历一半的值，然后交换镜像的两个值 !!!
    // 如果不减半的话，则会恢复成原来的值
    public static void reverse(int[] array) {
        int maxLength = array.length - 1;
        for (int i = 0; i < maxLength / 2; i++) {
            swapValuesWithIndex(array, i, maxLength - i);
        }
    }
}
