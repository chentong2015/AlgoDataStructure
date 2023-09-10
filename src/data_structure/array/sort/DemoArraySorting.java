package data_structure.array.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// TODO. 探索在什么条件下优先考虑使用Arrays.sort()排序 ?
// 1. 问题无法直接通过"常规的O(n)时间复杂度"来解决
// 2. 排序后能够将问题答案的"特征信息"完全展示出来
// 3. 排序后能够通过一次遍历得出结果 !!
public class DemoArraySorting {

    // Minimize Maximum Pair Sum in Array
    // n == nums.length
    // 2 <= n <= 10^5
    // n is even 数量必须是偶数
    // 1 <= nums[i] <= 10^5
    //
    // nums = [3,5,4,2,4,6]
    // sort -> 2 3 4 4 5 6 -> 8
    // 本质上只需要找到所有成组的对数中的和的最大值
    // 计算规律，最大值一定和最小的值进行组合成对，以降低pair和的最大值
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxPairSum = 0;
        int tempSum = 0;
        int length = nums.length - 1;
        for (int index = 0; index < nums.length / 2; index++) {
            tempSum = nums[index] + nums[length - index];
            if (maxPairSum < tempSum) {
                maxPairSum = tempSum;
            }
        }
        return maxPairSum;
    }

    // Given an array of integers, find minimum difference of two elements in array
    // 找出整型数组中两个元素之间最小的差值
    //
    // O(n(log(n)) 只有在排序之后，才能通过一次遍历迅速的找到结果
    public int minDiffElements(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        for (int index = 0; index < nums.length - 1; index++) {
            int diff = nums[index + 1] - nums[index];
            minDiff = Math.min(diff, minDiff);
        }
        return minDiff;
    }

    // Contains Duplicate
    // Return true if any value appears at least twice in the array
    // nums = [1,2,3,1] -> true
    public boolean containsDuplicate(int[] nums) {
        // 正确解法: 1. 先对数组进行排序，判断相邻两个值 Arrays.sort()     O(nlog(n)), O(1)
        //          2. 使用HashSet<>保存出现过的值，Set中不包含重复的值   O(n) 最差情况是读完全部的值 O(n)
        if (nums.length == 0) return false;

        // 使用HashSet<> 用空间复杂度来换取时间复杂度
        Set<Integer> setNums = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 优化算法，在边读取的时候边判断，而不是读完后，再进行二次遍历 !!
            if (setNums.contains(nums[i])) {
                return true;
            }
            setNums.add(nums[i]);
        }
        return false;
    }
}
