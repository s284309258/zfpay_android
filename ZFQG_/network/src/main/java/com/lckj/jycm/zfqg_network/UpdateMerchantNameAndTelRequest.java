package com.lckj.jycm.zfqg_network;

public class UpdateMerchantNameAndTelRequest {
    private String token;
    private String name;
    private String tel;
    private String sn;
    private String pos_type;

    public UpdateMerchantNameAndTelRequest(String token, String name, String tel, String sn, String pos_type) {
        this.token = token;
        this.name = name;
        this.tel = tel;
        this.sn = sn;
        this.pos_type = pos_type;
    }

    public UpdateMerchantNameAndTelRequest(String token, String sn, String name, String tel) {
        this.token = token;
        this.sn = sn;
        this.name = name;
        this.tel = tel;
    }
}
