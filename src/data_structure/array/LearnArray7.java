package data_structure.array;

public class LearnArray7 {

    // Find Peak Element
    // A peak element is an element that is strictly greater than its neighbors.
    // If the array contains multiple peaks, return the index to any of the peaks.
    // nums = [1] -> 0
    // nums = [1,2] -> 1
    // nums = [2,1] -> 1
    // nums = [1,2,3] -> 2
    // nums = [3,2,1] -> 0
    // nums = [1,2,3,1] -> 2
    // 开头和结尾的边界值需要做特殊的处理
    public static int findPeakElement(int[] nums) {
        int size = nums.length;
        if (size == 1 || nums[0] > nums[1]) {
            return 0;
        }
        if (nums[size-2] < nums[size-1]) {
            return size-1;
        }
        for (int index = 1; index < size - 1; index++) {
            if (nums[index] > nums[index-1] && nums[index] > nums[index+1]) {
                return index;
            }
        }
        return 0;
    }
}
