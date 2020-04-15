package com.lckj.jycm.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.lckj.jycm.R;
import com.lckj.jycm.home.TaskSignAdatper;

public class CustomDialog extends Dialog {

    private final int mDialog_layout;

    public CustomDialog(int dialog_layout, @NonNull Context context, int theme) {
        super(context, theme);
        mDialog_layout = dialog_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mDialog_layout);
    }

    public void setData(String money) {
        TextView tv_coin = (TextView) findViewById(R.id.tv_coin);
        TextView tv_money = (TextView) findViewById(R.id.tv_money);
        tv_coin.setText(money);
        tv_money.setText("+" + money);
    }
}
