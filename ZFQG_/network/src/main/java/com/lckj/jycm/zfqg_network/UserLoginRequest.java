package com.lckj.jycm.zfqg_network;

public class UserLoginRequest implements RSARequest{

    /**
     *  login_type : account
     *  sys_user_account  :  18772101525
     * login_password  :  hr2013125118
     */

    private String login_type;
    private String sys_user_account;
    private String login_password;
    private String token;
    private String device_type;
    private String device_no;
    private String version_no;

    public UserLoginRequest(String login_type, String sys_user_account, String login_password, String token, String device_type, String device_no, String version_no) {
        this.login_type = login_type;
        this.sys_user_account = sys_user_account;
        this.login_password = login_password;
        this.token = token;
        this.device_type = device_type;
        this.device_no = device_no;
        this.version_no = version_no;
    }
}
