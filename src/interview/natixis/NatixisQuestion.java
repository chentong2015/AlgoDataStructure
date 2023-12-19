package interview.natixis;

import java.io.PrintStream;
import java.util.Scanner;

public class NatixisQuestion {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int s1 = in.nextInt();
        int s2 = in.nextInt();
        PrintStream outStream = System.out;
        System.setOut(System.err);
        int res = computeJoinPoint(s1, s2);
        System.setOut(outStream);
        System.out.println(res);
    }

    // TODO. 算法隐藏条件: 始终是在小值的基础上增加，再和大值比较，直到两个数据相同
    // 计算当前的数值和它的各个分位上的数值之和 ==> 递归当前方法
    // 471 -> 471 + 4 + 7 + 1 = 483
    // 480 -> 480 + 4 + 8 + 0 = 492
    public static int computeJoinPoint(int s1, int s2) {
        if (s1 == s2) {
            return s1;
        }
        if (s1 < s2) {
            int nextValueS1 = calculateNextValue(s1);
            if (nextValueS1 == s2) {
                return nextValueS1;
            }
            return computeJoinPoint(nextValueS1, s2);
        } else {
            int nextValueS2 = calculateNextValue(s2);
            if (s1 == nextValueS2) {
                return s1;
            }
            return computeJoinPoint(s1, nextValueS2);
        }
    }

    private static int calculateNextValue(int value) {
        if (value == 10) {
            return value + 1;
        }
        if (value < 10) {
            return value + value;
        }

        int tempValue = value;
        while (tempValue >= 10) {
            value += tempValue % 10;
            tempValue = tempValue / 10;
        }
        value += tempValue;
        return value;
    }

    // 计算小于某个数的所有满足条件的数值之和
    // 累计的数必须是3 5 7及其倍数
    public static int computeMultiplesSum(int n) {
        if (n < 3) {
            return -1;
        }
        int sum = 0;
        for (int index = 3; index < n; index++) {
            if (index % 3 == 0 || index % 5 == 0 || index % 7 == 0) {
                sum += index;
            }
        }
        return sum;
    }
}
