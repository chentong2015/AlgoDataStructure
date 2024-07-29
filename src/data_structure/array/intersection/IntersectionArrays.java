package data_structure.array.intersection;

import java.util.*;

// 对于任意的两组数据，均有适配的算法来找出它们的交集
public class IntersectionArrays {

    public static void main(String[] args) {
        List<Integer> results = intersect(List.of(1,3,3,4,5), List.of(2,3,4,7,9,10));
        for (int item: results) {
            System.out.println(item);
        }

        int[] nums1 = { 1,3,4,5 };
        int[] nums2 = { 2,4,7,9,3 };
        List<Integer> results2 = intersect2(nums1, nums2);
        for (int item: results2) {
            System.out.println(item);
        }
    }

    // TODO. Intersection of Two Lists/Arrays 排序 + 交集元素不重复
    // 1. 使用两个index坐标，顺次遍历即可
    // O(N+M) O(?) 需要遍历每一个位置上的数据，空间复杂度为交集的大小
    public static List<Integer> intersect(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        if (list1 == null || list2 == null || list1.isEmpty() || list2.isEmpty()) {
            return result;
        }
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            if (list1.get(index1) == list2.get(index2)) {
                result.add(list1.get(index1));
                index1++;
                index2++; // 同时往后移动，同一个交集数据只会记录一遍
            } else if (list1.get(index1) < list2.get(index2)) {
                index1++;
            } else {
                index2++;
            }
        }
        return result;
    }

    // TODO. Intersection of Two Arrays II 乱序 + 交集元素不重复
    // 1. 先排序后处理，不使用任何空间
    // 2. 通过HashSet表存储快速的判断key的存在，以空间换时间
    // O(N+M) O(N+?)
    public static List<Integer> intersect2(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        int[] numTemp = nums1;
        if (nums1.length > nums2.length) {
            nums1 = nums2;
            nums2 = numTemp;
        }

        // 将数量小的array存储到Hash表中，减少空间复杂度
        for (int item: nums1) {
            set.add(item);
        }
        for (int item: nums2) {
            if (set.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // TODO. Intersection of Two Arrays III 乱序 + 交集元素可能重复 > 返回不重复的结果
    // 1. 先排序后处理，不使用任何空间
    // 2. 使用HashMap统计数据量，判断key存在时减少统计量，以空间换取时间
    // nums1 = [4,9,5],
    // nums2 = [9,4,9,8,4] -> [4,9]
    public int[] intersect3(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        int index = 0;
        for (int num : nums2) {
            if (map.containsKey(num)) {
                int count = map.get(num);
                // 判断统计量非0才作为交集元素
                if (count > 0) {
                    // 读取的是nums1数组数据，下标对于nums1不会越界
                    nums1[index++] = num;
                    map.put(num, count - 1);
                }
            }
        }
        // 直接使用现有的数组作为最终的结果，不需要额外开辟新的结果数组
        return Arrays.copyOf(nums1, index);
    }
}
