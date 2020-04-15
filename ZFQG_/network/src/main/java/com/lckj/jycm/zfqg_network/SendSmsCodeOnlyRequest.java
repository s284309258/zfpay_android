package com.lckj.jycm.zfqg_network;

public class SendSmsCodeOnlyRequest implements RSARequest{

    /**
     * sys_user_account  :  18772101525
     * bus_type :  FrontRegister
     * img_id :  20190820155440463991928
     * img_code :  TALZ
     */

    private String sys_user_account;
    private String bus_type;
    private String img_id;
    private String img_code;

    public SendSmsCodeOnlyRequest(String sys_user_account, String bus_type, String img_id, String img_code) {
        this.sys_user_account = sys_user_account;
        this.bus_type = bus_type;
        this.img_id = img_id;
        this.img_code = img_code;
    }

    public String getSys_user_account() {
        return sys_user_account;
    }

    public void setSys_user_account(String sys_user_account) {
        this.sys_user_account = sys_user_account;
    }

    public String getBus_type() {
        return bus_type;
    }

    public void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    public String getImg_code() {
        return img_code;
    }

    public void setImg_code(String img_code) {
        this.img_code = img_code;
    }
}
