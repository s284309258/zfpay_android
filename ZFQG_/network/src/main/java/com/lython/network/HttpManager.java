package com.lython.network;

import android.content.Context;


public class HttpManager {
    private static Context sAppContext;
    public static void init(Context context){
        sAppContext = context;
    }

    public static Context getAppContext(){
        return sAppContext;
    }
}
