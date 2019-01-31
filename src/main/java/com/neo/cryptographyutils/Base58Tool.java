package com.neo.cryptographyutils;

import java.math.BigInteger;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: Base58Tool
 * @Package com.neo.cryptographyutils
 * @Description: 对数据做Base58编码和解码
 * @date Created in 17:21 2018/10/9
 */
public class Base58Tool {
    /**
     *  base58编码的字母表
     */
    public static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final BigInteger BASE = BigInteger.valueOf(ALPHABET.length());


    /**
      * @Author:doubi.liu
      * @description:对字符串做base58解码
      * @param input 要解码的字符串
      * @return 返回解码后的字节数组
      * @date:2018/10/11
    */
    public static byte[] decode(String input) {
        BigInteger bi = BigInteger.ZERO;
        for (int i = 0; i < input.length(); i++) {
            int index = ALPHABET.indexOf(input.charAt(i));
            if (index == -1) {
                throw new IllegalArgumentException();
            }
            bi = bi.multiply(BASE).add(BigInteger.valueOf(index));
        }
        byte[] bytes = bi.toByteArray();
        boolean stripSignByte = bytes.length > 1 && bytes[0] == 0 && bytes[1] < 0;
        int leadingZeros = 0;
        for (; leadingZeros < input.length() && input.charAt(leadingZeros) == ALPHABET.charAt(0); leadingZeros++);
        byte[] tmp = new byte[bytes.length - (stripSignByte ? 1 : 0) + leadingZeros];
        System.arraycopy(bytes, stripSignByte ? 1 : 0, tmp, leadingZeros, tmp.length - leadingZeros);
        return tmp;
    }


    /**
      * @Author:doubi.liu
      * @description:对数据做base58编码
      * @param input 要编码的字节数组
      * @return 返回编码后的字符串
      * @date:2018/10/11
    */
    public static String encode(byte[] input) {
        BigInteger value = new BigInteger(1, input);
        StringBuilder sb = new StringBuilder();
        while (value.compareTo(BASE) >= 0) {
            BigInteger[] r = value.divideAndRemainder(BASE);
            sb.insert(0, ALPHABET.charAt(r[1].intValue()));
            value = r[0];
        }
        sb.insert(0, ALPHABET.charAt(value.intValue()));
        for (byte b : input) {
            if (b == 0) {
                sb.insert(0, ALPHABET.charAt(0));
            } else {
                break;
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        String data="17ad5cac596a1ef6c18ac1746dfd304f93964354b578a58322";
        System.out.println("输入加盐数据："+data);
        System.out.println("输出地址："+encode(ConversionTool.hexStringToByte(data)));
    }
}
