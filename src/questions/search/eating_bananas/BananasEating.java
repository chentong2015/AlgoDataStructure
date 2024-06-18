package questions.search.eating_bananas;

// https://leetcode.com/problems/koko-eating-bananas/description/
public class BananasEating {

    // TODO. 在结果的范围区间中二分查找，在每一步判断是否是结果
    //  可能没有答案或者最后选择的答案并不不具有唯一性 ？！
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
    //          4 > 11= 1 + 1 + 1 + 1
    //          5 > 7 = 1 + 1 + 1 + 2
    //          6 > 6 = 1 + 1 + 2 + 2
    //          7 > 5 = 1 + 2 + 2 + 2
    //          8 > 4 = 1 + 2 + 2 + 3 = 小时数的推理没有规律，无法直接获得判断条件
    //          9 > 3 = 1 + 2 + 3 + 4 = 10 指定的9小时无解
    //
    // piles.length <= h <= 10^9 小时数必须大于等于pile香蕉堆数目，否则不能吃完
    // minHour 取最大框中的数量则速度最快
    // maxHour 取一个则速度最慢，等于香蕉的总和
    //
    // O(n*logM) O(1) 对数据中的最大值计算log
    public static int minEatingSpeed(int[] piles, int h) {
        int maxNum = piles[0];
        for (int pile: piles) {
            if (pile > maxNum) {
                maxNum = pile;
            }
        }
        int low = 0; // min num take for one time
        int high = maxNum; // max num take for one time
        while (low <= high) { // the result is between min and max 这里可以取证
            int mid = low + (high-low)/2;
            int countHour = getEatingHours(piles, mid);
            if (countHour == h) {
                return mid;
            } else if (countHour > h) {
                low = mid + 1; // 如果时间花多了，则必须提交每小时的数量
            } else {
                high = mid - 1;
            }
        }
        return 0;
    }

    private static int getEatingHours(int[] piles, int amount) {
        int sumHours = 0;
        for (int pile: piles) {
            while (pile / amount != 0) {
                sumHours++;
                pile -= amount; // 每次拿掉指定的数量
            }
            if (pile != 0) { // 如果是刚好拿完，则无需再统计
                sumHours++;
            }
        }
        return sumHours;
    }

    // 首先测试极限情况
    public static void main(String[] args) {
        int[] piles = {312884470};
        System.out.println(minEatingSpeed(piles, 312884469));

        int[] piles1 = {30,11,23,4,20};
        System.out.println(minEatingSpeed(piles1, 5));
        System.out.println(minEatingSpeed(piles1, 6));

        int[] piles2 = {3,6,7,11};
        System.out.println(minEatingSpeed(piles2, 8));
        System.out.println(minEatingSpeed(piles2, 5));
    }
}
