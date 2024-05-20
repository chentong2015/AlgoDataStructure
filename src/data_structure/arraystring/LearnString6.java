package data_structure.arraystring;

public class LearnString6 {

    // TODO. 考虑两次遍历数组，第二次时得出结果
    // Maximum Score After Splitting a String
    // Given a string s of zeros and ones, return the maximum score after splitting
    // the string into two non-empty substrings (i.e. left substring and right substring)
    //
    // The score is the number of zeros in left substring plus the number of ones in right substring.
    // 0 1 1 1 0 1 = 5  注意左右两边划分出来的子字符串不能为空
    // 1 0 1 0 0 0 = 3
    // 0 0 0 1 1 1 1 = 7
    //
    // O(n) O(1)
    public static void main(String[] args) {
        System.out.println(maxScores("10"));
        System.out.println(maxScores("01"));
        System.out.println(maxScores("011101"));
        System.out.println(maxScores("101000"));
        System.out.println(maxScores("0001111"));
    }

    public static int maxScores(String str) {
        if (str == null || str.length() < 2) {
            return -1;
        }
        // 初始化左右两边结果的统计
        char[] chars = str.toCharArray();
        int leftZero = chars[0] == '0' ? 1: 0;
        int rightOne = 0;
        for (int index = 1; index < chars.length; index++) {
            if (chars[index] == '1') {
                rightOne++;
            }
        }

        // 只有当0被移动到左边时，才有可能增加最后的结果
        int maxScore = leftZero + rightOne;
        for (int index = 1; index < chars.length - 1; index++) {
            if (chars[index] == '0') {
                leftZero++;
                maxScore = Math.max(maxScore, leftZero + rightOne);
            } else {
                rightOne--;
                // 当1被移动到左侧时，和的结果值降低
            }
        }
        return maxScore;
    }
}
