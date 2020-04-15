package com.lython.network.model;


import android.os.Environment;

import java.io.File;

public class Const {
    public static final String SignField = "sign";//存放http请求的签名字段
    public static final int SHOP_SIGN = 1;    //电商签名类型
    public static final String TID_ANDROID = "1";
    /*public static final String FIELD_TID = "ttid";

    public static final String FIELD_VERSION = "v";

    public static final int OKCODE = 22000;
    public static final int NO_DATA_CODE = 24041;
    public static final int NO_DATA_CODE_CAR = 24127;

    public static final String API_CODE_SUCCESS = "200";//成功
    public static final String API_CODE_UNAUTH = "403"; //未授权*/

    public static final int SORT_TOGE = 1;
    public static final int SORT_SALE = 2;
    public static final int SORT_PRICE = 3;
    public static final String UPLOAD_TOKEN_BIZ_COMMON = "common";//公共
    public static final String UPLOAD_TOKEN_BIZ_VIDEO = "video";
    public static final String AppUserDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lwcl/";

    public static String getCacheImageDir() {
        String cacheImageDir = Const.AppUserDir + "imgcache/";
        File dir = new File(cacheImageDir);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        return cacheImageDir;
    }
}
