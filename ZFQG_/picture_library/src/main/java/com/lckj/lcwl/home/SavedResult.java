package com.lckj.lcwl.home;

import android.content.Intent;

class SavedResult {
    private final int requestCode;
    private final int resultCode;
    private final Intent data;

    public SavedResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }
    /*public SavedResult(int requestCode, int resultCode, Intent data) {

    }*/
}
