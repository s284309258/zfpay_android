package com.lckj.jycm.network;

public class AdvInfoListRequest {
    /**
     * currentPageNo : 1
     * queryType : 1
     * pageSize : 10
     * status : 1
     * sign : 3E4CB126D1F16332483A2E0E1B25E3D3
     * token : 11|ENV6DOGA71VHEUS9OBKLXX670BJIXD08
     */

    private int currentPageNo;
    private String system_type = "showAppAdvInfoList";
    private int queryType;
    private int status;
    private String token;

    public AdvInfoListRequest(int currentPageNo, int queryType, int status, String token) {
        this.currentPageNo = currentPageNo;
        this.queryType = queryType;
        this.status = status;
        this.token = token;
    }

    private String getSystem_type() {
        return system_type;
    }

    private void setSystem_type(String system_type) {
        this.system_type = system_type;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
