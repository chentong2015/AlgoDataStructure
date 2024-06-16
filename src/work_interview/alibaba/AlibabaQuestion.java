package work_interview.alibaba;

import java.util.Arrays;

public class AlibabaQuestion {

    // TODO: 数据统计的相互抵消性质，一定是出现次数多的那个数据占优势(被留下来)
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 2,2,2,2,2,1,3,3,6  假如有一个数字出现的次数超过一半，count统计的mostFrequencyValue一定是这个数字
    // 1,2,2,3,3,2,6,2,2  统计的mostFrequencyValue值和数组中数据的排序无关
    //
    // O(n) O(1) 最佳的空间复杂度，避免使用HashMap<>开辟额外空间
    public int findMostFrequencyElement(int[] gifts, int n) {
        int count = 0;
        int mostFrequencyValue = gifts[0];
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                count = 1;   // 重新累计新的统计数字
                mostFrequencyValue = gifts[i];
            } else {
                if (mostFrequencyValue == gifts[i]) {
                    count++;
                } else {
                    count--; // 并新的数字抵消统计
                }
            }
        }
        // 最后确定元素的最大频率是否超过一半(该数据据不一定存在)
        int element = mostFrequencyValue;
        int size = (int) Arrays.stream(gifts).filter(item -> item == element).count();
        return (size > n / 2) ? mostFrequencyValue : 0;
    }
}
