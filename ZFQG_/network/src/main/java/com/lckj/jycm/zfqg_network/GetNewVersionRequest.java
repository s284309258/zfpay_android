package com.lckj.jycm.zfqg_network;

public class GetNewVersionRequest {
    /**
     * token : 3|7KBG2J44WRY0JMHJYD7VOTHQCM4LBKQL
     * device_type : android
     * sign : 8B81BFBCA95A2A7B307953F349B527F8
     */

    private String token;
    private String device_type;

    public GetNewVersionRequest(String token, String device_type) {
        this.token = token;
        this.device_type = device_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }
}
