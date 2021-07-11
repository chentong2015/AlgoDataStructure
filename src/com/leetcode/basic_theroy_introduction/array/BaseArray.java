package com.leetcode.basic_theroy_introduction.array;

// Array 数组: 存储量(长度)固定
// 1. 查找元素速度快，根据下标index访问
// 2. 在中间插入元素慢，需要移动后面的元素
public class BaseArray {

    /**
     * TODO: 数组方法论, 如何解决数组相关的问题
     * 明确常见数组Constraints的约束条件：值的长度，值的范围，值的特点
     * 1. Change In-place直接利用数组位置，前端和尾部可以定位存放特征值，覆盖值或者交换位置
     * 2. 直接利用位置下标进行计算，将问题转换成和index相关
     * 3. 找寻value和index之间的对应关系，转换条件
     * 4. 遍历两遍数组，第一遍查找关键信息，第二遍得出结果
     * 5. 使用两个标识left，right，从左右两端往中间遍历
     * 6. 利用HashMap<>统计频率和HashSet<>判断key值
     * 7. 排序数组Arrays.sort()，将特征信息展示出来
     * 8. "bit位运算" & "求和解法"
     */

    // return new int[] {item1, item2, item3, ...}             直接在创建对象的时候初始化, []中无需表明具体数目
    // return Arrays.copyOf(results, count);                   从起始点截取指定的长度
    // return Arrays.copyOfRange(results, 0, count);           定位截取指定区间的数据, 复制到count位置的前一个位置 !!
    // System.arraycopy(digits, 0, results, 1, digits.length); 直接copy源数组的数据到目标数组中
}