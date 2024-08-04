package data_structure.array.sliding_windows;

public class ArrayWindowSubString {

    // TODO. Sliding Windows 金典使用场景
    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // Return the smallest index (left, right)
    public static int findClosestSumIndex(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        int left = 0;
        int closestSum = 0;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            while (sum > m) {
                sum -= nums[left];
                left++;
            }

            // 退出循环的条件:
            // sum <= m 在小于等于m的所有数里，越大的数则越接近
            if (sum > closestSum) {
                closestSum = sum;
                // 记录坐标[left, index]
            }
        }
        return 0;
    }

    // Minimum Size Subarray Sum
    // Given an array of positive integers nums and a positive integer target
    // Return the minimal length of a "contiguous subarray" [nums l, nums l+1, ..., nums r-1, nums r]
    // The sum is greater than or equal to target. If there is no such subarray, return 0
    // nums = [2,3,1,2,4,3], target = 7  -> [4,3] -> 2
    //
    // O(2n)=O(n) O(1)
    // 最终可能left和right从左到右各自移动一遍
    public int minSubArrayLen(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        // 在中间while迭代的时候，不能返回，需要判断到数组的最后
        while (right < nums.length) {
            sum += nums[right];
            // 如果累计的值超过了目标数值，则需要从左端进行循环
            while (sum >= target) {
                // 通过[j-i]计算有效最小窗口的宽度，注意长度 +1
                minLength = Math.min(minLength, right - left + 1);

                // 从头left开始减少，逐渐缩小区间
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // TODO. 尝试从计算的结果中找到普遍规律，以辅助最后的计算
    // Sum of All Odd Length SubArrays
    // Given an array of positive integers arr, return the sum of all possible odd-length subArrays of arr.
    // A subArray is a contiguous subsequence of the array.
    //
    // 1 2 = 1 + 2 = 1*1 + 2*1 = 3  <count=1,2>
    // 1 2 3 = 1 + 2 + 3 + (1+2+3) = 1*2 + 2*2 + 3*2 = 12 <count=3>
    // 1 2 3 4 = 1*2 + 2*3 + 3*3 + 4*2 = 25 <count=4>
    // 1,4,2,5,3 = 1*3 + 4*4 + 2*4 + 5*4 + 3*3 = 58 <count=5>
    // Explanation:
    // [1] = 1
    // [4] = 4
    // [2] = 2
    // [5] = 5
    // [3] = 3
    // [1,4,2] = 7
    // [4,2,5] = 11
    // [2,5,3] = 10
    // [1,4,2,5,3] = 15
    // => 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
    public static int sumOddLengthSubArrays(int[] arr) {
        int sum = 0;
        int length = 1;
        while (length <= arr.length) {
            int left = 0;
            int right = left + length - 1;

            // 初始化计算一轮指定区间长度的sub sum
            int subSum = 0;
            for (int index = left; index <= right; index++) {
                subSum += arr[index];
            }
            sum += subSum;

            // TODO. 典型的滑动窗口算法
            // 在滑动窗口时，将每一个数据累加到最后的结果sum中
            // 每次移动时，都需要更新subSum的值用于下一次的计算
            right++;
            while (right < arr.length) {
                subSum = subSum + arr[right] - arr[left];
                sum += subSum;
                left++;
                right++;
            }

            // 循环完一种长度之后，循环下一个奇数的长度
            length += 2;
        }
        return sum;
    }
}
