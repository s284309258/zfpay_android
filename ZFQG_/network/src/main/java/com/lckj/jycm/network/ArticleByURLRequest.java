package com.lckj.jycm.network;

public class ArticleByURLRequest {
    /**
     * sign : 89CAF251E00A12C460B9BFC43C8119C0
     * url : https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw
     * token : 11|VJA9CDV8Z2XXQJBCAWHZI02AKCY43I87
     */
    private String system_type = "getArticleByURL";
    private String url;
    private String token;

    public ArticleByURLRequest(String url, String token) {
        this.url = url;
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
