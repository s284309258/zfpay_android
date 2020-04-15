package com.lckj.jycm.zfqg_network;

public class GetPosActivityApplyListDetailRequest {

    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * apply_id : 1
     * sign : 1E4AB1CD031123BE586AB49754EBF5F3
     */

    private String token;
    private String apply_id;

    public GetPosActivityApplyListDetailRequest(String token, String apply_id) {
        this.token = token;
        this.apply_id = apply_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }
}
