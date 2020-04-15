package com.lckj.framework.rxjava;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.lckj.framework.dialog.ProgressDlgHelper;

import chat.base.NetWorkFragment;
import io.reactivex.functions.Consumer;

import com.lcwl.base.NetworkApplication;
import com.lython.network.R;
import com.lython.network.tools.interceptor.Utils;

import java.lang.ref.WeakReference;

//import rx.functions.Action1;
//implements Action1<Throwable>

public class ThrowableConsumer<T> implements Consumer<Throwable> {

    private WeakReference<Context> mWRefCtx;
    private WeakReference<NetWorkFragment> mWRefFragment;

    public ThrowableConsumer(Context context) {
        mWRefCtx = new WeakReference<>(context);
    }

    public ThrowableConsumer(NetWorkFragment fragment) {
        this.mWRefFragment = new WeakReference<NetWorkFragment>(fragment);
    }


    @Override
    public void accept(Throwable throwable) {
        if (this.mWRefCtx != null) {
            Context context = this.mWRefCtx.get();
            if (context == null) {
                return;
            } else if (context instanceof FragmentActivity) {
                if (((FragmentActivity) context).isDestroyed()) {
                    return;
                }
            }
        } else if (mWRefFragment != null &&
                (mWRefFragment.get() == null ||
                        (mWRefFragment.get() != null && mWRefFragment.get().isViewDestroy()))) {
            return;
        }
        if (throwable != null) {
            throwable.printStackTrace();
            Log.e("REQUEST", "Something get err!==>" + "Please hand it!");
        }
        onError(throwable);
    }

    public Context getContext() {
        if (mWRefCtx != null) {
            return mWRefCtx.get();
        } else {
            return mWRefFragment.get().getActivity();
        }
    }

    public void onError(Throwable throwable) {
        ProgressDlgHelper.closeDialog();
        Context context = getContext();
        if (context != null) {
            if (throwable.getCause() != null && throwable.getCause().getMessage().equals("passwordCheckErr")) {

            } else {
                Utils.showMsg(getContext(), getErrorMsg(throwable));
            }
        }
    }

    public String getErrorMsg(Throwable throwable) {
        int resId = R.string.msg_service_error;
        return NetworkApplication.getAppContext().getString(resId);
    }
}
