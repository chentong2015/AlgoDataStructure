package interview.alibaba;

public class AlibabaQuestion {

    // Alibaba Interview Question
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 1. 使用HashMap<>来存储每个元素的频率 O(n)       O(n)
    // 2. 使用两遍遍历数组：第一遍找出其中出现次数最多的数字，第一遍统计这个数字出现的次数是否超过一半 !!

    // TODO: 从特殊性到一般性的过渡，满足同一个逻辑条件的成立
    //       数据统计的相互抵消性质，一定是出现次数多的那个数据占优势(被留下来) !!
    // More than half frequency element
    // Find elements with more than half the frequency, suppose there is only one answer
    // nums = [1,2,2,3,3,2,6,2,2]  ->  2
    // 2,2,2,2,2,1,3,3,6  假如有一个数字出现的次数超过一半，通过count统计最后留下来的mostFrequencyValue一定是这个数字
    // 1,2,2,3,3,2,6,2,2  数据打乱之后依然能保留到这个数字，原因是因为在count统计的过程中数据会呈现相互抵消的效果 !!
    public int findMostFrequencyElement2(int[] gifts, int n) {
        if (gifts.length < n || gifts.length == 0) return 0;
        int count = 0;
        int mostFrequencyValue = gifts[0];
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                // 只有当count被累积清空之后，才会变动记录的数字mostFrequencyValue
                mostFrequencyValue = gifts[i];
                count = 1;
            } else {
                // 累计频率或者是抵消
                if (mostFrequencyValue == gifts[i]) count++;
                else count--;
            }
        }
        // 前面统计出来的最大频率的数据，并没有准确的统计出其数目
        // 需要再次统计最大频率的数目是否超过一半(该数据据不一定存在)
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (gifts[i] == mostFrequencyValue) size++;
        }
        return (size > n / 2) ? mostFrequencyValue : 0;
    }
}
