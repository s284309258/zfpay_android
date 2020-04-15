package com.lckj.jycm.zfqg_network;

public class GetMoneyLockerCollegeDetailRequest {

    /**
     * sign : 1C38D0512830AA4A00C157E4FB7493D2
     * money_locker_id : 2
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String money_locker_id;
    private String token;

    public GetMoneyLockerCollegeDetailRequest(String money_locker_id, String token) {
        this.money_locker_id = money_locker_id;
        this.token = token;
    }

    public String getMoney_locker_id() {
        return money_locker_id;
    }

    public void setMoney_locker_id(String money_locker_id) {
        this.money_locker_id = money_locker_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
