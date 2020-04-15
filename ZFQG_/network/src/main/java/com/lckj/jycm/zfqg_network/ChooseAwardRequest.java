package com.lckj.jycm.zfqg_network;

public class ChooseAwardRequest {
    private String id;
    private String token;
    private String mer_id;
    private String mer_name;

    public ChooseAwardRequest(String id, String token, String mer_id, String mer_name) {
        this.id = id;
        this.token = token;
        this.mer_id = mer_id;
        this.mer_name = mer_name;
    }
}
