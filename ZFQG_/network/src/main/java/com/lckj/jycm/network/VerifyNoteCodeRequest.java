package com.lckj.jycm.network;

public class VerifyNoteCodeRequest {

    /**
     * system_type :  verifyNoteCode
     * tel : 13333333333
     * bus_type : ModifyTel
     * sign : ABDFE877387348DJFDJF
     * token : 11|E16M0O1YBXHRBMGJJ5RIJA28VZ2VP50G
     */

    private String system_type = "verifyNoteCode";
    private String tel;
    private String bus_type;
    private String token;
    private String code;

    public VerifyNoteCodeRequest(String tel, String bus_type, String token, String code) {
        this.tel = tel;
        this.bus_type = bus_type;
        this.token = token;
        this.code = code;
    }

    private String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
