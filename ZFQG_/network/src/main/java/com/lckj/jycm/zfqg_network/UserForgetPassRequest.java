package com.lckj.jycm.zfqg_network;

public class UserForgetPassRequest implements RSARequest{
    /**
     *  sys_user_account  :  18772101525
     *  login_password :  xyz13607252249
     *  sms_code :  435558
     */

    private String sys_user_account;
    private String login_password;
    private String sms_code;

    public UserForgetPassRequest(String sys_user_account, String login_password, String sms_code) {
        this.sys_user_account = sys_user_account;
        this.login_password = login_password;
        this.sms_code = sms_code;
    }
}
