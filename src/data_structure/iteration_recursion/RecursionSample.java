package data_structure.iteration_recursion;

public class RecursionSample {

    public static long sum(int n) {
        return (n * (n + 1)) / 2;
    }

    // 使用迭代计算阶乘
    public static long factor(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factor(n - 1);
    }

    // fi(n) = fi(n-1) + fi(n-2) 通项公式 !!!
    public static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
