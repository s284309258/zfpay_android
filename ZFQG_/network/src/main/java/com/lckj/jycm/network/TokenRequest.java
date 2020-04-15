package com.lckj.jycm.network;

public class TokenRequest {
    /**
     * token : 2|Z4DQFUWIMH2MJDZ98O5XN4QWTR2K4EL1
     */

    private String token;
    private String key_word;
    private String last_id;
    private String pos_type;

    public TokenRequest(String token, String key_word, String last_id, String pos_type) {
        this.token = token;
        this.key_word = key_word;
        this.last_id = last_id;
        this.pos_type = pos_type;
    }

    public TokenRequest(String token, String key_word, String last_id) {
        this.token = token;
        this.key_word = key_word;
        this.last_id = last_id;
    }

    public TokenRequest(String token, String key_word) {
        this.token = token;
        this.key_word = key_word;
    }

    public TokenRequest(String token) {
        this.token = token;
    }

    private String getKey_word() {
        return key_word;
    }

    private void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
