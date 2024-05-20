package data_structure.array;

public class LearnArray2 {

    // TODO. 使用变量保留index右侧的最大值信息(变量的历史信息)
    // Replace Elements with Greatest Element on Right Side
    // replace every element in that array with the greatest element among the elements to its right
    // replace the last element with -1
    // arr = [17,18,5,4,6,1] -> [18,6,6,6,1,-1]
    //
    // 从后往前，一遍读取，每次记录index后面所有值中的最大值
    public int[] replaceElements(int[] arr) {
        int index = arr.length - 1;
        int maxRight = -1;
        while (index >= 0) {
            int temp = arr[index];
            arr[index] = maxRight;
            maxRight = Math.max(maxRight, temp);
            index--;
        }
        return arr;
    }

    // Sort Array By Parity
    // return an array consisting of all the even elements of nums, followed by all the odd elements of nums
    // nums = [3,1,2,4,5] -> [2,4,3,1,5] 将所有偶数值提取到奇数值的前面
    public int[] sortArrayByParity(int[] nums) {
        // 测试理解: 1. 从左到右依次读取，如果是奇数，则和末尾的值进行交换，将后面值提到前面来判断，如果还是奇数，则和倒数第二个值交换...
        //             O(n) O(1)
        //
        // 正确理解：1. 优化提高 > 如果尾部上的值已经是奇数，则不需要交换到前面，再交换到后面 > 减少交换的次数 !!
        int right = nums.length - 1;
        int index = 0;
        while (index < right) {
            if (nums[index] % 2 == 1) {
                // 由于右端的奇数不需要做任何的调整，通过while循环可以快速缩小范围
                // 找到偶数才交换，否则往左边移动，直到index位置
                while (right > index && nums[right] % 2 == 1) {
                    right--;
                }
                swapArrayValues(nums, index, right);
                right--;
            } else {
                index++;
            }
        }
        return nums;
    }

    private void swapArrayValues(int[] nums, int index, int right) {
        int temp = nums[right];
        nums[right] = nums[index];
        nums[index] = temp;
    }
}
