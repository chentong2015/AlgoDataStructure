package data_structure.iteration_recursion;

import java.util.ArrayList;
import java.util.List;

// Java提供两个迭代的类型, 用于迭代访问集合中的数据
// 1. Iterator<T> hasNext()
// 2. Enumeration<E> hasMoreElements()
public class LearnIteration {

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

    // TODO: 在数组或者矩阵循环过程中，如果反复的判断.length会对(空间)复杂度有一定的影响 !!
    // Search a 2D Matrix II
    // Searches for a target value in an m x n integer matrix 矩阵从左到右，从上到下数值逐渐增加
    // [1,4,7,11,15],
    // [2,5,8,12,19],
    // [3,6,9,16,22],
    // [10,13,14,17,24],
    // [18,21,23,26,30]],
    public boolean searchMatrix(int[][] matrix, int target) {
        // 测试理解：1. 如果一个位置的值比target大，那么它的右边和下面都不需要再遍历
        //            O(n*m) O(1)
        if (matrix == null) return false;
        int row = 0;
        int lastCol = matrix[0].length - 1;
        int length = matrix.length;
        while (row < length) {
            if (target < matrix[row][0]) return false;        // 注意边界条件，减少不必要的循环和判断 !!
            for (int index = 0; index <= lastCol; index++) {
                if (target == matrix[row][index]) return true;
                if (target < matrix[row][index]) {
                    lastCol = index;
                    break;
                }
            }
            row++;
        }
        return false;
    }

    // TODO：从矩阵或者二位数组的倾斜角度入手，唯一确定移动方向
    // [1,4,7,11,15],     -> 从右上角开始入手，往左下角斜方向进行判断，在值的比较过程中(3种可能)，可以唯一判断行列移动的位置
    // [2,5,8,12,19],     -> 或者从左下角往右上角，在倾斜的方向上面判断
    // [3,6,9,16,22],
    // [10,13,14,17,24],
    // [18,21,23,26,30]], -> 从其他角度入手，每一步都没有办法准确判断移动方向
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) { // 边界条件(1 <= n, m <= 300)确定，并排除干净 !!
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row <= matrix.length - 1 && col >= 0) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
