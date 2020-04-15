package com.lckj.jycm.zfqg_network;

public class GetTraditionalPosInstallDetailRequest {

    /**
     * install_id : 1
     * sign : 0372A813A2A9E7E6C3DF314987727B22
     * token : 40|TN7SQJ50Q3853HL6R8WHI3CIBPA4T6XJ
     */

    private String install_id;
    private String token;

    public GetTraditionalPosInstallDetailRequest(String install_id, String token) {
        this.install_id = install_id;
        this.token = token;
    }

    public String getInstall_id() {
        return install_id;
    }

    public void setInstall_id(String install_id) {
        this.install_id = install_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
