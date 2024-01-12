package questions.dfs_bfs_traverse.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Given an array of distinct integers candidates and a target integer target,
// return a list of all unique combinations of candidates where the chosen numbers sum to target.
// You may return the combinations in any order.
// 数组中的数据是离散不重复的，且都在有效的范围
//
// Input: candidates = [2,3,6,7], target = 7   Output: [[2,2,3],[7]]
// Input: candidates = [2,3,5], target = 8     Output: [[2,2,2,2],[2,3,3],[3,5]]
// Input: candidates = [2], target = 1         Output: []
public class BacktrackingCombinationSum {

    // 穷尽所有的情况，形成标准的树状结构，从上往下判断是否形成目标值
    //                  15
    //        1      2      3      6
    //  1  2  3  6             1  2  3  6
    // 1..                            1 2 3 6
    //
    //                    <Problem>
    // 似于树的遍历，直到找到叶子节点，或者有明显的判断不能继续找到有效答案，则放弃
    // Candidate 1          ....         Candidate i
    //    \backtracking\回溯的核心在于判断到异常情况时的撤回
    //     将原先设置的条件恢复，再进行下一步的递归判断，一层一层的趋近最终的Solution
    // Candidate 1.1 (KO)   ....         Candidate i.j
    //                   Solution m-1    Solution m

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain >= 0) {
            if (remain == 0) {
                list.add(new ArrayList<>(tempList));
            } else {
                for (int i = start; i < nums.length; i++) {
                    tempList.add(nums[i]);

                    // 在回溯时，修改目标的值并移动index位置
                    // not i + 1 because we can reuse same elements
                    backtrack(list, tempList, nums, remain - nums[i], i);

                    // 移除最后添加到临时列表中的元素
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
}
