package data_structure.collections.stack.parentheses;

import java.util.Stack;

// TODO. 典型的Stack应用场景："Open Close平衡逻辑"的判断
public class ParenthesesProblems {

    // Valid Parentheses
    // ((()())()) -> true
    // ((()())(()) -> false
    // 使用"平衡法则"判断是否满足括号的原则: 确保第一个添加的符号是"("
    // 逐个判断的时候，左括号必须先出现，否则最后无法消除
    private boolean isValidParenthesis(char[] chars) {
        int balance = 0;
        for (char c : chars) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            // 左括号必须是起始, 多出的右括号必然不平衡
            if (balance < 0) {
                return false;
            }
        }
        // 最后确定左括号被完全抵消掉了
        return (balance == 0);
    }

    // Valid Parentheses String
    // s containing just the characters '(', ')', '{', '}', '[' and ']',
    // determine if the input string is valid
    // { { } [ ] [ [ [ ] ] ] } VALID expression
    //           [ [ [ ] ] ]   VALID sub-expression
    //   { } [ ]               VALID sub-expression
    // O(n) O(n)
    public boolean isValid(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c: chars) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char temp = stack.peek();
                if ((temp == '{' && c == '}') || (temp == '[' && c == ']') || (temp == '(' && c == ')')) {
                    stack.pop();  // 判断出栈相消的三种条件
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }

    // TODO. Stack不仅可以存储char元素，还可以存储index位置用于计算距离
    //  必须知道开闭区间才能计算长度，而在这个开闭区间中'(' ')'相互消耗 ！！
    // Longest Valid Parentheses String
    // Given a string containing just the characters '(' and ')',
    // return the length of the longest valid (well-formed) parentheses substring
    // "(()" -> 2
    // ")()())" -> 4
    // "()(()" -> 2
    // "()(())" -> 6
    // ")()())))()()()))()" -> 6
    //
    //    ) ( ) ( ) )
    //    0 1 2 3 4 5
    // -1   方便初始位置的计算
    // 0    重新累计位置
    // 0 1
    // 0    2-0=2 当正常消耗时统计位置长度
    // 0 3
    // 0    4-0=4
    // 5    重新累计位置
    public static int longestValidParentheses(String str) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i); // 直接存储open开括号的index位置
            } else {
                stack.pop();   // 如果是close则直接出栈，消耗掉之前存储的open开括号
                if (stack.isEmpty()) {
                    stack.push(i); // 如果stack栈消耗完，则刷新计算index起点
                } else {
                    int lastIndexStart = stack.peek();
                    maxLen = Math.max(maxLen, i - lastIndexStart);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()(())")); // 6
        System.out.println(longestValidParentheses("()(()"));  // 2

        System.out.println(longestValidParentheses(""));
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses(")()())))()()()))()"));
    }
}
