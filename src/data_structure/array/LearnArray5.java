package data_structure.array;

public class LearnArray5 {

    // 找出int类型的数组中最接近0的值，如果有两个同距离的值，则返回正数
    // [-2, 3, 2, 5, -1] => -1
    // [-2, 3, 5] => -2
    //
    // O(n) O(1) 在不增加额外的空间复杂度的条件下，使用一个标识符来判断找到的值是否为正
    public static int computeClosestToZero(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int result = 0;
        int valueAbsClosestToZero = Integer.MAX_VALUE;
        for (int item : array) {
            int valueAbs = Math.abs(item);
            if (valueAbs < valueAbsClosestToZero) {
                valueAbsClosestToZero = valueAbs;
                result = item;
            }

            // 始终保证返回的是正数的值
            if (valueAbs == valueAbsClosestToZero && result < item) {
                result = item;
            }
        }
        return result;
    }
}
