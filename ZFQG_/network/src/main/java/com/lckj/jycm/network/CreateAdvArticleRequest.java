package com.lckj.jycm.network;

public class CreateAdvArticleRequest {


    public CreateAdvArticleRequest(String artCover, String artIntro, String artTitle, String artType, String artUrl, String source, String token) {
        this.artCover = artCover;
        this.artIntro = artIntro;
        this.artTitle = artTitle;
        this.artType = artType;
        this.artUrl = artUrl;
        this.source = source;
        this.token = token;
    }

    /**
     * artCover : https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ymIrwFa8nkfUPCzy8f62pVE2jdUE5jECbj0x5E1Z6gC9rbbbvss8yZpZqqo5EB8iaGqOjVpqaJALzQ/640?wx_fmt=png
     * artIntro : 程序员相关事情，可以的测试新数据。。。
     * artTitle : 14 年经验程序员的吐槽：如何招不到程序员？
     * artType : 互联网行业
     * artUrl : https://mp.weixin.qq.com/s/IVDCx8dpyq_481b-kfWtWw
     * sign : FE4624AD7166FD54744C39F3BBB9AC55
     * source : 程序员的那些事
     * token : 11|VJA9CDV8Z2XXQJBCAWHZI02AKCY43I87
     */



    private String system_type = "createAdvArticle";
    private String artCover;
    private String artIntro;
    private String artTitle;
    private String artType;
    private String artUrl;
    private String source;
    private String token;

    public String getArtCover() {
        return artCover;
    }

    public void setArtCover(String artCover) {
        this.artCover = artCover;
    }

    public String getArtIntro() {
        return artIntro;
    }

    public void setArtIntro(String artIntro) {
        this.artIntro = artIntro;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public String getArtUrl() {
        return artUrl;
    }

    public void setArtUrl(String artUrl) {
        this.artUrl = artUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
