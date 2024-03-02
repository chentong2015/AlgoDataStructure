package questions.work_others.math;

public class BaseMath {

    // 判断质数的算法: 只能被一和自身进行整除
    public boolean isPrimeDigit(int digit) {
        if (digit == 1) {
            return false;
        } else {
            // 使用平方根Math.sqrt(checkNum)优化算法的复杂度
            for (int i = 2; i <= (long) Math.sqrt(digit); i++) {
                if (digit % i == 0) {
                     return false;
                }
            }
        }
        return true;
    }
}
