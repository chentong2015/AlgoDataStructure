package data_structure.array.array_base;

import java.util.*;

public class BaseArray2 {



    // TODO: 本质上是一个"连续梯度上升"的问题，通过排序将这一个特征显现出来 !!
    //  通过DP + Binary解决连续梯度上升特征的问题 !! Longest Increasing Subsequence
    // Russian Doll Envelopes
    // envelopes[i] = [wi, hi] represents the width and the height of an envelope
    // One envelope can fit into another if and only if both the width and height of one envelope are greater than the other
    // Return the maximum number of envelopes you can Russian doll
    // 1. 作用范围，必须是长宽严格大于才能放入
    // 2. 同时不能够旋转envelope(把长当作宽来使用)
    // envelopes = [[5,4],[6,4],[6,7],[2,3]] -> [2,3] => [5,4] => [6,7] -> 3
    // sorted    = [[2,3],[5,4],[6,4],[6,7]]
    public int maxEnvelopesDP(int[][] envelopes) {
        // 自定义比较器, 提供Lambda作为比较器的实现 ==> 二分法搜索时判断是否找到的比较条件 !!
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) return arr2[1] - arr1[1];
            else return arr1[0] - arr2[0];
        });
        int dp[] = new int[envelopes.length];
        int maxLength = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, envelope[1]);
            if (index < 0) index = -(index + 1);
            dp[index] = envelope[1];
            if (index == maxLength) maxLength++;
        }
        return maxLength;
    }

    // TODO: 本质上是找到最近的连续上升区间(梯度)，排序之后如何体现出来 !! O(nlog(n)) O(n)
    // Find Right Interval
    // Array of intervals, where intervals[i] = [starti, endi] and each starti is unique 这里这左位置是唯一的，可以作为key值 !!
    // The right interval for an interval i is an interval j such that endi <= startj and startj is minimized
    // Return an array of right interval indices for each interval i
    // If no right interval exists for interval i, then put -1 at index i
    // intervals = [[3,4],[2,3],[1,2]] -> [-1,0,1] 找到每个index位置最小右区间的坐标
    public int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;
        int[] starts = new int[length];
        Map<Integer, Integer> mapStartToIndex = new HashMap<>();
        for (int i = 0; i < length; i++) {
            starts[i] = intervals[i][0];
            mapStartToIndex.put(intervals[i][0], i);
        }
        Arrays.sort(starts);                 // 先将区间左的所有左位置进行排序, 然后在排序好的数组中找每个区间右位置的(index)位置
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int index = Arrays.binarySearch(starts, intervals[i][1]);
            if (index >= 0) {               // 在准确的找到右坐标的情况下，用HashMap来查找左位置所对应的原始index坐标
                result[i] = mapStartToIndex.get(starts[index]);
            } else {
                index = -index - 1;
                if (index < length) {
                    result[i] = mapStartToIndex.get(starts[index]);
                } else {
                    result[i] = -1;
                }
            }
        }
        return result;
    }
}
