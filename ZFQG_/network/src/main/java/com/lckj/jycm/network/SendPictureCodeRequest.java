package com.lckj.jycm.network;

public class SendPictureCodeRequest {
    /**
     * system_type : securityCode
     * sign : ABDFE877387348DJFDJF
     */

    private String system_type = "securityCode";

    public String getSystem_type() {
        return system_type;
    }

    public void setSystem_type(String system_type) {
        this.system_type = system_type;
    }
}
