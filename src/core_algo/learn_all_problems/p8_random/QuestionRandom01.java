package core_algo.learn_all_problems.p8_random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QuestionRandom01 {

    // Random Pick Index
    // Given an integer array nums with possible duplicates, 
    // randomly output the index of a given target number. 
    // You can assume that the given target number must exist in the array.
    //
    // Input  ["Solution", "pick", "pick", "pick"]
    //        [[[1, 2, 3, 3, 3]], [3], [1], [3]]
    // Output [null, 4, 0, 2] 取值3的时候，从所有的该值位置index中随机取出一个位置
    // 有效位置不一定连续，每个有效位置被取到的概率相同
    //
    // 约束条件 Restrictions: 
    // 1 <= nums.length <= 2 * 10^4
    // -2^31 <= nums[i] <= 2^31 - 1
    // target is an integer from nums.
    // At most 10^4 calls will be made to pick.

    private int[] nums;
    private Map<Integer, Integer> countNums;

    // 需要避免增加额外的数据存储和统计 !!
    public QuestionRandom01(int[] nums) {
        this.nums = nums;
        countNums = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int count = countNums.getOrDefault(nums[index], 0);
            countNums.put(nums[index], count + 1);
        }
    }

    // Random(0 ~ n) 需要一个数字n作为随机可能性的范围数目
    // 使用返回的随机值，确定要取的第x个位置的值，不需要完全遍历数组中全部的值
    // O(n) O(1)
    public int pick(int target) {
        int count = countNums.get(target);
        int randomOffsetIndex = new Random().nextInt(count);
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == target) {
                if (randomOffsetIndex == 0) {
                    return index;
                } else {
                    randomOffsetIndex--;
                }
            }
        }
        return -1;
    }

    // TODO: 使用Math.random()返回"大于等于0.0且小于1.0的伪随机双精度数"
    //  借助ArrayList提取出来的信息，可以取指定位置的值
    // 需要遍历数组中全部的值 O(n) O(n)
    public int pick2(int[] arr, int target) {
        ArrayList<Integer> targetList = new ArrayList<>();
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == target) {
                targetList.add(index);
            }
        }
        int random = (int) (Math.random() * targetList.size());
        return targetList.get(random);
    }
}
