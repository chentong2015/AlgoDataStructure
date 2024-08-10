package work_algorithms.sliding_windows;

public class ArrayWindowSubString {

    // TODO. Sliding Windows 金典使用场景 1
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

    // TODO. Sliding Windows 金典使用场景 2
    // Minimum Size SubArray Sum
    // Given an array of positive integers nums and a positive integer target
    // Return the minimal length of a "contiguous subArray" [nums l, nums l+1, ..., nums r-1, nums r]
    // The sum is greater than or equal to target. If there is no such subArray, return 0
    // nums = [2,3,1,2,4,3], target = 7  -> [4,3] -> 2
    //
    // O(2n)=O(n) O(1) 最终可能left和right从左到右各自移动一遍
    public int minSubArrayLen(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                // 通过[j-i]计算有效最小窗口的宽度，注意长度 +1
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // TODO. Sliding Windows 自定义变化滑框的大小(画框逐渐变化截取)
    // Sum of All Odd Length SubArrays
    // Given an array of positive integers arr,
    // return the sum of all possible odd-length subArrays of arr.
    // A subArray is a contiguous subsequence of the array.
    //
    // 1 2 = 1 + 2 = 3
    // 1 2 3 = 1 + 2 + 3 + (1+2+3) = 12
    // 1 4 2 5 3 = 1 + 4 + 2 + 5 + 3 + '7 + '11 + '10 + '15 = 58
    //
    // O(N + (N-3) + (N-5) + ... + 1) 所有可能的组合数据都需要遍历一遍
    // O(1)
    public static int sumOddLengthSubArrays(int[] arr) {
        int sum = 0;
        int oddLength = 1;
        while (oddLength <= arr.length) {
            int left = 0;
            int right = left + oddLength - 1;

            // 初始化计算一轮滑动区间[left, left+oddLength-1]
            int subSum = 0;
            for (int index = left; index <= right; index++) {
                subSum += arr[index];
            }
            sum += subSum;

            // TODO. 有了基础的滑动数据后，每移动一个index增减一个数据
            right++;
            while (right < arr.length) {
                subSum = subSum + arr[right] - arr[left];
                sum += subSum;
                left++;
                right++;
            }

            // 使用下一个奇数长度来作为滑动窗口的大小
            oddLength += 2;
        }
        return sum;
    }
}
