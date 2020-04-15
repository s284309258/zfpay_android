package com.lckj.jycm.zfqg_network;

public class GetTraditionalPosInstallListRequest {

    /**
     * sign : 33FFC15D50A94F849F800F49E465F296
     * biz_code : 00
     * token : 40|TN7SQJ50Q3853HL6R8WHI3CIBPA4T6XJ
     */

    private String biz_code;
    private String token;
    private String last_id;
    private String key_word;
    private String pos_type;

    public GetTraditionalPosInstallListRequest(String biz_code, String token, String last_id, String key_word, String pos_type) {
        this.biz_code = biz_code;
        this.token = token;
        this.last_id = last_id;
        this.key_word = key_word;
        this.pos_type = pos_type;
    }

    public GetTraditionalPosInstallListRequest(String biz_code, String token, String last_id, String key_word) {
        this.biz_code = biz_code;
        this.token = token;
        this.last_id = last_id;
        this.key_word = key_word;
    }

    public String getBiz_code() {
        return biz_code;
    }

    public void setBiz_code(String biz_code) {
        this.biz_code = biz_code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
