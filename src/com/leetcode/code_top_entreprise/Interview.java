package com.leetcode.code_top_entreprise;

public class Interview {

    // Murex在线测试题
    // 道路连接的问题 ==> 找出所有可能的通路，有且只有一条可能的通路
    // (0, 0)
    // A   E   A   E "3 * 3"
    // A   A   A   E
    // A   A   E   E
    // E   A   A   D
    public void testPath() {

    }

    // Twitter算法面试题: 计算每个位置的柱子上方还能储水的量，和该柱子的前后柱子没有关系，只和它前后元素中的最大高度柱子有关系 !!
    // 解法1: 暴力破解
    // 解法2: Dynamic programming
    // 解法3: Using stack
    // 解法4: Two pointers
    public static int testSaveWater(int[] arr) {
        // 测试理解：1. 对于任何一个柱子，如果它"左边位置的最大值"和"右边位置的最大值"都比该位置的高度高，则柱子上方还有空间存水 !!
        //            O(n*n) 由于每个位置都会前后遍历，所以复杂度不可取 O(1)

        // 正确理解: 1. 通过左右两个标识完成左右最大值的更新，依次往中间移动，一次遍历 O(n) O(1)
        if (arr == null || arr.length < 3) return 0;
        int sum = 0;
        int left = 0;
        int right = arr.length - 1;
        int largestLeft = arr[left];
        int largestRight = arr[right];
        while (left < right) {
            if (largestLeft > largestRight) {        // 如果右边的最大高度更低, 则往右标识往左移动, 保证整个左边的元素的最大值不变 !!
                sum += largestRight - arr[right--];  // 此处的计算不可能为负数
                largestRight = Math.max(arr[right], largestRight);
            } else {
                sum += largestLeft - arr[left++];
                largestLeft = Math.max(arr[left], largestLeft);
            }
        }
        return sum;
    }
}
