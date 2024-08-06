package data_structure.array.dimension3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Dimension3Sum {

    // TODO: 经典降维问题: 确定一个Index位置的坐标，从三维降到二维的复杂度
    // 3Sum Question
    // return all the triplets [nums[i], nums[j], nums[k]]
    // Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
    // Solution set must not contain duplicate triplets
    // nums = [-1,0,1,2,-1,-4] -> [[-1,-1,2],[-1,0,1]]
    //         -4 -1 -1 0 1 2
    //
    public List<List<Integer>> threeSumZero(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果index位置和index-1的位置相同，则说明该值已经被判断过了，无需重复
            if (i == 0 || nums[i - 1] != nums[i]) {
                int target = -nums[i];

                int jIndex = i + 1;
                int kIndex = nums.length - 1;
                while (jIndex < kIndex) {
                    if (nums[jIndex] + nums[kIndex] == target) {
                        res.add(Arrays.asList(nums[i], nums[jIndex], nums[kIndex]));
                        // 如果是相同的值，在直接跳过，直到是不同的值
                        while (jIndex < kIndex && nums[jIndex] == nums[jIndex + 1])
                            jIndex++;
                        while (jIndex < kIndex && nums[kIndex] == nums[kIndex - 1])
                            kIndex--;
                        jIndex++;
                        kIndex--;
                    } else if (nums[jIndex] + nums[kIndex] < target) {
                        jIndex++;
                    } else {
                        kIndex--;
                    }
                }
            }
        }
        return res;
    }
}
