package com.lckj.framework.network;

import android.text.TextUtils;

public class HttpResponse extends BaseModel {

    protected String msg;
    protected boolean success = false;
    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpResponse() {

    }

    public HttpResponse(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }


    public String getMsg() {
        if (TextUtils.isEmpty(msg))msg = "";
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}