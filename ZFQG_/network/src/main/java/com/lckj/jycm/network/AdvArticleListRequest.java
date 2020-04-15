package com.lckj.jycm.network;

public class AdvArticleListRequest {

    private int queryType;
    /**
     * page : {"currentPageNo":1,"pageSize":10}
     * status : 1
     * token : 11|VJA9CDV8Z2XXQJBCAWHZI02AKCY43I87
     */

    private String system_type = "showAdvArticleList";
    private int status;
    private String token;

    public AdvArticleListRequest(int page, int status, String token,int queryType) {
        this.currentPageNo = page;
        this.status = status;
        this.token = token;
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

    private int currentPageNo;


//        private int pageSize;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }
}
