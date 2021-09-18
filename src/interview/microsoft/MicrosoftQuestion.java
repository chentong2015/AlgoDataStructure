package interview.microsoft;

import java.util.HashMap;

public class MicrosoftQuestion {

    // Microsoft Interview Question 01
    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // Return the smallest index (left, right) 如果没有这层限制，则使用DP
    public static int findClosestSumIndex(int[] nums, int m) {
        // 1. 使用Sliding Windows框出一部分数据，判断特征
        // if (nums == null || nums.length == 0) return null;
        // MyPair pair = new MyPair();
        int checkSum = 0;
        int sum = 0;
        int left = 0;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            while (sum > m) {
                sum -= nums[left];
                left++;
            }
            if (sum > checkSum) {
                checkSum = sum;
                // pair.left = left;
                // pair.right = index;
            }
        }
        // if (checkSum == 0) return null;
        return 0;
    }

    // Microsoft Interview Question 02
    // Excel Formula Validation
    // input: array AA -> ZZ, 00 -> 99, cell data is numeric
    // AA00: "10"
    // AB00: "20"
    // AA01: AA00 + AB00 = "30"
    // AB01: AA01(AA00 + AB00) * AB00 + AC00(0) ? 公式如何解析
    private HashMap<String, String> map = new HashMap<>();

    private boolean canEvaluated(String[][] cells) {
        for (String[] cell : cells) {
            if (!isCellFormula(cell[1])) map.put(cell[0], cell[1]);
        }
        for (String[] cell : cells) {
            if (!isValidCellValue(cell[1])) return false;
        }
        return true;
    }

    private boolean isCellFormula(String cellValue) {
        return cellValue.toLowerCase().charAt(0) - 'A' > 0;
    }

    private boolean isValidCellValue(String cellFormula) {
        if (isCellFormula(cellFormula)) {
        }
        return true;
    }
}
