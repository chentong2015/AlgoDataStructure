package data_structure.array.team_form;

import java.util.Arrays;

public class DivideTeamPlayers {

    // TODO. 分组平衡的本质是：最大的和最小的组合，结果是公平平均值(甚至无需指定这个值是多少)
    // Divide Players Into Teams of Equal Skill
    // You are given a positive integer array skill of even length n
    // where skill[i] denotes the skill of the ith player.
    // Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
    // Return the sum of the chemistry of all the teams,
    // Return -1 if there is no way to divide the players into teams.
    // skill = [3,2,5,1,3,4] -> 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
    // skill = [3,4] -> 3 * 4 = 12.
    // skill = [1,1,2,3] -> -1
    //
    // 必须约束长度是偶数才有拆分的可能
    // 2 <= skill.length <= 105
    // skill.length is even.
    // 1 <= skill[i] <= 1000
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int left = 0;
        int right = skill.length - 1;
        int totalTeamSkill = skill[left] + skill[right];
        // 计算时必须转换成long避免值的溢出
        long sum = (long) skill[left] * skill[right];
        while (left + 1 < right) {
            left++;
            right--;
            if (totalTeamSkill != skill[left] + skill[right]) {
                return -1;
            }
            sum += (long) skill[left] * skill[right];
        }
        return sum;
    }

}
