package com.leetcode.learn_all_problems.p7_bits;

/**
 * Bit位运算，侧重底层实现的原理，数据存储形式以及计算方式
 * 位运算的4大操作：与，或，异或，位移动
 * 测试时，将java的int类型视为无符号的类型来处理
 */
public class QuestionBits01 {

    // Number of 1 bits
    // 统计一个int类型的值中有多少个bit 1，由于Java的int类型都是带有符号的，所以需要注意最高位的处理
    // 基础类库已经实现的方法 Integer.bitCount(n);
    public int hammingWeight(int n) {
        // 正确理解：1. 从低位到高位，循环移位求于运算统计  O(1) 常量的bit位置数 O(1)
        int count = 0;
        int mark = 1;
        for (int i = 0; i < 32; i++) { // bit的位置从0到31
            if ((mark & n) != 0) {     // 00000000 00000000 00000000 00000001
                count++;
            }
            mark <<= 1;
        }
        return count;
    }

    // 正确理解：2. 从高位到低位，依次消掉位上面的值，统计
    public int countNumberOfBits(int n) {
        int sum = 0;
        while (n != 0) {     // 为0则说明，没有一个bit位置上面是1
            sum++;
            n = n & (n - 1); // n   = 01000000 00000000 00010000 00000010   => 将位置为10000，拆分成01111....
        }                    // n-1 = 01000000 00000000 00010000 00000001   => 与运算之后，这个1位置和后面的位置都清0，继续判断高位剩下的1
        return sum;          // n&(n-1) != 0 计算之后，高位上面的bits保持不变
    }

    // The Hamming distance between two integers is the number of positions at
    // which the corresponding bits are different 两个int不同的bit位的数目
    public int hammingDistance(int x, int y) {
        // 测试理解：0000 0001 0000 1010  求异或，不同的为1，相同的为0，然后统计1的数目
        //         0000 1000 0000 1000  依次从低到高判断每一位是否相同
        int num = x ^ y;
        return countNumberOfBits(num);
    }

    // Reverse Bits
    // 10000000000000000000000000000001
    // 11111111111111111111111111111101 ==> java中最高位的1会被考虑成(-1)负数来处理, 也就时在最后的结果上减1
    // 10111111111111111111111111111101 ==> test
    // 10111111111111111111111111111111 ==> expected
    public int reverseBits(int n) {
        // 测试理解: 1. 从低位到高位，依次取每个位置上bit，然后累加(放置)到结果的指定位置上面
        int result = 0;
        int position = 31;
        int mark = 1;
        for (int index = 0; index < 32; index++) {
            int bit = n & mark;
            if (index == 31 && bit != 0) { // 最高位bit需要特殊处理
                result += 1;               // -1反转过来的值
            } else {
                bit = bit >>= index;
                result += bit <<= position;
                position--;
                mark <<= 1;
            }
        }
        return result;
    }

    // C++解法：
    // uint32_t reverseBits(uint32_t n) {
    //   n = (n >> 16) | (n << 16);
    //   n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
    //   n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
    //   n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
    //   n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
    //   return n;
    // }
}
