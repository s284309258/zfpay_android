package com.lcwl.qiniu;

public class GetTokenRequest {

    /**
     * system_type : qiniu_token
     * token : 43|HU385CV62GCYIKEC6OGC1IUQQIREUD6F
     * sign : 50C4A8F12F075EA010F1B2D0EF33D94D
     */

    private String system_type = "qiniu_token";
    private String token;

    public GetTokenRequest(String token) {
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
