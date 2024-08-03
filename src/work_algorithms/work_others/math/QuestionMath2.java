package work_algorithms.work_others.math;

// TODO. 使用纯数学的乘法累计计算方式来得出结果
//  将大位数的乘法转换成所有位置个位数乘法的和
public class QuestionMath2 {

    // Multiply Strings
    // Given two non-negative integers num1 and num2 represented as strings,
    // return the product of num1 and num2, also represented as a string.
    // You must not use any built-in BigInteger library or convert the inputs to integer directly.
    // 1 <= num1.length, num2.length <= 200
    //
    // num1 = "2", num2 = "3"     -> "6"
    // num1 = "123", num2 = "456" -> "56088"
    // 不能将字符串转换成整数进行四则运算, 计算量太大导致无法直接得出结果
    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                // char - '0' 将数字字符转换成整数运算，并存储单个结果
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }

        // 最后的结果存储在int类型的数组中，而非单个变量中
        StringBuilder sb = new StringBuilder();
        for(int p : pos) {
            if(!(sb.isEmpty() && p == 0)) {
                sb.append(p);
            }
        }
        return sb.isEmpty() ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        char c = '1';
        char d = '2';
        // System.out.println((c - '0') * (d- '0'));
        System.out.println(multiply("498828660196", "840477629533"));
    }
}
