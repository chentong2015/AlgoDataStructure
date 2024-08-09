package data_structure.array.sorting_array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeIntervalsQuestion {

    // TODO. 根据区间的低值排序，根据数据特征循环遍历
    // Merge Intervals
    // Given an array of intervals where intervals[i] = [start, end], start <= end
    // intervals = [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
    //
    // O(n*log(n)) n是区间的数量
    // O(n) 最后的结果可能没有合并，将原始区间数目输出
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                // 更新合并区间的上边界，等效于将合并的区间范围扩大
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
