package com.lython.network.tools.interceptor;

import android.util.Log;

public class OEFunction {
    // Used to load the 'oeasy-core' library on application startup.
    /*static {
        System.loadLibrary("lcwl");
    }*/

    /**
     * A native method that is implemented by the 'oeasy-core' native library,
     * which is packaged with this application.
     */
    public static String signParams(String data){
//        MD5Util.getMD5ofStr(data,true);
        return MD5Util.getMD5ofStr(data,true);
    };


}
