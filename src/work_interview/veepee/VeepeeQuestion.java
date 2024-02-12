package work_interview.veepee;

import java.util.*;

// Veepee面试经验
// 1. 分析题目的SCENARIO, 考虑异常的特殊场景
// 2. 先写测试代码, 从单元测试驱动算法的开发
// 3. 考虑使用Stream来优化代码
public class VeepeeQuestion {

    // Problem
    // Let's consider a second-price, sealed-bid auction:
    // - An object is for sale with a reserve price.
    // - We have several potential buyers, each one being able to place one or more bids.
    // - The buyer winning the auction is the one with the highest bid above or equal to the reserve price.
    // - The winning price is the highest bid price from a non-winning buyer above the reserve price
    //   (or the reserve price if none applies)
    //
    // Example
    // Consider 5 potential buyers (A, B, C, D, E) who compete to acquire an object
    // with a reserve price set at 100 euros, bidding as follows:
    // this
    // A: 2 bids of 110 and 130 euros
    // B: 0 bid
    // C: 1 bid of 125 euros
    // D: 3 bids of 105, 115 and 90 euros
    // E: 3 bids of 132, 135 and 140 euros
    //
    // The buyer E wins the auction at the price of 130 euros.
    // The goal is to implement an algorithm for finding the winner and the winning price.

    // TODO. 使用Stream来操作hashmap的排序，简化for循环
    public Result findWinnerAndWinningPrice(int reservedPrice, Map<String, List<Integer>> playersMap) {
        // throw new IllegalArgumentException for special cases
        if (reservedPrice <= 0 || playersMap.keySet().isEmpty()) {
            return new Result();
        }
        // anyMatch()匹配hashmap values的任何条件, 替代循环逻辑
        boolean hasBids = playersMap.values().stream().anyMatch(list -> !list.isEmpty());
        if(!hasBids) {
            return new Result();
        }
        boolean hasBidValid = playersMap.values().stream()
                .anyMatch(list -> list.stream().anyMatch(value -> value >= reservedPrice));
        if (!hasBidValid) {
            return new Result();
        }

        // 确定后面的计算有值，通过两次循环分别找到两个结果
        // 使用排序会增加时间复杂度，O(n*logn)
        String winner = null;
        int maxBid = reservedPrice;
        for (Map.Entry<String, List<Integer>> entry: playersMap.entrySet()) {
            Optional<Integer> max = entry.getValue().stream().max(Integer::compare);
            if (max.isPresent() && max.get() > maxBid) {
                maxBid = max.get();
                winner = entry.getKey();
            }
        }
        int winningPrice = reservedPrice; // 设置默认返回的值
        for (Map.Entry<String, List<Integer>> entry: playersMap.entrySet()) {
            if (!entry.getKey().equals(winner)) {
               Optional<Integer> max = entry.getValue().stream().max(Integer::compare);
               if (max.isPresent() && max.get() > winningPrice) {
                   winningPrice = max.get();
               }
            }
        }
        return new Result(winner, winningPrice);
    }

    // @Test
    public void testReservedPriceLessOrEqualThanZero() {
        VeepeeQuestion playerClass = new VeepeeQuestion();
        Map<String, List<Integer>> playersMap = new HashMap<>();
        playersMap.put("A", Collections.emptyList());
        playersMap.put("B", Collections.emptyList());

        Result result = playerClass.findWinnerAndWinningPrice(0, playersMap);
        // Assertions.assertEquals(result, new Result());
    }
}
