package work_interview.criteo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 输入一组数字，输出所有能够的得出来的字符串
// T9 mode
// input: sequence_of_digits, not empty
// output: list of strings
//
//  1     2     3
//        abc   def
//  4     5     6
//  ghi   jkl   mno
//  7     8     9
//  pqrs  tuv   wxyz
//
// Dict = ["sage", "rage", "raid", "hello", "morning", "blabla" ...] : L : 2.10^5
// 7243: "sage", "rage", "raid": N
// List of sequence of digits: S
// Time: O(L + row*col + 1)
// Space: O(S + L) S is sum of chars in dict

// TODO. 分析Mapping的核心逻辑 + 分析复杂度不彻底
// https://leetcode.com/discuss/interview-question/system-design/685338/Microsoft-or-Onsite-or-Design-the-T9-predictive-text-algorithm-and-system
public class Model9Mapping {

    public static void main(String[] args) {
        List<String> dict = List.of("sage", "rage", "raid", "hello", "morning", "blabla");
        List<String> digits = List.of("7243", "43556");
        getStringByDigits(digits, dict);
    }

    private static void getStringByDigits(List<String> digits, List<String> dict) {
        HashMap<String, List<Integer>> result = buildMap(dict);
        for(String str: digits) {
            List<Integer> listIndex = result.getOrDefault(str, new ArrayList<>());
            for(int index: listIndex) {
                System.out.println(dict.get(index));
            }
        }
    }

    private static HashMap<String, List<Integer>> buildMap(List<String> dict) {
        HashMap<String, List<Integer>> map = new HashMap<>();
        for(int index=0; index < dict.size(); index++) {
            StringBuilder stringBuilder = new StringBuilder();
            for(char c: dict.get(index).toCharArray()) {
                stringBuilder.append(getDigitByChar(c));
            }

            String key = stringBuilder.toString();
            if(map.containsKey(key)) {
                List<Integer> list = map.get(key);
                list.add(index);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(index);
                map.put(stringBuilder.toString(), newList);
            }
        }
        return map;
    }

    // 以下的Mapping方法不造成时间复杂度
    private static int getDigitByChar(char character) {
        return switch (character) {
            case 'a', 'b', 'c' -> 2;
            case 'd', 'e', 'f' -> 3;
            case 'g', 'h', 'i' -> 4;
            case 'j', 'k', 'l' -> 5;
            case 'm', 'n', 'o' -> 6;
            case 'p', 'q', 'r', 's' -> 7;
            case 't', 'u', 'v' -> 8;
            case 'w', 'x', 'y', 'z' -> 9;
            case ' ' -> 0;
            default -> -1;
        };
    }

    private static String getStringByDigit(int digit) {
        return switch (digit) {
            case 2 -> "abc";
            case 3 -> "def";
            case 4 -> "ghi";
            case 5 -> "jkl";
            case 6 -> "mno";
            case 7 -> "pqrs";
            case 8 -> "tuv";
            case 9 -> "wxyz";
            case 0 -> " ";
            default -> "";
        };
    }
}
