package data_structure.array.item_duplicate;

import java.util.ArrayList;
import java.util.List;

// 找出任意一组特殊数据中是否存在重复元素
public class FoundDuplicateItem {

    // TODO. 对于特殊的一组数据，可以考虑使用数学计算
    // Find the Duplicate Number II
    // n(n>=1)个值在[1,n+1]数组中，数字乱序，连续分布，其中有且仅有一个值重复
    public int findDuplicate(int[] nums) {
        int sum = 0;
        int length = nums.length;
        int total = ((1 + length) * length) / 2;
        for (int num : nums) {
            sum += num;
        }
        return length - (total - sum);
    }

    // TODO：金典龟兔赛跑: 有且仅有一个重复值，可以重复多次
    // Find the Duplicate Number
    // Given an array of integers nums containing n + 1 integers
    // where each integer is in the range [1, n] inclusive
    public int findDuplicate2(int[] nums) {
        // 正确理解：1. 联想到1-n数字和数组的下标index有关，通过下标转换造成Linked List
        //            > 为什么会有Cycle出现 ? 通过f(x)=nums[x],x=nums[x]构建的Linked List链表可以看到重复的值是循环入口点
        //            > 约束条件：数组中值的范围必须是[1,n],不能是[0,n-1],否则第一个位置如果是0,则算法无法往后查找
        //            O(n) O(1)
        int tortoise = nums[0];        // 根据位置的值确定下一个移动的位置坐标
        int hare = nums[0];
        do {
            hare = nums[nums[hare]];
            tortoise = nums[tortoise]; // 一个超前一倍前进，当两者相遇说明(跑前面的已经在循环中将后者套圈了，此时为第一次相遇的点)
        } while (tortoise != hare);    // 要相遇必然出现圈，并且hare必须在圈内

        tortoise = nums[0];            // 将其中一个退回到起使点，然后一步一步开始
        while (tortoise != hare) {     // 直到两者第一次遇到，则为循环圈的入口点，重复位置的开始点 !!
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return hare;
    }

    // TODO: 找特殊Array(元素值不超过下标)中的重复元素，将值和index坐标进行关联计算
    // 不需要使用排序或者对数据进行存储
    // Find All Duplicates in an Array
    // All the integers of nums are in the range [1, n] and each integer appears once or twice
    // Return an array of all the integers that appears twice
    //
    // [4,3,2,7,8,2,3,1] -> [2,3]
    // [4,3,2,7,8,2,3,1]
    // [4,3,2,-7,8,2,3,1]  标记负数表示能够通过某个位置上的值"定位找到"这个位置上面来
    // [4,3,-2,7,8,2,3,1]  如果之前已经"被找到过"，那个这个数字就是第二次出现
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            // 计算value映射到的index坐标
            int findIndex = Math.abs(nums[index]) - 1;
            if (nums[findIndex] < 0) {
                result.add(findIndex + 1);
            }
            // 将找的坐标位置的数据改为负数。这个负数可以重复利用于计算它对于的坐标
            nums[findIndex] = -nums[findIndex];
        }
        return result;
    }
}
