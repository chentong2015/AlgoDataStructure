package data_structure.arraystring.anagrams;

public class SubStringAnagrams1 {

    // TODO. KO 废弃算法
    //  本质上获取SubString时每次只变化两个字符，使用StringBuilder会遍历所有字符
    // 判断一个字符串是否包含另一个字符串(字符)的所有排列
    // Contain Permutation of SubString
    // s1 and s2 consist of lowercase English letters
    // 1 <= s1.length, s2.length <= 10^4
    //
    // str1="hskoebacdh", str2 = "abc", -> true
    //
    // O(N*M) 每循环一个字符都需要循环str2.length()的次数来生成key判断
    // O(M)   开辟了空间来存储Str2长度的数据，造成空间复杂度
    public static boolean containsPermutationOfSubstring(String str1, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index=0; index < str2.length(); index++) {
            stringBuilder.append(str1.charAt(index));
        }

        int right = str2.length() - 1;
        String str2Key = generateAnagramsKey(str2);
        while (right < str1.length()) {
            String str1Key = generateAnagramsKey(stringBuilder.toString());
            if (str1Key.equals(str2Key)) {
                return true;
            }

            right++;
            if (right == str1.length()) {
                return false;
            }
            stringBuilder.deleteCharAt(0);
            stringBuilder.append(str1.charAt(right));
        }
        return false;
    }

    // 每次生成key时都遍历str2.length()长度的字符并且将char[]转换成String，造成时间复杂度
    private static String generateAnagramsKey(String str) {
        char[] chars = new char[26]; // only 26 types of letters
        for (char c: str.toCharArray()) {
            chars[c - 'a']++;
        }
        return new String(chars);
    }
}
