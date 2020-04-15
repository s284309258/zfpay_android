package com.lckj.jycm.zfqg_network;

public class GetTraditionalPosAllocationListRequest {
    private String token;
    private String start_number;
    private String end_number;
    private String pos_type;

    public GetTraditionalPosAllocationListRequest(String token, String start_number, String end_number, String pos_type) {
        this.token = token;
        this.start_number = start_number;
        this.end_number = end_number;
        this.pos_type = pos_type;
    }

    public GetTraditionalPosAllocationListRequest(String token, String start_number, String end_number) {
        this.token = token;
        this.start_number = start_number;
        this.end_number = end_number;
    }
}
