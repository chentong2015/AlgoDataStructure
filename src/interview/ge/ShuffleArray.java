package interview.ge;

import java.util.Random;

// Shuffle an Array 随机洗牌一个数组，然后恢复成原来的顺序
// 算法思路：Fisher-Yates Algorithm 循环遍历数组中的每一个值，随机生成一个值(要和后面第几个位置交换)
public class ShuffleArray {

    private int[] array;
    
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
