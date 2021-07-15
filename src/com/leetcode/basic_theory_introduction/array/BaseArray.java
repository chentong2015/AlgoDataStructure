package com.leetcode.basic_theory_introduction.array;

// Array 数组: 存储量(长度)固定
// 1. 查找元素速度快，根据下标index访问
// 2. 在中间插入元素慢，需要移动后面的元素
// 明确数组常见Constraints约束条件：值的长度，值的范围，值的特点
public class BaseArray {

    /**
     * 数组方法论:
     * 1. Change In-place直接利用数组位置，前端和尾部可以定位存放特征值，覆盖值或者交换位置
     * 2. 直接利用位置下标进行计算，将问题转换成和index相关
     * 3. 遍历两遍数组，第一遍查找关键信息，第二遍得出结果
     * 4. 使用两个标识left，right，从左右两端往中间遍历
     * 5. 使用HashSet<>判断key值，HashMap<>统计频率
     * 6. 使用Arrays.sort()排序，将特征信息展示出来
     * 7. "bit位运算" & "求和解法"
     * 8. TODO: 寻找value和index之间对应关系，转换条件
     * 9. TODO: 观察满足条件的通项公式，使之不仅在一般条件下成立，同时最大可能性成立 !!
     * 10.TODO: 在遍历时用一两个变量记录历史关键信息，避免使用额外数据结构 !!
     */

    // return new int[] {item1, item2, item3, ...}             返回数据，需要将数组构建出来
    // return Arrays.copyOf(results, count);                   从起始点截取指定的长度
    // return Arrays.copyOfRange(results, 0, count);           定位截取指定区间的数据, 复制到count位置的前一个位置 !!
    // System.arraycopy(digits, 0, results, 1, digits.length); 直接copy源数组的数据到目标数组中
}
