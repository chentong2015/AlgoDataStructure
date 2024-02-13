package questions.sorting_algo.bucket_sort;

import java.util.ArrayList;
import java.util.List;

// TODO. Bucket Sort 将大量的数据划分到不同的桶中，将桶联系在一起组成从小到大的顺序
// 桶排序涉及创建一个数组(桶)，其中的元素表示覆盖要排序的数字范围的桶
// 想象一下尝试对一副纸牌进行排序: 只需浏览一遍这副牌，就能将其完全分类到13个“桶”中，每个桶对应一个值
// 然后遍历各个存储桶并执行另一个较小的排序，然后再将整个甲板连接在一起
public class BucketSortQuestion {

    // Maximum Gap
    // Given an integer array nums, return the maximum difference
    // between two successive elements in its sorted form.
    // If the array contains less than two elements, return 0.
    // You must write an algorithm that runs in linear time and uses linear extra space.
    //
    // nums = [3,6,9,1] -> [1,3,6,9] -> 3 必须是两个相邻元素之间的差
    // nums = [2,5,3,8,4,2] -> [2,2,3,4,5,8] -> 3
    //
    // O(n) O(n) 线性的时间复杂度和空间复杂度(允许开辟空间)
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int maxValue = 0;
        int minValue = Integer.MAX_VALUE;
        int maxGap = 0;
        for (int n : nums) {
            maxValue = Math.max(maxValue, n);
            minValue = Math.min(minValue, n);
        }

        // TODO. Bucket Sort 桶排序的核心：确定桶的数量
        int bSize = Math.max((maxValue - minValue) / (nums.length - 1), 1);
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = (maxValue - minValue) / bSize; i >= 0; i--) {
            buckets.add(new ArrayList<>());
        }

        // 读取数据插入到对应的bucket中
        for (int n : nums) {
            buckets.get((n - minValue) / bSize).add(n);
        }

        // 依次从桶中取数并判断收尾的差距
        int currentHigh = 0;
        for (List<Integer> b : buckets) {
            if (b.isEmpty()) {
                continue;
            }
            int previousHigh = currentHigh > 0 ? currentHigh
                    : b.get(0), currentLow = b.get(0);
            for (int n : b) {
                currentHigh = Math.max(currentHigh, n);
                currentLow = Math.min(currentLow, n);
            }
            maxGap = Math.max(maxGap, currentLow - previousHigh);
        }
        return maxGap;
    }
}
