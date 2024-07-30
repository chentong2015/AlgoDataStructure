package data_structure.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LearnArray7 {

    // TODO. 直接利用数组并本身来对数据进行统计, 通过index区间统计
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

    // TODO. 循环数组：从其中框选k段的长度, 保证截取的长度中包含开头或者结尾index
    // Maximum Points You Can Obtain from Cards
    // The points are given in the integer array cardPoints
    // In one step, you can take one card from the beginning or from the end of the row.
    // You have to take exactly k cards.
    // Return the maximum score you can obtain.
    //
    // [1,2,3,4,5,6,1], k = 3 -> 1 + 6 + 5 = 12
    // O(k) O(1) 只需要在数组中循环k个位置的值
    public int maxScore(int[] cardPoints, int k) {
        int baseScore = 0;
        for (int index = 0; index <k; index++) {
            baseScore += cardPoints[index];
        }

        int left = k - 1;
        int index = 0;
        int maxScore = baseScore;
        while (index < k) {
            // 循环数组：每移动一个位置，重新计算累计的总和
            baseScore = baseScore - cardPoints[left] + cardPoints[cardPoints.length - 1 - index];
            maxScore = Math.max(maxScore, baseScore);
            left--;
            index++;
        }
        return maxScore;
    }
}
