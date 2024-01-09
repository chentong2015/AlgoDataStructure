package interview.murex;

public class BfsDfsQuestion1 {

    // TODO: BFS-DFS，使用深度优先遍历，直到找到最深的那个目标终点 -- 递归思想
    // 传入n*m的二维数组数据，返回唯一一条通路的步数，如果没有通路，则返回-1
    // 道路连接的问题: 找出所有可能的通路，有且只有一条可能的通路
    //
    // A通过，E阻断，D终点: 更新经过的标记
    // (0,0)
    // A   E   A   E
    // A   A   A   E
    // A   A   E   E
    // E   A   A   D (n*m)
    // O(n*m)  O(1)
    //
    // TODO: 选择不同的BFS遍历策略，会造成时间复杂度的不稳定性
    private static int stepCount;

    public static int transmissionLength(char[][] planetsMap) {
        stepCount = 0;
        findNextStep(planetsMap, 0, 0);
        return stepCount == 0 ? -1 : stepCount;
    }

    // 最优的BFS遍历方式: 顺时针方法进行遍历
    private static boolean findNextStep(char[][] planetsMap, int i, int j) {
        if (i < 0 || j < 0 || i >= planetsMap.length || j >= planetsMap[0].length)
            return false;
        if (planetsMap[i][j] == 'B' || planetsMap[i][j] == 'E') {
            return false;
        }
        if (planetsMap[i][j] == 'D')
            return true; // 如果找到目标，则直接返回

        // 遍历过的位置需要做修改值的处理，避免无限递归造成StackOverflow
        planetsMap[i][j] = 'B';

        boolean upStep = findNextStep(planetsMap, i - 1, j);
        boolean rightStep = findNextStep(planetsMap, i, j + 1);
        boolean downStep = findNextStep(planetsMap, i + 1, j);
        boolean leftStep = findNextStep(planetsMap, i, j - 1);

        // 只有在当前的位置，成功的往后走"有效的"一步，才统计
        // 反之不会纳入统计，因为没有找到最终的'D'，没有true这结果返回 !!!
        if (upStep || downStep || leftStep || rightStep) {
            System.out.println(i + " ++ " + j);
            stepCount++;
            return true;
        }
        return false;
    }
}
