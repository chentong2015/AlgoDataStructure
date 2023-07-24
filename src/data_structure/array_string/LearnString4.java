package data_structure.array_string;

import java.util.Stack;

public class LearnString4 {
    
    // Basic Calculator II
    // Given a string s which represents an expression, evaluate this expression and return its value
    // s represents a valid expression: integers and operators('+','-','*','/') 都是正数，且保证值的范围不会溢出 !!
    // Char和Integer之间的转换会根据unicode码值来对应
    // 运算currentChar-'0'或者char-'a'转成对应的整数位置int值
    public int calculate(String s) {
        // 测试理解：1. 如何根据操作符来拆分字符串，获得其中的操作数，必须先算乘除，然后加减
        //            使用栈来记录和统计操作数 O(n) O(n)
        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        int currentNumber = 0;
        char operation = '+'; // 初始第一个数必然是加
        Stack<Integer> stack = new Stack<>();
        s = s.replace(" ", "");
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);     // 将读取的字符合成Integer
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');  // 将数字全部组合起来成int类型的值
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber); // 将减法用负数来存储，最后出栈是统一计算加法 !!
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    // 正确理解：1. 在连续操作的过程中，如果是乘除则立刻计算，如果是加减则暂存一个值，然后结合前面的值进行计算，如此迭代，则不需要stack来存储
    //            O(n) O(1)
    public int calculate2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {  // 遇到加减，则可以更新前面的总计算结果 !! 切断往后
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;  // 用上一个操作数来更新上一个操作数，直到把"乘除运算"算完
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar; // 更新操作符号
                currentNumber = 0;       // 清空记录的当前操作数
            }
        }
        result += lastNumber; // "1+2*3+6*5" 添加最后一个计算结果30
        return result;
    }
}
