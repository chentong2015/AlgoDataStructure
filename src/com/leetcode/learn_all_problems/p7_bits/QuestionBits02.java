package com.leetcode.learn_all_problems.p7_bits;

public class QuestionBits02 {

    // JDK7 Hashmap源码之位运算
    // 如果找到小于等于一个int类型的数的"二的幂次方值": 17->16, 10->8, 4->4
    // TODO: 二的幂次方值指的是bit位置上只有一个位置为1，其余的位置都为0
    // 1. 只用while循环，依次判断2个幂次方的数是否小于等于指定的数字，一旦超过(注意边界)则停止循环
    // 2. 直接使用位运算+位移，将数子所在的最高bit位以下的所有位置全部设置成1，然后做差值
    //       001* ****
    // >>1   0001 ****
    // |     0011 ****
    // >>2   0000 11**
    // |     0011 11**
    // >>4   0000 0011
    // |     0011 1111 到此最高位1的后面全部都设置成1
    // >>8             之后的移动不会再改变结果
    // >>16
    // >>>1  0001 1111 最后将结果再做无符号右移一位
    // -     0010 0000 "二的幂次方值"的最大值，低位全部清除成0
    public static int highestOneBit(int value) {
        // int做占32位，除掉符号位为31位，最多移动5次则结果不会再改变
        value |= (value >> 1);
        value |= (value >> 2);
        value |= (value >> 4);
        value |= (value >> 8);
        value |= (value >> 16);
        return value - (value >>> 1);
    }

    // 反之：找到大于等于给定值的"二的幂次方值": 16->16, 17->32
    public static int lowerOneBit(int value) {
        // TODO: 先减一然后翻倍找小于等于给定值的"二的幂次方值"
        return highestOneBit((value - 1) << 1);
    }
}
