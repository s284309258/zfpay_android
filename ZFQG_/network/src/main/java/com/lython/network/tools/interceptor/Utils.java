package com.lython.network.tools.interceptor;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.lython.network.BuildConfig;

import java.net.Proxy;

public class Utils {
    public static final boolean DEBUG = "debug".equals(BuildConfig.BUILD_TYPE);

    public static String getVersionName(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "V1.0.0";
        }
    }

    public static void showMsg(Context context, int resId) {
        showMsg(context, context.getString(resId));
    }
    private static Toast mToast;
    private static boolean isShowingToast = false;
    private static String mLastToastMsg = null;
    private static final int WHAT_TOAST_RELEASE = 1;
    private static Handler sHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_TOAST_RELEASE:
                    isShowingToast = false;
                    break;
            }
        }
    };
    public static void showMsg(Context context1, CharSequence msg) {
        Context context = context1.getApplicationContext();
        if (msg == null) {
            return;
        }
        String result = msg.toString();
        if (!isShowingToast || mLastToastMsg == null || !mLastToastMsg.equals(result)) {//若是不一致的信息，则必须显示
            isShowingToast = true;
            mLastToastMsg = result;
            if (null == mToast) {
                mToast = Toast.makeText(context, result, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
            sHandler.removeMessages(WHAT_TOAST_RELEASE);
            sHandler.sendEmptyMessageDelayed(WHAT_TOAST_RELEASE, 1500);
        }

    }

    public static void releaseToast() {
        if (null != mToast) {
            mToast.cancel();
            isShowingToast = false;
            mToast = null;
        }
    }
}
