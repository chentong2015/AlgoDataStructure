package com.leetcode.learn_introduction.stack;

/**
 * Stack栈：严格按照指定的访问顺序来process data
 * 1. Stack: First-in-last-out
 * 2. Stack frame: Debug 栈帧
 * 3. Stack: DFS 深度优先遍历
 */
public class LearnStack {

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
    public int evalRPN(String[] tokens) {

        return 0;
    }
}
