package data_structure.array_string;

public class LearnString6 {

    // Maximum Score After Splitting a String
    // Given a string s of zeros and ones, return the maximum score after splitting
    // the string into two non-empty substrings (i.e. left substring and right substring)
    //
    // The score is the number of zeros in left substring plus the number of ones in the right substring.
    // Input: s = "011101"
    // Output: 5
    // All possible ways of splitting s into two non-empty substrings are:
    // left = "0" and right = "11101", score = 1 + 4 = 5 
    // left = "01" and right = "1101", score = 1 + 3 = 4 
    // left = "011" and right = "101", score = 1 + 2 = 3 
    // left = "0111" and right = "01", score = 1 + 1 = 2 
    // left = "01110" and right = "1", score = 2 + 1 = 3
    //
    // O(n) O(1)
    public static int maxScore(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int maxScore = 0;
        int leftCount = 0;
        int rightCount = 0;
        // 先遍历一遍所有的字符，统计1的数目作为右侧的初始值
        for (char c : str.toCharArray()) {
            if (c == '1') {
                rightCount++;
            }
        }
        for (char c : str.toCharArray()) {
            if (c == '0') {
                leftCount++;
            } else {
                rightCount--;
            }
            if (maxScore < leftCount + rightCount) {
                maxScore = leftCount + rightCount;
            }
        }
        return maxScore;
    }
}
