package data_structure.collections.stack.parentheses;

import java.util.Stack;

public class ParenthesesProblems {

    // 使用"平衡法则"判断是否满足括号的原则: 确保第一个添加的符号是"("
    // ((()())()) 逐个判断的时候，左括号必须先出现，否则最后无法消除
    //
    // O(n) O(1)
    private boolean isValidParenthesis(char[] chars) {
        int balance = 0;
        for (char c : chars) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            // 如果抵消掉左括号后，右括号多了，则必然是无效的 ==> 因为消除后，左括号必须是起始!!
            if (balance < 0) {
                return false;
            }
        }
        // 最后确定左括号被完全抵消掉了
        return (balance == 0);
    }

    // Valid Parentheses 典型的Stack应用场景：带有"平衡原则"的逻辑
    // s containing just the characters '(', ')', '{', '}', '[' and ']',
    // determine if the input string is valid
    // { { } [ ] [ [ [ ] ] ] } VALID expression
    //           [ [ [ ] ] ]   VALID sub-expression
    //   { } [ ]               VALID sub-expression
    public boolean isValid(String str) {
        // 测试理解：1. 使用栈进行迭代判断，如果是一组对应的符号则出栈，否则入栈等待
        //  不需要在stack中存储String类型，避免类型转换造成的"装箱和拆箱"  O(n) O(n)
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(str.charAt(i));
            } else {
                char temp = stack.peek();
                char c = str.charAt(i);
                // 直接列举出3中对应的可能，满足条件的一对，则可以相互消掉 !!
                if ((temp == '{' && c == '}')
                        || (temp == '[' && c == ']')
                        || (temp == '(' && c == ')')) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }


    // TODO. 找最长的连续有效的括号集合，有效的'(' ')'一定连续存在
    // Longest Valid Parentheses
    // Given a string containing just the characters '(' and ')',
    // return the length of the longest valid (well-formed) parentheses substring
    // "(()" -> 2
    // ")()())" -> 4
    // ")()())))()()()))()" -> 6
    public static int longestValidParentheses(String s) {
        int maxResult = 0;
        int maxTemp = 0;
        boolean isContinue = false;

        char[] chars = s.toCharArray();
        for (int index=0; index<chars.length; index++) {
            if (chars[index] == '(') {
                if (index == chars.length - 1) {
                    continue;
                }
                if (chars[index+1] == ')') {
                    if (isContinue) {
                        maxTemp+=2;
                    } else {
                        maxTemp = 2;
                    }
                    maxResult = Math.max(maxResult, maxTemp);
                    index++; // Jump two steps for valid ()
                    isContinue = true; // Keep it continue
                } else {
                    maxTemp = 0;
                    isContinue = false; // Restart the calculation
                }
            } else {
                // Clean continue and move to next char
                maxTemp = 0;
                isContinue = false;
            }
        }
        return maxResult;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(""));
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));
        System.out.println(longestValidParentheses(")()())))()()()))()"));
    }

}
