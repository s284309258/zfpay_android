package com.lckj.jycm.zfqg_network;

public class SnRequest {

    /**
     * sign : 8954FD8BA621F99AF433F38E0C77D875
     * sn : 95217293
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String sn;
    private String token;
    private String mer_name;
    private String pos_type;

    public SnRequest(String sn, String token, String mer_name, String pos_type) {
        this.sn = sn;
        this.token = token;
        this.mer_name = mer_name;
        this.pos_type = pos_type;
    }

    public SnRequest(String sn, String token, String mer_name) {
        this.sn = sn;
        this.token = token;
        this.mer_name = mer_name;
    }

    public SnRequest(String sn, String token) {
        this.sn = sn;
        this.token = token;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
