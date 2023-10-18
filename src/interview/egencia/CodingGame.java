package interview.egencia;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CodingGame {

    // TODO. 如果不使用HashMap<>存储结构，可以直接使用数组+Index来统计统计最后的结果
    // 根据item统计的数据排序; 根据count排序
    // the dog got the bonne
    // the the dog dog home ... 排序之后的特征出来了，只需要统计相邻的数据
    // 1
    // 2
    // 2   2  1
    //
    // 使用额外的存储空间来排序 LinkedHashMap => 一定要保证排序之后的结果是有序的
    // O(n*log(n)) O(n)
    public static int[] count(String[] words) {
        if (words == null || words.length == 0) {
            return new int[0];
        }
        Arrays.sort(words);
        HashMap<String, Integer> countsMap = new LinkedHashMap<>();
        for (String word : words) {
            if (countsMap.containsKey(word)) {
                countsMap.put(word, countsMap.get(word) + 1);
            } else {
                countsMap.put(word, 1);
            }
        }

        int index = 0;
        int[] counts = new int[countsMap.size()];
        for (int value : countsMap.values()) {
            counts[index++] = value;
        }
        return counts;
    }

    // 所有的商品中，只有最贵的价格可以享受折扣discount
    // 当有多个相同的最贵价格的商品时，只能计算折扣一次
    public static int total2(int[] prices, int discount) {
        if (prices.length == 1) {
            return calculate(prices[0], discount);
        }

        int maxPriceItem = prices[0];
        for (int index = 1; index < prices.length; index++) {
            if (prices[index] > maxPriceItem) {
                maxPriceItem = prices[index];
            }
        }

        int totalPrice = 0;
        boolean hasCalculatedDiscount = false;
        for (int index = 1; index < prices.length; index++) {
            if (!hasCalculatedDiscount && prices[index] == maxPriceItem) {
                totalPrice += calculate(prices[index], discount);
                hasCalculatedDiscount = true;
            } else {
                totalPrice += prices[index];
            }
        }
        return totalPrice;
    }

    // 折扣的约束: 0 <= discount <= 100
    // 当计算中出现浮点小数时，需要向下取整数
    private static int calculate(int price, int discount) {
        if (discount == 0) {
            return price;
        } else if (discount == 100) {
            return 0;
        } else {
            return (int) Math.floor(price * (100 - discount) / 100);
        }
    }
}
