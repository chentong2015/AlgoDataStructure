package data_structure.array.dimension3;

import java.util.*;

// TODO: Sorted排序数据的降维问题: index + 双指针
//  双指针的移动将找到所有“两两不重复的数据“且”它们和为target目标“
public class Dimension3Sum {

    // 3Sum Question
    // return all the triplets [nums[i], nums[j], nums[k]]
    // Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
    // Solution set must not contain duplicate triplets
    // nums = [-1,0,1,2,-1,-4] -> [[-1,-1,2],[-1,0,1]]
    // sorted= -4 -1 -1 0 1 2
    //            i  j      k
    //
    // 如何确保返回的结果组合不唯一 ？排除相同数据的判断
    // Time complexity: O(n^2)
    // Space complexity: O(n)
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // TODO. index每当移动到新值
            //  基于该值在[index+1,length-1]区间中找出所有符合要求的排列组合
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // 每次for循环，内层最多执行移动length长度，O(n)复杂度
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];
                if (total > 0) {
                    k--; // 总和值大，则减少最大值
                } else if (total < 0) {
                    j++; // 总和值小，则增加最小值
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // j移动到下一个不同的值，再进行组合j+k
                    j++;
                    while (nums[j] == nums[j-1] && j < k) {
                        j++;
                    }
                }
            }
        }
        return res;
    }
}
