package com.lckj.framework.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StyleRes;

public class ProgressDlgHelper {

    private static SimpleProgressDialog sProcessDialog = null;

    public static void openDialog(Context context) {
        openDialog(context, null);
    }

    public static void openDialog(Context context, String msg) {
        openDialog(context, msg, true);
    }

    public static void openDialog(Context context, String msg, boolean cancelable) {
        openDialog(context,msg,cancelable,null);
    }

    public static void openDialog(Context context, String msg, boolean cancelable, int bgResId) {
        openDialog(context,msg,cancelable, bgResId, 0, null);
    }

    public static void openDialog(Context context, String msg, boolean cancelable, DialogInterface.OnDismissListener listener){
        openDialog(context, msg, cancelable, 0, 0, listener);
    }

    public static void openDialog(Context context, String msg, boolean cancelable, int bgResId, DialogInterface.OnDismissListener listener){
        openDialog(context, msg, cancelable, bgResId, 0, listener);
    }

    public static void openDialog(
            Context context,
            String msg,
            boolean cancelable,
            int bgResId,
            @StyleRes int styleResId,
            DialogInterface.OnDismissListener listener){
        if (sProcessDialog == null || !sProcessDialog.mDialog.isShowing()) {
            sProcessDialog = new SimpleProgressDialog(context, msg, cancelable, bgResId, styleResId);
            try {
                sProcessDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(listener != null){
                sProcessDialog.getDialog().setOnDismissListener(listener);
            }
        }
    }



    public static void closeDialog() {
        if (sProcessDialog != null) {
//            if (sProcessDialog.mDialog.isShowing()) {
            try {
                if(sProcessDialog.mDialog != null){
                    sProcessDialog.mDialog.dismiss();
                }
            } catch (Throwable ex) {  //避免会报PhoneWindow$DecorView not attached to window manager 2016.5.7 by wp.nine
                ex.printStackTrace();
            }
//            }
            sProcessDialog = null;
        }
    }
}
