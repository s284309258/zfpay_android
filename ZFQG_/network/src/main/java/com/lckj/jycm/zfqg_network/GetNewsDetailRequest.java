package com.lckj.jycm.zfqg_network;

public class GetNewsDetailRequest {

    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * news_id : 3
     * sign : 8D239C47E20038F4991028EECA8CBDA9
     */

    private String token;
    private String news_id;

    public GetNewsDetailRequest(String token, String news_id) {
        this.token = token;
        this.news_id = news_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }
}
