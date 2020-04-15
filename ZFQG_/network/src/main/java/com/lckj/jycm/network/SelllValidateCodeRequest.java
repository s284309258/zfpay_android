package com.lckj.jycm.network;

public class SelllValidateCodeRequest {

    /**
     * system_type : securityCode
     * tel : 13333333333
     * bus_type : register
     * sign : ABDFE877387348DJFDJF
     */

    private String system_type = "securityCode";
    private String tel;
    private String bus_type;
    private String img_id;
    private String img_code;

    public SelllValidateCodeRequest(String tel, String bus_type, String img_id, String img_code) {
        this.tel = tel;
        this.bus_type = bus_type;
        this.img_code = img_code;
        this.img_id = img_id;
    }

    private String getImg_id() {
        return img_id;
    }

    private void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    private String getImg_code() {
        return img_code;
    }

    private void setImg_code(String img_code) {
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
}
