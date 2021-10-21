package leetcode.interview_questions.easy_collection;

import java.util.Random;

public class OthersDesign {

    private int[] array;
    private int[] original; // 可以设计Immutable Class不可变的类型, field的值保持不变

    public OthersDesign(int[] nums) {
        this.array = nums;  // 这里的array和外面的nums公用的一个数据(数组), array的变化会引起外部nums中值的改变
        this.original = nums.clone(); // clone出来，存储原始的数据，相互独立
    }

    public int[] reset() {
        this.array = this.original;
        this.original = this.original.clone(); // 再次备份一个独立的原始数组，以免受到array变化的影响 !!
        return original;
    }

    // Shuffle an Array 随机洗牌一个数组，然后恢复成原来的顺序
    // 算法思路：Fisher-Yates Algorithm 循环遍历数组中的每一个值，随机生成一个值(要和后面第几个位置交换)
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            int swapIndex = getRandomIndex(i, array.length);
            swapValuesByIndex(i, swapIndex);
        }
        return array;
    }

    private void swapValuesByIndex(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int getRandomIndex(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }
}
