package com.lcwl.qiniu;

public class UploadRequest {
    public String biz;        //app标识，现在定义了oeasy
    public String json;         //省编码,获取小区启动页时也可以不传
    private String xid;
    private String isCallBack;

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getIsCallBack() {
        return isCallBack;
    }

    public void setIsCallBack(String isCallBack) {
        this.isCallBack = isCallBack;
    }
}
