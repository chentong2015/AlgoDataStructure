package com.leetcode.basic_theory_introduction.hash_table;

import java.util.*;

public class LearnHashTable1 {

    // TODO: HashSet<,>实战
    //       一般用在判断重复元素 & 记录出现过的数据, 但是会开辟额外的空间复杂度 !!
    //       牺牲掉的空间复杂度完全值得，因为在大多时候，在循环的过程中间便出会出结果
    // Happy Number
    // Starting with any positive integer, replace the number by the sum of the squares of its digits
    // Input: n = 19  -> true
    // 1^2 + 9^2 = 82 -> 8^2 + 2^2 = 68 -> 6^2 + 8^2 = 100 -> 1^2 + 0^2 + 0^2 = 1 直到结果迭代到1
    public boolean isHappy(int n) {
        // 正确理解：1. 需要一个额外的存储结构来判断计算的结果是否出现过，如果出现就表示循环，无解
        //            O(?) 时间复杂度没有统一的逻辑规律!! O(n)
        HashSet<Integer> set = new HashSet<>();
        int sum = calculateSum(n);
        set.add(sum);
        while (sum != 1) {
            sum = calculateSum(sum);
            if (set.contains(sum)) return false;
            set.add(sum);
        }
        return true;
    }

    private int calculateSum(int n) {
        int sum = 0;
        while (n / 10 > 0) {
            sum += (n % 10) * (n % 10);
            n = n / 10;
        }
        sum += n * n;
        return sum;
    }

    // TODO: HashMap<>实战
    //       需要快速判断特征条件的Key值
    //       具备Map准确映射关系的特征(比如index坐标位置)
    // Two Sum
    // Given an array of integers nums and an integer target
    // Return indices of the two numbers such that they add up to target
    // nums = [3,2,4], target = 6 -> [1,2]  数组中的元素只能使用一次，有且仅有一个解
    // nums = [3,3],   target = 6 -> [0,1]
    public int[] twoSum(int[] nums, int target) {
        // 正确理解：使用HashMap<value, index>存储值和index位置，判断key和target-key同时存在，One Pass一遍读取 !!
        //         O(n) O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution");
    }

    // Isomorphic Strings
    // All occurrences of a character must be replaced with another character while preserving the order of characters
    // No two characters may map to the same character, but a character may map to itself 两个字符不能映射到同一个字符
    // s = "paper", t = "title" -> true
    // s = "paper", t = "titte" -> false  p -> t, e -> t 后面的映射不成立
    public boolean isIsomorphic(String s, String t) {
        // 测试理解：1. 根据每个位置上的字符map映射关系，判断是否能够完全s映射到t ==> 有必要借助别的数据结构
        //            O(n) O(n)
        if (s.length() != t.length()) return false;
        HashMap<Character, Character> map = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            char mapChar = t.charAt(index);
            if (map.containsKey(c)) {
                if (map.get(c) != mapChar) return false;
            } else {
                if (map.containsValue(mapChar)) return false;  // 新的key键不能映射到前面已经映射过的value
                map.put(c, mapChar);
            }
        }
        return true;
    }

    // Minimum Index Sum of Two Lists
    // Find out their common interest with the least list index sum
    // 两份选择菜单，各自的内容不重复且具有优先级，挑选出一个共同的选择，并且这个选择是对于凉两人来说是最优的(总体排名最前)
    // 假设一定存在解，也就是具有共同的选择 !!
    // list1 = ["Shogun","Tapioca Express","Burger King","KFC"],
    // list2 = ["KFC","Shogun","Burger King","Test"]
    //            0+1=1   -2               2+2=4         3+0=2    改变初始位置标记的坐标，如果同时出现，则计算SumIndex，否则数字是负数
    // list1 = ["Shogun","Tapioca Express","Burger King","KFC"],
    // list2 = ["KFC","Burger King","Tapioca Express","Shogun"]
    //       -> Output: ["KFC","Burger King","Tapioca Express","Shogun"] 输出结果中的优先级都是一致的，都是可以选择的
    public String[] findRestaurant(String[] list1, String[] list2) {
        // 测试理解：1. 只需要统计出所有共同餐厅所对应的优先级，取其中最小的数据
        //            O(m+n) O(m*l) m表示list1列表中的数目, l表示字符串的平均长度
        int minSumIndex = Integer.MIN_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        for (int index = 0; index < list1.length; index++) {
            map.put(list1[index], -index - 1);
        }
        for (int index = 0; index < list2.length; index++) {
            if (map.containsKey(list2[index])) {
                int sumIndex = -map.get(list2[index]) - 1 + index;
                map.put(list2[index], sumIndex);
                minSumIndex = Math.min(minSumIndex, sumIndex);
            }
        }
        String[] results = new String[list1.length];
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == minSumIndex) {
                results[count++] = entry.getKey();
            }
        }
        return Arrays.copyOfRange(results, 0, count);
    }

    // TODO: 同时借助HashMap<>, ArrayList<>, 边读取边判断特征, 取最后的结果
    // 正确理解：1. 借助ArrayList<>，在读取list2中的数据时，便开始判断并提取最后的结果
    //            在读取list2中数据的时候，如果index2明显的超过了minSumIndex，则往后的数据可以不用判断(和一定更大)
    public String[] findRestaurant2(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> res = new ArrayList<>();         // 动态列表的优势：长度是变化的，同时可以.clear()方便清空数据 !!
        int minSumIndex = Integer.MAX_VALUE;
        for (int j = 0; j < list2.length && j <= minSumIndex; j++) {
            if (map.containsKey(list2[j])) {
                int sumIndex = j + map.get(list2[j]);
                if (sumIndex < minSumIndex) {         // 添加第一次最小值的结果
                    res.clear();
                    res.add(list2[j]);
                    minSumIndex = sumIndex;
                } else if (sumIndex == minSumIndex) { // 或者添加相同结果的一组值 !!
                    res.add(list2[j]);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }
}
