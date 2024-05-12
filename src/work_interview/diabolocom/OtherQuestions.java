package work_interview.diabolocom;

import java.util.*;

public class OtherQuestions {

    // TODO. 最后的结果=累计所有list2的值 + 两个列表差值最大的前k个数
    //  不需要将简单算法的问题复杂化，考虑“从列表中取k个元素”会增加题目难度
    // Task Completion
    // 如何从两个序列中取出指定数量的值，使得最后的和最大
    // 不能排序否则index会打乱，不能做index映射因为value可能重复
    // k = 3 -> 15 + 4 + 4 = 23
    // 1 3 6 5 2
    // 5 3 2 1 4 = 15
    //
    // k = 3 -> 15 + 4 + 2 = 21
    // 5 4 3 2 1
    // 1 2 3 4 5 = 15
    //
    // O(n*logn) 排序算法便是最终的复杂度
    public static int getMaximumRewardPoints(int k, List<Integer> reward_1, List<Integer> reward_2) {
        int maxResult = 0;
        int length = reward_1.size();
        int[] gaps = new int[length];
        for(int index=0; index < length; index++) {
            gaps[index] = reward_1.get(index) - reward_2.get(index);
            maxResult += reward_2.get(index);
        }

        Arrays.sort(gaps);
        for (int index=0; index < k; index++) {
            maxResult += gaps[length-1-index];
        }
        return maxResult;
    }

    // TODO. 每次折扣最大价值的商品，最终得到的总价是最小值 ？
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

    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>();
        prices.add(2);
        prices.add(5);
        prices.add(6);
        System.out.println(findMinimumPrice(prices, 2));
    }
}
