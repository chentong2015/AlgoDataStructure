package data_structure.array;

public class LearnArray9 {

    // TODO. 内层循环和外层index循环重复
    // Gas Station
    // There are n gas stations along a circular route,
    // where the amount of gas at the ith station is gas[i].
    // You have a car with an unlimited gas tank and cost[i] of gas to travel
    // from the ith station to its next (i + 1)th station.
    // return starting index if you can travel around circuit once in clockwise direction, otherwise -1
    // gas = [1, 2, 3, 4, 5], cost = [3, 4, 5, 1, 2] -> 3
    // tmp =  -2,-2,-2,3, 3 出发的index位置必须是正数才有剩余
    //
    // gas = [3,3,4], cost = [3,4,4]
    //        0,-1,0
    //
    // O(N*N) O(1) Time Limit Exceeded ！！常规解法不符合算法题目的初衷
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        for (int index = 0; index < gas.length; index++) {
            int gasLeft = gas[index] - cost[index];
            int count = 0;
            int tmpIndex = index;
            // TODO. 错误解法，内层tmpIndex可以替换成外层index ！！
            while (gasLeft >= 0) {
                count++;
                if (count == gas.length) {
                    return index;
                }
                tmpIndex = tmpIndex == (gas.length - 1) ? 0 : tmpIndex + 1;
                gasLeft = gasLeft + gas[tmpIndex] - cost[tmpIndex];
            }
        }
        return -1;
    }

    // TODO. 问题的本质: 只需要维持剩余的gas不为0，便可以继续前进
    // 1. 只要index是起点，则它可以遍历到后面的任何一个点
    // 2. 由于确认只有唯一解，因此可以直接遍历一次即可
    // O(N) O(1)
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
        }
        // 理论上，所有加油的gas总量要超过消耗的总量才有可能遍历全部
        if(totalGas < totalCost) {
            return -1;
        }

        int remainsGas = 0;
        int start = 0;
        for(int i = 0; i < gas.length; i++){
            remainsGas = remainsGas + (gas[i] - cost[i]);
            if(remainsGas < 0){
                start = i + 1;
                remainsGas = 0;
            }
        }
        return start;
    }
}
