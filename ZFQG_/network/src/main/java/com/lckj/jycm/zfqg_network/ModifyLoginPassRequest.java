package com.lckj.jycm.zfqg_network;

public class ModifyLoginPassRequest implements RSARequest{
    /**
     *  token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * login_password  :  hr2013125118
     *  sms_code :  068360
     */

    private String token;
    private String login_password;
    private String sms_code;

    public ModifyLoginPassRequest(String token, String login_password, String sms_code) {
        this.token = token;
        this.login_password = login_password;
        this.sms_code = sms_code;
    }
}
