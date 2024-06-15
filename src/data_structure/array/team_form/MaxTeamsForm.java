package data_structure.array.team_form;

import java.util.Arrays;

public class MaxTeamsForm {

    // Given an array of integers that represents the team.
    // Every element in the array - a skill level of a team member.
    // Also given 2 integers teamSize and maxDifference.
    // Return the maximum number of teams that can be formed,
    //  - where the size of them is exactly teamSize
    //  - the difference between team members is no greater than maxDifference
    //
    // skill = [3, 4, 3, 1, 6, 5], teamSize = 3, maxDiff = 2.
    // Expected result: 2. Maximum 2 teams can be formed: [3,3,1] and [4,6,5]
    //
    // 从左到右框选指定范围长度的Team, 超出差值的无法划分到一个team中
    // 使用双指针依次循环，两次while循环总的次数是O(n)
    // O(n*log(n))  O(1)
    public static int formTeams(int[] skills, int teamSize, int maxDiff) {
        if (skills == null || skills.length <teamSize) {
            return 0;
        }
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
            index = right; // while循环结束，重新更新下一个初始计算位置
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
