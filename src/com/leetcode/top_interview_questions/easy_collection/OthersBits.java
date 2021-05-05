package com.leetcode.top_interview_questions.easy_collection;

/**
 * 位运算的4大操作：与，或，异或，位移动
 */
public class OthersBits {

    // Number of 1 bits
    // 统计一个int类型的值中有多少个bit 1，由于Java的int类型都是带有符号的，所以需要注意最高位的处理
    public int hammingWeight(int n) {
        // 测试理解：基本的位运算，求位与和位或
        //         Integer.bitCount(n); 不能直接使用已有的方法

        // 正确理解：1. 循环移位求于运算，然后统计 -> 从地位到高位
        //         2. 依次消掉位上面的值，统计   -> 从高位到低位
        //         O(1) 一共只需要比较32个位上面的值  O(1)
        int count = 0;
        int mark = 1;  // 00000000 00000000 00000000 00000001
        for (int i = 0; i < 32; i++) {
            if ((mark & n) != 0) {  // 不为0, 则说明测试的位置上是1
                count++;
            }
            mark <<= 1; // 往左边，往高位移动
        }

        // 01000000 00000000 00010000 00000001
        // 00100000 00000000 000010000 00000001 减1往右边移动，从最高位开始测试，然后取移位后的下一个值 !!
        int sum = 0;
        while (n != 0) {
            sum++;
            n = n & (n - 1);
        }
        return count;
    }

    // The Hamming distance between two integers is the number of positions at
    // which the corresponding bits are different 两个int不同的bit位的数目
    public int hammingDistance(int x, int y) {
        // 测试理解：0000 0001 0000 1010  求异或，不同的为1，相同的为0，然后统计1的数目
        //         0000 1000 0000 1000  依次从低到高判断每一位是否相同
        //         O(1) O(1)
        int n = x ^ y;
        int count = 0;
        int mark = 1;
        for (int i = 0; i < 32; i++) {
            if ((mark & n) != 0) {
                count++;
            }
            mark <<= 1;
        }
        return count;
    }

    // Reverse Bits
    // Treat n as an unsigned value 在Java中，将值视为无符号的int
    public int reverseBits(int n) {
        // 测试理解: 新的值需要从低往高取出bit位上的值, 然后加给反转的int值
        int mark = Integer.MAX_VALUE; // 这里没有办法设置到值 1000 0000 0000 ...
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += mark & n;
            result <<= 1;
            mark >>= 1;
        }

        // 正确理解: 从低位往高位输送，而不是从从高位取下来送到低位 !!
        for (int i = 0; i < 32; i++) {
            result += mark & n;
            result <<= 1;
            mark >>= 1;
        }
        return result;
    }
}
