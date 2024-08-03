package work_algorithms.dp_algo.jump_game;

public class GasStationQuestion {

    // TODO. 加油站问题的本质: 只需要维持剩余的gas不为0，便可以继续前进
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
    // 1. 只要index是起点，则它可以遍历到后面的任何一个点
    // 2. 由于确认只有唯一解，因此可以直接遍历一次即可
    // O(N) O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        for(int i = 0; i < gas.length; i++){
            totalGas += gas[i];
            totalCost += cost[i];
        }
        // 理论上加油的gas总量必须不能低于消耗的总量
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
