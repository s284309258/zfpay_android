package com.lckj.jycm.zfqg_network;

public class GetNoticeDetailRequest {
    private String token;
    private String notice_id;

    public GetNoticeDetailRequest(String token, String notice_id) {
        this.token = token;
        this.notice_id = notice_id;
    }
}
