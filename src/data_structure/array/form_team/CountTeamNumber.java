package data_structure.array.form_team;

public class CountTeamNumber {

    // TODO. 必须要遍历到所有可能的情况, 常规的暴力破解不可行
    // Count Number of Teams
    // You have to form a team of 3 soldiers amongst them under the following rules:
    // - Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
    // - (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k])
    //
    // Return the number of teams you can form given the conditions.
    // (soldiers can be part of multiple teams).
    // rating = [2,1,3] -> 0
    // rating = [2,5,3,4,1] -> (2,3,4), (5,4,1), (5,3,1) -> 3
    // rating = [1,2,3,4] -> 123, 234, 124, 134 -> 4
    //
    // Constraints
    // 3 <= n <= 1000
    // 1 <= rating[i] <= 105
    // All the integers in rating are unique.
    public int numTeams(int[] rating) {
        int countNum = 0;
        for (int i=0; i < rating.length; i++) {
            for (int j=i+1; j<rating.length; j++) {
                if (rating[j] > rating[i]) {
                    for (int k=j+1; k<rating.length; k++) {
                        if (rating[k] > rating[j]) {
                            countNum++;
                        }
                    }
                }
            }
        }
        return countNum;
    }

    // TODO. 经典降维思想，将三层复杂度降低维二层(使用额外数据结构+从中间层开始挖掘)
    // 定位中间位置index往两边扩展，避免从一个方向寻找造成O(n*n)的复杂度
    // O(n^2) 每一次循环，内层的循环总次数为o(n)
    // O(1)
    public int numTeams2(int[] arr) {
        int count = 0;
        for (int index = 0; index < arr.length; index++) {
            int leftSmaller = 0;
            int leftLarger = 0;
            int rightSmaller = 0;
            int rightLarger = 0;
            for (int left = 0; left < index; left++) {
                if (arr[left] < arr[index]) {
                    leftSmaller++;
                } else {
                    leftLarger++;
                }
            }
            for (int right = index + 1; right < arr.length; right++) {
                if (arr[index] < arr[right]) {
                    rightLarger++;
                } else {
                    rightSmaller++;
                }
            }
            // TODO. 排列组合的配比，和具体的位置无关
            count += leftSmaller * rightLarger + leftLarger * rightSmaller;
        }
        return count;
    }
}
