package com.lckj.jycm.zfqg_network;

public class LastIdRequest {
    /**
     * token : 1|E2J6S88XQ34NPJERQ02M0TMKYCJ0AUGJ
     * sign : D903DA71857377D4A7653ABFBD51B638
     */

    private String token;
    private String last_id;
    private String pos_type;

    public LastIdRequest(String token, String last_id, String pos_type) {
        this.token = token;
        this.last_id = last_id;
        this.pos_type = pos_type;
    }

    public LastIdRequest(String token, String last_id) {
        this.token = token;
        this.last_id = last_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
