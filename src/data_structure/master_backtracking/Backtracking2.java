package data_structure.master_backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Backtracking2 {

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

    // Generate Parentheses
    // Given n pairs of parentheses
    // write a function to generate all combinations of well-formed parentheses
    // n = 3 -> ["((()))","(()())","(())()","()(())","()()()"]
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        backtrack(result, stringBuilder, 0, 0, n);
        return result;
    }
    
    // 每种递归的方法中都有两种if可以执行，如此反复，穷举出所有的可能性
    public void backtrack(List<String> result, StringBuilder currentStr, int open, int close, int max) {
        if (currentStr.length() == max * 2) {
            result.add(currentStr.toString());
            return;
        }

        if (open < max) {   // 左侧括号
            currentStr.append("(");
            backtrack(result, currentStr, open + 1, close, max);
            currentStr.deleteCharAt(currentStr.length() - 1); // 回溯的核心：递归完成之后，取出尾部的字符 !!
        }
        if (close < open) { // 右侧括号如果比左侧括号少，则可添加
            currentStr.append(")");
            backtrack(result, currentStr, open, close + 1, max);
            currentStr.deleteCharAt(currentStr.length() - 1);
        }
    }
}
