package work_interview.adobe;

import java.util.HashSet;
import java.util.Set;

public class FibonacciQuestion {

    // Length of Longest Fibonacci Subsequence
    // A sequence x1, x2, ..., xn is Fibonacci-like if:
    // n >= 3
    // xi + xi+1 == xi+2 for all i + 2 <= n
    //
    // Given a strictly increasing array arr of positive integers forming a sequence,
    // return the length of the longest Fibonacci-like subsequence of arr.
    // If one does not exist, return 0.
    //
    // arr = [1,2,3,4,5,6,7,8]   ->  5 [1,2,3,5,8]
    // arr = [1,3,7,11,12,14,18] ->  3 [3,11,14]
    // 3 <= arr.length <= 1000
    //
    // 提供的是严格增长的序列, 结果的起点并不一定是序列的开头, 每个值之间的间隔不定 !!
    // strictly increasing array => no same integers
    // Use Set to save all integers in order to find them quickly
    // Need to loop all possible starting integers => which will cause O(n*n) for time complexity
    //
    // O(n*n) O(n)
    public static int lenLongestFibSubsequence(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int item: arr) {
            set.add(item);
        }
        int maxLength = 2;
        for(int first=0; first < arr.length-2; first++) {
            for(int second=first+1; second < arr.length - 1; second++) {
                int tempLength = 2;

                // 数列的初始值一定是满足不同的排序组合，复杂度有N*N种
                int firstValue = arr[first];
                int secondValue = arr[second];
                int thirdValue = firstValue + secondValue;

                // 使用Set来提供更快的查询效率，快速定位到第三个值
                while (set.contains(thirdValue)) {
                    tempLength++;
                    firstValue = secondValue;
                    secondValue = thirdValue;
                    thirdValue = firstValue + secondValue;
                }
                maxLength = Math.max(maxLength, tempLength);
            }
        }
        return maxLength == 2 ? 0: maxLength;
    }
}
