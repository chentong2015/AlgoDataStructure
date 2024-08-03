package work_algorithms.search;

// TODO. 二分法的衍生版本: 找到的median并不是最后结果，而是在二分过程中找边界值
public class LearnBinarySearch3 {

    // 在指定的时间间隔中，需要吃多少才能保证全部吃完
    // Koko Eating Bananas
    // Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
    // The guards have gone and will come back in h hours.
    // Koko can decide her bananas-per-hour eating speed of k.
    // Each hour, she chooses some pile of bananas and eats k bananas from that pile.
    // If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
    //
    // Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
    // Return the minimum integer k such that she can eat all the bananas within h hours.
    //
    // piles = [30,11,23,4,20], h = 5 Output: 30 (1~30) 可选择值的范围
    // piles = [30,11,23,4,20], h = 6 Output: 23 (1~30)
    // piles = [3,6,7,11],      h = 8 Output: 4 (1~4)
    //         3 = 1 + 2 + 3 + 4 吃3根香蕉将耗费10小时
    //         4 = 1 + 2 + 2 + 3 > 8 相同时间数，选择每小时更小的数量
    //         5 = 1 + 2 + 2 + 3 > 8 相同时间数，可选择的数量可能不唯一
    //         6 = 1 + 1 + 2 + 2 > 6
    //         7 = 1 + 1 + 1 + 2 > 5
    //         11= 1 + 1 + 1 + 1 > 4
    // 每堆至少吃一次，因此小时数必须大于等于pile香蕉堆数目
    // 每次至少吃一根，因此小时数最大不超过香蕉的总数目
    //
    // O(n*logM) O(1) 对数据中的最大值计算log
    public static int minEatingSpeed(int[] piles, int h) {
        int maxNum = piles[0];
        for (int pile: piles) {
            if (pile > maxNum) {
                maxNum = pile;
            }
        }

        int minEatingValue = 0;
        int low = 1;
        int high = maxNum;
        while (low <= high) {
            int mid = low + (high-low)/2;
            if(isEatingMoreTime(mid, piles, h)) {
                // 如果吃了更多的时间，则提高单位时间的数量up
                low = mid + 1;
            } else {
                // 如果吃了更少或者恰好的时间，则减低单位时间的数量down
                high = mid - 1;

                // TODO. 此时的mid是一个极限边界值
                //  当使用mid数量去吃的时候，一定能够在h约束时间内解决
                minEatingValue = mid;
            }
        }
        // 推出循环的条件为low > high, 返回临时存储的最低要求的值
        return minEatingValue;
    }

    public static boolean isEatingMoreTime(int speed, int[]piles, int h) {
        int countHours = 0;
        for (int pile : piles) {
            if (pile > speed) {
                if (pile % speed != 0) {
                    countHours += (pile / speed) + 1;
                } else {
                    countHours += (pile / speed);
                }
            } else {
                countHours += 1;
            }
            if (countHours > h) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] piles = {312884470};
        System.out.println(minEatingSpeed(piles, 312884469));

        int[] piles1 = {30,11,23,4,20};
        System.out.println(minEatingSpeed(piles1, 5));
        System.out.println(minEatingSpeed(piles1, 6));

        int[] piles2 = {3,6,7,11}; // 1 + 2 + 2 + 3
        System.out.println(minEatingSpeed(piles2, 8));
        System.out.println(minEatingSpeed(piles2, 5));
    }
}
