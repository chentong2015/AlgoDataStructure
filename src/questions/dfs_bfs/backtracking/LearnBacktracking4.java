package questions.dfs_bfs.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LearnBacktracking4 {

    // TODO. Backtracking本质上上回溯问题，需要选择一个special进行Tree树状递归
    // Shopping Offers
    // Return the lowest price you have to pay for exactly certain items as given
    // You could use any of the special offers as many times as you want.
    //
    // price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2] -> Output: 14=3+5*2
    // price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1] -> Output: 11 = 4+3+4
    // price = [1,1,1],  special = [[1,1,0,0],[2,2,1,0]], needs = [1,1,1] -> Output: 1 = 0+1
    // 1. 用最少的钱买够需求，不能超过需求量
    // 2. 可以无限次的使用special特价(比单独买便宜)，直到不能使用特价为止

    private static int minPrice = Integer.MAX_VALUE;

    public static void main(String[] args) {
        List<Integer> price = List.of(1, 1, 1);
        List<List<Integer>> special = List.of(List.of(1, 1, 0, 0), List.of(2, 2, 1, 0));
        List<Integer> needs = new ArrayList<>();
        needs.add(1);
        needs.add(1);
        needs.add(1);
        System.out.println(shoppingOffers(price, special, needs));
    }

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        shoppingOffersBacktracking(price, special, needs, 0);
        return minPrice;
    }

    private static void shoppingOffersBacktracking(List<Integer> price, List<List<Integer>> special,
                                                   List<Integer> remainingNeeds, int totalPrice) {
        for (List<Integer> rowSpecial : special) {
            if (canUseTheSpecial(rowSpecial, remainingNeeds)) {
                useTheSpecial(rowSpecial, remainingNeeds);

                int newTotalPrice = totalPrice + rowSpecial.get(remainingNeeds.size());
                shoppingOffersBacktracking(price, special, remainingNeeds, newTotalPrice);

                // 递归结束之后需要回溯，将数据恢复
                restoreTheSpecial(rowSpecial, remainingNeeds);
            }
        }

        // TODO. 所有递归最深处的节点位置都会进入这个条件的判断
        // 最后截止条件: for所有special中没有找到能使用的rowSpecial
        for (int index = 0; index < remainingNeeds.size(); index++) {
            if (remainingNeeds.get(index) != 0) {
                totalPrice = totalPrice + price.get(index) * remainingNeeds.get(index);
            }
        }
        minPrice = Math.min(minPrice, totalPrice);
    }

    private static boolean canUseTheSpecial(List<Integer> rowSpecial, List<Integer> remainingNeeds) {
        for (int index = 0; index < remainingNeeds.size(); index++) {
            if (rowSpecial.get(index) > remainingNeeds.get(index)) {
                return false;
            }
        }
        return true;
    }

    private static void useTheSpecial(List<Integer> rowSpecial, List<Integer> remainingNeeds) {
        for (int index = 0; index < remainingNeeds.size(); index++) {
            remainingNeeds.set(index, remainingNeeds.get(index) - rowSpecial.get(index));
        }
    }

    private static void restoreTheSpecial(List<Integer> rowSpecial, List<Integer> remainingNeeds) {
        for (int index = 0; index < remainingNeeds.size(); index++) {
            remainingNeeds.set(index, remainingNeeds.get(index) + rowSpecial.get(index));
        }
    }
}
