package com.leetcode.learn_introduction.stack;

import java.util.Stack;

/**
 * Stack栈：严格按照指定的访问顺序来process data
 * 1. Stack: First-in-last-out
 * 2. Stack frame: Debug 栈帧
 * 3. Stack: DFS 深度优先遍历
 */
public class LearnStack {

    // TODO: Stack类型 new Stack<>() + push() + peek() + pop();

    // TODO: 使用特殊的stack栈，同数组的下标index来存储和读取，始终在top前端位置添加和删除 !!
    // Daily Temperatures
    // Array of integers temperatures represents the daily temperatures
    // Return an array answer such that answer[i] is the number of days you have to wait
    //   after the i^th day to get a warmer temperature
    // If there is no future day for which this is possible, keep answer[i] == 0 instead
    // temperatures = [73,74,75,71,69,72,76,73] -> [1,1,4,2,1,1,0,0]
    // 1 <= temperatures.length <= 10^5
    // 30 <= temperatures[i] <= 100 注意温度值的约束范围
    public int[] dailyTemperatures(int[] temperatures) {
        // 正确理解：1. 使用stack[top]存储读取过的index位置，如果后面有位置有更大的值，则更新前面相关index对应结果的数组 !!
        int top = -1;
        int length = temperatures.length;
        int[] stack = new int[length];
        int[] ret = new int[length];
        for (int i = 0; i < length; i++) {
            // 这里内层的while在执行次数上面，最多ret[index]只能被设置n次，和外面的for循环是累加的关系 !!
            while (top > -1 && temperatures[stack[top]] < temperatures[i]) {
                int index = stack[top];
                ret[index] = i - index;
                top--;
            }
            top++;
            stack[top] = i;
        }
        return ret;
    }

    // TODO: Stack典型运用，数学四则公式的运算 + - * /
    // Evaluate Reverse Polish Notation
    // Evaluate the value of an arithmetic expression in Reverse Polish Notation
    // Valid operators are +, -, *, and /. Each operand may be an integer or another expression
    // It is guaranteed that the given RPN expression is always valid, always evaluate to result
    // tokens = ["2","1","+","3","*"]  ->  ((2 + 1) * 3) = 9
    public static int evalRPN(String[] tokens) {
        // 测试理解：1. 在没有特殊条件的情况下，注意约束和标准解法  O(n) O(n) 运算的符号和运算数相差为1，大概为整个tokens长度的一半 !!
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (stack.isEmpty()) {
                stack.push(Integer.parseInt(token));
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int value = stack.pop();    // 如果是运算符号，则栈中一定有两个运算数据可以使用 !!
                int newValue = stack.pop();
                if (token.equals("+")) stack.push(value + newValue);
                if (token.equals("-")) stack.push(newValue - value); // 注意运算符的计算顺序
                if (token.equals("*")) stack.push(value * newValue);
                if (token.equals("/")) stack.push(newValue / value); // 前面的值除以后面的值
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    // TODO: 当一个栈无法实现的时候，考虑使用两个栈同步存储，单独入栈，同时出栈
    // Decode String
    // Given an encoded string, return its decoded string
    // k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times
    // Assume that the input string is always valid; No extra white spaces, square brackets are well-formed
    // s长度>1，其中的字符只有方括号和小写英文字符和数字，s一定是有效的编码字符串，其中数字的范围
    // s = "3[a]2[bc]" -> "aaabcbc"    假设输入的encode的字符串都是有效的
    // s = "3[a2[c]]"  -> "accaccacc"  可能包含嵌套的[[]]需要从内向外依次展开

    // 使用一个栈能够实现 ?
    // c  当遇到一个右方括号的时候，处理一个数字，出栈的如果是字符则直接拼接到一起
    // 2  cc
    // a  acc
    // 3  accaccacc
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';   // 将数字从高位向低位整合起来
            } else if (ch == '[') {      // 将统计出来的数字入栈，将[后面积累的cur字符串入栈 !!
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {      // ]  出现的时候需要decode字符，重复指定的次数
                StringBuilder tmp = cur; // c  cur中存储的是需要重复的
                cur = strStack.pop();    // a+ 再拿到前面入strStack中的存储的字符，进行拼接
                for (k = intStack.pop(); k > 0; k--) {
                    cur.append(tmp);
                }
            } else {
                cur.append(ch);
            }
        }
        return cur.toString();
    }
}
