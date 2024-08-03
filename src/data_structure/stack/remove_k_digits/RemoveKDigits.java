package data_structure.stack.remove_k_digits;

public class RemoveKDigits {

    // Remove K Digits
    // Given string num representing a non-negative integer num, and an integer k
    // return the "smallest possible" integer after removing k digits from num
    // num = "1432219", k = 3 -> 1219
    // num = "10200",   k = 1 -> 200
    // num = "11200",   k = 1 -> 1100
    //
    // 1 <= k <= num.length <= 10^5 严格的大小约束,
    // num consists of only digits 是有效的数字，最高位不为0
    public String removeKDigits(String num, int k) {
        // 正确理解：1. 通过栈数据结构来存储和比较，如果"前面的数字比当前的数字大"，则删除(使用while来循环删除)
        // O(n + k) 最终会遍历所有的字符，同时将k减小到0  O(n)
        char[] stack = new char[num.length()];
        int left = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (left > 0 && stack[left - 1] > c && k > 0) {
                left--;
                k--;
            }
            stack[left++] = c;
        }

        // 移除stack栈数组中开头的0，同时截取后面的字符组成最后的字符串 !!
        // 最终结果的字符串长度一定是 num.length() - k !!
        int idx = 0;
        int digits = num.length() - k;
        while (idx < digits && stack[idx] == '0') {
            idx++;
        }
        if (idx == digits) {
            return "0";
        }
        return new String(stack, idx, digits - idx);
    }
}
