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
    // O(n*logn) 排序算法便是最终的复杂度  O(n)消耗空间复杂度
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

    // TODO. 本质上是求折扣后价的总和 != 所有的总价放在一起折扣
    // Find Minimum Price
    // 2 4, m = 2   -> 2 + 1 = 3     6/2
    // 2 4 5, m = 2 -> 2 + 2 + 2 = 6   11/2
    // 1 3 5 7, m = 2 -> 1 + 3 + 2 + 3 = 8  16/2
    // 2 4 5 6, m = 3 -> 2 + 2 + 2 + 3 = 9  17/3
    //
    // 始终选择最大的价格进行折扣，最多折扣m次
    // O(n*m) O(1) 约束约束m值较大，对时间复杂度(循环的限制)要求较高
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
