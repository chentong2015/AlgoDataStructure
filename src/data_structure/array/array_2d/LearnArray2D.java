package data_structure.array.array_2d;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LearnArray2D {

    // TODO: 经典降维问题01: 根据题目条件特征，从三维降到二维的复杂度
    //       确定一个Index位置的坐标，则另外两个index坐标和起来的遍历复杂度是O(n)
    // 3Sum: return all the triplets [nums[i], nums[j], nums[k]]
    // Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
    // Solution set must not contain duplicate triplets 不能出现重复的结果集
    // nums = [-1,0,1,2,-1,-4] -> [[-1,-1,2],[-1,0,1]]
    //         -4 -1 -1 0 1 2
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 如果index位置和index-1的位置相同，则说明该值已经被判断过了，无需重复
            if (i == 0 || nums[i - 1] != nums[i]) {
                int jIndex = i + 1;
                int kIndex = nums.length - 1;
                int sum = -nums[i];
                while (jIndex < kIndex) {
                    if (nums[jIndex] + nums[kIndex] == sum) {
                        res.add(Arrays.asList(nums[i], nums[jIndex], nums[kIndex]));
                        // 如果是相同的值，在直接跳过，直到是不同的值
                        while (jIndex < kIndex && nums[jIndex] == nums[jIndex + 1])
                            jIndex++;
                        while (jIndex < kIndex && nums[kIndex] == nums[kIndex - 1])
                            kIndex--;
                        jIndex++;
                        kIndex--;
                    } else if (nums[jIndex] + nums[kIndex] < sum) {
                        jIndex++;
                    } else {
                        kIndex--;
                    }
                }
            }
        }
        return res;
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
