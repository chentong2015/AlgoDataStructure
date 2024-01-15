package data_structure.array_string.windows;

public class ArrayWindowSubString {

    // TODO. 尝试从计算的结果中找到普遍规律，以辅助最后的计算
    // Sum of All Odd Length SubArrays
    // Given an array of positive integers arr, return the sum of all possible odd-length subArrays of arr.
    // A subArray is a contiguous subsequence of the array.
    //
    // 1 2 = 1 + 2 = 1*1 + 2*1 = 3  <count=1,2>
    // 1 2 3 = 1 + 2 + 3 + (1+2+3) = 1*2 + 2*2 + 3*2 = 12 <count=3>
    // 1 2 3 4 = 1*2 + 2*3 + 3*3 + 4*2 = 25 <count=4>
    // 1,4,2,5,3 = 1*3 + 4*4 + 2*4 + 5*4 + 3*3 = 58 <count=5>
    // Explanation:
    // [1] = 1
    // [4] = 4
    // [2] = 2
    // [5] = 5
    // [3] = 3
    // [1,4,2] = 7
    // [4,2,5] = 11
    // [2,5,3] = 10
    // [1,4,2,5,3] = 15
    // => 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
    public static int sumOddLengthSubArrays(int[] arr) {
        int sum = 0;
        int length = 1;
        while (length <= arr.length) {
            int left = 0;
            int right = left + length - 1;

            // 初始化计算一轮指定区间长度的sub sum
            int subSum = 0;
            for (int index = left; index <= right; index++) {
                subSum += arr[index];
            }
            sum += subSum;

            // TODO. 典型的滑动窗口算法
            // 在滑动窗口时，将每一个数据累加到最后的结果sum中
            // 每次移动时，都需要更新subSum的值用于下一次的计算
            right++;
            while (right < arr.length) {
                subSum = subSum + arr[right] - arr[left];
                sum += subSum;
                left++;
                right++;
            }

            // 循环完一种长度之后，循环下一个奇数的长度
            length += 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,2,5,3};
        System.out.println(sumOddLengthSubArrays(arr));
    }
}
