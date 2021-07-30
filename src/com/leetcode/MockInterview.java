package com.leetcode;

import java.util.HashMap;
import java.util.List;

// 企业题库  https://codetop.cc/#/home              Login: tong / TCHong15
// 题目全集  https://leetcode.com/problemset/all/   Login: chen2015tong@gmail.com / TCHong15&
// 算法总结  http://www.leetcodecn.com/
//         https://longwang2.gitbooks.io/lintcode/content/
// 1. Easy   ：只包含1个基础的核心逻辑，一个思想
// 2. Medium : 包含1个较难的逻辑，或者包含2到3个(构建在基础题目上面的)逻辑和思想
// 3. Hard   : 包含4个或者更换多的判断和逻辑，一般是一些小游戏的逻辑或是小工具软件的组成部分
public class MockInterview {

    // Twitter Interview Question
    // 计算每个位置的柱子上方还能储水的量，和该柱子的前后柱子没有关系，只和它前后元素中的最大高度柱子有关系
    // 对于任何一个柱子，如果它"左边位置的最大值"和"右边位置的最大值"都比该位置的高度高，则柱子上方还有空间存水 !!
    public static int testSaveWater(int[] arr) {
        // 正确理解: 通过左右两个标识完成左右最大值的更新，依次往中间移动，线性时间复杂度
        if (arr == null || arr.length < 3) return 0;
        int sum = 0;
        int left = 0;
        int right = arr.length - 1;
        int largestLeft = arr[left];
        int largestRight = arr[right];
        while (left < right) {
            if (largestLeft > largestRight) {        // 如果右边的最大高度更低, 则往右标识往左移动, 保证整个左边的元素的最大值不变 !!
                sum += largestRight - arr[right--];  // 此处的计算不可能为负数
                largestRight = Math.max(arr[right], largestRight);
            } else {
                sum += largestLeft - arr[left++];
                largestLeft = Math.max(arr[left], largestLeft);
            }
        }
        return sum;
    }

    // Alibaba Interview Question
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 1. 排序之后判断每个元素所占的长度区间  O(nlog(n)) O(1)
    // 2. 使用HashMap<>来存储每个元素的频率 O(n)       O(n)
    // 3. 使用两遍遍历数组：第一遍找出其中出现次数最多的数字，第一遍统计这个数字出现的次数是否超过一半 !!
    public int findMostFrequencyElement(int[] nums) {
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
    public boolean checkContains(String str1, String str2) {

        return false;
    }

    // Microsoft Interview Question
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

    // Google Interview Question
    // https://www.hackerrank.com/interview/interview-preparation-kit
    // 有一个n*n的棋盘，上面有m个糖果，最开始有一个人在棋盘左上角，他可以向左向右或者向下移动，但不能向上移动，最少需要多少步吃完所有糖果
    // ? 0 0 0      1. 不能向上，说明一行中的糖果必须吃完才下移
    // 0 0 1 0      2. 如何选择进入一行的切入点位置，避免在一行中来回移动
    // 1 0 0 1      3. 每一行必须移动的距离是最左边和最右边的距离
    // 0 1 0 0      4. 如果只有一行只有一个，则计算该位置和上一行边界位置的一个距离差 !!
    public int countSteps(int[][] nums, int m) {
        return 0;
    }

    // 2020 Facebook interview
    // TODO: https://leetcode.com/problems/remove-invalid-parentheses/solution/
    // http://www.noteanddata.com/leetcode-301-Remove-Invalid-Parentheses-java-solution-note2.html
    // Remove Invalid Parentheses
    // Remove the minimum number of invalid parentheses to make the input string valid
    // Return all the possible results in any order
    // s = "()())()"  -> ["(())()","()()()"]   去除最小的括号数(左右括号的数量差值), 使字符串有效
    // s = "(a)())()" -> ["(a())()","(a)()()"]
    public List<String> removeInvalidParentheses(String str) {
        // 测试理解: 1. )((( )))( 有效的字符串，左右括号数量必须一致/数量一致并不一定就有效，左括号必须第一字符，右括号必须最后字符
        //          2. 暴力破解，依次(全部可能)去除一定数目的字符，判断是否是有效字符串
        return null;
    }

    // Amazon Interview
    // Median of Two Sorted Arrays
    // TODO: https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn)))-solution-with-explanation
}
