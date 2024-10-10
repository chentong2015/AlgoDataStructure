package data_structure.list.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LearnArrayList1 {

    // TODO. 保存区间的时候没有必要存储remaining数据的所用中间值
    // Build Interval Sections
    // Input: excluded numbers list, max num limit
    // Output: all remaining numbers less than limit, group by [start num, end num]
    //
    // input: 0 1 1 1 5, max = 10
    // output: 2 4  6 9
    //
    // 1. 如果输入乱序数据 ? 使用别的数据结构进行排序 ?
    // 2. 如果输入数据中包含重复Num ? 在遍历的时候进行过滤
    //
    // O(n*log(n) + n)  O(n) 极限情况下结构区间数据和Nums数据在一个数量级
    public static List<Integer> buildInternalNumbers(List<Integer> excludedNumbers, int maxNum) {
        List<Integer> remainingNums = new ArrayList<>();
        excludedNumbers.add(maxNum);
        Collections.sort(excludedNumbers);

        // TODO. 在遍历时同时取前后两个数字，如果不同，则判断是否计算区间
        for (int index=0; index < excludedNumbers.size() - 1; index++) {
            int currentNum = excludedNumbers.get(index);
            int nextNum = excludedNumbers.get(index + 1);
            if (currentNum != nextNum) {
                if (currentNum + 1 < nextNum) {
                    remainingNums.add(currentNum + 1);
                    remainingNums.add(nextNum - 1);
                }
            }
            // 因为在Excluded List中添加limit值，因此直接判断相等
            // 如果Excluded List中没有包含limit值，则必须在添加区间的时候判断
            if (nextNum == maxNum) {
                break;
            }
        }
        return remainingNums;
    }

    public static void main(String[] args) {
        Integer[] items = {0, 0, 1, 1, 1, 1, 2, 6, 7, 20};
        List<Integer> excludedNumbers = new ArrayList<>(Arrays.asList(items));
        List<Integer> result = buildInternalNumbers(excludedNumbers, 10);
        System.out.println(result.size());
    }
}
