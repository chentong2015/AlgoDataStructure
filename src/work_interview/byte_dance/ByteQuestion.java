package work_interview.byte_dance;

public class ByteQuestion {

    // Trapping Rain Water
    // 计算每个位置的柱子上方还能储水的量
    // 和该柱子的前后柱子没有关系，只和它前后的最大高度柱子有关系
    // 如果它"左边位置的最大值"和"右边位置的最大值"都比该位置的高度高，则柱子上方有空间存水
    //
    // 2 5 3 7 6 => 2 左右两侧的边缘无法存水，只有3的位置能够存2单位的水
    // O(N)  O(1)
    //
    // TODO. 储水量计算的本质
    //  1. 计算方法：计算当前柱子与左右两侧最大值中的较小值的差值
    //  2. 那侧的最大柱子更低，则移动那侧的index，储水量只能存到较低的那个柱子
    public static int testSaveWater(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int sum = 0;
        int left = 0;
        int right = arr.length - 1;
        int largestLeft = arr[left];
        int largestRight = arr[right];

        while (left < right) {
            if (largestLeft > largestRight) {
                sum += largestRight - arr[right];
                right--;
                largestRight = Math.max(arr[right], largestRight);
            } else {
                // 当left往右移动时，largestLeft很能在它的左侧，以便用来计算当前位置的能存多少水
                sum += largestLeft - arr[left];
                left++;
                largestLeft = Math.max(arr[left], largestLeft);
            }
        }
        return sum;
    }
}
