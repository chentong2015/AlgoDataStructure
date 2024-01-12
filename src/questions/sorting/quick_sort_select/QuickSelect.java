package questions.sorting.quick_sort_select;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Quick Select: 查找数据中的前K个什么特征的数据
// 1. 统计集合中每个元素出现的频率(hash table)
// 2. 根据每个元素的频率，进行Quick Sort快速排序，从低频到高频
// 3. 直到要取的前k的频率和pivot分区位置相等，则它右边的就是前k个最大频率
// O(n) O(n)

// TODO. 返回的是出现频率大于K的数据
//  一表存储非重复的离散数据(用于交换位置)
//  一表存储每个离散数据的重复次数
// 1 4 5 2 7 4 1 1 2
//
// 1 -> 3
// 4 -> 2
// 5 -> 1
// 2 -> 2
// 7 -> 1
public class QuickSelect {

    private int countElements;
    private int[] uniqueElements;
    private Map<Integer, Integer> mapCounts;

    public int[] topKFrequent(int[] nums, int k) {
        countNumFrequencies(nums);
        addAllUniqueElements();

        quickSelect(0, countElements - 1, countElements - k);
        // 只取右侧位置前k个元素，已经分区过的元素
        return Arrays.copyOfRange(uniqueElements, countElements - k, countElements);
    }

    private void countNumFrequencies(int[] nums) {
        mapCounts = new HashMap();
        for (int num : nums) {
            int baseCount = mapCounts.getOrDefault(num, 0);
            mapCounts.put(num, baseCount + 1);
        }
        countElements = mapCounts.size();
    }

    private void addAllUniqueElements() {
        int index = 0;
        uniqueElements = new int[mapCounts.size()];
        for (int num : mapCounts.keySet()) {
            uniqueElements[index] = num;
            index++;
        }
    }

    // 从左到右，从低到高，第Kth个最大频率的元素正好位于第(n-k)个位置上
    // 其余更左边的没有分区的，可以不用考虑，因为都是更小的值
    public void quickSelect(int left, int right, int kSmallest) {
        if (left == right) {
            return;
        }
        int pivotIndex = getRandomPivot(left, right);
        pivotIndex = partition(left, right, pivotIndex);

        if (kSmallest == pivotIndex) return;

        if (kSmallest < pivotIndex) {
            quickSelect(left, pivotIndex - 1, kSmallest);
        } else {
            quickSelect(pivotIndex + 1, right, kSmallest);
        }
    }

    // TODO: 分区的随机算法影响算法的时间复杂度
    private int getRandomPivot(int left, int right) {
        Random randomNum = new SecureRandom();
        return left + randomNum.nextInt(right - left);
    }

    // 最后将最右边存储的pivot移动到index，作为分区的中间位置 !!
    public int partition(int left, int right, int pivot) {
        swap(pivot, right);
        int index = left;
        for (int i = left; i <= right; i++) {
            // 判断的是元素的统计量来进行移动Element
            // 最后返回的是低于自定统计量以下的数据
            if (mapCounts.get(uniqueElements[i]) < mapCounts.get(uniqueElements[pivot])) {
                swap(index, i);
                index++;
            }
        }
        swap(index, right);
        return index;
    }

    public void swap(int index1, int index2) {
        int temp = uniqueElements[index1];
        uniqueElements[index1] = uniqueElements[index2];
        uniqueElements[index2] = temp;
    }
}
