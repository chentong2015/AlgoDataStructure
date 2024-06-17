package questions.search.k_sum;

public class KSumProblem {

    // TODO. 二分位两侧可以直接使用公式求和，而无需一个值一个值的累加
    //  等差数列请和公式 N*(N+1)/2
    // Given an interger N
    // return the integer number K in a way that
    // the sum that goes from 1 to K-1 is equals to the sum that goes form k+1 to N (all inclusive),
    // if there is no number that met the condition return -1.
    //
    // N = 8 -> k = 6
    // Explanation: [1,2,3,4,5,6,7,8]
    // LEFT = SUM(1,K-1) = 1 + 2 + 3 + 4 + 5 = 15
    // RIGHT = SUM(K+1,N) = 7 + 8 = 15
    //
    // O(logn) O(1)
    public static int findKValue(int n) {
        int left = 1;
        int right = n;
        int total = (n * (n + 1)) / 2;
        while (left < right) {
            int mid = left + (right - left)/2;
            int sumLeft = (mid * (mid - 1))/2;    // mid-1 刚好是左边的数目
            int sumRight = total - mid - sumLeft; // 右侧结果直接通过算数得出
            if (sumLeft == sumRight) {
                return mid;
            } else if (sumLeft < sumRight) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }

    // O(n) O(1) 从两端往中间遍历，只需要一次遍历时间
    public static int findKValue2(int n) {
        int leftIndex = 1;
        int rightindex = n;
        int leftSum = leftIndex;
        int rightSum = rightindex;

        while (leftIndex + 1 < rightindex) {
            if (leftSum < rightSum) {
                leftIndex++;
                leftSum+=leftIndex;
            } else if (leftSum > rightSum) {
                rightindex--;
                rightSum+=rightindex;
            } else {
                // 如果左右两边相等的时候，恰好间隔了一个值，则中间值为正确答案
                if (leftIndex + 2 == rightindex) {
                    return leftIndex + 1;
                }
            }
        }
        return -1;
    }
}
