package data_structure.arraystring.windows;

public class StringSlidingWindow {

    // Minimum Size Subarray Sum
    // Given an array of positive integers nums and a positive integer target
    // Return the minimal length of a "contiguous subarray" [nums l, nums l+1, ..., nums r-1, nums r]
    // The sum is greater than or equal to target. If there is no such subarray, return 0
    // nums = [2,3,1,2,4,3], target = 7  -> [4,3] -> 2
    //
    // O(2n)=O(n) O(1)
    // 最终可能left和right从左到右各自移动一遍
    public int minSubArrayLen(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        // 在中间while迭代的时候，不能返回，需要判断到数组的最后
        while (right < nums.length) {
            sum += nums[right];
            // 如果累计的值超过了目标数值，则需要从左端进行循环
            while (sum >= target) {
                // 通过[j-i]计算有效最小窗口的宽度，注意长度 +1
                minLength = Math.min(minLength, right - left + 1);

                // 从头left开始减少，逐渐缩小区间
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Longest Substring
    // Given a string s, find the length of the longest substring without repeating characters
    // s consists of English letters, digits, symbols and spaces.
    // s = "abcabcbb" -> "abc" -> 3
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int result = 0;

        // TODO. 将字符串中的char映射到int值，通过数组来统计指定char出现的次数
        // int[256] for ASCII of 8 bits
        int[] chars = new int[128];
        while (right < s.length()) {
            char r = s.charAt(right);
            chars[r]++;

            // 如果指定的数组位置的值大于1, 说明之前有计算过，左边的lift坐标需要往右边移动
            while (chars[r] > 1) {
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            // 在while循环之后，统计当前截取位置的距离长度
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }

    // TODO: 滑动窗口算法的优化版本, left坐标位置不需要逐步移动，而是一步跳跃式的移动到指定位置
    // "mabcdeafbdgcbb"
    // a: 6    字符始终记录出现的最后一个位置的index，每次更新位置     chars[r] = right;
    // b: 12   当发现字符出现，取出它的位置，并从它的下一个位置开始截断  left = index + 1;
    // c: 11   同时每一步更新结果                                 res = Math.max(,)
    // d: 9
    // ...
    public int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int right = 0;
        int res = 0;
        // TODO: 这个字符数组中存放的是，某个字符在字符串中出现的最后位置
        //  如果出现重复，则它前面的位置全部跳过，避免一步一步滑动
        Integer[] chars = new Integer[128]; // char -> int 根据ASCII码值表对应十进制的值，作为数组的下标
        while (right < s.length()) {
            char r = s.charAt(right);
            Integer charLastIndex = chars[r]; // 使用引用类型来做null空的判断，避免之间判断index的范围
            if (charLastIndex != null && left <= charLastIndex && charLastIndex < right) {
                left = charLastIndex + 1;
            }
            res = Math.max(res, right - left + 1); // 需要在移动过程中记录结果
            chars[r] = right;
            right++;
        }
        return res;
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
            if ((n - (k * (k - 1) / 2)) % k == 0) count++;
        }
        return count;
    }
}
