package work_algorithms.work_others.math;

public class QuestionMath2 {

    public static void main(String[] args) {
        char c = '1';
        char d = '2';
        // System.out.println((c - '0') * (d- '0'));
        System.out.println(multiply("498828660196", "840477629533"));
    }

    // TODO. 纯数学的乘法累计计算: 将大位数的乘法转换成所有位置个位数乘法的和
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

    // TODO: 从数据的连续性推导出"数学公式"的成立，假设能够拆分的数字个数是k个，连续数字的第一个数字是x
    //  时间复杂度必须小于O(n)，一般只有两种可能O(log(n)) & O(n^0.5)和数据的平方有关系
    // Consecutive Numbers Sum
    // Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers
    // n = 5   -> 5 = 2 + 3 -> 2
    // n = 9   -> 9 = 4 + 5 = 2 + 3 + 4 -> 3
    // n = 15  -> 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5 -> 4
    // 1. 如何判断一个数字能拆，如何拆才是合理的? 如何统计拆分的可能性?
    // 2. 反向推理，使用"Sliding Windows"从结果推理出Sum的总和值!! O(n) O(1)
    // 3. 通项公式，转换成数学问题
    //    x+(x+1)+(x+2)+(x+3)...(x+(k-1)) = N
    //    ((2x+k-1)*k)/2 = N
    //    xk + k(k-1)/2 = N
    //    xk = N - k(k-1)/2    对每一个拆分出来的数目k，对k求余数必须是整除的(结果保证x的值)
    //    1< k < Math.sprt(2N) 这里k的范围约束在n的平方根之下，降低时间复杂度为O(n^0.5)
    public int consecutiveNumbersSum(int n) {
        int count = 1;
        for (int k = 2; k < Math.sqrt(2 * n); k++) {
            if ((n - (k * (k - 1) / 2)) % k == 0) {
                count++;
            }
        }
        return count;
    }

    // Kick Start 2021
    // For the first day K, next day K+1, K+2 ...
    // At the N day, the sum must equal G value         一旦生产的数量总和超过G, 则结束, 可以相等
    // K + (K+1) + .. (2K+day-1) ... = (2K+day-1)*day / 2 = G  在生产到第n天的"求和公式", 必须严格相等
    private static int test(int g) {
        // 测试理解：1. 结合等差数列的求和公式，推导出所有的可能 O(G*G^0.5)  O(1)
        //            至少有一种方式，是第一天直接生产g个数量
        int count = 1;
        for (int k = 1; k < (g + 1) / 2; k++) {     // 这里可以取到二分之一的位置, 超过中间再double则必然超出 !!
            int day = 1;                            // k < (g + 1) / 2 或者 k <= g / 2
            int sum = k;                            // 起使点的第一天是正数
            while (sum < g) {
                day++;
                sum = ((2 * k + day - 1) * day) / 2;// 1<= G <=10^4 根据G的约束，这里不可能溢出 !!
            }
            if (sum == g) count++;
        }
        return count;
    }
}
