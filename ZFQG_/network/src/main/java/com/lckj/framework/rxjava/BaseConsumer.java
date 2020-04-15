package com.lckj.framework.rxjava;

import android.content.Context;

import chat.base.NetWorkFragment;


public abstract class BaseConsumer<T> extends CHttpBaseConsumer<T> {


    public BaseConsumer(Context context) {
        super(context);
    }

    public BaseConsumer(Context context, boolean autoShowToast) {
        super(context, autoShowToast);
    }

    public BaseConsumer(NetWorkFragment fragment) {
        super(fragment);
    }


}