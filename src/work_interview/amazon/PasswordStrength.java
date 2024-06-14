package work_interview.amazon;

// Find the password strength.
// For each substring of the password which contains at least one vowel and one consonant, its strength goes up by 1.
// vowels={'a', 'e', 'i', 'o', 'u'}, and rest of letters are all consonant.
// thisisbeautiful -> this, is, be, aut, if, ul -> 6
// hackerrank -> hack, er, rank -> 3
// aeiou -> 0
public class PasswordStrength {

    public static void main(String[] args) {
        System.out.println(findPasswordStrength("thisisbeautiful"));
        System.out.println(findPasswordStrength("hackerrank"));
        System.out.println(findPasswordStrength("aeiou"));
    }

    public static int findPasswordStrength(String password) {
        int strength = 0;
        boolean hasFoundVowel = false;
        boolean hasFoundConsonant = false;
        for (char c: password.toCharArray()) {
            if (isVowel(c)) {
                hasFoundVowel = true;
            } else {
                hasFoundConsonant = true;
            }
            if(hasFoundVowel && hasFoundConsonant) {
                // 判断符合条件之后，重新开始下一轮的寻找
                strength++;
                hasFoundVowel = false;
                hasFoundConsonant = false;
            }
        }
        return strength;
    }

    // 除了直接判断是否找到以外，还可统计找到的次数，避免true和false切换
    public static int findPasswordStrength2(String password) {
        int vowel = 0;
        int consonant = 0;
        int res = 0;
        for (char c : password.toCharArray()) {
            if (isVowel(c)) {
                vowel++;
            } else {
                consonant++;
            }
            if (vowel >= 1 && consonant >= 1) {
                res++;
                vowel = 0;
                consonant = 0;
            }
        }
        return res;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c== 'u';
    }
}
