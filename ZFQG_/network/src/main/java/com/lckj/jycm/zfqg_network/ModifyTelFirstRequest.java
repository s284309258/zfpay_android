package com.lckj.jycm.zfqg_network;

public class ModifyTelFirstRequest implements RSARequest{
    /**
     *  token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     *  sms_code :  250970
     */

    private String token;
    private String sms_code;

    public ModifyTelFirstRequest(String token, String sms_code) {
        this.token = token;
        this.sms_code = sms_code;
    }
}
