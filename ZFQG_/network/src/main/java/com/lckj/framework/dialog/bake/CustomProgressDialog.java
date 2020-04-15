package com.lckj.framework.dialog.bake;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lython.network.R;


public class CustomProgressDialog {
    public Dialog mDialog;
    private AnimationDrawable animationDrawable = null;

    public CustomProgressDialog(Context context, String message) {
        this(context, message, true, 0, 0);
    }

    public CustomProgressDialog(Context context, String message, boolean cancelable){
        this(context, message, cancelable, 0, 0);
    }

    public CustomProgressDialog(Context context, String message, boolean cancelable, int bgResId, @StyleRes int styleId) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_progress_custom, null);

        View container = view.findViewById(R.id.ll_progress_custom_container);
        if(bgResId != 0){
            container.setBackgroundResource(bgResId);
        }

        TextView text = (TextView) view.findViewById(R.id.tv_progress_message);
        if (TextUtils.isEmpty(message))
            message = "请稍候...";
        text.setText(message);
        ImageView loadingImage = (ImageView) view.findViewById(R.id.progress_view);
        loadingImage.setImageResource(R.drawable.header_refresh);
        animationDrawable = (AnimationDrawable) loadingImage.getDrawable();
        if (animationDrawable != null) {
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        }
        int style = styleId == 0 ? R.style.Theme_AppCompat_Dialog : styleId;
        mDialog = new Dialog(context, style);
        mDialog.setCancelable(cancelable);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(cancelable);


    }

    public void show() {
        mDialog.show();
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
    }

    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
            animationDrawable.stop();
        }
    }

    public boolean isShowing() {
        if (mDialog.isShowing()) {
            return true;
        }
        return false;
    }


    public Dialog getDialog(){
        return mDialog;
    }
}