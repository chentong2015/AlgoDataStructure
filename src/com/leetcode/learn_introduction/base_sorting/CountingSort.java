package com.leetcode.learn_introduction.base_sorting;

// Counting Sort 计数排序：稳定的线性时间排序算法
// O(n+k) 当输入的元素是n个0到k之间的整数时
// O(n+k)
public class CountingSort {

    // 计数排序是用来排序0到100之间的数字的最好的算法
    // 比如：10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位

    // 找出待排序的数组中最大和最小的元素
    // 统计数组中每个值为i的元素出现的次数，存入数组C的第i项
    // 对所有的计数累加C中的第一个元素开始，每一项和前一项相加
    // 反向填充目标数组：将每个元素i放在新数组的第C[i]}项，每放一个元素就将C[i]减去1

    public static int[] countSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for (int i : a) {
            if (i > max) max = i;
            if (i < min) min = i;
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for (int i = 0; i < a.length; ++i) {
            c[a[i] - min] += 1; //优化过的地方，减小了数组c的大小
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = a.length - 1; i >= 0; --i) {
            b[--c[a[i] - min]] = a[i]; //按存取的方式取出c的元素
        }
        return b;
    }
}
