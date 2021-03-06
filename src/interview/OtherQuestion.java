package interview;

import java.util.List;

public class OtherQuestion {

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

    // 2020 Facebook base_interview
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
