package interview.murex;

public class BfsDfsQuestion2 {

    // TODO: BFS-DFS，在递归遍历过程中增加判断的条件，使用两次BFS
    // 判断从(0,0)是否能在(m,n)的矩阵中找到4
    // 0 表示可以通过
    // 1 表示墙，不能通过
    // 2 表示窗，只有在拿到key才能通过
    // 3 表示key钥匙
    // 4 表示目标
    public static void main(String[] args) {
        int[][] array = {{0, 0, 0, 3},
                {1, 0, 1, 1},
                {1, 0, 2, 4}};
        System.out.println(trialOfValor(array));
    }

    // TODO. 如果不使用公共的成员属性，则需将结果变量带到递归方法中
    private static int mLength;
    private static int nLength;
    private static boolean hasFoundKey = false;
    private static boolean hasFoundTarget = false;

    public static boolean trialOfValor(int[][] array) {
        mLength = array.length;
        nLength = array[0].length;
        findPointKey(array, 0, 0, 3);
        findPointTarget(array, 0, 0, 4);
        return hasFoundTarget;
    }

    // 第一遍递归遍历，先判断能否将key值找到
    // 第一轮在没有找到key的情况下是无法开窗的，因此无法判断是否能找到target
    private static void findPointKey(int[][] array, int i, int j, int keyValue) {
        // Check weather the hasFoundKey is already true, to avoid useless iteration
        if (array[i][j] == keyValue) {
            hasFoundKey = true;
            return;
        }
        if (array[i][j] == 0) {
            if (i < mLength - 1) {
                findPointKey(array, i + 1, j, keyValue);
            }
            if (j < nLength - 1) {
                findPointKey(array, i, j + 1, keyValue);
            }
        }
    }

    // 第二遍在增加key的情况下，提高递归的判断条件
    private static void findPointTarget(int[][] array, int i, int j, int targetValue) {
        if (array[i][j] == targetValue) {
            hasFoundTarget = true;
            return;
        }
        if (array[i][j] == 0 || (array[i][j] == 2 && hasFoundKey)) {
            if (i < mLength - 1) {
                findPointTarget(array, i + 1, j, targetValue);
            }
            if (j < nLength - 1) {
                findPointTarget(array, i, j + 1, targetValue);
            }
        }
    }
}
