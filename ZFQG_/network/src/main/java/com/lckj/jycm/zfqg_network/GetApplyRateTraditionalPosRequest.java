package com.lckj.jycm.zfqg_network;

public class GetApplyRateTraditionalPosRequest {
    /**
     * sign : FE4FBF40C3D491D2A7E936B2BE1B9F1F
     * sn_list : 95476182,92308252
     * credit_card_rate : 0.62
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String sn_list;
    private String credit_card_rate;
    private String token;
    private String pos_type;

    public GetApplyRateTraditionalPosRequest(String sn_list, String credit_card_rate, String token, String pos_type) {
        this.sn_list = sn_list;
        this.credit_card_rate = credit_card_rate;
        this.token = token;
        this.pos_type = pos_type;
    }

    public GetApplyRateTraditionalPosRequest(String sn_list, String credit_card_rate, String token) {
        this.sn_list = sn_list;
        this.credit_card_rate = credit_card_rate;
        this.token = token;
    }

    public String getSn_list() {
        return sn_list;
    }

    public void setSn_list(String sn_list) {
        this.sn_list = sn_list;
    }

    public String getCredit_card_rate() {
        return credit_card_rate;
    }

    public void setCredit_card_rate(String credit_card_rate) {
        this.credit_card_rate = credit_card_rate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
