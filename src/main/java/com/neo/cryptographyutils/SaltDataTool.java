package com.neo.cryptographyutils;

/**
 * @author doubi.liu
 * @version V1.0
 * @Title: SaltDataTool
 * @Package com.neo.cryptographyutils
 * @Description: 添加版本号形成预加盐数据
 * @date Created in 17:03 2018/10/9
 */
public class SaltDataTool {
    /**
      * @Author:doubi.liu
      * @description:添加版本号形成预加盐数据
      * @param byte[] 数据
      * @return 予加盐数据
      * @date:2018/10/11
    */
    public static byte[] addSalt(byte[] data){
        byte[] saltdata = new byte[21];
        saltdata[0] = 0x17;//Settings.Default.AddressVersion;
        System.arraycopy(data, 0, saltdata, 1,20);
        return saltdata;
    }

    public static void main(String[] args) throws Exception {
        String scripthash="ad5cac596a1ef6c18ac1746dfd304f93964354b5";
        System.out.println("输入合约脚本hash："+scripthash);
        byte[] saltdata=addSalt(ConversionTool.hexStringToByte(scripthash));
        System.out.println("输出预加盐数据："+ConversionTool.bytesToHex(saltdata));
    }
}
