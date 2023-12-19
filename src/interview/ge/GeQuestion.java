package interview.ge;

import java.util.List;
import java.util.Random;

public class GeQuestion {

    // 输入一个日期字符串，返回这一年的全部日子
    public void getYearDays(String year) {
        // 1. 非算法题
        // 2. 调用Java库类型的API方法
    }

    // Random list items 将List中的所有元素随机打乱
    private Random random = new Random();

    // 1. 相对随机：原来位置上的item在经过随机之后又回到原始位置
    public void randomListNumbers(List<String> values) {
        for (int index = 0; index < values.size(); index++) {
            // between zero (inclusive) and bound (exclusive)
            // 0 <= random number < bound 随机值的区间
            int randomIndex = random.nextInt(values.size());
            swap(values, index, randomIndex);
        }
    }

    private void swap(List<String> values, int index1, int index2) {
        String tempStr = values.get(index1);
        values.set(index1, values.get(index2));
        values.set(index2, tempStr);
    }

    // 2. 绝对随机: 原来位置上的item全部被改变位置
    // 后面大值的数据，一旦被移动到前面，则不可能再被移动到原来的位置
    // 前面小值的数据，一旦被随机地选中，移动到最后，则可能在移动回去初始位置
    public void randomListNumbers2(List<String> values) {
        for (int index = 0; index < values.size(); index++) {
            int lastIndex = values.size() - 1 - index;
            int randomIndex = random.nextInt(lastIndex);
            swap(values, lastIndex, randomIndex);
        }
    }
}
