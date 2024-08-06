package data_structure.array.dimension3;

import java.util.*;

// 降维思想: 使用一层遍历，将三维复杂度的问题降维到二维来处理
// 遍历时需要每次抠除指定index位置的数据，将其和数组末尾值交换，判断结束后还需要恢复 !!
public class Dimension3Target {

    // TODO. 判断是否存在三个数的和等于要找的目标值
    //  数组中任意三个数的和能够形成的组合数复杂度为N^3种 = Cn3
    // O(n*n) O(n)
    private static boolean canFindThreeValues(int[] array, int target) {
        if (array == null || array.length < 3) {
            return false;
        }
        int length = array.length;
        for (int index = 0; index < length - 1; index++) {
            swapValueByIndex(array, index, length - 1);
            int remainingValue = target - array[index];
            if (findTargetValue(array, remainingValue, length - 1)) {
                return true;
            }
            swapValueByIndex(array, length - 1, index);
        }
        return false;
    }

    // 需要将遍历的index的位置和array末尾的值进行交换
    private static void swapValueByIndex(int[] array, int index1, int index2) {
        int tempValue = array[index1];
        array[index1] = array[index2];
        array[index2] = tempValue;
    }

    // TODO. 判断int类型数组中是否有两个数的和等于要找的目标值
    //  从数组中取任意的两个值求和，形成的组合数复杂度为N^2 = Cn2
    // Check if there exist sum of two values in an array is equal to the target value
    // Return true if found, otherwise return false.
    // [1, 6, 2, 6, 0, 7, 0], 12 -> true
    // [1, 6, 2, 6, 0, 7, 0], 15 -> false
    //
    // O(n) O(n) 空间换时间，一维的时间复杂度
    private static boolean findTargetValue(int[] array, int target, int length) {
        if (array == null || array.length < 2) {
            return false;
        }
        Set<Integer> integerSet = new HashSet<>();
        for (int index = 0; index < length; index++) {
            // 在遍历时，同时完成对于结果的判断
            if (integerSet.contains(target - array[index])) {
                return true;
            }
            integerSet.add(array[index]);
        }
        return false;
    }
}
