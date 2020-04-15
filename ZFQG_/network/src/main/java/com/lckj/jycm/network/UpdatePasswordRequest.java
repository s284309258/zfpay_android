package com.lckj.jycm.network;

public class UpdatePasswordRequest {
    /**
     * token : 2|046D5A2M33IZQBZUP73I2HOFWI1LEIHZ
     * loginPassword : 110110
     * code : 377327
     * tel : 13599888899
     * bus_type : SetLoginPass
     * sign : BFA88022C1383323203512F53FBEF8E9
     */

    private String token;
    private String loginPassword;
    private String system_type = "updatePassword";
    private String code;
    private String tel;
    private String bus_type;

    public UpdatePasswordRequest(String token, String loginPassword, String code, String tel, String bus_type) {
        this.token = token;
        this.loginPassword = loginPassword;
        this.code = code;
        this.tel = tel;
        this.bus_type = bus_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}
