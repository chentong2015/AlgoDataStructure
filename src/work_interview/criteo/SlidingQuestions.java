package work_interview.criteo;

import java.util.ArrayList;
import java.util.List;

// 有关Sliding Windows滑动窗口算法的案例运用
public class SlidingQuestions {

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 4, 1, 8};
        List<Integer> result = getSlidingMax(nums, 2);
        for (int item: result) {
            System.out.println(item);
        }
    }

    // TODO. 滑动过程中如果窗口的Size确定，则可以只使用一个Index
    // Sliding Average
    // Calculate the average in windows size k, return list of averages
    //
    // input = 2 5 3 1 8, k = 3
    // output = 3, 3, 4
    //
    // O(n)   时间基于遍历完所有的数据
    // O(n-k+1) 空间基于会产生多少结果，最糟糕是O(n), 最佳为O(1)
    public static List<Integer> getSlidingAverage(int[] nums, int k) {
        List<Integer> averageList = new ArrayList<>();
        if (nums == null || nums.length < k) {
            return averageList;
        }
        if (k == 1) {
            for (int num: nums) {
                averageList.add(num);
            }
            return averageList;
        }

        // For the average for first k numbers
        int index = 0;
        int sum = 0;
        while (index < k) {
            sum += nums[index];
            index++;
        }
        averageList.add(sum / k);

        // For rest of numbers
        while (index < nums.length) {
            sum += nums[index];
            sum -= nums[index - k]; // remove the first num
            averageList.add(sum / k);
            index++;
        }
        return averageList;
    }

    // TODO. 使用一个Index来标识在Window中要找的结果
    // Sliding Max
    // Get the max value inside widows size k
    //
    // input = 2 5 3 4 1 8, k = 3
    // output = 5, 5, 4, 8
    //
    // O(n+n)   内层while和外层while分别最多只能运行n次
    // O(n-k+1)
    public static List<Integer> getSlidingMax(int[] nums, int k) {
        // Check conditions
        List<Integer> maxList = new ArrayList<>();
        int maxIndex = 0;
        int rightIndex = 0;

        // Get the first max value
        while (rightIndex < k) {
            if (nums[maxIndex] < nums[rightIndex]) {
                maxIndex = rightIndex;
            }
            rightIndex++;
        }
        maxList.add(nums[maxIndex]);

        while (rightIndex < nums.length) {
            if (maxIndex == rightIndex - k) { // if the max value is the first value
                maxIndex++; // Move maxIndex to next step, and refresh it
                int tempIndex = maxIndex;
                while (tempIndex <= rightIndex) {
                    if (nums[maxIndex] < nums[tempIndex]) {
                        maxIndex = tempIndex;
                    }
                    tempIndex++;
                }
            } else {
                if (nums[maxIndex] < nums[rightIndex]) {
                    maxIndex = rightIndex;
                }
            }
            maxList.add(nums[maxIndex]);
            rightIndex++;
        }
        return maxList;
    }
}
