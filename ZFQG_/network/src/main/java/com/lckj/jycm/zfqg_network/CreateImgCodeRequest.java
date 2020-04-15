package com.lckj.jycm.zfqg_network;

public class CreateImgCodeRequest{

    /**
     * interface_type : createImgCode
     * sign : 561C176A2CD406E36FF519673EE628A5
     */

    private String interface_type;

    public CreateImgCodeRequest(String interface_type) {
        this.interface_type = interface_type;
    }

    public String getInterface_type() {
        return interface_type;
    }

    public void setInterface_type(String interface_type) {
        this.interface_type = interface_type;
    }
}
