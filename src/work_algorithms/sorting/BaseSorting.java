package work_algorithms.sorting;

import java.util.Arrays;

public class BaseSorting {

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
