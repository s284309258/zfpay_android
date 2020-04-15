package com.lckj.jycm.network;

public class SimpleRequest {

    /**
     * sign : BECB772395049464E8B11CB79CD15DF6
     * token : 11|OCOLG35SJSGKV5Q6WU2VGSA28UFRDDJD
     */

    private String token;
    private String system_type = "getMerchantInfo";

    public SimpleRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
