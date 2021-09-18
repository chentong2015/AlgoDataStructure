package interview.tencent;

// Tencent Interview Question
// Permutation in String 判断一个字符串是否包含另一个字符串的所有排列
// str1 = "abc", str2="hskoebacdh" -> true
// 1 <= s1.length, s2.length <= 104
// s1 and s2 consist of lowercase English letters
public class TencentQuestion {

    // 测试理解 1: 将str1排序成mapKey，判断str2中是否有这key所在的区间
    //           写法复杂，由于排序造成的复杂度过高
    public boolean checkContains(String str1, String str2) {
        // 先做几个常见的特殊情况的判断
        int[] counts = new int[26];
        for (char c : str1.toCharArray()) {
            counts[c - 'a']++;
        }
        StringBuilder subString = new StringBuilder();
        int countChars = 0;
        for (int index = 0; index < str2.length(); index++) {
            int findIndex = str2.charAt(index) - 'a';
            if (counts[findIndex] > 0) {
                countChars++;
                subString.append(str2.charAt(index));
                if (countChars == str1.length()) {
                    // 判断提取的subString是否符合counts数组统计出来的值
                } else if (countChars > str1.length()) {
                    // 删除截取的开头字符，然后比较subString
                    subString.deleteCharAt(0);
                }
            } else {
                countChars = 0;
                // 清空已经提取的子字符串，重新截取
                if (subString.length() > 0) {
                    subString = new StringBuilder();
                }
            }
        }
        return false;
    }

    // TODO: 涉及到截取，首尾增删，不要使用StringBuilder在过渡String字符中的子字符串 !!
    //       使用数组可以根据char字符指定定位index，进行统计操作
    // 解法 1. Array : 将str1的每一个字符统计到数组中，依次截取str2指定长度的子字符串，比较两个统计出来的数组
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map))
                return true;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }

    // 解法 2. Sliding Window
    // https://leetcode.com/problems/permutation-in-string/solution/
    public boolean checkInclusionPlus(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (s1map[i] == s2map[i]) count++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26) return true;
            s2map[r]++;
            if (s2map[r] == s1map[r]) count++;
            else if (s2map[r] == s1map[r] + 1) count--;
            s2map[l]--;
            if (s2map[l] == s1map[l]) count++;
            else if (s2map[l] == s1map[l] - 1) count--;
        }
        return count == 26;
    }
}
