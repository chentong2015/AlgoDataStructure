package interview.datadog;

// 1. 输入指定的字符串，遍历所有的子字符串
// 2. 判断每个子字符串是否满足特殊的条件(所有的字符的映射值和能够被子字符串的长度整除)
public class ExtraSubStrings {

    public static void main(String[] args) {
        System.out.println('b' - 'a');
        System.out.println(getTotalNumOfNonEmptyExSubstrings("absa"));
    }

    // 复杂度的计算: 时间复杂度(等差数列Sequence)，空间复杂度O(N)
    // c1 c2 c3 ... cN            N为字符的数目
    // N + (N-1) + (N-2) + ... 1  子字符串必须时联系的相邻的字符
    // N*(N-1)*N                  总的复杂度为O(N^3)
    public static int getTotalNumOfNonEmptyExSubstrings(String input) {
        char[] chars = input.toLowerCase().toCharArray();
        int totalNum = 0;
        for (int startIndex = 0; startIndex < chars.length; startIndex++) {
            for (int endIndex = startIndex; endIndex < chars.length; endIndex++) {
                if (isValidExSubstring(chars, startIndex, endIndex)) {
                    System.out.println(startIndex + "--" + endIndex);
                    totalNum++;
                }
            }
        }
        return totalNum;
    }

    // TODO. 利用26个字符本身的隐射关系来完成数据的计算
    // a b -> 1  只有前两个字符处在特殊的位置
    // c d e -> 2
    // f g h -> 3
    private static boolean isValidExSubstring(char[] chars, int startIndex, int endIndex) {
        int sum = 0;
        for (int index = startIndex; index <= endIndex; index++) {
            if (chars[index] == 'a' || chars[index] == 'b') {
                sum += 1;
            } else {
                int count = (chars[index] - 'b') / 3 + 2;
                sum += count;
            }
        }
        return (sum % (endIndex - startIndex + 1)) == 0;
    }
}
