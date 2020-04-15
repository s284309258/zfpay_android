package com.lckj.jycm.zfqg_network;

public class ModifyTelSecondRequest implements RSARequest{

    /**
     *  token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     *  sys_user_account :  18772101527
     *  valid_flag :  20190820174641114642743
     *  sms_code :  250970
     */

    private String token;
    private String sys_user_account;
    private String valid_flag;
    private String sms_code;

    public ModifyTelSecondRequest(String token, String sys_user_account, String valid_flag, String sms_code) {
        this.token = token;
        this.sys_user_account = sys_user_account;
        this.valid_flag = valid_flag;
        this.sms_code = sms_code;
    }
}
