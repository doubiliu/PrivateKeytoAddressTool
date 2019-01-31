package com.neo.cryptographyutils;

import com.neo.cryptographyutils.ECC.ECC;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: PublicKeyTool
 * @Package com.neo.cryptographyutils
 * @Description: 获取公钥的工具类
 * @date Created in 11:48 2018/10/9
 */
public class PublicKeyTool {
    /**
      * @Author:doubi.liu
      * @description: 使用私钥作为参数，通过调用ECC相关方法求给基点G的K倍点
      * @param byte[] 32字节私钥
      * @return ECPoint 基点G的K倍点(公钥)
      * @date:2018/10/11
    */
    public static ECPoint getPublicKey(byte[] data){
        if (data.length != 32 && data.length != 96 && data.length != 104) {
            throw new IllegalArgumentException();
        }
        byte[] privateKey = new byte[32];
        ECPoint publicKey=null;
        System.arraycopy(data, data.length - 32, privateKey, 0, 32);
        if (privateKey.length == 32) {
            publicKey = ECC.secp256r1.getG().multiply(new BigInteger(1, privateKey)).normalize();
        } else {
            byte[] encoded = new byte[65];
            encoded[0] = 0x04;
            System.arraycopy(privateKey, 0, encoded, 1, 64);
            publicKey = ECC.secp256r1.getCurve().decodePoint(encoded);
        }
        return publicKey;
    }

    public static void main(String[] args) {
        String input_privatekey="c7134d6fd8e73d819e82755c64c93788d8db0961929e025a53363c4cc02a6962";
        System.out.println("输入："+input_privatekey);
        byte[] privatekey=ConversionTool.hexStringToByte(input_privatekey);
        ECPoint publickey=getPublicKey(privatekey);
        byte[] data=publickey.getEncoded(true);
        System.out.println("输出（压缩型）："+ConversionTool.bytesToHex(data));
        data=publickey.getEncoded(false);
        System.out.println("输出（非压缩型）："+ConversionTool.bytesToHex(data));
    }
}
