package questions.dfs_bfs_traverse.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LearnBacktracking1 {

    // Shopping Offers
    // Return the lowest price you have to pay for exactly certain items as given
    // You could use any of the special offers as many times as you want.
    //
    // price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2] -> Output: 14=3+5*2
    // price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1] -> Output: 11 = 4+3+4
    // 1. 用最少的钱买够需求，不能超过需求量
    // 2. 可以无限次的使用special特价(比单独买便宜)，直到不能使用特价为止
    // 3. 是否有special特价使用的先后问题 ？如何直到从那个特价开始买 ？
    // TODO. Backtracking需要通过回溯算法来比较之前历史数据
    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int minPrice = 0;
        int length = needs.size();
        for (List<Integer> rowSpecial: special) {
            boolean canUseSpecial = true;
            while (canUseSpecial) {
                for (int index=0; index<length; index++) {
                    if (rowSpecial.get(index) > needs.get(index)) {
                        canUseSpecial = false;
                        break;
                    }
                }
                if (canUseSpecial) {
                    for (int index=0; index<length; index++) {
                        needs.set(index, needs.get(index) - rowSpecial.get(index));
                    }
                    // 注意这里添加的是special最后的价格数据
                    minPrice += rowSpecial.get(length);
                }
            }
        }
        for (int index=0; index<length; index++) {
            if (needs.get(index) != 0) {
                minPrice = minPrice + needs.get(index) * price.get(index);
            }
        }
        return minPrice;
    }

    public static void main(String[] args) {
        List<Integer> price = List.of(2, 5);
        List<List<Integer>> special = List.of(List.of(3, 0, 5), List.of(1, 2, 10));
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        System.out.println(shoppingOffers(price, special, needs));
    }
}
