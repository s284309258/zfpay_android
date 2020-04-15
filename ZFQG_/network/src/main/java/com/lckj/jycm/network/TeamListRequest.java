package com.lckj.jycm.network;

public class TeamListRequest {
    /**
     * currentPageNo : 1
     * sign : 91E82467144A8BBE3520C2128AF3CDAB
     * token : 11|JJDJRAOQRKHH2GDKC6I70WY2C7HDRWEW
     */

    private int currentPageNo;
    private String token;
    private String system_type = "getTeamList";

    public TeamListRequest(int currentPageNo, String token) {
        this.currentPageNo = currentPageNo;
        this.token = token;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
