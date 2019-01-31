package com.neo.cryptographyutils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: Sha256Tool
 * @Package com.neo.cryptographyutils
 * @Description: Sha256的工具类
 * @date Created in 14:37 2018/10/9
 */
public class Sha256Tool {
    /**
      * @Author:doubi.liu
      * @description:对数据做啥256
      * @param String 待处理的字符串
      * @return String  处理后的消息hash
      * @date:2018/10/11
    */
    public static String getSHA256(String str) throws Exception{
        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        //执行消息摘要
        return ConversionTool.bytesToHex(md.digest(str.getBytes("UTF-8")));
    }

    /**
      * @Author:doubi.liu
      * @description:对数据做啥256
      * @param byte[] 待处理的字节数据
      * @return byte[]  处理后的消息hash
      * @date:2018/10/11
    */
    public static byte[] getSHA256(byte[] data) throws Exception{

        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        //执行消息摘要
        return md.digest(data);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getSHA256("Hello World"));
    }

}
