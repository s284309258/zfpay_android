package com.lckj.jycm.zfqg_network;

public class ModifyPayPassRequest implements RSARequest{

    /**
     *  token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     *  pay_password  :  hr2013125118
     *  sms_code :  250970
     */

    private String token;
    private String pay_password;
    private String sms_code;

    public ModifyPayPassRequest(String token, String pay_password, String sms_code) {
        this.token = token;
        this.pay_password = pay_password;
        this.sms_code = sms_code;
    }
}
