package com.neo.cryptographyutils;

import static com.neo.cryptographyutils.Sha256Tool.getSHA256;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: Base58CheckTool
 * @Package com.neo.cryptographyutils
 * @Description: 对数据做Base58Check变换（加盐）
 * @date Created in 17:13 2018/10/9
 */
public class Base58CheckTool {
    /**
     * @param data 预加盐数据
     * @return byte[] 加盐数据
     * @Author:doubi.liu
     * @description:对数据做Base58Check变换（加盐）
     * @date:2018/10/11
     */
/*    public static byte[] doEncode(byte[] data) throws Exception {
        byte[] checksum = getSHA256(getSHA256(data));
        byte[] buffer = new byte[data.length + 4];

        System.arraycopy(data, 0, buffer, 0, data.length);
        System.arraycopy(checksum, 0, buffer, data.length, 4);
        return buffer;
    }*/
    public static String doEncode(byte[] data) throws Exception {
        byte[] checksum = getSHA256(getSHA256(data));
        byte[] buffer = new byte[data.length + 4];

        System.arraycopy(data, 0, buffer, 0, data.length);
        System.arraycopy(checksum, 0, buffer, data.length, 4);

        return Base58Tool.encode(buffer);
    }


    public static void main(String[] args) throws Exception {
        String saltdata = "17ad5cac596a1ef6c18ac1746dfd304f93964354b5";
        System.out.println("输入预加盐数据：" + saltdata);
/*        System.out.println("输出加盐数据："+ConversionTool.bytesToHex(doEncode(
                ConversionTool.hexStringToByte(saltdata))));*/
        System.out.println("输入加盐数据：" + Base58CheckTool.doEncode(ConversionTool.hexStringToByte(saltdata)));
    }
}
