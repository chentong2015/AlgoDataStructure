package work_interview.others;

import java.util.*;

public class OtherQuestions {

    // TODO. 核心问题是当满足什么条件时，从list1中取数据 ？
    // Task Completion
    // 如何从两个序列中取出指定数量的值，使得最后的和最大
    // 不能排序否则index会打乱，不能映射index因为value可能重复
    // k = 3 -> 23
    // 1 3 6 5 2 = 14
    // 5 3 2 1 4 = 9
    //
    // k = 3 -> 21
    // 5 4 3 2 1 = 12
    // 1 2 3 4 5 = 9
    //
    // 1 2 3 2 由于提供的数据相同，因此无论怎么取都是一样的结果
    // 1 2 3 2
    public static int getMaximumRewardPoints(int k, List<Integer> reward_1, List<Integer> reward_2) {
        int[] gapRewards = new int[reward_1.size()];
        for(int index=0; index < reward_1.size(); index++) {
            gapRewards[index] = reward_1.get(index) * 2 - reward_2.get(index);
        }
        Arrays.sort(gapRewards);

        int sum = 0;
        int index = 0;
        while (index < k) {
            int compareValue = gapRewards[gapRewards.length - 1 - index];
            for (int i = 0; i < reward_1.size(); i++) {
                int value = reward_1.get(i) * 2 - reward_2.get(i);
                if (reward_1.get(i) != 0 && value == compareValue) {
                    sum += reward_1.get(i);
                    reward_1.set(i, 0);
                    reward_2.set(i, 0);
                    break;
                }
            }
            index++;
        }
        for (Integer item : reward_2) {
            if (item != 0) {
                sum += item;
            }
        }
        return sum;
    }

    // TODO. 通常的算法没有办法满足"特殊场景"，需要找到通用算法 ?
    // Item Purchase
    // 一共有m个折扣，可以使用x个在某个商品上，商品一旦被使用折扣，便会除以2
    // m = 2
    // 2 4 5
    // > 2 + 2 + 2 = 6 需要将折扣分散处理，每次在列表中找最大的值进行折扣
    // > 2 + 4 + 1 = 7
    public static long findMinimumPrice(List<Integer> price, int m) {
        while (m > 0) {
            int maxValue = price.get(0);
            int maxIndex = 0;
            for (int index = 1; index < price.size(); index++) {
                if (maxValue < price.get(index)) {
                    maxValue = price.get(index);
                    maxIndex = index;
                }
            }
            int discountValue = Math.floorDiv(price.get(maxIndex), 2);
            price.set(maxIndex, discountValue);
            m--;
        }
        int sum = 0;
        for (Integer item: price) {
            sum += item;
        }
        return sum;
    }
}
