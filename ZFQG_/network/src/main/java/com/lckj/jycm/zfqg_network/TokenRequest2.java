package com.lckj.jycm.zfqg_network;

public class TokenRequest2 {

    /**
     * pos_type : MPOS
     * sign : A3B633968B65970B63FDA2592179D3C0
     * token : 1828|TGBQFLC3BBFKNOKLE5VYKD405YANUD5B
     */

    private String pos_type;
    private String token;
    /**
     * sign : 316996CFD63BC56BC7D67B77BC1F2EDA
     * sn : 190
     */

    private String sn;
    private String user_id;
    private String batch_no;

    public TokenRequest2(String pos_type, String token, String sn, String user_id, String batch_no) {
        this.pos_type = pos_type;
        this.token = token;
        this.sn = sn;
        this.user_id = user_id;
        this.batch_no = batch_no;
    }

    public TokenRequest2(String pos_type, String token, String sn) {
        this.pos_type = pos_type;
        this.token = token;
        this.sn = sn;
    }

    public TokenRequest2(String pos_type, String token) {
        this.pos_type = pos_type;
        this.token = token;
    }

    public String getPos_type() {
        return pos_type;
    }

    public void setPos_type(String pos_type) {
        this.pos_type = pos_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
