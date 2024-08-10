package data_structure.arraystring.reversed;

// TODO. 将字符串换成它的反转字符串
//  只需将“反转字符串序列”中不匹配的字符”甩“到末尾重新组合
//  要移动的步数是”甩“到末尾字符数目，每次只要”甩“的正确字符，成功的步数一定最小
//  无需关心”甩“的正确字符的先后次序，移动的过程不重要 ！！
public class ConvertToReversedString {

    // String Manipulation
    // given a binary representation of string type.
    // in one operation you can take any bit and append at the end of string.
    // return the minimum number of operations required until string matches with its reversed string
    // input1 = "00110101" output = 3
    // input2 = "1010100" output = 3
    // constraints : 1<S.length<=1e5
    //
    // 0 0 1’ 1 0’ 1’ 0’ 1’
    // 1 0 1  0 1  1  0  0
    //
    // 1 0’ 1 0’ 1’ 0’ 0
    // 0 0  1 0  1  0  1
    //
    // 直接在字符数组本身进行比较，不需要创建出反转的字符数组
    public static int minStepsToReversedString(String value) {
        char[] chars = value.toCharArray();
        int n = chars.length;
        int commonPrefix = 0;
        for (char aChar : chars) {
            if (aChar == chars[n - 1 - commonPrefix]) {
                commonPrefix++;
            }
        }
        // 最后要移动的字符便是排除掉公共前缀的字符数目
        return n - commonPrefix;
    }
}
