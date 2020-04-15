package com.lckj.jycm.zfqg_network;

public class RecallTrafficCardRequest {

    /**
     * sign : 4AAB05A6637B9A2E3858BDEDE3116799
     * card_list : 100002,100003
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String card_list;
    private String token;

    public RecallTrafficCardRequest(String card_list, String token) {
        this.card_list = card_list;
        this.token = token;
    }

    public String getCard_list() {
        return card_list;
    }

    public void setCard_list(String card_list) {
        this.card_list = card_list;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
