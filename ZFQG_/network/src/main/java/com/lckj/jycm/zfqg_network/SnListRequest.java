package com.lckj.jycm.zfqg_network;

public class SnListRequest {

    /**
     * sign : 8DB086E8AAFF2ABCAF4152E8A4279B5E
     * sn_list : 91581156,95476182
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String sn_list;
    private String token;
    private String pos_type;

    public SnListRequest(String sn_list, String token, String pos_type) {
        this.sn_list = sn_list;
        this.token = token;
        this.pos_type = pos_type;
    }

    public SnListRequest(String sn_list, String token) {
        this.sn_list = sn_list;
        this.token = token;
    }

    public String getSn_list() {
        return sn_list;
    }

    public void setSn_list(String sn_list) {
        this.sn_list = sn_list;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
