package data_structure.arraystring.diff_string;

// TODO. 使用最佳的复杂度比较两个字符串之间的字符差异
// 通过字符统计+抵消来判断，无需遍历两次完整的CharArray ！
// StringA = 00000200001000010001..
// StringB = 00000100000000-100000..
public class DiffStringQuestions {

    public static void main(String[] args) {
        // Test Diff one char
        System.out.println(stringsDiffByOneChar("leet", "leat"));
        System.out.println(stringsDiffByOneChar("leet", "lead"));

        // Test Diff two chars
        System.out.println(stringsDiffByOneChar("chen", "cheh"));
        System.out.println(stringsDiffByOneChar("chen", "chch"));
    }

    // TODO. 快速判断两个字符串是否相差一个字符 > 扩展到相差n个字符?
    // 由于两个字符串必须长度一致, 因此哪个字符串先统计无关紧要
    private static boolean stringsDiffByOneChar(String strA, String strB) {
        if (strA.length() != strB.length()) {
            return false;
        }
        int[] counts = new int[26];
        for (char c: strA.toCharArray()) {
            counts[c - 'a']++;
        }

        int countDiffChar = 0;
        for (char c: strB.toCharArray()) {
            // 当位置值为0: 出现新的字符/或者之前记录的字符已经被抵消了
            // 当位置小于0: 说明是之前出现的新的字符
            if (counts[c - 'a'] <= 0) {
                countDiffChar++;
            }

            // strB的新字符(差异的字符)不能超过一个
            // TODO. 修改判断条件精确控制差异的字符数目
            if (countDiffChar > 1) {
                return false;
            }
            counts[c - 'a']--;
        }
        return true;
    }

    // TODO. 判断长字符串是否包含短字符串的所有字符(字符不重复使用)
    // Ransom Note
    // Given two strings ransomNote and magazine,
    // return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
    // Each letter in magazine can only be used once in ransomNote.
    //
    // 1 <= ransomNote.length, magazine.length <= 105
    // ransomNote and magazine consist of lowercase English letters.
    //
    // O(n + m?) 时间复杂度主要取决于magazine的字符长度
    // O(1)      只开辟了常量的内存空间
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // TODO. 优先统计长字符串的所有字符，这里O(n)的遍历比不可少
        int[] counts = new int[26];
        for (char c : magazine.toCharArray()) {
            counts[c - 'a']++;
        }

        // 在遍历短字符串的过程中可以判断是否返回，无需遍历到最后
        for (char c : ransomNote.toCharArray()) {
            if (counts[c - 'a'] == 0) {
                return false;
            }
            counts[c - 'a']--;
        }
        return true;
    }
}
