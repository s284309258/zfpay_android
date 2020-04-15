package com.lckj.jycm.zfqg_network;

public class GetAllocationTraditionalPosDetailRequest {
    /**
     * sign : 7D9F52F4EA245D9268EA154397AF6D4E
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String token;
    private String allocation_id;

    public GetAllocationTraditionalPosDetailRequest(String token, String allocation_id) {
        this.token = token;
        this.allocation_id = allocation_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
