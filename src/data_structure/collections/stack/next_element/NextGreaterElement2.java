package data_structure.collections.stack.next_element;

import java.util.Stack;

public class NextGreaterElement2 {

    // Next Greater Element II
    // Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
    // return the next greater number for every element in nums
    // The next greater number of a number x is the first greater number to its traversing-order next in the array
    // 判断每一个位置的后面第一个比该位置的值大的数据，没有则返回-1
    // nums = [1,2,1]     -> [2,-1,2]
    // nums = [1,2,3,4,3] -> [2,3,4,-1,4]
    // 1 <= nums.length <= 10^4
    // -10^9 <= nums[i] <= 10^9
    //
    // 5 4 3 2 1 -> -1,5,4,3,2 极限情况下的时间复杂度(The worst case) O(n*n)
    public int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        int[] results = new int[length];
        for (int i = 0; i < length; i++) {
            boolean hasFound = false;
            int j = (i == length - 1) ? 0 : i + 1; // next element of index i
            while (j != i) {
                if (nums[j] > nums[i]) {
                    results[i] = nums[j];
                    hasFound = true;
                    break; // 找到之后赋值，需要跳出查找的循环
                }
                j++;
                if (j == length) {
                    j = 0;
                }
            }
            if (!hasFound) {
                results[i] = -1;
            }
        }
        return results;
    }

    // TODO. 使用Stack栈来存储index坐标，然后通过坐标直接定位数组中的数据
    // O(2n)=O(n) O(2n)=O(n)
    public int[] nextGreaterElementsStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int size = nums.length;
        int[] res = new int[size];
        for (int i = size + (size-1); i >= 0; --i) {
            int numIndex = i % size; // 原始数组的index坐标，从后往前遍历

            // 从遍历过程中存储的之后的值中找到比当前位置大的值
            while (!stack.empty() && nums[stack.peek()] <= nums[numIndex]) {
                stack.pop();
            }
            // Stack中的数据要么被找完，要么存储更大的值
            res[numIndex] = stack.empty() ? -1 : nums[stack.peek()];
            // 最后存储原始数组的坐标，用于判断它是否比它的前面值大
            stack.push(numIndex);
        }
        return res;
    }
}
