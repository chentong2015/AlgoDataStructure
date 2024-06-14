package work_interview.amazon;

import java.util.Arrays;

// TODO. 将问题简化成只需要移动一次到左右一侧，比较两种方式的步数
// 使用最小的步数将0和1排序好
// Sort 1's and 0's in array to the end. Either end is fine.
// Find the minimum number of adjacent swaps required to sort.
// [1,1,1,0,0,0] -> 0
// [0,0,0,1,1,1] -> 0
// [0,0,1,0,1,0] -> 3
// [0,0,0,0,1,0,1,0,0] -> 5
public class SwitchOneZero {

    public static void main(String[] args) {
        int[] nums1 = {1,1,1,0,0,0};
        int[] nums2 = {0,0,0,1,1,1};
        int[] nums3 = {1,0,1,0,1,0};
        int[] nums4 = {0,0,0,0,1,0,1,0,0};
        System.out.println(countMovesToRight(nums3, 1));

        System.out.println(findMinStepsOfSorting(nums1));
        System.out.println(findMinStepsOfSorting(nums2));
        System.out.println(findMinStepsOfSorting(nums3));
        System.out.println(findMinStepsOfSorting(nums4));
    }

    // TODO. 只需要统计移动的步数，并不需要交换数据的位置 > 不要求返回移动好的数组
    //  需要避免移动时将target位置上的值进行覆盖，造成步数统计错误
    public static int countMovesToRight(int[] nums, int target) {
        int count = 0;
        int right = nums.length - 1;
        for (int index=0; index < nums.length; index++) {
            if (nums[index] == target) {
                while (right > index && nums[right] == target) {
                    right--;
                }
                count+= right - index;
                right--;
            }
            if (right == index) {
                break;
            }
        }
        return count;
    }

    // 如果修改了原来的数组(Swap交换了元素)，则需要Copy拷贝出新的数组，增加空间复杂度
    public static int findMinStepsOfSorting(int[] nums) {
        int minStep1 = moveToRight(Arrays.copyOf(nums, nums.length), 1);
        int minStep2 = moveToRight(nums, 0);
        return Math.min(minStep1, minStep2);
    }

    private static int moveToRight(int[] nums, int moveValue) {
        int minSteps = 0;
        int right = nums.length - 1;
        for (int index=0; index < nums.length; index++) {
            if (nums[index] == moveValue) {
                while (right > index && nums[right] == moveValue) {
                    right--;
                }
                if (right > index) {
                    minSteps+= right - index;
                    swap(nums, index, right);
                } else {
                    break;
                }
            }
        }
        return minSteps;
    }

    private static void swap(int[] nums, int id1, int id2) {
        int tempValue = nums[id2];
        nums[id2] = nums[id1];
        nums[id1] = tempValue;
    }
}
