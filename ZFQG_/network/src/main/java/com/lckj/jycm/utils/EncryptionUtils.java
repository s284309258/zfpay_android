package com.lckj.jycm.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EncryptionUtils {

    /**
     * 获取加密后的参数字符串
     * @param params 含有nonce_str(随机字符串)的不为空的参数列表
     * @param key 密钥（10位及以上）
     * @return 加密后的字符串
     */
    public static String getSh1ParamsCode(Map<String,String> params,String key){
        if(params == null || params.size()<1 || key ==null || key.length()<9){
            return null;
        }
//        Map<String,String> orderedMap = sortMapByDicOrder(params);
//        Map<String,String> orderedMap = getFormatParams(params);
        String formatParams = getFormatParams(params);
        StringBuilder strTemp = new StringBuilder();
//        for(String mapKey: orderedMap.keySet()){
//            strTemp.append(mapKey + "=" + orderedMap.get(mapKey) + "&");
//        }
        strTemp.append("deviceKey=" + key);
        String s = formatParams + "&deviceKey=" + key;
//        System.out.println("加密，计算SHA1之前："+s);
        return getSha1(s).toUpperCase();
//        return getSha1("nonce=pGCIVav0qS5ahemZXAgrZ4BAALn0FtPOSVyiFFP23bGQTN9aS9&sn=ZD8EE2AE&timestamp=2019-05-16T18:45:14&deviceKey=5a88b9dae5123e8af3cc83ccf7d35ab0").toUpperCase();
    }

    public static String getFormatParams(Map<String,String> params){
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(params.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> arg0, Map.Entry<String, String> arg1) {
                return (arg0.getKey()).compareTo(arg1.getKey());
            }
        });
        String ret = "";
        for (Map.Entry<String, String> entry : infoIds) {
            ret += entry.getKey();
            ret += "=";
            ret += entry.getValue();
            ret += "&";
        }
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }

    /***
     * 将map的key按照字典序进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByDicOrder(Map<String, String> map) {
        if (map == null || map.size() < 1) {
            return null;
        }
        Map<String, String> returnMap = new HashMap<String, String>();
        Collection<String> keyset = map.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            returnMap.put(list.get(i), map.get(list.get(i)));
        }
        return returnMap;
    }

    /**
     * 根据长度生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     *     * 利用java原生的类实现SHA256加密
     *     
     *     * @return
     *     
     */
    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     *     * 将byte转为16进制
     *     * @param bytes
     *     * @return
     *     
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * sha1 加密方式
     * @param data
     * @return
     */
    public static String getSha1(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(data.getBytes("UTF-8"));
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte message : messageDigest) {
                String shaHex = Integer.toHexString(message & 0xFF);
                if (shaHex.length() < 2)
                    hexString.append(0);

                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    public static String MD5Encode(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(md.digest(data.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n >>> 4;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


}
