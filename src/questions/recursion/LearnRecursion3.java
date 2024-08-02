package questions.recursion;

public class LearnRecursion3 {

    // TODO. 从基础到一般归纳问题的本质特征
    // Stone Game
    // There are an even number of piles arranged in a row,
    // and each pile has a positive integer number of stones piles[i].
    // A player takes the entire pile of stones either from the beginning or from the end of the row.
    // piles = [5,3,4,5] -> true for the first player
    // piles = [3,7,2,3] -> true
    // piles = [3,7,2,3,5,5] -> true
    //
    // 从数组的头或尾取数，累计最后的值，判断是否先选的人会胜利

    private int countFirst = 0;
    private int countSecond = 0;

    public boolean stoneGame(int[] piles) {
        return stoneGameRecursion(piles, 0, piles.length-1);
    }

    // 使用递归，从最少的两个数推理到最后的结果
    public boolean stoneGameRecursion(int[] piles, int left, int right) {
        if (left + 1 == right) {
            if (piles[left] > piles[right]) {
                return countFirst + piles[left] > countSecond + piles[right];
            } else {
                return countFirst + piles[right] > countSecond + piles[left];
            }
        }
        // TODO. 如果选择当前位置，则它旁边的相邻位置无法选择
        //  每次选择最优的值，移动到最后便是最后值
        for (int index = 0; index <2; index++) {
            if (piles[left] + piles[right-1] < piles[left+1] + piles[right]) {
                countFirst += piles[right];
                right--;
            } else {
                countFirst += piles[left];
                left++;
            }
        }
        return stoneGameRecursion(piles, left, right);
    }
}
