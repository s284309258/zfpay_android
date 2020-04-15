package com.lckj.jycm.network;

public class RegisterRequest {

    /**
     * system_type : register
     * tel : 13599888899
     * inviteId : RenegadeNic
     * bus_type : register
     * loginPassword : 123456
     * code : 999999
     * sign : 04CD42027954F75984F490B3B07D9062
     */

    private String system_type = "register";
    private String tel;
    private String bus_type;
    private String loginPassword;
    private String code;
    private String img_code;


    public RegisterRequest(String tel, String bus_type, String loginPassword, String code, String img_code) {
        this.tel = tel;
        this.bus_type = bus_type;
        this.loginPassword = loginPassword;
        this.code = code;
        this.img_code = img_code;
    }

    public String getSystem_type() {
        return system_type;
    }

    public void setSystem_type(String system_type) {
        this.system_type = system_type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBus_type() {
        return bus_type;
    }

    public void setBus_type(String bus_type) {
        this.bus_type = bus_type;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
