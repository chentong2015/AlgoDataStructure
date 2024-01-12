package data_structure.collections.stack.stack_compare;

import java.util.Stack;

public class BaseStackCompare {

    // Next Greater Element II
    // Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
    // return the next greater number for every element in nums
    // The next greater number of a number x is the first greater number to its traversing-order next in the array
    // nums = [1,2,1]     --> [2,-1,2]     必须使用结果的数组的数据结构，否则会导致值的覆盖
    // nums = [1,2,3,4,3] --> [2,3,4,-1,4] 判断每一个位置的后面第一个比该位置的值大的数据，没有则返回-1
    // 1 <= nums.length <= 10^4
    // -10^9 <= nums[i] <= 10^9
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int length = nums.length;
        int[] results = new int[length];
        for (int i = 0; i < length; i++) {
            boolean hasFound = false;
            int j = (i == length - 1) ? 0 : i + 1;
            while (j != i) {
                if (nums[j] > nums[i]) {
                    results[i] = nums[j];
                    hasFound = true;
                    break;
                }
                j++;
                if (j == length) j = 0;
            }
            if (!hasFound) results[i] = -1;
        }
        return results;
    }

    // TODO: 使用特殊栈(数组结构)，作为判断的临时存储结构，优化时间复杂度
    // O(2n)=O(n) O(2n)=O(n)
    public int[] nextGreaterElementsStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length); // 存储index坐标
        }
        return res;
    }
}
