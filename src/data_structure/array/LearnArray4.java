package data_structure.array;

// TODO: 数组统一相似问题的研究，"bit位运算" & "求和解法" & "数据相消理论"
// 1. 找数组中唯一出现一次的数字
// 2. 找(连续)数组中唯一缺失的数字
// 3. 找(连续)数组中唯一重复的数字
public class LearnArray4 {

    // Single Number
    // Non-empty array of integers nums, every element appears twice except for one. Find that single one.
    // Input: nums = [4,1,2,1,2] -> 4  1 1 2 2 4
    // 测试理解: 1. 先对数组进行排序, 然后之间判断相邻的两个值 Arrays.sort(nums) O(nlog(n)), O(1)
    //         2. 借助HashSet<>, 通过求和来计算: 2∗(a+b+c)−(a+a+b+b+c)=c   O(n), O(n)
    public static int findSingleNumber(int[] nums) {
        // 正确解法: 1. Bit Manipulation 比特位运算    O(n) O(1)
        //            a⊕b⊕a=(a⊕a)⊕b=0⊕b=b 将0和所有的值求XOR异或运算(相同的消去)，结果为出现一次的那个值
        //            0^x=x 和0运算之后会保持原来的值不变
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }

    // TODO: 将值补充出来，构建为位运算"两两相消"的效果 !!
    // Missing Number
    // Given an array nums containing n distinct numbers in the range [0, n], all the numbers of nums are unique
    // Return the only number in the range that is missing from the array
    // nums = [3,0,1] -> 2
    // nums = [0,1]   -> 2 从0到2的数的闭合区间范围 !!
    // 测试理解：1. 理论上，用[0, n]的总值减去nums的总值，差值就是缺失的那个值，sumNums的最大有效值是n，不会溢出
    // 正确理解：1. Gauss Formula 将上述的计算抽象出来，直接使用length计算总和sum，然后减去数组中值的总和
    //         2. Bit Manipulation 使用位运算，XOR异或计算之后，最后剩下的是出现一次的那个(空缺的那个值) !!
    //            missing =4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4) = 2 除了数字2意外，其他的数字都会最后"两两相消"
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


}
