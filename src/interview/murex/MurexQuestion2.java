package interview.murex;

import java.util.Collections;
import java.util.List;

public class MurexQuestion2 {

    // 0 - 999 计算出座位可以被最少划分，最多可以将3个连续的座位算到一起
    // O(nlog(n)) O(1)
    public static int checkNeighboring(List<Integer> seatNumbers) {
        if (seatNumbers == null || seatNumbers.size() < 1) return 0;
        int sum = 0;
        int count = 0;
        int countNumber = -1;
        Collections.sort(seatNumbers);
        for (int seat : seatNumbers) {
            if (count == 0) {
                countNumber = seat;
                count++;
            } else {
                if (countNumber + 1 == seat) {
                    count++;
                    if (count == 3) {
                        sum++;
                        count = 0;
                    }
                } else {
                    sum++;
                    count = 0;
                }
                countNumber = seat; // 注意更新统计值的变化
            }
        }
        if (count < 3) sum++; // 最后一部分需要进行统计
        return sum;
    }

    // Complete the 'findSuspect' function below.
    // The function is expected to return an instance of DecryptionResult containing the
    // suspect's decrypted name as well as the Caesar cipher key used by the cryptographer
    // to encrypt his name.
    // If the decrypted name is not found in the guest list, the EMPTY object should be returned.
    //
    // The function accepts following parameters:
    //  1. STRING encryptedName
    //  2. STRING_ARRAY guestList
    // 判断一个字符串是否能够通过加密，得到list中的一个值，并指出这个加密的offset index
    // 如果找不到，则返回空的DecryptionResult对象
    // O(n * L * m) 会比较所有字符的长度 O(1)
    public static final class DecryptionResult {
        private final String name;
        private final int encryptionKey;

        public DecryptionResult(String name, int encryptionKey) {
            this.name = name;
            this.encryptionKey = encryptionKey;
        }
    }

    public static final DecryptionResult EMPTY = new DecryptionResult("", -1);

    public static DecryptionResult findSuspect(String encryptedName, List<String> guestList) {
        if (encryptedName == null || guestList.size() < 1) return EMPTY;
        char[] nameChars = encryptedName.toCharArray();
        for (String guestStr : guestList) {
            char[] guestChars = guestStr.toCharArray();
            if (nameChars.length == guestChars.length) {
                int key = calculateCharOffset(nameChars[0], guestChars[0]);
                for (int index = 1; index < nameChars.length; index++) {
                    int key1 = calculateCharOffset(nameChars[index], guestChars[index]);
                    if (key1 != key) {
                        key = -1;
                        break;
                    }
                }
                if (key != -1) {
                    return new DecryptionResult(guestStr, 26 - key);
                }
            }
        }
        return EMPTY;
    }

    // 计算从nameChar字符到guestChar的偏移量，offset >=0
    public static int calculateCharOffset(char nameChar, char guestChar) {
        int nameCharIndex = nameChar - 'a';
        int guestCharIndex = guestChar - 'a';
        int offset;
        if (nameCharIndex > guestCharIndex) {
            offset = guestCharIndex + 26 - nameCharIndex;
        } else {
            offset = guestCharIndex - nameCharIndex;
        }
        return offset;
    }

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

    private static int mLength = 3;
    private static int nLength = 4;
    private static boolean hasFoundKey = false;
    private static boolean hasFoundTarget = false;

    public static boolean trialOfValor(int[][] array) {
        passPointKey(array, 0, 0, 3);
        passPointTarget(array, 0, 0, 4);
        return hasFoundTarget;
    }

    // 第一遍递归遍历，先判断能否将key值找到
    // 第一轮在没有找到key的情况下是无法开窗的，因此无法判断是否能找到target
    private static void passPointKey(int[][] array, int i, int j, int keyValue) {
        if (array[i][j] == keyValue) {
            hasFoundKey = true;
            return;
        }
        if (array[i][j] == 0) {
            if (i < mLength - 1) {
                passPointKey(array, i + 1, j, keyValue);
            }
            if (j < nLength - 1) {
                passPointKey(array, i, j + 1, keyValue);
            }
        }
    }

    // 第二遍在增加key的情况下，提高递归的判断条件
    private static void passPointTarget(int[][] array, int i, int j, int targetValue) {
        if (array[i][j] == targetValue) {
            hasFoundTarget = true;
            return;
        }
        if (array[i][j] == 0 || (array[i][j] == 2 && hasFoundKey)) {
            if (i < mLength - 1) {
                passPointTarget(array, i + 1, j, targetValue);
            }
            if (j < nLength - 1) {
                passPointTarget(array, i, j + 1, targetValue);
            }
        }
    }
}
