package interview;

import java.util.List;

// 数据结构绘图工具 https://processon.com/diagrams
// 数据结构可视化   https://www.cs.usfca.edu/~galles/visualization/Algorithms.html

// TODO: 50道基本算法题 https://www.bilibili.com/video/BV1a54y1b74k
// 企业题库      https://codetop.cc/#/home (tong / TCHong15)
// 题目全集      https://leetcode.com/problemset/all/ (chen2015tong / TCHong15&)
// 算法总结题库
// http://www.leetcodecn.com/
// https://longwang2.gitbooks.io/lintcode/content/
// https://wizardforcel.gitbooks.io/the-art-of-programming-by-july/content/00.01.html
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
