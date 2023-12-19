package interview.sg;

public class LiveCoding {

    // 累计数组前面的值:
    // input: [0, 1, 2, 3, 4]
    // output: [0, 1, 3, 6, 10]
    public void calculate(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }

        int tempSumBefore = 0;
        for (int index = 0; index < nums.length; index++) {
            int indexValue = nums[index];
            nums[index] = tempSumBefore + indexValue;
            tempSumBefore += indexValue;
        }

        // TODO. 还有优化的空间, 只使用纯数组和无变量操作
        for (int index = 1; index < nums.length; index++) {
            nums[index] += nums[index - 1];
        }
    }
}
