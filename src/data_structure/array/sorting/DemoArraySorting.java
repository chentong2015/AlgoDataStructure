package data_structure.array.sorting;

import java.util.*;

// TODO. 探索在什么条件下优先考虑使用Arrays.sort()排序 ?
// 1. 问题无法直接通过"常规的O(n)时间复杂度"来解决
// 2. 排序后能够将问题答案的"特征信息(数值的相互计算和间隔)"完全展示出来
// 3. 排序后能够通过一次遍历得出结果(一次遍历可以完成两个层面的逻辑)
public class DemoArraySorting {

    // Minimize "Maximum Pair Sum" in Array
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
        int length = nums.length - 1;
        for (int index = 0; index < nums.length / 2; index++) {
            int tempSum = nums[index] + nums[length - index];
            if (maxPairSum < tempSum) {
                maxPairSum = tempSum;
            }
        }
        return maxPairSum;
    }

    // Given an array of integers, find minimum difference of two elements in array
    // 找出整型数组中两个元素之间最小的差值
    // [2, 5, 9, 4, 10] -> 1
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

    // TODO. 排序后的数据相邻位置的间隔值一定小于间隔位置的差值 !!
    //  最小的绝对值差值一定出现在排序后的两个相邻值之间
    // Minimum Absolute Difference 
    // Given an array of distinct integers' arr, 提供的数组中没有相同的值, 数据是乱序排列的
    // find all pairs of elements with the minimum absolute difference of any two elements.
    // 2 <= arr.length <= 105 注意检查特殊条件
    // Input: arr = [4,2,1,3]     --> Output: [[1,2],[2,3],[3,4]]
    // Input: arr = [1,3,6,10,15] --> Output: [[1,3]]
    //
    // O(nlogn+n+n) O(n)
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int index = 1; index < arr.length; index++) {
            int tempValue = arr[index] - arr[index - 1];
            if (tempValue < minDiff) {
                minDiff = tempValue;
                // 在找到最小间隔值时，清除掉之前存储的数据再重新累计
                resultList.clear();
                resultList.add(List.of(arr[index - 1], arr[index]));
            } else if (tempValue == minDiff) {
                // 将相同的最小间隔值在一次遍历时顺便插入 !!
                resultList.add(List.of(arr[index - 1], arr[index]));
            }
        }
        return resultList;
    }
}
