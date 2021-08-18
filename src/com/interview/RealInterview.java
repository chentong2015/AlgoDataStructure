package com.interview;

import java.util.HashMap;

public class RealInterview {

    // Alibaba Interview Question
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 1. 排序之后判断每个元素所占的长度区间  O(nlog(n)) O(1)
    // 2. 使用HashMap<>来存储每个元素的频率 O(n)       O(n)
    // 3. 使用两遍遍历数组：第一遍找出其中出现次数最多的数字，第一遍统计这个数字出现的次数是否超过一半 !!
    public int findMostFrequencyElement(int[] nums) {
        // TODO: 以下不是最优解
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > nums.length / 2) return num;
            map.put(num, count);
        }
        return 0;
    }

    // Tencent Interview Question
    // https://www.nowcoder.com/ta/exam-qq
    // Check Contains subString 确认一个字符串中是否包含另一个字符串(可以乱序排列字符)
    // str1 = "abc", str2="hskoebacdh" -> true
    // 1. 对str1排序之后，构建StringKey，判单str2中相同长度的子字符串是否和该Key相同
    // 2. TODO：如何判断每一个打乱顺序的str1 ??
    // 3. 使用滑动窗口算法取str2字符串的指定长度，然后判断是否和str1一致
    //    先将str1的字符信息统计到HashMap中，然后使用map来做判断 !!
    //    O(n + n*m)  O(n)
    public boolean checkContains(String str1, String str2) {
        // 先做几个常见的特殊情况的判断
        return false;
    }

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

    // Autodesk Interview Question
    // Intersection of Two Arrays
    public int[] intersect(int[] nums1, int[] nums2) {
        return null;
    }
}
