package questions.work_others.bits;

public class QuestionBits03 {

    // Given the bit string, check whether it is divisible by 3.
    // A binary number is divisible by 3 iff the sum of the odd bits is equal to the sum of the even bits.
    //
    // 将字符串转换成char数组，转换成正数值进行判断
    public static boolean isDivisibleBy3(String bitsStr) {
        char[] bits = bitsStr.toCharArray();
        int multiValue = 1;
        int value = 0;
        for (int index = bits.length - 1; index >= 0; index--) {
            value += multiValue * bits[index];
            multiValue = multiValue * 2;
        }
        return value % 3 == 0;
    }

    public static void main(String[] args) {
        System.out.println(isDivisibleBy3("0000")); // true
        System.out.println(isDivisibleBy3("1001")); // true
        System.out.println(isDivisibleBy3("0011")); // true
        System.out.println(isDivisibleBy3("1100")); // true
        System.out.println(isDivisibleBy3("1101")); // false
    }
}
