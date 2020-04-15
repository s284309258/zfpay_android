package com.lckj.jycm.zfqg_network;

public class UpdateNewsReadFlagRequest {
private String token;
    private String news_type;
    private String read_flag;

    public UpdateNewsReadFlagRequest(String token, String news_type, String read_flag) {
        this.token = token;
        this.news_type = news_type;
        this.read_flag = read_flag;
    }
}
