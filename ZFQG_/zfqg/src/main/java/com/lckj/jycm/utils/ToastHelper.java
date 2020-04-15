package com.lckj.jycm.utils;

import android.widget.Toast;

import com.lckj.base.MainApplication;

public class ToastHelper {
    public static void showToast(String msg) {
        if (android.text.TextUtils.isEmpty(msg))
            return;
        Toast.makeText(MainApplication.getInstance().getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
