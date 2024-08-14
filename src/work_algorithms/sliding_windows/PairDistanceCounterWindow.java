package work_algorithms.sliding_windows;

import java.util.Arrays;

public class PairDistanceCounterWindow {

    // TODO. 数组必须先排序好才能运用该滑动窗口
    // Count Pair Distance less than target
    // 滑动时每增加一个数则距离的最大值增减，从左侧划掉一个则首尾的距离降低
    //
    // nums = [1,3,5,6,9,10], target = 5
    // 1 + 2 + 3 + 2 + 3 = 11 一共有11个组合的距离小于等于5
    //
    // O(n*log(n) + n) O(1)
    private static int countDistanceLessThanMid(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > target) {
                left++;
            }
            // (left, left+1, left+2,,,right)
            // 区间中right与它左侧的距离都小于等于target
            // 每添加一个right坐标将累计形成的组合数量，所有Pair对组合情况都考虑了 ！
            count += right - left;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6,9,10};
        System.out.println(countDistanceLessThanMid(nums, 5));
        System.out.println(countDistanceLessThanMid(nums, 6));
    }
}
