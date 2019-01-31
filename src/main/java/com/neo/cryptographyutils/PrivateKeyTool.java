package com.neo.cryptographyutils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: PrivateKeyTool
 * @Package com.neo.cryptographyutils
 * @Description: 获取私钥的工具类
 * @date Created in 12:02 2018/10/8
 */
public class PrivateKeyTool {


    /**
      * @Author:doubi.liu
      * @description: 从随机数中获取32字节私钥
      * @param
      * @return byte[] 32字节私钥
      * @date:2018/10/11
    */
    public static byte[] getPrivateKey() {
        Random secureRandom = new SecureRandom();
        byte[] privateKey = new byte[32];
        secureRandom.nextBytes(privateKey);
        return privateKey;
    }

    /**
      * @Author:doubi.liu
      * @description: 把外部输入的16进制字符串形式秘钥转成32字节形式秘钥输出
      * @param hexString 16进制字符串形式秘钥
      * @return byte[] 32字节私钥
      * @date:2018/10/11
    */
    public static byte[] getPrivateKey(String hexString) {
        byte[] privateKey = ConversionTool.hexStringToByte(hexString);
        return privateKey;
    }

    public static void main(String[] args) {
        String input_privatekey="c7134d6fd8e73d819e82755c64c93788d8db0961929e025a53363c4cc02a6962";
        byte[] privateKey=PrivateKeyTool.getPrivateKey(input_privatekey);
        System.out.println("输入："+input_privatekey);
        System.out.println("输出："+ConversionTool.bytesToHex(privateKey));
        byte[] wifdata = new byte[34];
        wifdata[0] = (byte) 0x80;//Settings.Default.AddressVersion;
        System.arraycopy(privateKey, 0, wifdata, 1,32);
        try {
            String r=Base58CheckTool.doEncode(wifdata);
            System.out.println("输出："+r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
