package questions.greedy;

// Greedy Algorithm 贪婪(贪心)算法
// 1. 一种在每一步选择中都采取在"当前状态下"最好或最优(即最有利)的选择，从而希望导致结果是最好或最优的算法
// 2. 问题能够分解成子问题来解决，子问题的最优解(局部问题的最优解)能递推到最终问题的最优解(全局最优解)

// Greedy算法与DP/backtracking算法的区别
// - 它对每个子问题的解决方案都做出选择，不能回退
// - dp动态规划则会保存以前的运算结果, 有回退功能 !!
// - backtracking回溯算法通过回溯来罗列所有的匹配情况 !!
public class LearnGreedyAlgo {

    // 一旦一个问题可以通过贪心法来解决，那么贪心法一般是解决这个问题的最好办法
    // 1. 贪心法高效性以及所求得答案比较接近最优结果，可作辅助算法或者直接解决一些要求不特别精确的问题
    // 2. 贪心法容易过早做决定，因而没法达到最佳解

    // Cut Rope
    // 把一根绳子剪成多段，并且使得每段的长度乘积最大
    // 将绳子拆成 1 和 n-1，则 1(n-1)-n=-1<0，即拆开后的乘积一定更小，所以不能出现长度为 1 的绳子
    // 将绳子拆成 2 和 n-2，则 2(n-2)-n = n-4，在 n>=4 时这样拆开能得到的乘积会比不拆更大
    // 将绳子拆成 3 和 n-3，则 3(n-3)-n = 2n-9，在 n>=5 时效果更好
    // 将绳子拆成 4 和 n-4，因为 4=2*2，因此效果和拆成2一样
    // 将绳子拆成 5 和 n-5，因为 5=2+3，而5<2*3，所以不能出现5的绳子，而是尽可能拆成2和3
    // 将绳子拆成 6 和 n-6，因为 6=3+3，而 6<3*3，所以不能出现6的绳子，而是拆成3和3
    // 继续拆成更大的绳子可以发现都比拆成 2 和 3 的效果更差，只考虑将绳子拆成2和3，并且优先拆成3
    public int cutRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int timesOf3 = n / 3;
        // 优先考虑拆成3的长度，并计算拆出来的总乘积值
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        int sumTimesOf3 = (int) (Math.pow(3, timesOf3));

        int timesOf2 = (n - timesOf3 * 3) / 2;
        int sumTimeOf2 = (int) (Math.pow(2, timesOf2));
        return sumTimeOf2 * sumTimesOf3;
    }
}
