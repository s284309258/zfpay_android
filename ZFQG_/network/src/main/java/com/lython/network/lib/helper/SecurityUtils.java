package com.lython.network.lib.helper;

import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lython.network.tools.interceptor.OEFunction;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {

    public static final String AES_ALGORITHM = "AES";
    private static final String DIGEST_ALGORITHM = "MD5";
    private static final String CIPHER_ALGORITHM = "AES/ECB/NoPadding";

    public static byte[] encrypt(byte[] key, byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, AES_ALGORITHM));
        return cipher.doFinal(input);
    }


    public static byte[] longToByte(long number){
        long temp = number;
        byte[] b =new byte[5];
        for(int i =b.length-1; i >=0 ; i--){
            b[i]=new Long(temp &0xff).byteValue();//
            //将最低位保存在最低位
            temp = temp >>8;// 向右移8位
        }
        return b;
    }

    /**
     * 整数转字节数组
     *
     * @param n
     * @return
     */
    public static byte[] int2Bytes(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public static byte[] int2BytesEle(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n >> 24 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[3] = (byte) (n & 0xff);
        return b;
    }


    public static byte[] int2EBytes(int n) {
        byte[] b = new byte[8];
        b[0] = (byte) (n >> 56 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[7] = (byte) (n & 0xff);
        return b;
    }

    public static byte[] int2BytesE(int n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n & 0xff);
        return b;
    }
    public static byte[] int2BytesT(int n) {
        byte[] b = new byte[3];
        b[0] = (byte) (n >> 16 & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n & 0xff);
        return b;
    }
    /**
     * 字节数组转整数,bytes数组为小端派结构，即字节排序按低到高来排
     *
     * @param bytes
     * @return
     */
    public static long bytes2Long(byte[] bytes) {
        long num = 0;
        for (int i = 0; i < bytes.length; i++) {
            num = num | ((0xFF & bytes[i]) << (i * 8));
        }
        return num;
    }

    /**
     * 字节数组转整数,bytes数组为大端派结构，即字节排序按高到低来排
     *
     * @param bytes
     * @return
     */
    public static long bytes2LongLarge(byte[] bytes) {
        long num = 0;
        for (int i = 0; i < bytes.length; i++) {
            num = num | ((0xFF & bytes[i]) << ((bytes.length-i - 1) * 8));
        }
        return num;
    }



    /**
     * 整数转字节数组
     *
     * @param n
     * @return
     */
    public static byte[] short2byte(short n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) (n >> 8 & 0xff);
        return b;
    }
    /**字节数组转整数,bytes数组为大端派结构，即字节排序按低到高来排
     * */
    public static int bytes2int(byte[] bytes){
        int num = 0;
        num = num | bytes[0] << 24;
        num = num | bytes[1] << 16;
        num = num | bytes[2] << 8;
        num = num | bytes[3];
        return num;
    }


    /**
     * Utility method to convert a byte array to a hexadecimal string.
     *
     * @param bytes Bytes to convert
     * @return String, containing hexadecimal representation.
     */
    public static String byteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2]; // Each byte has two hex characters (nibbles)
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF; // Cast bytes[j] to int, treating as unsigned value
            hexChars[j * 2] = hexArray[v >>> 4]; // Select hex character from upper nibble
            hexChars[j * 2 + 1] = hexArray[v & 0x0F]; // Select hex character from lower nibble
        }
        return new String(hexChars);
    }

    /**
     * Utility method to convert a hexadecimal string to a byte string.
     * <p/>
     * <p>Behavior with input strings containing non-hexadecimal characters is undefined.
     *
     * @param s String containing hexadecimal characters to convert
     * @return Byte array generated from input
     * @throws IllegalArgumentException if input length is incorrect
     */
    public static byte[] hexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2]; // Allocate 1 byte per 2 hex characters
        for (int i = 0; i < len; i += 2) {
            // Convert each character into a integer (base-16), then bit-shift into place
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static char[] hexStringToBitArray(String s) throws IllegalArgumentException {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(256);
        byte[] rawArray = hexStringToByteArray(s);
        for (byte i = 0; i < rawArray.length; i++) {
            sb.append(byteToBit(rawArray[i]));
        }
        return sb.toString().trim().toCharArray();
    }

    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }

    /**
     * Utility method to concatenate two byte arrays.
     *
     * @param first First array
     * @param rest  Any remaining arrays
     * @return Concatenated copy of input arrays
     */
    public static byte[] concatArrays(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * Utility method to generate md5
     *
     * @param input
     * @return
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String genMd5(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM);
        return byteArrayToHexString(md.digest(input)).toLowerCase();
    }


    /**
     * @param rawData
     * @param len
     * @return
     */
    public static byte[] formatData(byte[] rawData, int len) {
        int realLen = rawData.length;

        if (realLen == len) {
            return rawData;
        }

        if (realLen > len) {
            len += 16;
            return formatData(rawData, len);
        }

        byte[] newStr = new byte[len];
        int addLen = len - realLen;

        for (int i = 0; i < len; ++i) {
            if (i < addLen) {
                newStr[i] = ' ';
            } else {
                newStr[i] = rawData[i - addLen];
            }
        }

        return (newStr);
    }

    /**
     * @param rawStr
     * @param len
     * @return
     */
    public static byte[] formatData(String rawStr, int len) {
        return formatData(rawStr.getBytes(), len);
    }



    public static LinkedList<KeyValue> sortParam(Map<String, Object> params) {
        LinkedList<KeyValue> pairs = new LinkedList<KeyValue>();
        ArrayList<String> temp = new ArrayList<String>();
        for (String key : params.keySet()) {
            temp.add(key);
        }
        temp.trimToSize();
        Collections.sort(temp);
        Gson gson = new Gson();
        for (String string : temp) {
            Object valueObj = params.get(string);
            String value = "";
            if (valueObj != null) {
                if (valueObj instanceof Integer || valueObj instanceof String || valueObj instanceof Double || valueObj instanceof Float
                        || valueObj instanceof Long || valueObj instanceof Boolean || valueObj instanceof Byte || valueObj instanceof Character) {
                    value = valueObj.toString();
                } else {
                    value = gson.toJson(valueObj);
                }
            }
            pairs.add(new KeyValue(string, value));
        }
        return pairs;
    }


    public static String signParams(Map<String, Object> params) {
        LinkedList<KeyValue> paramList = sortParam(params);
        StringBuilder sb = new StringBuilder();

        for (KeyValue pair : paramList) {
            if (!TextUtils.isEmpty(pair.getValue()))
                sb.append(pair.getKey()).append("=").append(pair.getValue()).append("&");
        }
        if(paramList.size() > 0){
            sb.deleteCharAt(sb.length() - 1);
        }
        return OEFunction.signParams(sb.toString());
    }

    public static byte str2IntByte(String str, int startPos, int len){
        byte back = 0;
        if(TextUtils.isEmpty(str)) return back;
        if(str.length() <= startPos || str.length() < (startPos + len)){
            return back;
        }
        try{
            String temp = str.substring(startPos, startPos + len);
            back = (byte) (Integer.parseInt(temp, 10) & 0xFF);
        }catch (Exception e){
            e.printStackTrace();
        }
        return back;
    }


    public static class KeyValue {
        private String key;
        private String value;

        public KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
