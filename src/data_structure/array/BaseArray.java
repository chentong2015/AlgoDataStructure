package data_structure.array;

// Array 数组
// 1. 存储量(长度)固定
// 2. 查找元素速度快，根据下标index访问
// 3. 在中间插入元素慢，需要移动后面的元素
public class BaseArray {

    // 确定数组常见的约束条件: 值的长度，值的范围，值的特点

    // TODO: 数组方法论: 数组问题的最优解, 避免造成空间复杂度
    // 1. 直接利用数组位置(Change In-place)，前端和尾部可以定位存放特征值，覆盖值或者交换位置
    // 2. 直接利用位置下标进行计算，将问题转换成和index相关
    // 3. 遍历两遍数组，第一遍查找关键信息，第二遍得出结果，先找特殊情况下的特征，再找一般性
    // 4. 使用两个标识left，right，从左右两端往中间遍历
    // 5. 使用HashSet<>判断key值，HashMap<>统计key频率
    // 6. 使用Arrays.sort()排序，将特征信息展示出来
    // 7. "bit位运算" & "求和解法"
    // 8. 寻找value和index之间对应关系，转换条件
    // 9. 观察满足条件的通项公式，使之不仅在一般条件下成立，同时最大可能性成立
    // 10.在遍历时用一两个变量记录历史关键信息，避免使用额外数据结构

    // 常见数组API
    // return new int[] {item1, item2, ...}             返回数据，需要将数组构建出来
    // return Arrays.copyOf(results, count);            从起始点截取指定的长度
    // return Arrays.copyOfRange(results, 0, count);    定位截取指定区间的数据, 复制到count前一个位置
    // System.arraycopy(digits, 0, results, 1, digits.length); 从原始数组copy指定长度到目标数组
}
