package work_interview.sg;

public class CodingGame {

    /**
     * 判断两个输入的整数值的和是否等于提供的结果，如果错误则返回计算错误的数值位置
     *
     * @param valOne     第一个加数
     * @param valTwo     第二个加数
     * @param calcResult 用于判断的和数
     * @return 返回ok字符串或者返回index位置字符串
     */
    public static String compute(int valOne, int valTwo, int calcResult) {
        int indexFault = 0;
        int additionValue = 0;
        while (valOne >= 10) {
            int sum = valOne % 10 + valTwo % 10 + additionValue; // 累加上低位的求和
            if (sum >= 10) {
                additionValue = 1; // 保留低位求和的进位值
                sum = sum % 10;
            } else {
                additionValue = 0;
            }
            if (sum != calcResult % 10) { // 比较每一轮计算出来的末尾数是否正确
                return String.valueOf(indexFault);
            }

            valOne = valOne / 10;
            valTwo = valTwo / 10;
            calcResult = calcResult / 10;
            indexFault++;
        }
        if (valOne + valTwo != calcResult) {
            return String.valueOf(indexFault);
        }
        return "ok";
    }
}
