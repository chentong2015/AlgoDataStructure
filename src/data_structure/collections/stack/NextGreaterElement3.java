package data_structure.collections.stack;

import java.util.ArrayList;
import java.util.List;

public class NextGreaterElement3 {

    public static void main(String[] args) {
        // System.out.println(nextGreaterElement(101)); // 110
        // System.out.println(nextGreaterElement(12)); // 21
        // System.out.println(nextGreaterElement(21)); // -1
        // System.out.println(nextGreaterElement(123)); // 132
        // System.out.println(nextGreaterElement(1324)); // 1342
        // System.out.println(nextGreaterElement(53621)); // 56321
        // System.out.println(nextGreaterElement(54321)); // -1

        System.out.println(nextGreaterElement(230241)); // 230412, not 230'421'
    }

    // TODO. 准备"足够的案例"来推导出隐藏在背后的算法逻辑
    // Next Greater Element III
    // Given a positive integer n, find the smallest integer
    // which has exactly the same digits existing in the integer n and is greater in value than n.
    // If no such positive integer exists, return -1.
    // 返回下一个比原值更大的值，数字的数目相同
    //
    // TODO. 算法:
    // 从个位判断它的前面是否有比更小的数，找到第一个更小的digit进行替换
    // 从十位判断...，找到之后替换，替换之后如果比个位数替换的更小，则保留 ！！
    // 从百位判断...
    // 找完一轮替换的数字之后，需要替换位置的右侧来排列组合出最小的值
    // 从index右侧个位开始判断它的前面是否有比更大的数
    // 从index右侧百位开始判断...
    public static int nextGreaterElement(int n) {
        // 将int类型的数据处理转换成List列表的问题
        // 注意循环的边界条件，在n=10的时候同样处理
        int initValue = n;
        List<Integer> digits = new ArrayList<>();
        while (n >= 10) {
            digits.add(n % 10);
            n = n / 10;
        }
        digits.add(n);

        int nextGreaterValue = Integer.MAX_VALUE;
        for (int index=0; index < digits.size() - 1; index++) {
            for (int j=index+1; j < digits.size(); j++) {
                if (digits.get(index) > digits.get(j)) {
                    int iValue = digits.get(index);
                    int jValue = digits.get(j);
                    digits.set(index, jValue);
                    digits.set(j, iValue);

                    // 如果替换之后的结果更大，则需要恢复原来的替换
                    int currentValue = getValueFromDigits(digits);
                    if (currentValue != -1 && currentValue < nextGreaterValue) {
                       nextGreaterValue = currentValue;
                    }
                    digits.set(index, iValue);
                    digits.set(j, jValue);
                    break;
                }
            }
        }
        return nextGreaterValue;
    }

    // 从数字列表中将结果重新组装，如果int值溢出则返回-1
    private static int getValueFromDigits(List<Integer> digits) {
        int multiple = 1;
        int result = 0;
        for (int digit: digits) {
            try {
                result += digit * multiple;
            } catch (Exception exception) {
                return -1;
            }
            multiple = multiple * 10;
        }
        return result;
    }
}
