package data_structure.array;

public class LearnArray2 {

    // Valid Mountain Array
    // Given an array of integers arr, return true if and only if it is a valid mountain array
    // 山峰数组特点: arr.length >= 3, 数组两端的数小, 中间的数字大, 必须是严格的上升或者下降, 必须有升有降
    // arr = [0,3,2,1]       -> true
    // arr = [0,1,2,3,4,5]   -> false
    public boolean validMountainArray(int[] arr) {
        // 测试理解：1. 从两边往中间读取，直到左边到达山峰点，右边到达山峰点，最后这两个山峰点必须是同一个位置
        //            O(n) O(1)
        if (arr.length < 3) return false;
        int left = 0;
        int right = arr.length - 1;
        while (left < arr.length - 1) {
            if (arr[left] >= arr[left + 1]) break;
            left++;
        }
        while (right > 0) {
            if (arr[right - 1] <= arr[right]) break;
            right--;
        }
        return left > 0 && right < arr.length - 1 && left == right; // 左右两边必须出发至少一步
    }

    // 测试理解：2. 使用一个index从左往右，判断具有上升和下降
    public boolean validMountainArray2(int[] arr) {
        if (arr.length < 3) return false;
        int index = 0;
        while (arr[index] < arr[index + 1]) {
            index++;
        }
        if (index == 0 || index == arr.length - 1) { // index > 0 必须至少上升一步
            return false;
        }
        while (arr[index] > arr[index + 1]) {
            index++;
        }
        return index == arr.length - 1; // 必须下降直到结尾
    }

    // Replace Elements with Greatest Element on Right Side
    // replace every element in that array with the greatest element among the elements to its right
    // replace the last element with -1
    // arr = [17,18,5,4,6,1] -> [18,6,6,6,1,-1]
    public int[] replaceElements(int[] arr) {
        // 测试理解：1. 从后往前，一遍读取，每次记录index后面所有值中的最大值
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
