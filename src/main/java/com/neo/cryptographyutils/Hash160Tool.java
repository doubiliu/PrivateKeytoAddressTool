package com.neo.cryptographyutils;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: Hash160Tool
 * @Package com.neo.cryptographyutils
 * @Description: hash160工具类
 * @date Created in 14:29 2018/10/9
 */
public class Hash160Tool {
    /**
      * @Author:doubi.liu
      * @description:对数据做hash160变换（一次sha256吃一次RIPEMD160）
      * @param byte[] 需做hash160的数组
      * @return byte[] 做完hash160变换的数据
      * @date:2018/10/11
    */
    public static byte[] doHash160(byte[] data) throws Exception {
        byte[] hash256=Sha256Tool.getSHA256(data);
        byte[] ripeMD160=RipeMD160Tool.encodeRipeMD160(hash256);
        return ripeMD160;
    }

    public static void main(String[] args) throws Exception {
        String publickey="035a928f201639204e06b4368b1a93365462a8ebbff0b8818151b74faab3a2b61a";
        System.out.println("输入压缩型公钥："+publickey);
        String script="21"+publickey+"ac";
        System.out.println("输出合约脚本："+script);
        String scripthash=ConversionTool.bytesToHex(doHash160(ConversionTool.hexStringToByte(script)));
        System.out.println("输出合约脚本hash："+scripthash);
    }
}
