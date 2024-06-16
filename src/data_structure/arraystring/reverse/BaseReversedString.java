package data_structure.arraystring.reverse;

public class BaseReversedString {

    // TODO. 使用while循环来直接修改字符数组，造成反转效果
    // Reverse String
    // Modifying the input array in-place with O(1) extra memory
    public static void reverseChars(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }

    // TODO. 使用递归一层一层反转特定的index位置
    private static String recursive(char[] chars, int index) {
        int length = chars.length;
        if (index < length / 2 + 1) {
            char tempChar = chars[index - 1];
            int swapIndex = length - index;
            chars[index - 1] = chars[swapIndex];
            chars[swapIndex] = tempChar;

            recursive(chars, index + 1);
        }
        return new String(chars);
    }

    // Reverse integer
    // return x with its digits reversed, return 0 if it's outside integer range
    // input -120 -> -21
    // -2^31 <= x <= 2^31 - 1
    public static int reverseInteger(int x) {
        // 测试理解：需要将int中包含的数字进行颠倒，形成新的值
        // 1. int -> String -> StringBuilder reverse -> new int

        // 正确理解:
        // 1. 可以归结位数学问题，和公式计算 !!
        // 2. 通过StringBuilder可以实现其中的reverse字符操作，复杂度取决于reverse方法
        int value = x >= 0 ? x : -x;
        StringBuilder stringBuilder = new StringBuilder(Integer.toString(value));
        stringBuilder.reverse();
        if (stringBuilder.length() == 0) {
            return 0;
        } else {
            // 这里有可能因为值过大而无法发生转换 ? 使用更大的数据来承载 !!
            int result = Integer.parseInt(stringBuilder.toString());
            return x >= 0 ? result : -result;
        }
    }
}
