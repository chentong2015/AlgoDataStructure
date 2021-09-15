package interview;

import java.util.HashMap;

public class RealInterview {

    // Alibaba Interview Question
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 1. 排序之后判断每个元素所占的长度区间  O(nlog(n)) O(1)
    // 2. 使用HashMap<>来存储每个元素的频率 O(n)       O(n)
    // 3. 使用两遍遍历数组：第一遍找出其中出现次数最多的数字，第一遍统计这个数字出现的次数是否超过一半 !!
    public int findMostFrequencyElement(int[] nums) {
        // TODO: 以下不是最优解
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if (count > nums.length / 2) return num;
            map.put(num, count);
        }
        return 0;
    }

    // Tencent Interview Question
    // https://www.nowcoder.com/ta/exam-qq
    // Check Contains subString 确认一个字符串中是否包含另一个字符串(可以乱序排列字符)
    // str1 = "abc", str2="hskoebacdh" -> true
    // 1. 将str1排序成mapKey，判断str2中是否有这key所在的区间
    // 2. TODO: 将str1的每一个字符统计到数组中，依次截取str2指定长度的子字符串，进行比较判断  ==> 假设只有26个小写字母
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
                    // 删除截取的开头的字符，然后比较subString
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

    // Microsoft Interview Question 01
    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // Return the smallest index (left, right) 如果没有这层限制，则使用DP
    public static int findClosestSumIndex(int[] nums, int m) {
        // 1. 使用Sliding Windows框出一部分数据，判断特征
        // if (nums == null || nums.length == 0) return null;
        // MyPair pair = new MyPair();
        int checkSum = 0;
        int sum = 0;
        int left = 0;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            while (sum > m) {
                sum -= nums[left];
                left++;
            }
            if (sum > checkSum) {
                checkSum = sum;
                // pair.left = left;
                // pair.right = index;
            }
        }
        // if (checkSum == 0) return null;
        return 0;
    }

    // Microsoft Interview Question 02
    // Excel Formula Validation
    // input: array AA -> ZZ, 00 -> 99, cell data is numeric
    // AA00: "10"
    // AB00: "20"
    // AA01: AA00 + AB00 = "30"
    // AB01: AA01(AA00 + AB00) * AB00 + AC00(0) ? 公式如何解析
    private HashMap<String, String> map = new HashMap<>();

    private boolean canEvaluated(String[][] cells) {
        for (String[] cell : cells) {
            if (!isCellFormula(cell[1])) map.put(cell[0], cell[1]);
        }
        for (String[] cell : cells) {
            if (!isValidCellValue(cell[1])) return false;
        }
        return true;
    }

    private boolean isCellFormula(String cellValue) {
        return cellValue.toLowerCase().charAt(0) - 'A' > 0;
    }

    private boolean isValidCellValue(String cellFormula) {
        if (isCellFormula(cellFormula)) {
        }
        return true;
    }

    // Autodesk Interview Question
    // Intersection of Two Arrays
    public int[] intersect(int[] nums1, int[] nums2) {
        return null;
    }
}
