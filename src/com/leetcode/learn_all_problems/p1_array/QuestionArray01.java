package com.leetcode.learn_all_problems.p1_array;

import java.util.ArrayList;
import java.util.List;

public class QuestionArray01 {

    // TODO: 利用数组值的区间来找坐标index位置，利用数组存储空间完成值的交换 !!
    // Find All Duplicates in an Array
    // All the integers of nums are in the range [1, n] and each integer appears once or twice
    // Return an array of all the integers that appears twice
    // 1 <= nums.length <= 10^5
    // 1 <= nums[i] <= nums.length                 1. 数组中元素的值的范围不超过数组的长度范围
    // Each element in nums appears once or twice  1. 只可能出现过一次或者两次
    // nums = [4,3,2,7,8,2,3,1] -> [2,3]
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            int currentNum = nums[index];
            int findIndex = currentNum - 1;
            if (currentNum == 0 || currentNum == index + 1) // 如果该位置是0，或者值已经在正确的位置上面了
                continue;
            if (currentNum != nums[findIndex]) {
                swap(nums, index, findIndex);
            } else {
                result.add(currentNum); // 说明要找的那个位置(要交换的位置)的值出现重复了
                nums[index] = 0;
            }
            index--; // 无论是交换还是提取重复的值，都需要退回一步再做判断
        }
        return result;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    // TODO: 根据数据的特点，value<->index通过位置关联，设置数组中的值，记录特征点，不需要交换两者的值
    // [4,3,2,7,8,2,3,1]
    // [4,3,2,-7,8,2,3,1]  标记负数表示能够通过某个位置上的值"定位找到"这个位置上面来
    // [4,3,-2,7,8,2,3,1]  当再次找位置的时候，如果之前已经"被找到过"，那个这个数字就是第二次出现的
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            int findIndex = Math.abs(nums[index]) - 1;  // 通过求Math.abs()绝对值，忽略掉原来标记的值造成的影响 !!
            if (nums[findIndex] < 0) {
                result.add(findIndex + 1);              // 根据下标记录重复的数据值
            }
            nums[findIndex] = -nums[findIndex];         // 由于最多只能出现两次，如果从负数恢复到正数，则不造成任何影响 !!
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // TODO: 如何才能使得模板最大可能的成立 nums[i] < nums[k] < nums[j] ?                  ==> O(n^3) 普通阀复杂度
    //       1. jIndex最为Peek峰值，取前面x小于它的最小值iIndex, 最有可能可能小于后面的kIndex  ==> O(n^2) 从前往后遍历，记录前面的min
    //       2. kIndex取后面小于jIndex值中的最大之，最有可能大于前面的iIndex                 ==> O(n)   遍历时比较并记录iValue或者kValue
    // 132 Pattern
    // 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
    // i < j < k and nums[i] < nums[k] < nums[j] Pattern满足坐标和值的对应关系，坐标不一定连续
    // Return true if there is a 132 pattern in nums, otherwise, return false
    // nums = [1,2,3,4]  -> false     1. 初始一共有n^3中组合可能
    // nums = [3,1,4,2]  -> true      2. 转换成n^2的时间复杂度     ==> i并不需要移动遍历，只需要记录在j前面的最小值，使得Pattern最有可能出现 !!
    // nums = [-1,3,2,0] -> true      3. 最终使用一次遍历O(n)复杂度 ==> 弄清楚3个位置要比较的关系，通过记录最值来解决
    public boolean find132Pattern(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            min = Math.min(nums[j], min);
            if (min == nums[j]) continue; // 这是把nums[j]取为最小值，需要往后再做判断
            // 说明前面的min < nums[j], 此条件下有可能成立
            for (int k = nums.length - 1; k > j; k--) {
                if (min < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    //             8 12 9 10 8 7 6 从前往后记录iIndex也可行
    // iValue=max  8j
    // iValue=8    12j
    // iValue=8         8<9<12成立

    // 1 2 3 4 5 6    降低的值会依次排布在前面，直到出现一个大值，改变排序并插入其中，同时设置kValue的值
    //           6j   kValue=min
    //         5j     kValue=min
    //       4j       kValue=min
    // ...

    // 8 12 9 10 8 7   6
    //                 6j   kValue=min
    //                 7j   kValue=6   当设置kValue的值的时候，说明最终条件成立了一半: nums[k] < nums[j]
    //                 8j   kValue=7
    //                 10j  kValue=8   对于10j而言，它后面小于它的且最大的值是8
    //             9j  10   kValue=8   9正好位于8~10之间，所以直接添加在前面    ==> 同时也说明9比10后面的值都要大，并不构成132Pattern
    //             12j 10   kValue=10  对于12j而言，它后面小于它的且最大的值是10 ==> 需要找到比12小的后面的最大值，使得132出现的概率最大
    // 8<10<12 条件成立
    public boolean find132Pattern2(int[] nums) {
        int jIndex = nums.length;
        int kValue = Integer.MIN_VALUE;
        for (int index = nums.length - 1; index >= 0; index--) {
            int iValue = nums[index];
            if (iValue < kValue) return true; // 当iValue小于kValue的时候，在这中间一定有一个jValue比kValue大，且满足左边关系 !!
            while (jIndex < nums.length && iValue > nums[jIndex]) {
                kValue = nums[jIndex++];
            }
            nums[--jIndex] = iValue;          // 往前一个位置坐标设置值
        }
        return false;
    }
}
