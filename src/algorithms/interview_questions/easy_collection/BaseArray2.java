package algorithms.interview_questions.easy_collection;

public class BaseArray2 {

    // Move Zeroes
    // Given an integer array nums, move all 0's to the end of it while
    // maintaining the relative order of the non-zero elements.
    // nums = [0,1,0,3,12] -> [1,3,12,0,0] 只能在原始的数组上操作
    //         1 3 12
    public void moveZeroes(int[] nums) {
        // 测试理解：1. 将非0的值依次排列在数组的开头，最后留下的位置就是0的值(统计数目) O(n) O(1)
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            } else {
                count++;
            }
        }
        for (int i = 0; i < count; i++) { // 最后补充结尾的0位置
            nums[nums.length - 1 - i] = 0;
        }
    }

    // Rotate Image
    // You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
    // You have to rotate the image in-place 必须在原始矩阵上面改
    public void rotate(int[][] matrix) {
        // 正确理解：1. 不需要内部的while()循环，本质是在旋转的时候，只有4个点(东南西北)位置的坐标值需要依次交换
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
}
