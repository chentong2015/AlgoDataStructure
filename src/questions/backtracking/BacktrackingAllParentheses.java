package questions.backtracking;

import java.util.ArrayList;
import java.util.List;

// 使用回溯法生成所有括号的排序组合
public class BacktrackingAllParentheses {

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
