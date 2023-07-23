package interview.byte_dance;

public class ByteQuestion {

    // 计算每个位置的柱子上方还能储水的量，和该柱子的前后柱子没有关系，只和它前后元素中的最大高度柱子有关系
    // 对于任何一个柱子，如果它"左边位置的最大值"和"右边位置的最大值"都比该位置的高度高，则柱子上方还有空间存水 !!
    public static int testSaveWater(int[] arr) {
        // 正确理解: 通过左右两个标识完成左右最大值的更新，依次往中间移动，线性时间复杂度
        if (arr == null || arr.length < 3) return 0;
        
        int sum = 0;
        int left = 0;
        int right = arr.length - 1;
        int largestLeft = arr[left];
        int largestRight = arr[right];

        while (left < right) {
            // 如果右边的最大高度更低, 则往右标识往左移动, 保证整个左边的元素的最大值不变 !!
            if (largestLeft > largestRight) {
                sum += largestRight - arr[right];  // 此处的计算不可能为负数
                right--;                           // 往左边移动
                largestRight = Math.max(arr[right], largestRight); // 重新判断右侧的最大高度
            } else {
                sum += largestLeft - arr[left];
                left++;
                largestLeft = Math.max(arr[left], largestLeft);
            }
        }
        return sum;
    }
}
