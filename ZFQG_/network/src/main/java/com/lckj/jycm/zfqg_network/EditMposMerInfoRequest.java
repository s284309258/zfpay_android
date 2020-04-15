package com.lckj.jycm.zfqg_network;

public class EditMposMerInfoRequest {
    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * name : 张三
     * tel : 13545632453
     * sn : 95314100
     * sign : A4BBA2FA68BFE0DF913A0C966F257CB4
     */

    private String token;
    private String name;
    private String tel;
    private String sn;
    private String sign;

    public EditMposMerInfoRequest(String token, String name, String tel, String sn) {
        this.token = token;
        this.name = name;
        this.tel = tel;
        this.sn = sn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
