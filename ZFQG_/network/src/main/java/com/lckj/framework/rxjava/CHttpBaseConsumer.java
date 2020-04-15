package com.lckj.framework.rxjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.lckj.framework.dialog.ProgressDlgHelper;
import com.lckj.framework.network.ApiUtil;
import com.lckj.framework.network.HttpResponse;
import com.lckj.framework.network.BaseResponse;
import com.lckj.framework.network.CBaseConst;
import com.lckj.jycm.network.NewVersionInfoBean;
import com.lckj.jycm.network.WithoutLoginPage;
import com.lcwl.base.NetworkApplication;
import com.lython.network.tools.interceptor.Utils;

import java.lang.ref.WeakReference;

import chat.base.NetWorkFragment;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public abstract class CHttpBaseConsumer<T> implements Consumer<T> {
    private WeakReference<Context> mContext;
    private Gson gson = new Gson();
    private WeakReference<NetWorkFragment> mFragment;
    private boolean mAutoShowToast = true;

    public CHttpBaseConsumer(Context context) {
        this.mContext = new WeakReference<Context>(context);
    }

    public CHttpBaseConsumer(Context context, boolean autoShowToast) {
        this.mContext = new WeakReference<Context>(context);
        mAutoShowToast = autoShowToast;
    }

    /**
     * 支持Fragment的生命周期关联，使用Fragment时最好用该方法关联
     */
    public CHttpBaseConsumer(NetWorkFragment fragment) {
        this.mFragment = new WeakReference<NetWorkFragment>(fragment);
    }

    //
    @Override
    public void accept(T response) {
        if (this.mContext != null) {
            Context context = this.mContext.get();
            if (context == null) {
                return;
            } else if (context instanceof Activity) {
                if (((Activity) context).isFinishing()) {
                    return;
                }
            }
        } else if (this.mFragment != null &&
                (this.mFragment.get() == null || (this.mFragment.get() != null && this.mFragment.get().isViewDestroy()))) {
            return;
        }

        if (response == null) {
            onFailedCall(response);
        } else if (response instanceof HttpResponse) {             //对BaseResponse的处理
            HttpResponse baseResponse = (HttpResponse) response;
//            Log.i("RESP","response = "+gson.toJson(baseResponse));//不要在主线程中做多余的操作 2016.3.31 by wp.nine
            if (baseResponse.isSuccess()) { //说明成功
                onSuccessCall(response);
            } else {
                onFailedCall(response);
            }
        } else if (response instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) response;
            if (Utils.DEBUG) {
                try {
                    Log.i("RESP", "response = " + gson.toJson(baseResponse));
                } catch (Throwable throwable) {
                }
            }
            if (ApiUtil.isSuccessful(baseResponse)) {
                onSuccessCall(response);
            } else {
                onFailedCall(response);
            }
        } else if (response instanceof ResponseBody) {
            if (response == null) {
                onFailedCall(response);
            } else {
                onSuccessCall(response);
            }
        } else {
            onSuccessCall(response);
//            int i = 0/0;            //这里故意抛出异常，用于通知用错类来使用
        }
        onCompletedCall(response);
    }

    public void call(T response) {

    }

    /**
     * 状态成功时会走向这里
     */
    public abstract void onSuccessCall(T response);

    /**
     * 状态非成功时会走向这里，提供默认的实现，如果有需要可进行重写
     */
    public void onFailedCall(T response) {
        ProgressDlgHelper.closeDialog();
        if (response == null) {
            if (!mAutoShowToast) return;
            Utils.showMsg(getContext(), "请求数据失败！");
        } else if (response instanceof BaseResponse) {
            handleDataFailed((BaseResponse) response);
        } else if (response instanceof HttpResponse) {
            handleBaseFailed((HttpResponse) response);
        }
    }


    private void handleBaseFailed(HttpResponse response) {
        if(response!=null&&"code_999990".equals(response.getCode())){
            if (!(getContext() instanceof WithoutLoginPage)) {
                NetworkApplication instance = NetworkApplication.getInstance();
                instance.coloseActivitys();
                getContext().startActivity(new Intent().setAction("com.lckj.zfqg.login"));
//                getContext().startActivity(new Intent().setAction("android.intent.action.PwLoginActivity"));
            }
        }
        if(response!=null&&!TextUtils.isEmpty(response.getMsg())){
            showMsg(response.getMsg());
        } else {
            showMsg("请求数据失败！");
        }

    }

    //无论成功或失败都会走向这里，可用于统一处理失败和成功都需要处理的事件
    public void onCompletedCall(T response) {

    }

    private void handleDataFailed(BaseResponse response) {
        switch (response.getCode()) {
            case CBaseConst.API_CODE_UNAUTH:
                onAccountUnvaliable();
                break;
            default:
                showMsg(response.getDesc());
                break;
        }
    }


    protected void onAccountUnvaliable() {
        Intent intent = new Intent("com.xxx.xxx.user.LOGIN_OUT");
        intent.setPackage(getContext().getPackageName());
        getContext().sendBroadcast(intent);
    }


    private void showMsg(int str) {
        Context context = getContext();
        if (context != null) {
            Utils.showMsg(context, str);
        }
    }

    private void showMsg(String msg) {
        if (!mAutoShowToast) return;
        Context context = getContext();
        if (context != null) {
            if (msg == null) msg = "";
            Utils.showMsg(context, msg);
        }
    }

    public void showSuccessMsg(String msg) {
        Context context = getContext();
        if (context != null) {
            if (msg == null) msg = "";
            Utils.showMsg(context, msg);
        }
    }

    protected Context getContext() {
        if (mContext != null) {
            return mContext.get();
        } else {
            return mFragment.get().getActivity();
        }
    }


}
