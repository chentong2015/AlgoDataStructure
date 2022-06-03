package core_algo.others_algo;

// 字符串数据的加密和解密，算法设计
public class EncryptedProblem {

    public static byte[] changeToDecryptedString(String strText) {
        strText = strText.trim();
        byte[] bytes = new byte[strText.length() / 4];
        for (int i = 0; i < strText.length(); i += 4) {
            String str = strText.substring(i, i + 4);
            byte temp;
            try {
                temp = Byte.parseByte(str, 16);
            } catch (NumberFormatException var8) {
                int iByte = Integer.parseInt(str, 16);
                temp = (byte) (iByte - 256);
            }
            bytes[i / 4] = temp;
        }
        return bytes;
    }
}
