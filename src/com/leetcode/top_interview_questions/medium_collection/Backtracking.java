package com.leetcode.top_interview_questions.medium_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Backtracking {

    // 使用"平衡法则"判断是否满足括号的原则: 确保第一个添加的符号是"("
    // O(n) O(1)
    private boolean isValidParenthesis(char[] chars) {
        int balance = 0;
        for (char c : chars) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) return false; // 如果抵消掉左括号后，右括号多了，则必然是无效的 ==> 因为消除后，左括号必须是起始!!
        }
        return (balance == 0); // 最后确定左括号被完全抵消掉了 !!
    }

    // Valid Parentheses 典型的Stack应用场景：带有"平衡原则"的逻辑
    // s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid
    // { { } [ ] [ [ [ ] ] ] } VALID expression
    //           [ [ [ ] ] ]   VALID sub-expression
    //   { } [ ]               VALID sub-expression
    public boolean isValid(String str) {
        // 测试理解：1. 使用栈进行迭代判断，如果是一组对应的符号则出栈，否则入栈等待
        //            不需要在stack中存储String类型，避免类型转换造成的"装箱和拆箱"
        //            O(n) O(n)
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(str.charAt(i));
            } else {
                char temp = stack.peek();
                char c = str.charAt(i);
                // 直接列举出3中对应的可能，满足条件的一对，则可以相互消掉 !!
                if ((temp == '{' && c == '}') || (temp == '[' && c == ']') || (temp == '(' && c == ')')) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }

    // 以上为基础测试 ----------------------------------------------------------------------------------------------------

    // Generate Parentheses
    // Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses
    // n = 3 -> ["((()))","(()())","(())()","()(())","()()()"]
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    // 每种递归的方法中都有两种if可以执行，如此反复，穷举出所有的可能性
    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {   // 左侧括号
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);              // 回溯的核心：递归完成之后，取出尾部的字符 !!
        }
        if (close < open) { // 右侧括号如果比左侧括号少，则可添加
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    // Subsets
    // Given an integer array nums of unique elements, return all possible subsets (the power set) 返回所有的组合形式
    // The solution set must not contain duplicate subsets
    // nums = [1,2,3] -> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]] nums中所有值都唯一
    public List<List<Integer>> subsets(int[] nums) {
        // 测试理解：1. 每一个位置上的值，都有两种可能，要么出现，要么不出现 -> 结果的数目是2^n个

        // 正确理解：1. 根据每个具体位置的不同可能, 多重"递归", 当递归到最后一个位置，再提取值到结果中 !!
        //            O(n*2^n) 递归调用方法的数目*最后的取值
        //            O(n+n*2^n)*n)=O(n*2^n) 空间复杂度用来存储所有的可能结果, 一共有2^n中可能，其中最大存储空间是全部的n个数
        List<List<Integer>> results = new ArrayList<>();
        int[] list = new int[nums.length];
        getSubsets(-1, list, nums, results);
        return results;
    }

    private void getSubsets(int index, int[] list, int[] nums, List<List<Integer>> results) {
        index++;
        int[] cloneList = list.clone();                 // 使用clone出来的列表，不会造成数据的共享差错 !!
        if (index == nums.length) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {     // 递归的最后，还需要遍历N来取出所有的结果，增加时间复杂度
                if (list[i] == 1) {
                    result.add(nums[i]);
                }
            }
            results.add(result);
        } else {
            cloneList[index] = 0;                       // 回溯的核心：改变设置递归出不同的结果, 0表示不出现, 1表示出现 !!
            getSubsets(index, cloneList, nums, results);
            cloneList[index] = 1;
            getSubsets(index, cloneList, nums, results);
        }
    }
}
