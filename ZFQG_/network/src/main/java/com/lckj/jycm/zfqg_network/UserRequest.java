package com.lckj.jycm.zfqg_network;

public class UserRequest {
    private String token;
    private String user_id;
    private String last_id;
    private String trade_status;
    private String pos_type;

    public UserRequest(String token, String user_id, String last_id, String trade_status, String pos_type) {
        this.token = token;
        this.user_id = user_id;
        this.last_id = last_id;
        this.trade_status = trade_status;
        this.pos_type = pos_type;
    }

    public UserRequest(String token, String user_id, String last_id, String trade_status) {
        this.token = token;
        this.user_id = user_id;
        this.last_id = last_id;
        this.trade_status = trade_status;
    }

    public UserRequest(String token, String user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public UserRequest(String token, String user_id, String last_id) {
        this.token = token;
        this.user_id = user_id;
        this.last_id = last_id;
    }
}
