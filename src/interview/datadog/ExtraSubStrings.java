package interview.datadog;

// 1. 输入指定的字符串，遍历所有的子字符串
// 2. 判断每个子字符串是否满足特殊的条件(所有的字符的映射值和能够被子字符串的长度整除)
public class ExtraSubStrings {

    public static void main(String[] args) {
        System.out.println('b' - 'a');
        System.out.println(getTotalNumOfNonEmptyExSubstrings("absa"));
    }

    // 26个字符和int之间存在之间的对应关系，之后ab字符的位置特殊
    // a i k e j
    // <i -> J> 所有的字符串的可能性一定时N*N
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

    // TODO. 利用字符本身的隐射关系来完成数据的计算
    // a b -> 1
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
