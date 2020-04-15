package com.lckj.jycm.network;

public class LoginRequest {

    /**
     * tel : 13599888899
     * loginPassword : 123456
     * sign : E4DBFF2A09A600E41F9D9E4C821496F9
     */

    private String tel;
    private String loginPassword;
    private String system_type = "login";
    private String img_code;
    private String img_id;

    public LoginRequest(String tel, String loginPassword, String img_code, String img_id) {
        this.tel = tel;
        this.loginPassword = loginPassword;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
