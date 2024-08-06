package work_algorithms.iteration;

import java.util.ArrayList;
import java.util.List;

public class LearnIteration2 {

    // Pascal's Triangle "Recurrence 循环"
    //     1  0  0  0  0 ...
    //    1  1  0  0  0     level=2
    //   1 1+1 1  0  0      level=3
    //  1  3  3  1  0       level=4
    // 1  4  6  4  1        level=5 表示要算到第几个index的位置
    // Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle
    // Use only O(rowIndex) extra space 额外的空间复杂的约束
    public List<Integer> getRow(int level) {
        // 正确理解：循环在[1, index]区间从后往前累计计算
        //         避免先计算的值对后值的累加造成影响(避免保存前一个位置值的历史值) !!
        List<Integer> results = new ArrayList<>();
        results.add(0, 1);
        for (int index = 1; index < level; index++) {
            // TODO. 从右侧往左侧计算，当前位置的新值=它的前一个位置的旧值 + 当前位置的旧值
            for (int j = index - 1; j >= 1; j--) {
                int temp = results.get(j - 1) + results.get(j);
                results.add(j, temp);
            }
            // 每层循环结束，补充后侧末尾的值1
            results.add(index, 1);
        }
        return results;
    }


}
