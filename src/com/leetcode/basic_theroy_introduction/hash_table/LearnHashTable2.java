package com.leetcode.basic_theroy_introduction.hash_table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// TODO: Hash Table如何使用key键值
// 1. 通过key键值进行聚和，分组，统计
// 2. 通过key键值定位index位置
public class LearnHashTable2 {

    // Intersection of Two Arrays
    // 题型1：两个数组排序好，只需要找出都包含的公共元素即可           ==> 使用两个index坐标，顺次遍历即可 / 不需要额外的数据结构

    // Intersection of Two Arrays II
    // 题型2: 如果数组是排序好的，则可以不需要使用Hash Table
    //       如果数组nums1数量小于nums2，则将数量小的存储到Hash表中  ==> 减少空间复杂度
    //       如果nums2的数据存储在硬盘中，无法全部加载到内存中 ?      ==> RandomAccessFile 利用偏移量加载文件片段，而不是全部

    // 题型3: 两个数组乱序，找出公共的元素，并且保证出现的次数是一致的   ==> 使用Hash Table对数据进行聚和，然后直接判断key的存在
    // nums1 = [4,9,5], nums2 = [9,4,9,8,4] -> [4,9]
    public int[] intersect(int[] nums1, int[] nums2) {
        // 测试理解：1. 增加一定的空间复杂度，最大限度的减少时间复杂度  O(n+m) O(min(n, m))
        //            不需要使用额外的map，也不需要使用ArrayList<>来存储结果 !!
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        int index = 0; // 读取的是nums1数组的数据，所以这里的下标对于nums1来说一定不会越界 !!
        for (int num : nums2) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                if (count > 0) {
                    nums1[index++] = num;
                    map.put(num, count - 1);
                }
            }
        }
        return Arrays.copyOf(nums1, index);
    }

    // Contains Duplicate II 
    // 在原始判断是否有重复值的基础上增加一定的约束条件
    // Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array
    // nums[i] == nums[j] and abs(i - j) <= k
    // nums = [1,2,3,1], k = 3 -> true 两个相同值1的位置间隔为3
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 测试理解：1. 由于在读每个位置上面的数据时，需要知到前面或者后面的数据，因此考虑使用别的数据结构来存储
        //            最优复杂度 O(n) O(n)
        if (nums == null || nums.length == 0) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            if (map.containsKey(nums[index])) {
                int oldIndex = map.get(nums[index]);
                if (index - oldIndex <= k) return true;
            }
            map.put(nums[index], index);
        }
        return false;
    }

    // TODO: 使用Set<>, 取消对值的"下标位置"的存储, 取消对无效数据的存储(由于区间约束) !!
    //       等效可以使用Queue队列，先进先出，再判断是否存在
    // 正确理解：1. k参数决定了，每次只需要关注[区间长度为k]范围中的数据 ==> 区间隐藏在Set<>存储数量中, 如果在存储的区间中包含key, 则为true
    //            O(n) 大多数情况比上面时间复杂度更加优化
    //            O(k) 很明显的减少空间复杂度
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int index = 0; index < nums.length; index++) {
            if (index > k) {
                set.remove(nums[index - k - 1]); // 移除的是前面指定位置的值，不能根据左边来移除
            }
            if (!set.add(nums[index])) { // 在添加的时候，如果已经存在，则返回false ==> 在添加时候同时做判断
                return true;
            }
        }
        return false;
    }
}
