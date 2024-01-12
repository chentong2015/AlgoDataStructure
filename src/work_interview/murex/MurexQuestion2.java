package work_interview.murex;

import java.util.Collections;
import java.util.List;

public class MurexQuestion2 {

    // 0 - 999 计算出座位可以被最少划分，最多可以将3个连续的座位算到一起
    // O(nlog(n)) O(1)
    public static int checkNeighboring(List<Integer> seatNumbers) {
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            return 0;
        }
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
    // suspect's decrypted name as well as the Caesar cipher key used by the cryptographer to encrypt his name.
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
        if (encryptedName == null || guestList.isEmpty()) {
            return EMPTY;
        }
        char[] nameChars = encryptedName.toCharArray();
        for (String guestStr : guestList) {
            char[] guestChars = guestStr.toCharArray();
            if (nameChars.length == guestChars.length) {
                // 判断是否字符串的所有char字符都满足统一的便宜量offset
                int keyOffset = calculateCharOffset(nameChars[0], guestChars[0]);
                for (int index = 1; index < nameChars.length; index++) {
                    int keyTemp = calculateCharOffset(nameChars[index], guestChars[index]);
                    if (keyTemp != keyOffset) {
                        keyOffset = -1;
                        break;
                    }
                }
                if (keyOffset != -1) {
                    return new DecryptionResult(guestStr, 26 - keyOffset);
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
}
