package work_interview.alibaba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO. 多数投票算法的本质：如果一个数据占据的比例较大，那么循环后一定是最容易留下来的数据
// 在循环的过程中根据数据的异同来进行相消处理
public class MajorityVotingAlgorithm {

    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 2,2,2,2,2,1,3,3,6  假如有一个数字出现的次数超过一半，count统计的mostFrequencyValue一定是这个数字
    // 1,2,2,3,3,2,6,2,2  统计的mostFrequencyValue值和数组中数据的排序无关
    //
    // O(n) O(1) 最佳的空间复杂度，避免使用HashMap<>开辟额外空间
    public int findMostFrequencyElement(int[] gifts, int n) {
        int count = 0;
        int mostFrequencyValue = gifts[0];
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                count = 1;   // 重新累计新的统计数字
                mostFrequencyValue = gifts[i];
            } else {
                if (mostFrequencyValue == gifts[i]) {
                    count++;
                } else {
                    count--; // 并新的数字抵消统计
                }
            }
        }
        // 最后确定元素的最大频率是否超过一半(该数据据不一定存在)
        int element = mostFrequencyValue;
        int size = (int) Arrays.stream(gifts).filter(item -> item == element).count();
        return (size > n / 2) ? mostFrequencyValue : 0;
    }

    // TODO. 多数元素的特征：1/3的数也是大多数，一组数据中超过1/3的数不可能超过2个
    // Majority Element II
    // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
    // nums = [3,2,3] -> 3
    // nums = [1] -> 1
    // nums = [1,2] -> 1,2
    // 1 <= nums.length <= 5 * 10^4
    // -10^9 <= nums[i] <= 10^9
    //
    // O(n) O(n) 使用HashMap<Integer, Integer>统计数据频率
    // O(n) O(1) 使用投票算法来降低空间复杂度
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int candidate1 = 0, candidate2 = 1, count1 = 0, count2 = 0;

        // 同时统计第一频率和第二频率的数据
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // 判断第一和第二频率的数据是否超过一定的比例
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > n / 3) res.add(candidate1);
        if (count2 > n / 3) res.add(candidate2);

        return res;
    }
}
