package com.lckj.jycm.network;

public class NewVersionInfoRequest {

    /**
     * system_type : getNewVersionInfo
     * token : 38|9UOL25ZZFDV2C60MLLSACJO4GHMGRL12
     */

    private String system_type = "getNewVersionInfo";
    private String token;

    public NewVersionInfoRequest(String token) {
        this.token = token;
    }

    public String getSystem_type() {
        return system_type;
    }

    public void setSystem_type(String system_type) {
        this.system_type = system_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
