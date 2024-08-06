package data_structure.array.item_duplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainDuplicateItem {

    // TODO. 判断一组序列数据中是否存在重复元素：排序数据 & HashSet<>
    // Contains Duplicate
    // Return true if any value appears at least twice in the array
    // nums = [1,2,3,1] -> true
    // 1. 如果是无序数据则排序   O(nlog(n))+O(n) O(1)
    // 2. HashSet<>存储key值   O(n) O(n) 最差情况是读完全部的值
    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) return false;
        Arrays.sort(nums);
        for (int index=0; index < nums.length -1; index++) {
            if (nums[index] == nums[index+1]) {
                return true;
            }
        }

        // 优化算法，在边读取的时候边判断，而不是读完后，再进行二次遍历
        Set<Integer> setNums = new HashSet<>();
        for (int num : nums) {
            if (setNums.contains(num)) {
                return true;
            }
            setNums.add(num);
        }
        return false;
    }

    // TODO. 在指定Array集合的子区间中判断是否存在重复元素 => 在原始Set<>集合上增加移除操作
    // Contains Duplicate II
    // Given an integer array nums and an integer k,
    // return true if there are two distinct indices i and j in the array
    // nums[i] == nums[j] and abs(i - j) <= k
    // nums = [1,2,3,1], k = 3 -> true 两个相同值1的位置间隔为3
    //
    // k参数决定了，每次只需要关注[K+1]个元素范围中的数据
    // O(n) 大多数情况比上面时间复杂度更加优化
    // O(k) 很明显的减少空间复杂度
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int index = 0; index < nums.length; index++) {
            if (index > k) {
                set.remove(nums[index - k - 1]); // 移除前面添加过的元素
            }
            if (set.contains(nums[index])) {
                return true;
            }
            set.add(nums[index]);
        }
        return false;
    }
}
