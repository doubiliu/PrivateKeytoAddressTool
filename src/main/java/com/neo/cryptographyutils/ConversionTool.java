package com.neo.cryptographyutils;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: ConversionTool
 * @Package com.neo.cryptographyutils
 * @Description: hexString 和byte[]转换
 * @date Created in 10:44 2018/10/9
 */
public class ConversionTool {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * byte[] to hex string
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }

    /**
     * 把16进制字符串转换成字节数组
     * @param hex
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {
        if(hex == null || hex.trim().equals("")) {
            return new byte[0];
        }
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i < hex.length() / 2; i++) {
            String subStr = hex.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    public static void main(String[] args) {
        byte[] b=ConversionTool.hexStringToByte
                ("aab2d44b32c091818e2781aa67fbb33513efd8627718c88e67cc385a72eeaa");
        String hex=ConversionTool.bytesToHex(b);
        System.out.println(hex);
    }
}
