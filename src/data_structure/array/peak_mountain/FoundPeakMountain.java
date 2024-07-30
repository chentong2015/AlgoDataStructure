package data_structure.array.peak_mountain;

public class FoundPeakMountain {

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

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 6, 3, 0};
        int[] nums2 = {1, 2, 5, 6, 6, 3, 0};
        int[] nums3 = {1, 9, 5, 6, 6, 3, 0};
        System.out.println(isMountainList(nums1));
        System.out.println(isMountainList(nums2));
        System.out.println(isMountainList(nums3));
    }

    // 判断数组中的数据是否形成"山"的形状: 数字规律必须(严格地)先升后降
    // [1, 2, 5, 6, 3, 0] -> true
    // [1, 2, 6, 6, 3, 0] -> false
    //
    // O(n)  O(1)
    public static boolean isMountainList(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        // 只需要判断是处在上升还是下降趋势即可，一次遍历循环
        boolean isUp = true;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] > nums[index - 1]) {
                if (!isUp) {
                    return false;
                }
            } else if (nums[index] == nums[index - 1]) {
                // 对于严格上升和严格下降而言，不存在连续两个相等的值
                return false;
            } else {
                if (isUp) {
                    isUp = false;
                }
            }
        }
        return true;
    }
}
