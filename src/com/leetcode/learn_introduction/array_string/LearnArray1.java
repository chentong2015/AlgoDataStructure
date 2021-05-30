package com.leetcode.learn_introduction.array_string;

import java.util.HashSet;

/**
 * Array 数组的认识
 * 1. 充分利用数组的位置和存储空间，通过一次遍历(或两次遍历)提取信息 ===> 数组的前端和尾部是可以定位存放特征值信息的，覆盖值或者进行加工 !!
 * 2. 常见技术 + 特殊情况
 * >  直接利用位置下标进行计算，将问题转换成和index相关
 * >  使用两端遍历，或者二分法优化元素判断
 * >  利用HashMap<>统计频率和HashSet<>判断key值存在
 * >  排序数组Arrays.sort(), 优化处理
 */
public class LearnArray1 {

    // Max Consecutive Ones
    // Given a binary array nums, return the maximum number of consecutive 1's in the array
    // nums = [1,1,0,1,1,1] -> 3
    public int findMaxConsecutiveOnes(int[] nums) {
        // 测试理解：1. 标记开始统计的起使位置，并在每一次完整的统计之后，比较最大的长度  O(n) O(1)
        int count = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count > 0) {
                if (nums[i] == 1) {
                    count++;
                } else {
                    max = Math.max(max, count);
                    count = 0;
                }
            } else {
                if (nums[i] == 1) count = 1;
            }
        }
        return Math.max(max, count); // 比较最后一个位置的统计结果
    }

    // Find Numbers with Even Number of Digits
    // Given an array nums of integers, return how many of them contain an even number of digits
    // nums = [12,345,2,6,7896] -> 2 只有两个数字是具有偶数个数的数字
    // 1 <= nums[i] <= 10^5 范围约束
    public int findNumbers(int[] nums) {
        // 测试理解：1. 根据约定的值的范围，只有2位和4位的数字需要被统计             ==> 10~99 || 1000~9999 受到数字范围的限制 !!
        //         2. 将int类型转成String类型，然后判断字符的多少，只取偶数的字符串 ==> 造成装箱的复杂度开销 !!

        // 正确理解：1. 取数字的长度来判断，依次除以10取出上一位进行累计
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int length = 1;
            int value = nums[i];       // 直接统计一个int数字有多少位，同时支持对负数的统计
            while (value / 10 != 0) {
                length++;
                value = value / 10;
            }
            if (length % 2 == 0) {
                sum++;
            }
        }
        return sum;
    }

    // Squares of a Sorted Array
    // Given an integer array nums sorted in non-decreasing order
    // Return an array of the squares of each number sorted in non-decreasing order
    // nums = [-4,-1,0,3,10] -> [0,1,9,16,100]
    // nums = [-7,-3,2,3,11] -> [4,9,9,49,121]
    public int[] sortedSquares(int[] nums) {
        // 测试理解：1. 最终根据结果值来排序，距离原点更远的排在后面，由于原始数组是从小到大排序的，所以可以从两边往中间读取，只需要读取一遍 !!
        //            O(n) O(n)
        int left = 0;
        int right = nums.length - 1;
        int index = nums.length - 1;
        int[] result = new int[nums.length];
        while (left < right) {
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                result[index--] = nums[right] * nums[right];
                right--;
            } else {
                result[index--] = nums[left] * nums[left];
                left++;
            }
        }
        result[0] = nums[left] * nums[left]; // 最后补充相同位置的中间最小值 !!
        return result;
    }

    // TODO: Duplicate Zeros 对数组中添加元素的典型优化, 两遍遍历(每次的操作和意义都不同) !!
    // Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right
    // Note that elements beyond the length of the original array are not written
    // arr = [1,0,2,3,0,4,5,0] -> [1,0,0,2,3,0,0,4] 只能在数组内操作，舍弃超过数组长度的数据
    //       [1,0,2,3,0,4,0,0] 中间过渡的数组，前面有多少个0，则尾部位置上面就补充多少个0，因为对应位置上的值会被移出数组之外 !!
    public void duplicateZeros(int[] arr) {
        // 测试理解：1. 常规解法，在数组中间插入值，需要移动后面所有的元素位置的值 O(n²) O(1)

        // 正确理解：1. 第一遍统计出来0，最后再将元素对应到到指定的位置，从尾部开始填写并将0进行展开 O(n) O(1)
        int countZero = 0;
        int length = arr.length - 1;
        for (int left = 0; left <= length - countZero; left++) {
            if (arr[left] == 0) {
                if (left == length - countZero) {   // 边界条件: [1,2,3,0,0?,0] ?位置的0没有办法被扩展2次，则直接保留一次即可
                    arr[length--] = 0;              // 将数组的最后一个位置确定为0，然后下降一位 !!
                    break;                          // break 退出，不考虑对该0的统计
                }
                countZero++;
            }
        }
        int last = length - countZero;               // 取第一个有效的元素，该元素的右侧全部为0
        for (int index = last; index >= 0; index--) {
            if (arr[index] == 0) {
                arr[index + countZero] = 0;          // 0的移动可以看成是包含自身作用的移动位置，和它前面的0的数目的移动位置
                countZero--;                         // 碰到一个0之后，降低一个0的作用
                arr[index + countZero] = 0;
            } else {
                arr[index + countZero] = arr[index]; // 普通index的位置，前面多少个0，就应该往后移动多少个位置 ===> 问题的核心
            }
        }
    }

    // TODO: 数组HashSet数据结构的金典使用，边读取边判断指定的条件 !!
    // Check If N and Its Double Exist
    // Check if there exists two integers N and M such that N is the double of M(N = 2*M), arr[i]==2*arr[j]
    // arr = [7,1,14,11]            -> true, 14=2*7
    // arr = [-2,0,10,-19,4,6,-8]   -> false
    public boolean checkIfExist(int[] arr) {
        // 测试理解：1. 直接使用HashSet<>, 判断key和2*key是否同时存在 O(2*n)=O(n) O(n)                           ===> 比使用Map<>快1ms
        HashSet<Integer> set = new HashSet<>();
        for (int a : arr) {
            if (a == 0 && set.contains(0)) {  // 0是特殊的值，如果有两个以上，则直接返回true !!
                return true;
            } else if (set.contains(2 * a) || (a % 2 == 0 && set.contains(a / 2))) { // 边读取值边判断是否包含 ==> 比重复判断快1ms
                return true;
            } else {
                set.add(a);
            }
        }
        return false;
    }
}
