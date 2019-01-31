package com.neo.cryptographyutils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: RipeMD160Tool
 * @Package com.neo.cryptographyutils
 * @Description: 对数据做RIPEMD160变换
 * @date Created in 14:53 2018/10/9
 */
public class RipeMD160Tool {
    /**
     * RipeMD160消息摘要
     * @param byte[] 待处理的消息摘要数据
     * @return byte[] 消息摘要
     * @date:2018/10/11
     * */
    public static byte[] encodeRipeMD160(byte[] data) throws Exception{
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md=MessageDigest.getInstance("RipeMD160");
        //执行消息摘要
        return md.digest(data);

    }

    public static void main(String[] args) {
        try {
            System.out.println(ConversionTool.bytesToHex(encodeRipeMD160("hello world".getBytes()
                )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
