package questions.dp_algo.dp_array;

public class DynamicProgrammingArray3 {

    // TODO. DP Programming 在遍历数组的每一步，都需要附加计算当前结果(且该结果依赖于前一步保存的计算结果)
    // 1. 数组中的最大值并不能决定划分的选择，以及值的取舍
    // 2. 由于划分的子数组的长度不确定，因此无法适用于Sliding Window算法
    // 3. DP动态编程算法
    //    For j = 1 .. k that keeps everything in bounds
    //    dp[i] is the maximum of dp[i-j] + max(A[i-1], ..., A[i-j]) * j
    //
    // Partition Array for Maximum Sum
    // Given an integer array arr, partition the array into (contiguous) subArrays of length at most k.
    // After partitioning, each subArray has their values become the maximum value of that subArray.
    //
    // Input: arr = [1,15,7,9,2,5,10], k = 3  => Output: 84
    // Explanation: [15,15,15,9,10,10,10]
    // Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4  => Output: 83
    // Explanation: [4,4,7,7,7,9,9,9,9,9,9] = 29 + 54 = 83
    //
    // 1 5 2 , k=2
    // 1 0 0
    // 1 6 0    -> 1 5 2  当5位置只往前不往前划分时的结果
    // 1 10 0   -> 5 5 2  当5往前划分一个位置的结果，比不划分的sum更大(10 > 6)
    // 1 10 12  -> 5 5 2
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length; // Length of the input array
        int[] dp = new int[n]; // Initialize an array to store maximum sums
        for (int i = 0; i < n; i++) {
            int maxPartitionSum = Integer.MIN_VALUE; // Initialize the maximum sum for the current element
            int maxElement = Integer.MIN_VALUE;      // Initialize the maximum element within the current group

            // 从index位置往后划分(最大k个长度)，计算在这个过程中能够得出的最大值
            // ==在任意位置往后移动k个位置，与在任意位置往前移动k个位置的效果是一致的==
            // 如果往后划分的区间中有更大的值，则按照“更大的值*Length长度” + “之前记录的最大值”=得出index位置可以划分的最大值
            for (int j = i; j >= (i - k + 1) && j >= 0; j--) {
                int partitionLength = i - j + 1;           // Length of the current partition
                maxElement = Math.max(maxElement, arr[j]); // Find the maximum element in the partition
                int currentPartitionSum = maxElement * partitionLength; // Calculate the sum of the current group
                if (j > 0) {
                    int previousMaxSum = dp[j - 1];        // Maximum sum before the current partition
                    maxPartitionSum = Math.max(maxPartitionSum, previousMaxSum + currentPartitionSum);
                } else {
                    maxPartitionSum = Math.max(maxPartitionSum, currentPartitionSum);
                }
            }

            // 存储当前位置在任意长度划分之后，能够得出的最大值
            // Store the maximum sum up to the current element
            dp[i] = maxPartitionSum;
        }
        return dp[n - 1]; // Return the maximum sum after partitioning
    }

    public static void main(String[] args) {
        int[] arr = {1,5,2};
        System.out.println(maxSumAfterPartitioning(arr, 2));
    }
}
