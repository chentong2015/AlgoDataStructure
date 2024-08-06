package data_structure.array.dimension2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LearnArray2D2 {

    // TODO：二位数组/矩阵的倾斜角度遍历，每一步比较都能确定移动方向
    // Search a 2D Matrix II
    // Searches for a target value in an m x n integer matrix
    // [1,4,7,11,15],     -> 从右上角开始入手，往左下角斜方向进行判断，在值的比较时唯一判断行列移动
    // [2,5,8,12,19],     -> 或者从左下角往右上角，在倾斜的方向上面判断
    // [3,6,9,16,22],
    // [10,13,14,17,24],
    // [18,21,23,26,30]], -> 从其他角度入手，每一步都没有办法准确判断移动方向
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
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

    // Set Matrix Zeroes
    // Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
    public void setZeroes(int[][] matrix) {
        // 正确理解: 1. 使用两个Set集来存储记录到的行和列，最后根据记录的值来修改  Time O(m*n) / Space O(m+n)
        //          2. 用第一行和第一列来作为是否变化的标识符，根据设置0来更改   Time O(m*n) / Space O(1)
        boolean firstRow = false;
        boolean firstCol = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                    matrix[0][j] = 0;  // 标记第一行和第一列作为判断
                    matrix[i][0] = 0;
                }
            }
        }
        // 设置除了第一行第一列的所有位置, 从row=1 & col=1开始往后
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后检查是否第一行和第一列需要设置
        if (firstRow) Arrays.fill(matrix[0], 0);
        if (firstCol) {
            for (int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
        }
    }
}
