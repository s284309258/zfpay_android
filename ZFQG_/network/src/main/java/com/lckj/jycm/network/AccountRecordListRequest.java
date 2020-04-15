package com.lckj.jycm.network;

public class AccountRecordListRequest {
    /**
     * queryType : 1
     * sign : 318987255E9CD72855133C4496AB665D
     * token : 11|2YD7KQ73PCYDWPWR84JZ5K1O3WRSI4AR
     */

    private int queryType;
    private String token;
    private String system_type = "showAccountRecordList";

    public AccountRecordListRequest(int queryType, String token) {
        this.queryType = queryType;
        this.token = token;
    }

    private String getSystem_type() {
        return system_type;
    }

    private void setSystem_type(String system_type) {
        this.system_type = system_type;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
