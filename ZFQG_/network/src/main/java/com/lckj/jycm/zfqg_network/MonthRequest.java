package com.lckj.jycm.zfqg_network;

public class MonthRequest {
    private String token;
    private String cre_month;
    private String user_id;
    private String pos_type;

    public MonthRequest(String token, String cre_month, String user_id, String pos_type) {
        this.token = token;
        this.cre_month = cre_month;
        this.user_id = user_id;
        this.pos_type = pos_type;
    }

    public MonthRequest(String token, String cre_month, String id) {
        this.token = token;
        this.cre_month = cre_month;
        this.user_id = id;
    }

    public MonthRequest(String token, String cre_month) {
        this.token = token;
        this.cre_month = cre_month;
    }
}
