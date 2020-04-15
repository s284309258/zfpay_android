package com.lckj.jycm.zfqg_network;

public class PageRequest {
    private String token;
    private String pageNum;

    public PageRequest(String token, String pageNum) {
        this.token = token;
        this.pageNum = pageNum;
    }
}
