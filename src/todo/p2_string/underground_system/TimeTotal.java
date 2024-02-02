package todo.p2_string.underground_system;

// 计算平均值的方式：即时存储总数和次数
public class TimeTotal {

    private double sum;
    private int times;

    public TimeTotal(double sum, int times) {
        this.sum = sum;
        this.times = times;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getSum() {
        return sum;
    }

    public int getTimes() {
        return times;
    }
}
