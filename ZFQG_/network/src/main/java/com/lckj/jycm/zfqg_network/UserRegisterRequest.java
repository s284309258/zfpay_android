package com.lckj.jycm.zfqg_network;

public class UserRegisterRequest implements RSARequest{

    /**
     * sys_user_account :  18772101525
     * login_password : hr2013125118
     * pay_password : 123456
     * invite_code :  18772101521
     * sms_code :  848403
     *  device_type :  iOS
     * version_no :  1.0.1
     */

    private String sys_user_account;
    private String login_password;
    private String pay_password;
    private String invite_code;
    private String sms_code;
    private String device_type;
    private String version_no;

    public UserRegisterRequest(String sys_user_account, String login_password, String pay_password, String invite_code, String sms_code, String device_type, String version_no) {
        this.sys_user_account = sys_user_account;
        this.login_password = login_password;
        this.pay_password = pay_password;
        this.invite_code = invite_code;
        this.sms_code = sms_code;
        this.device_type = device_type;
        this.version_no = version_no;
    }

    public String getSys_user_account() {
        return sys_user_account;
    }

    public void setSys_user_account(String sys_user_account) {
        this.sys_user_account = sys_user_account;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public String getPay_password() {
        return pay_password;
    }

    public void setPay_password(String pay_password) {
        this.pay_password = pay_password;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }
}
