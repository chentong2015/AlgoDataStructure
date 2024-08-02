package work_interview.criteo;

public class CriteoQuestions {

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
