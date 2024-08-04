package data_structure.array.sliding_windows;

// TODO. 最长的不包含重复字符的Substring
public class LongestSubstringWindow {

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
        // TODO: 这个字符数组中存放的是，某个字符在字符串中出现的index最后位置
        //  如果出现重复，则它前面的位置全部跳过，避免一步一步滑动
        Integer[] chars = new Integer[128];
        while (right < s.length()) {
            char r = s.charAt(right);
            Integer charLastIndex = chars[r];

            // 使用引用类型来做null空的判断，避免之间判断index的范围
            if (charLastIndex != null && left <= charLastIndex && charLastIndex < right) {
                left = charLastIndex + 1;
            }
            res = Math.max(res, right - left + 1);
            chars[r] = right;
            right++;
        }
        return res;
    }
}
