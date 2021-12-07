package data_structure.base_structures.collections.stack;

import java.util.Stack;

// Stack栈 / Debug栈帧：严格按照指定的访问顺序来process data, DFS 深度优先遍历
public class LearnStack {

    // TODO: 使用特殊stack栈(数组结构)
    //       同数组的下标index来存储和读取，始终在top前端位置添加和删除
    // Daily Temperatures
    // Array of integers temperatures represents the daily temperatures
    // Return an array answer such that answer[i] is the number of days you have to wait
    // after the i^th day to get a warmer temperature
    // If there is no future day for which this is possible, keep answer[i] == 0 instead
    // temperatures = [73,74,75,71,69,72,76,73] -> [1,1,4,2,1,1,0,0] 相当于每个位置后面更大的值
    // 1 <= temperatures.length <= 10^5
    // 30 <= temperatures[i] <= 100 注意温度值的约束范围
    public int[] dailyTemperatures(int[] temperatures) {
        // 正确理解：使用stack[top]存储读取过的index位置
        //         如果后面有位置有更大的值，则更新前面相关index对应结果的数组
        int top = -1;
        int length = temperatures.length;
        int[] stack = new int[length];
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            // 内层while在执行次数上面，最多ret[index]只能被设置n次，和外面的for循环是累加的关系
            while (top > -1 && temperatures[stack[top]] < temperatures[i]) {
                int stackIndex = stack[top];
                result[stackIndex] = i - stackIndex; // 计算间隔的步数
                top--;
            }
            top++;
            stack[top] = i;
        }
        return result;
    }

    // Remove K Digits
    // Given string num representing a non-negative integer num, and an integer k
    // return the "smallest possible" integer after removing k digits from num
    // num = "1432219", k = 3 -> 1219
    // num = "10200",   k = 1 -> 200
    // num = "11200",   k = 1 -> 1100
    // 1 <= k <= num.length <= 10^5 严格的大小约束, num consists of only digits 是有效的数字，最高位不为0
    public String removeKDigits(String num, int k) {
        // 正确理解：1. 通过栈数据结构来存储和比较，如果"前面的数字比当然的数字大"，则删除(使用while来循环删除)
        //            O(n + k) 最终会遍历所有的字符，同时将k减小到0  O(n)
        char[] stack = new char[num.length()];
        int left = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (left > 0 && stack[left - 1] > c && k > 0) {
                left--;
                k--;
            }
            stack[left++] = c;
        }
        // 移除stack栈数组中开头的0，同时截取后面的字符组成最后的字符串 !!
        // 最终结果的字符串长度一定是 num.length() - k !!
        int idx = 0;
        int digits = num.length() - k;
        while (idx < digits && stack[idx] == '0') idx++;
        if (idx == digits) return "0";
        return new String(stack, idx, digits - idx);
    }

    // TODO: Stack典型运用，数学四则公式的运算 + - * /
    // Evaluate Reverse Polish Notation
    // Evaluate the value of an arithmetic expression in Reverse Polish Notation
    // Valid operators are +, -, *, and /. Each operand may be an integer or another expression
    // It is guaranteed that the given RPN expression is always valid, always evaluate to result
    // tokens = ["2","1","+","3","*"]  ->  ((2 + 1) * 3) = 9
    public static int evalRPN(String[] tokens) {
        // 测试理解：1. 在没有特殊条件的情况下，注意约束和标准解法
        //            O(n) O(n) 运算的符号和运算数相差为1，大概为整个tokens长度的一半 !!
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
    //       思考：一个栈能否实现 ?
    // Decode String
    // Given an encoded string, return its decoded string
    // k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times
    // Assume that the input string is always valid; No extra white spaces, square brackets are well-formed
    // s长度>1，其中的字符只有方括号和小写英文字符和数字，s一定是有效的编码字符串，其中数字的范围
    // s = "3[a]2[bc]" -> "aaabcbc"    假设输入的encode的字符串都是有效的
    // s = "3[a2[c]]"  -> "accaccacc"  可能包含嵌套的[[]]需要从内向外依次展开
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
