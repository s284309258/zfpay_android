package com.lckj.jycm.zfqg_network;

public class GetMessageRecordDetailRequest {
    private String token;
    private String message_id;

    public GetMessageRecordDetailRequest(String token, String message_id) {
        this.token = token;
        this.message_id = message_id;
    }
}
