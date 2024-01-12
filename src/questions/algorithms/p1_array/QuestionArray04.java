package questions.algorithms.p1_array;

public class QuestionArray04 {

    // TODO: 经典降维问题02: 注意问题的核心和要返回的数据，去掉无关的循环
    // Count Number of Teams
    // There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
    // 1. Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
    // 2. A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k])
    //    where (0 <= i < j < k < n) 满足连续递增或者是连续递减的情况
    // Return the number of teams you can form given the conditions
    // Soldiers can be part of multiple teams
    // rating = [2,5,3,4,1] -> (2,3,4), (5,4,1), (5,3,1) -> 3
    // rating = [1,2,3,4]   -> 123, 124, 134, 234,       -> 4
    // 注意以下的约束: 
    // n == rating.length
    // 3 <= n <= 1000
    // 1 <= rating[i] <= 10^5
    // All the integers in rating are unique
    public int numTeams(int[] arr) {
        // 测试理解：定位中间值的位置index，统计左右两侧比它大和比它小的数目
        //         计算出一个位置对应出多少种可能的选择
        // O(n^2) O(1) 每一次循环，内层的复杂度为o(n)
        int count = 0;
        int len = arr.length;
        for (int j = 0; j < len; j++) {
            int leftSmaller = 0;
            int leftLarger = 0;
            int rightSmaller = 0;
            int rightLarger = 0;
            for (int i = 0; i < j; i++) {
                if (arr[i] < arr[j])
                    leftSmaller++;
                else if (arr[i] > arr[j])
                    leftLarger++;
            }
            for (int k = j + 1; k < len; k++) {
                if (arr[j] < arr[k])
                    rightLarger++;
                else if (arr[j] > arr[k])
                    rightSmaller++;
            }
            count += leftSmaller * rightLarger + leftLarger * rightSmaller;
        }
        return count;
    }
}
