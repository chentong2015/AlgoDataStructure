package data_structure.array.array_2d;

// 1. 2D Array 二维数组研究
//    In Java, the two-dimensional array is actually a one-dimensional array
//    which contains M elements, each of which is an array of N integers.
//    第一维M个元素，每个定位到第二维存储空间的起使点位置，通过M & N偏移定位到指定内存
// 2. Dynamic 2D Array 同样具有二位的动态数组，类似二维列表
public class BaseArray2D {

    // Rotate Image
    // You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
    // You have to rotate the image in-place 必须在原始矩阵上面改
    public void rotate(int[][] matrix) {
        // 正确理解：1. 不需要内部的while()循环
        // 本质是在旋转的时候，只有4个点(东南西北)位置的坐标值需要依次交换
        int n = matrix.length;                                      // n = 5
        for (int i = 0; i < (n + 1) / 2; i++) {                     // i = 0
            for (int j = 0; j < n / 2; j++) {                       // j = 1
                int temp = matrix[n - 1 - j][i];                    // 3,0  上下左右四个位置依次轮换计算 !!
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];// 4,3  数学推导
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];// 1,4
                matrix[j][n - 1 - i] = matrix[i][j];                // 0,1
                matrix[i][j] = temp;
            }
        }
    }

    // Diagonal Traverse 
    // Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order
    // 1 <= m, n <= 10^4, -10^5 <= mat[i][j] <= 10^5
    // mat = [[1,2,3],  -> [1,2,4,7,5,3,6,8,9]
    //        [4,5,6],  -> 位于同一个斜线上的数字，横纵坐标的和是相等的 i+j=level
    //        [7,8,9]]  -> 注意读取的方向每次需要颠倒 isUp
    // 00        i+j=0  -> 找下标的变化轨迹
    // 01 10     i+j=1
    // 20 11 02  i减少
    // 12 21     i增加
    // 22        第4层   -> i+j=4=m+n-2
    public int[] findDiagonalOrder(int[][] mat) {
        // 测试理解：1. 一层一层的斜对角读取，注意读取时候的前后顺序，先往右上(j增加)再往左下(i增加)
        //           O((row + col)*row)  O(row*col) 必须要一个存储结果的二维数组
        int row = mat.length;
        int col = mat[0].length;
        int index = 0;
        boolean isUp = true;
        int[] result = new int[row * col];
        for (int level = 0; level <= row + col - 2; level++) {
            if (isUp) {
                for (int i = 0; i < row; i++) {
                    int colIndex = level - i;
                    if (0 <= colIndex && colIndex < col)
                        result[index++] = mat[i][colIndex];
                }
            } else {
                for (int i = row - 1; i >= 0; i--) {
                    int colIndex = level - i;
                    if (0 <= colIndex && colIndex < col)
                        result[index++] = mat[i][colIndex];
                }
            }
            isUp = !isUp;
        }
        return result;
    }
}
