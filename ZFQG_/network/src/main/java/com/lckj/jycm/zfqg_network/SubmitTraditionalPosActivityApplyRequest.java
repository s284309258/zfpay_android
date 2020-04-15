package com.lckj.jycm.zfqg_network;

public class SubmitTraditionalPosActivityApplyRequest {
    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * activity_reward_id : 3
     * sn_list : 92280318,91581156
     * sign : 962A0F155F68903C2BC5EA94FE2A1E21
     */

    private String token;
    private String activity_reward_id;
    private String sn_list;

    public SubmitTraditionalPosActivityApplyRequest(String token, String activity_reward_id, String sn_list) {
        this.token = token;
        this.activity_reward_id = activity_reward_id;
        this.sn_list = sn_list;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActivity_reward_id() {
        return activity_reward_id;
    }

    public void setActivity_reward_id(String activity_reward_id) {
        this.activity_reward_id = activity_reward_id;
    }

    public String getSn_list() {
        return sn_list;
    }

    public void setSn_list(String sn_list) {
        this.sn_list = sn_list;
    }
}
