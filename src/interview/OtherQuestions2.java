package interview;

public class OtherQuestions2 {

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

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 6, 3, 0};
        int[] nums2 = {1, 2, 5, 6, 6, 3, 0};
        int[] nums3 = {1, 9, 5, 6, 6, 3, 0};
        System.out.println(isMountainList(nums1));
        System.out.println(isMountainList(nums2));
        System.out.println(isMountainList(nums3));
    }
}
