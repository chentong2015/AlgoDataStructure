package work_interview.others;

import java.util.*;

public class OtherQuestions {

    // TODO. 核心问题根本就不在于如何从list1中取数，而在于如何利用list1中的数？
    //  最后的结果=累计所有list2的值+两个列表差值最大的前k个数
    // Task Completion
    // 如何从两个序列中取出指定数量的值，使得最后的和最大
    // 不能排序否则index会打乱，不能映射index因为value可能重复
    // k = 3 -> 23 = 15 + 4 + 4
    // 1 3 6 5 2 = 14
    // 5 3 2 1 4 = 9
    //
    // k = 3 -> 21 = 15 + 4 + 2
    // 5 4 3 2 1 = 12
    // 1 2 3 4 5 = 9
    public static int getMaximumRewardPoints(int k, List<Integer> reward_1, List<Integer> reward_2) {
        int maxResult = 0;
        int length = reward_1.size();
        int[] gaps = new int[length];
        for(int index=0; index < length; index++) {
            gaps[index] = reward_1.get(index) - reward_2.get(index);
            maxResult += reward_2.get(index);
        }
        // O(n*logn) 排序算法便是最终的复杂度
        Arrays.sort(gaps);
        for (int index=0; index < k; index++) {
            maxResult += gaps[length-1-index];
        }
        return maxResult;
    }

    // TODO. 是否存在"特殊场景"，时间复杂度如何优化 ?
    // Item Purchase
    // 一共有m个折扣，可以使用其中x个在某个商品上，商品一旦被使用折扣，便会除以2
    // 2 4 5, m = 2
    // > 2 + 2 + 2 = 6
    // > 2 + 4 + 1 = 7
    public static long findMinimumPrice(List<Integer> price, int m) {
        while (m > 0) {
            int maxIndex = 0;
            for (int index = 1; index < price.size(); index++) {
                if (price.get(maxIndex) < price.get(index)) {
                    maxIndex = index;
                }
            }
            int discountPrice = Math.floorDiv(price.get(maxIndex), 2);
            price.set(maxIndex, discountPrice);
            m--;
        }
        int sum = 0;
        for (Integer item: price) {
            sum += item;
        }
        return sum;
    }
}
