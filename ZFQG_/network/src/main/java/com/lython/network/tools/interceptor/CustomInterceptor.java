package com.lython.network.tools.interceptor;

import android.util.Log;

import com.lython.network.HttpManager;
import com.lython.network.model.Const;
import com.lython.network.lib.helper.SecurityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().toString();
        Request.Builder builder = request.newBuilder();
        if ("GET".equals(request.method())) {
            if (url.contains("?")) {
                url = url + "&v=" + Utils.getVersionName(HttpManager.getAppContext()) + "&device=android&ttid=" + Const.TID_ANDROID;
            } else {
                url = url + "?v=" + Utils.getVersionName(HttpManager.getAppContext()) + "&device=android&ttid=" + Const.TID_ANDROID;
            }
            HashMap hashMap = new HashMap();
            String param = url.split("[?]")[1];
            String[] strs = param.split("&");
            for (String str : strs) {
                String[] keyvalue = str.split("=");
                if (2 == keyvalue.length) {
                    hashMap.put(keyvalue[0], URLDecoder.decode(keyvalue[1]));
                } else {
                    hashMap.put(keyvalue[0], null);
                }
            }
            String testUrl = url + "&sign=" + SecurityUtils.signParams(hashMap);
            if(Utils.DEBUG){
                Log.i("REQU", "request url = " + testUrl);
            }
            builder.url(testUrl);
        } else {
            if(Utils.DEBUG){
                Log.i("REQU", "request post url = " + request.method()+url);
            }
        }
        try{
            return chain.proceed(builder.build());
        }catch (Throwable throwable){
            throw new IOException("requestFailed " + url,throwable);//增加接口的封装，如果接口挂了并且没有做异常处理，则可以知道哪里错误 by wp.nine
        }
    }
}
