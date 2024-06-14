package work_interview.amazon;

import java.util.Arrays;

// https://leetcode.com/discuss/interview-question?currentPage=1&orderBy=hot&query=&tag=amazon
public class AmazonQuestions {

    // Given an array of integers that represents the team.
    // Every element in the array - a skill level of a team member.
    // Also given 2 integers teamSize and maxDifference.
    // Return the maximum number of teams that can be formed,
    // where the size of them is exactly teamSize and the difference between team members is no greater than maxDifference
    //
    // skill = [3, 4, 3, 1, 6, 5], teamSize = 3, maxDiff = 2.
    // Expected result: 2.
    // Maximum 2 teams can be formed with the following conditions: [3,3,1] and [4,6,5]
    //
    // 从左到右框选指定范围和指定长度的Team，满足什么条件框选出来的数目最多 ？
    // 大于maxDiff值的数据两侧的值需要断开
    // 1 3 3 4 、7 8 9 12
    public int formTeams(int[] skills, int teamSize, int maxDiff) {
        Arrays.sort(skills);

        return 0;
    }

}
