package com.lckj.jycm.zfqg_network;

public class AllocationTrafficCardRequest {

    /**
     * sign : 3FF0F8FAA2C81B55CBE7F5A085C40D6C
     * card_list : 100002,100003
     * acce_user_id : 4
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String card_list;
    private String acce_user_id;
    private String token;

    public AllocationTrafficCardRequest(String card_list, String acce_user_id, String token) {
        this.card_list = card_list;
        this.acce_user_id = acce_user_id;
        this.token = token;
    }

    public String getCard_list() {
        return card_list;
    }

    public void setCard_list(String card_list) {
        this.card_list = card_list;
    }

    public String getAcce_user_id() {
        return acce_user_id;
    }

    public void setAcce_user_id(String acce_user_id) {
        this.acce_user_id = acce_user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
