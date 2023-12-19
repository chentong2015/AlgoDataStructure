package algorithms.others_algo;

// 数学证明，离散数学，组合数学，例如N个中选k个的方法等
public class MathProblem {

    // Sum Game
    // Alice (go first) and Bob replaces ? with one number (0-9), until there is no ? in the String
    // There are even character in the String 确定字符串由偶数个字符组成
    // Check sums of the first half and the second half are equal
    // nums = "5023"     -> 5+0=2+3       -> Bob will win
    // nums = "25??"     -> "259?"        -> Alice will win
    // nums = "?329 5???" -> "9329 5927"  -> Bob 后填，始终能够找到一种填法，使得前后的和相等
    // 证明一定是后者赢的逻辑：两侧?的差值数*(-9)=两侧数值总和的两倍 !!
    public boolean sumGame(String str) {
        int count = 0;
        int diffTotal = 0;
        int number;
        boolean isLeft;
        for (int index = 0; index < str.length(); index++) {
            isLeft = index < str.length() / 2;
            if (str.charAt(index) == '?') {
                count += isLeft ? 1 : -1;
            } else {
                number = str.charAt(index) - '0'; // Convert char to integer
                diffTotal += isLeft ? number : -number;
            }
        }
        if (count * -9 == diffTotal * 2) return false;
        return true;
    }
}
