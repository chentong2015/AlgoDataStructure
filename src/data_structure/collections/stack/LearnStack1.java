package data_structure.collections.stack;

import java.util.Stack;

// TODO. Stack栈的运用场景 => 用空间换时间, 优化O(N)级别
// 1. Stack需要严格按照指定的顺序来Process数据
// 2. Stack本质上存储的是访问的记录数据(Stack Trace)栈帧
// 3. Stack通过“入栈选择”和“出栈判断”来执行核心算法
public class LearnStack1 {

    // TODO. Stack栈能够存储“历史访问的有序数据”信息 => DP存储的历史信息可能不满足有序顺序
    // Final Prices With a Special Discount in a Shop
    // You are given an integer array prices where prices[i] is the price of the ith item in a shop.
    //
    // There is a special discount for items in the shop. If you buy the ith item,
    // then you will receive a discount equivalent to prices[j] where j is the minimum index
    // such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.
    // prices = [8,4,6,2,3] -> [4,2,4,2,3] index位置的折扣数是它后面第一个小于等于它的值
    // prices = [1,2,3,4,5] -> [1,2,3,4,5]
    // prices = [10,1,1,6] -> [9,0,1,6]
    //
    // stack = [3]
    // stack = [2]
    // stack = [6 2]
    // stack = [4 2]
    // stack = [8 4 2] 栈中存储当前值以及它后面的更小值 ！！
    //
    // O(N) O(N)
    public static int[] finalPrices(int[] prices) {
       Stack<Integer> stack = new Stack<>();
       for (int index = prices.length - 1; index >= 0; index--) {
           int tempValue = prices[index];
           while (!stack.empty() && stack.peek() > prices[index]) {
               stack.pop();
           }
           if (!stack.empty()) {
               // 在计算完折扣的数据后，后面比它小的数据不能pop()
               prices[index] -= stack.peek();
           }
           stack.push(tempValue);
       }
       return prices;
    }

    // TODO: Stack典型运用，数学四则公式的运算 + - * /
    // Evaluate Reverse Polish Notation
    // Evaluate the value of an arithmetic expression in Reverse Polish Notation
    // Valid operators are +, -, *, and /. Each operand may be an integer or another expression
    // It is guaranteed that the given RPN expression is always valid, always evaluate to result
    // tokens = ["2","1","+","3","*"]  ->  ((2 + 1) * 3) = 9
    //
    // O(n) O(n) 运算的符号和运算数相差为1，大概为整个tokens长度的一半 !!
    public static int evalRPN(String[] tokens) {
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
}
