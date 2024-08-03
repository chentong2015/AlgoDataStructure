package work_algorithms.dp_algo;

import java.util.Arrays;

public class DynamicProgramming1 {

    // TODO: DP典型实例：结合二分法修改记录的历史数据，对dp数据进行扩充
    // 最长连续增长子序列，可以演变为最长满足条件的增长
    // Longest Increasing Subsequence
    // An integer array nums, return the length of the longest strictly increasing subsequence
    // nums  = [10,9,2,5,3,7,101,18] -> [2,3,7,101] 最长连续增长子序列, 数字之间可以不连续
    // steps = [1, 1,1,2,2,3,4  ,4]
    //
    // Dynamic Programming with Binary Search: O(nlog(n)) O(n)
    // Store increasing subsequence formed by including currently encountered element
    // input: [0, 8, 4, 12, 2] 数组需要预留足够的长度
    //    dp: [0]
    //    dp: [0, 8]
    //    dp: [0, 4]
    //    dp: [0, 4, 12]
    //    dp: [0, 2, 12] 这里存储的不是正确的排序值，但是数组的长度是最终的答案
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int length = 0;
        for (int num : nums) {
            // Returns index of search key
            // Return (-(insertion point) - 1) if not found
            int index = Arrays.binarySearch(dp, 0, length, num);
            if (index < 0) {
                index = -(index + 1); // 重新计算要插入的点，可能直接追加到最后
            }

            dp[index] = num;          // 将读取值添加到dp数组中指定位置"insertion point"
            if (index == length) {    // 如果是插入到最后，则需要延申长度
                length++;
            }
        }
        return length; // length比插入的index坐标位置要多1
    }

    // TODO. 动态编程：在遍历过程中存储两个重要的历史记录
    // 132 Pattern
    // 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
    // i < j < k and nums[i] < nums[k] < nums[j]
    // Return true if there is a 132 pattern in nums, otherwise return false
    // nums = [1,2,3,4]  -> false
    // nums = [3,1,4,2]  -> true
    // nums = [-1,3,2,0] -> true
    //
    // 1 2 3 4  5  6    降低的值会依次排布在前面直到出现一个大值，改变排序并插入其中，同时设置kValue的值
    //             6j   kValue=min
    //          5j 6    kValue=min
    //       4j 5  6    kValue=min
    //
    // 8 12 9 10 8 7   6
    //                 6j   kValue=min
    //                 7j   kValue=6   当设置kValue的值的时候，说明最终条件成立了一半nums[k] < nums[j]
    //                 8j   kValue=7
    //                 10j  kValue=8   对于10j而言，它后面小于它的且最大的值是8
    //
    //             9j  10   kValue=8   9正好位于8~10之间，所以直接添加在前面
    //                                 同时也说明9比10后面的值都要大，并不构成132Pattern
    //
    //             9   12j  kValue=10  对于12j而言，它后面小于它的且最大的值是10
    //                                 需要找到比12小的后面的最大值，使得132出现的概率最大
    //             8<10<12  条件成立
    //
    // O(n+n) O(1) 极限情况下内层的while循环会执行(n-1)次
    public boolean find132Pattern(int[] nums) {
        int jMaxIndex = nums.length;    // j为最大值的位置
        int kValue = Integer.MIN_VALUE; // k为j后面的小于j的最大值
        for (int index = nums.length - 1; index >= 0; index--) {
            int iValue = nums[index];
            if (iValue < kValue) {      // i为j前面的小于k的值
                return true;
            }

            // TODO. 找jValue的后面的小于它的kValue
            while (jMaxIndex < nums.length && nums[jMaxIndex] < iValue) {
                kValue = nums[jMaxIndex];
                jMaxIndex++;
            }

            // TODO. 将读取的iValue转换成jValue，当成132中的最大值存储
            jMaxIndex--;
            nums[jMaxIndex] = iValue;
        }
        return false;
    }
}
