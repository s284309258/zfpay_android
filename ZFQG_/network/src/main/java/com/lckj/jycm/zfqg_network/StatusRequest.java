package com.lckj.jycm.zfqg_network;

public class StatusRequest {

    /**
     * sign : 8AA015C0E1B20C1760245F94CD7A952C
     * token : 4|1HGOTLJ7XHXTMMPUNZ8PFKIDRTY8QC3P
     * status : 00
     */

    private String token;
    private String status;
    private String ids_list;
    private String pos_type;

    public StatusRequest(String token, String status, String ids_list, String pos_type) {
        this.token = token;
        this.status = status;
        this.ids_list = ids_list;
        this.pos_type = pos_type;
    }

    public StatusRequest(String token, String status, String ids_list) {
        this.token = token;
        this.status = status;
        this.ids_list = ids_list;
    }

    public StatusRequest(String token, String status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
