package com.lython.network.tools.interceptor;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MS5
 */
public class MD5Util {
	final static String md5key = "SPNbRiokxGNpAe0ysIEr732QdzcsofDe7O6HNkqd42IXPTJWy2ruCA4jNuwi9yZVryJP1Yrp";
	public static String MD5(String user, String token, String psw) {

		return Md5(Md5(user) + Md5(token) + psw);
	}

	public static String MD52(String user, String token) {

		return Md5(Md5(user) + Md5(token));
	}

	public static String Md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(buf != null){
			return buf.toString();
		}
		return null ;
	}
	
	public static String getMD5ofStr(String arg0, boolean isKeyRequired) {
        try {
        	arg0 = arg0.replaceAll(" ", "");
            if (isKeyRequired)  arg0 +="&key=" + md5key;
			if (Utils.DEBUG) Log.i("md5:",arg0);
            String digestHexStr = "";
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(arg0.getBytes());
            for (int i = 0; i < 16; i++) {
                digestHexStr += byteHEX(md5[i]);
            }
            return digestHexStr;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	 public static String byteHEX(byte byte0) {
	        char ac[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	        char ac1[] = new char[2];
	        ac1[0] = ac[byte0 >>> 4 & 0xf];
	        ac1[1] = ac[byte0 & 0xf];
	        String s = new String(ac1);
	        return s;
	    }
}
