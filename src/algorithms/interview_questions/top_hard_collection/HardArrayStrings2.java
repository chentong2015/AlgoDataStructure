package algorithms.interview_questions.top_hard_collection;

import java.util.HashSet;
import java.util.Stack;

public class HardArrayStrings2 {

    // TODO: 数组金典案列, 利用数组空间"暂存数据", 使用3轮遍历 !!
    // Given an unsorted integer array nums, find the smallest missing positive integer
    // You must implement an algorithm that runs in O(n) time and uses constant extra space
    // 3 4 5 1         -> 2  不能使用排序，不能超过O(n)的时间复杂度
    public int firstMissingPositive(int[] nums) {
        // 测试理解：1. 使用HashSet<>存储数据，然后从1开始判断缺失位置  O(n), O(n)
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        for (int index = 1; index <= nums.length; index++) {
            if (!set.contains(index)) {
                return index;
            }
        }
        return nums.length + 1; // 如果前面从数字1开始依次排序，则第一缺失的最小数是length+1
    }

    // 正确理解：1. > 理论基础1: [1,n]数组，如果数字全部从1到n连续出现，则第一个缺失的正数必然是n+1     ===> 时间复杂度比上面减低了15倍以上
    //            > 理论基础2: 如果数字区间有位置缺失，则必然在[1,n]区间中，排除负数和超区间外的值     ===> 稳定的算法
    // 6 0 9 2 3 1  -1  ==> 第一轮将边界外的值设置成特殊的值
    // 6 0 8 2 3 1  8   ==> 将负数和大数统一设置成n+1特征值，避免使用到该index来定位(因为在数组有效的范围之外)  !!
    // 6 0 8 2 3 -1 8   ==> 每个位置的值，使用绝对值来确定要标记的位置，这里的-1在被标记完之后，还能再次使用来定位 !!
    //           x
    //   x
    //     x
    // x
    // x x x     x      ==> 标记完成的结果，找到每一标记的位置，就是第一个缺失的正数
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for (int index = 0; index < n; index++) {
            if (nums[index] <= 0 || nums[index] > n) {
                nums[index] = n + 1; // n+1 特征的边界值
            }
        }
        for (int index = 0; index < n; index++) {
            int position = Math.abs(nums[index]);
            if (position <= n) {
                position--;          // 确定要"标记"负值的坐标位置; 如果要标记的位置上是负数，说明被标记过，无需再标记 !!
                if (nums[position] > 0) {
                    nums[position] = -1 * nums[position];
                }
            }
        }
        for (int index = 0; index < n; index++) {
            if (nums[index] > 0) {
                return index + 1;
            }
        }
        return n + 1;
    }

    // Basic Calculator II
    // Given a string s which represents an expression, evaluate this expression and return its value
    // s represents a valid expression: integers and operators('+','-','*','/') 都是正数，且保证值的范围不会溢出 !!
    // Char和Integer之间的转换会根据unicode码值来对应
    // 运算currentChar-'0'或者char-'a'转成对应的整数位置int值
    public int calculate(String s) {
        // 测试理解：1. 如何根据操作符来拆分字符串，获得其中的操作数，必须先算乘除，然后加减
        //            使用栈来记录和统计操作数 O(n) O(n)
        if (s == null || s.isEmpty()) return 0;
        int len = s.length();
        int currentNumber = 0;
        char operation = '+'; // 初始第一个数必然是加
        Stack<Integer> stack = new Stack<>();
        s = s.replace(" ", "");
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);     // 将读取的字符合成Integer
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');  // 将数字全部组合起来成int类型的值
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber); // 将减法用负数来存储，最后出栈是统一计算加法 !!
                } else if (operation == '+') {
                    stack.push(currentNumber);
                } else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    // 正确理解：1. 在连续操作的过程中，如果是乘除则立刻计算，如果是加减则暂存一个值，然后结合前面的值进行计算，如此迭代，则不需要stack来存储
    //            O(n) O(1)
    public int calculate2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {  // 遇到加减，则可以更新前面的总计算结果 !! 切断往后
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;  // 用上一个操作数来更新上一个操作数，直到把"乘除运算"算完
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar; // 更新操作符号
                currentNumber = 0;       // 清空记录的当前操作数
            }
        }
        result += lastNumber; // "1+2*3+6*5" 添加最后一个计算结果30
        return result;
    }
}
