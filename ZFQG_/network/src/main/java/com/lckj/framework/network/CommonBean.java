package com.lckj.framework.network;

public class CommonBean extends HttpResponse {

    private String data;
    private String token;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
