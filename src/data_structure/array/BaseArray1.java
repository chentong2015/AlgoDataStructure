package data_structure.array;

public class BaseArray1 {

    // Remove Element
    // Given an array nums and a value val, remove all instances of that value in-place and return the new length
    // The order of elements can be changed. It doesn't matter what you leave beyond the new length
    //
    // nums = [0,1,2,2,3,0,4,2], val = 2 -> nums = [0,1,3,0,4]
    // 对于不是要移除的元素，全部将其移动到数组的左边，返回左边指定长度的结果数据
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != val) {
                nums[left++] = nums[index];
            }
        }
        return left;
    }

    // Move Zeroes
    // Given an integer array nums, move all 0's to the end of it while
    // maintaining the relative order of the non-zero elements.
    // nums = [0,1,0,3,12] -> [1,3,12,0,0]
    // nums = [6,1,7,3,12] -> [6,1,7,3,12]
    public static void moveZeros1(int[] nums) {
        // 直接使用原始的数组来做数据的移动
        int left = 0;
        for (int index=0; index < nums.length; index++) {
            if (nums[index] != 0) {
                nums[left] = nums[index];
                left++;
            }
        }
        // 最后再补充尾部的0
        for (int index=left; index < nums.length; index++) {
            nums[index] = 0;
        }
    }

    // Rotate Array
    // Rotate the array to the right by k steps, where k is non-negative.
    // Input: nums = [1,2,3,4,5,6,7], k = 3 -> [5,6,7,1,2,3,4]
    // Reverse完全颠倒数组，然后再颠倒部分 O(n) O(1)
    // nums = [1, 2, 3, 4, 5, 6, 7]
    // nums = [7, 6, 5, 4, 3, 2, 1]
    //        [5, 6, 7, 4, 3, 2, 1]
    //        [5, 6, 7, 1, 2, 3, 4]
    public void rotateArray(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}
