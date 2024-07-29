package data_structure.array.duplicate_item;

// 对一组数据中的重复元素进行操作
public class OtherQuestions {

    // Remove duplicates from sorted array
    // The input array is passed in by reference
    // it doesn't matter what you leave beyond the returned length
    // [0,0,1,1,1,2,2,3,3,4] ->  [0,1,2,3,4,2,2,3,3,4]
    // [0,1,2,3,4] -> [0,1,2,3,4]
    // 排序好的数据，每次只需要检测新出现的值，直接利用数组本身来操作
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        for (int index = 1; index < nums.length; index++) {
            if (nums[left] != nums[index]) {
                if (left + 1 < index) {
                    nums[left] = nums[index]; // 只有当有间隔一个数据时才覆盖
                }
                left++; // 当出现不同的值时, left位置一定后移
            }
        }
        return left + 1;
    }
}
