package com.lckj.jycm.zfqg_network;

public class GetTraditionalPosOnlineActivityDetailRequest{

    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * apply_id : 1
     * sign : 1E4AB1CD031123BE586AB49754EBF5F3
     */

    private String token;
    private String activity_id;
    private String pos_type;

    public GetTraditionalPosOnlineActivityDetailRequest(String token, String activity_id, String pos_type) {
        this.token = token;
        this.activity_id = activity_id;
        this.pos_type = pos_type;
    }

    public GetTraditionalPosOnlineActivityDetailRequest(String token, String activity_id) {
        this.token = token;
        this.activity_id = activity_id;
    }

    private String getToken() {
        return token;
    }

    private void setToken(String token) {
        this.token = token;
    }

    private String getActivity_id() {
        return activity_id;
    }

    private void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }
}
