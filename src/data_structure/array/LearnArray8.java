package data_structure.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LearnArray8 {

    // Minimum Rounds to Complete All Tasks
    // You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task.
    // In each round, you can complete either 2 or 3 tasks of the same difficulty level.
    // Return the minimum rounds required to complete all the tasks,
    // or -1 if it is not possible to complete all the tasks.
    //
    // 2,2,3,3,2,4,4,4,4,4 => 4
    // 每次自能完成2个或者3个相同level的任务，只需统计任务并合理分配
    // 由于需要返回最少次数，以此优先考虑先执行3个任务
    //
    // O(n) O(n)
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> mapTasks = new HashMap<>();
        for (int task: tasks) {
            if (mapTasks.containsKey(task)) {
                mapTasks.put(task, mapTasks.get(task) + 1);
            } else {
                mapTasks.put(task, 1);
            }
        }
        int minRounds = 0;
        for (int count: mapTasks.values()) {
            if (count == 1) {
                return -1;
            } else {
                minRounds += count / 3;
                if (count % 3 != 0) {
                    minRounds++;
                }
            }
        }
        return minRounds;
    }

    // TODO. 直接利用数组并本身来对数据进行统计, 通过index区间统计
    // O(n*logn)+O(n)  O(1)
    // 使用时间复杂度来换取空间复杂度
    public int minimumRounds2(int[] tasks) {
        Arrays.sort(tasks);
        int ans = 0;
        int left = 0;
        for(int i = 1; i < tasks.length; i++){
            if(tasks[left] != tasks[i]){
                int count = i - left;
                if(count < 2) {
                    return -1;
                }
                ans += count / 3;
                if(count % 3 != 0) {
                    ans++;
                }
                left = i;
            }
        }
        // 再做一遍相同的计算逻辑
        int count = tasks.length - left;
        if(count < 2) {
            return -1;
        }
        ans += count / 3;
        if(count % 3 != 0) {
            ans++;
        }
        return ans;
    }
}
