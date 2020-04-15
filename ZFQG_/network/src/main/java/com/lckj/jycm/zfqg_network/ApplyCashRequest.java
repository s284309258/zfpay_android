package com.lckj.jycm.zfqg_network;

public class ApplyCashRequest implements RSARequest{
    /**
     *  token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     *  cash_money :  800
     *  card_id :  1452
     * pay_password :  hr2013125118
     */

    private String token;
    private String cash_money;
    private String card_id;
    private String pay_password;

    public ApplyCashRequest(String token, String cash_money, String card_id, String pay_password) {
        this.token = token;
        this.cash_money = cash_money;
        this.card_id = card_id;
        this.pay_password = pay_password;
    }
}
