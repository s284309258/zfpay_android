package com.lckj.jycm.zfqg_network;

public class UpdateUserCardRequest implements RSARequest{

    /**
     *  token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     *  user_card_oper :  user_card_add
     *  bank_code : 103100021172
     *  bank_name :  中国农业银行股份有限公司北京金融大街丰盛支行
     *  card_photo :  defaultAvatar.png,defaultAvatar.png
     * is_default :  1
     * account : 123456789012345678
     * pay_password :  hr2013125118
     */

    private String token;
    private String user_card_oper;
    private String bank_code;
    private String bank_name;
    private String card_photo;
    private String is_default;
    private String account;
    private String pay_password;
    private String card_id;

    public UpdateUserCardRequest(String token, String user_card_oper, String is_default, String card_id) {
        this.token = token;
        this.user_card_oper = user_card_oper;
        this.is_default = is_default;
        this.card_id = card_id;
    }

    public UpdateUserCardRequest(String token, String user_card_oper, String bank_code, String pay_password, String card_id) {
        this.token = token;
        this.user_card_oper = user_card_oper;
        this.pay_password = pay_password;
        this.card_id = card_id;
    }

    public UpdateUserCardRequest(String token, String user_card_oper, String bank_code, String bank_name, String card_photo, String is_default, String account, String pay_password) {
        this.token = token;
        this.user_card_oper = user_card_oper;
        this.bank_code = bank_code;
        this.bank_name = bank_name;
        this.card_photo = card_photo;
        this.is_default = is_default;
        this.account = account;
        this.pay_password = pay_password;
    }
}
