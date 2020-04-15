package com.lckj.jycm.zfqg_network;

public class SendSmsCodeTokenRequest implements RSARequest {

    /**
     * token  :  3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * bus_type :  FrontModifyPayPass
     * img_id : 20190820165016605644053
     * img_code : L8UI
     */

    private String token;
    private String bus_type;
    private String img_id;
    private String img_code;
    private String sys_user_account;

    public SendSmsCodeTokenRequest(String token, String bus_type, String img_id, String img_code, String sys_user_account) {
        this.token = token;
        this.bus_type = bus_type;
        this.img_id = img_id;
        this.img_code = img_code;
        this.sys_user_account = sys_user_account;
    }

    public SendSmsCodeTokenRequest(String token, String bus_type, String img_id, String img_code) {
        this.token = token;
        this.bus_type = bus_type;
        this.img_id = img_id;
        this.img_code = img_code;
    }
}
