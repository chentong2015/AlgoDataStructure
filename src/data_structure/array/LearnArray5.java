package data_structure.array;

import java.util.HashSet;

public class LearnArray5 {

    // 找出int类型的数组中最接近0的值，如果有两个同距离的值，则返回正数
    // [-2, 3, 2, 5, -1] => -1
    // [-2, 3, 5] => -2
    //
    // 一个值记录比较的最小绝对值，一个值记录最后的结果
    // 只有两种条件下才会更新最后的result
    // O(n) O(1)
    static int closestToZero(int[] ints) {
        if (ints == null || ints.length == 0) {
            return 0;
        }
        int result = 0;
        int closestAbs = Integer.MAX_VALUE;
        for (int i : ints) {
            if (Math.abs(i) < closestAbs) {
                closestAbs = Math.abs(i);
                result = i;
            } else if (Math.abs(i) == closestAbs && i > 0) {
                result = i;
            }
        }
        return result;
    }

    // TODO: 数组金典案列, 利用数组空间"暂存数据", 使用3轮遍历 !!
    // Given an unsorted integer array nums, find the smallest missing positive integer
    // You must implement an algorithm that runs in O(n) time and uses constant extra space
    // 3 4 5 1         -> 2  不能使用排序，不能超过O(n)的时间复杂度
    public int firstMissingPositive(int[] nums) {
        // 测试理解：1. 使用HashSet<>存储数据，然后从1开始判断缺失位置  O(n), O(n)
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int index = 1; index <= nums.length; index++) {
            if (!set.contains(index)) {
                return index;
            }
        }
        return nums.length + 1; // 如果前面从数字1开始依次排序，则第一缺失的最小数是length+1
    }

    // 正确理解：1. > 理论基础1: [1,n]数组，如果数字全部从1到n连续出现，则第一个缺失的正数必然是n+1     ===> 时间复杂度比上面减低了15倍以上
    //            > 理论基础2: 如果数字区间有位置缺失，则必然在[1,n]区间中，排除负数和超区间外的值     ===> 稳定的算法
    // 6 0 9 2 3 1  -1  ==> 第一轮将边界外的值设置成特殊的值
    // 6 0 8 2 3 1  8   ==> 将负数和大数统一设置成n+1特征值，避免使用到该index来定位(因为在数组有效的范围之外)  !!
    // 6 0 8 2 3 -1 8   ==> 每个位置的值，使用绝对值来确定要标记的位置，这里的-1在被标记完之后，还能再次使用来定位 !!
    //           x
    //   x
    //     x
    // x
    // x x x     x      ==> 标记完成的结果，找到每一标记的位置，就是第一个缺失的正数
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int index = 0; index < n; index++) {
            if (nums[index] <= 0 || nums[index] > n) {
                nums[index] = n + 1; // n+1 特征的边界值
            }
        }
        for (int index = 0; index < n; index++) {
            int position = Math.abs(nums[index]);
            if (position <= n) {
                position--;          // 确定要"标记"负值的坐标位置; 如果要标记的位置上是负数，说明被标记过，无需再标记 !!
                if (nums[position] > 0) {
                    nums[position] = -1 * nums[position];
                }
            }
        }
        for (int index = 0; index < n; index++) {
            if (nums[index] > 0) {
                return index + 1;
            }
        }
        return n + 1;
    }
}
