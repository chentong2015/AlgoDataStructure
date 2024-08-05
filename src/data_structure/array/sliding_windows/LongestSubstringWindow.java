package data_structure.array.sliding_windows;

// TODO. 最长的不包含重复字符的Substring
public class LongestSubstringWindow {

    // Longest Substring
    // Given a string s, find the length of longest substring without repeating characters
    // s consists of English letters, digits, symbols and spaces.
    // s = "abcabcbb" -> "abc" -> 3
    //
    // O(N)   N is length of chars
    // O(1+N) N is number of chars
    public int findLongestSubstring(String str) {
        int[] counts = new int[128]; // All ASCII letters
        int leftIndex = 0;
        int maxLength = 0;
        char[] chars = str.toCharArray();

        // 必须要使用index位置来计算长度
        for (int index=0; index < chars.length; index++) {

            // 滑动窗口：从头开始将统计移除，直到没有重复的统计
            while (counts[chars[index] - ' '] > 0) {
                counts[chars[leftIndex] - ' ']--;
                leftIndex++;
            }
            counts[chars[index] - ' ']++;
            maxLength = Math.max(maxLength, index - leftIndex + 1);
        }
        return maxLength;
    }

    // TODO: 滑动窗口算法的优化版本: left坐标位置不需逐步移动，而是一步跳跃到指定位置
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
