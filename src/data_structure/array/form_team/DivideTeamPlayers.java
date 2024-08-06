package data_structure.array.form_team;

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
    // 2 <= skill.length <= 105
    // skill.length is even.
    // 1 <= skill[i] <= 1000
    //
    // O(n*log(n))  O(1)
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);

        int left = 0;
        int right = skill.length - 1;
        int totalTeamSkill = skill[left] + skill[right];
        long sum = (long) skill[left] * skill[right];

        // 双坐标首尾同时移动index
        while (left + 1 < right) {
            left++;
            right--;
            if (skill[left] + skill[right] != totalTeamSkill) {
                return -1;
            }
            sum += (long) skill[left] * skill[right];
        }
        return sum;
    }

    // TODO: 排序之后按照特殊条件来分组
    // Maximum Number of Engineering Teams
    // Given an array of integers that represents the team.
    // Every element in the array - a skill level of a team member.
    // Also given 2 integers teamSize and maxDifference.
    // Return the maximum number of teams that can be formed,
    // - where the size of them is exactly teamSize
    // - the difference between team members is no greater than maxDifference
    //
    // skill = [3, 4, 3, 1, 6, 5], teamSize = 3, maxDiff = 2.
    // result: Maximum 2, [3,3,1] and [4,6,5]
    //
    // 从左到右框选指定范围长度的Team, 超出差值的无法划分到一个team中
    // 使用双指针依次循环，两次while循环总的次数是O(n)
    // O(n*log(n))
    // O(1)
    public static int formTeams(int[] skills, int teamSize, int maxDiff) {
        Arrays.sort(skills);
        int maxNumber = 0;
        int index = 0;
        while (index < skills.length) {
            int count = 1;
            int right = index + 1;
            while (right < skills.length && skills[right] - skills[index] <= maxDiff && count < teamSize) {
                count++;
                right++;
            }
            if (count == teamSize) {
                maxNumber++;
            }
            // index坐标往后移动
            // 跳出while循环的条件是right位置的数据不在前一个划分组中
            index = right;
        }
        return maxNumber;
    }

    public static void main(String[] args) {
        int[] skills = {0, 0, 0, 0, 0};
        System.out.println(formTeams(skills, 3, 2));

        int[] skills1 = {3, 4, 3, 1, 6, 5};
        System.out.println(formTeams(skills1, 3, 2));

        int[] skills2 = {1, 3, 3, 4, 4, 5, 7, 8, 12, 15};
        System.out.println(formTeams(skills2, 3, 2));

        int[] skills3 = {34, 5, 72, 48, 15, 2};
        System.out.println(formTeams(skills3, 1, 1));
        System.out.println(formTeams(skills3, 3, 20));
        System.out.println("3 30 = " + formTeams(skills3, 3, 30));
        System.out.println(formTeams(skills3, 3, 5));

        int[] skills4 = {1, 7, 18, 32, 65, 72, 90, 98, 100, 113, 146};
        System.out.println(formTeams(skills4, 3, 25));
    }
}
