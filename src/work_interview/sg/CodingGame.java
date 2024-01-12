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
            int value1 = valOne % 10;
            int value2 = valTwo % 10;
            int result = calcResult % 10;

            // 在判断低位数的和时，需要考虑直接计算所进位的数值
            int sum = value1 + value2 + additionValue;
            if (sum >= 10) {
                if (sum % 10 != result) {
                    return String.valueOf(indexFault);
                }
                additionValue = 1;
            } else {
                if (sum != result) {
                    return String.valueOf(indexFault);
                }
                additionValue = 0;
            }

            indexFault++;
            valOne = valOne / 10;
            valTwo = valTwo / 10;
            calcResult = calcResult / 10;
        }

        if (valOne + valTwo != calcResult) {
            return String.valueOf(indexFault);
        }
        return "ok";
    }
}
