package com.lckj.jycm.zfqg_network;

public class DateRequest {
    /**
     * date : 201908
     * sign : 643C6BF88DA1B76F0FE42B885399FEBF
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String date;
    private String token;
    private String last_id;
    private String pos_type;

    public DateRequest(String date, String token, String last_id, String pos_type) {
        this.date = date;
        this.token = token;
        this.last_id = last_id;
        this.pos_type = pos_type;
    }

    public DateRequest(String date, String token, String last_id) {
        this.date = date;
        this.token = token;
        this.last_id = last_id;
    }

    public DateRequest(String date, String token) {
        this.date = date;
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
