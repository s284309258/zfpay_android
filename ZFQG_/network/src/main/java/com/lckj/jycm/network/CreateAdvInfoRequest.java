package com.lckj.jycm.network;

public class CreateAdvInfoRequest {

    /**
     * advImg : http://mmbiz.qpic.cn/mmbiz_jpg/nhlGsolibOWFbzP77VCGbSdfo8XTA6txicdJmGVRQjMwTyzPQXx2tedia7jZPCB7BaGglCELzjNVCnPqPZcSmucEQ/0?wx_fmt=jpeg
     * advIntro : 好用 性价比高。 响应快 体验号
     * advTitle : 神车思域 百米用时7.9秒 ..
     * advUrl : https://mp.weixin.qq.com/s/m7vWM9wq6zYXqJF0KqWafw
     * lat : 22.5234
     * lng : 141.2342
     * putBudget : 80
     * revShare : 0.08
     * sign : 96245A219F75D202FCC615E47545A881
     * token : 11|ZXKDHCR186KJWO3KJGU3EA1LY2BIM1S1
     */

    private String system_type = "createAdvInfoToApp";
    private String artId;
    private String advImg;
    private String advIntro;
    private String advTitle;
    private String advUrl;
    private String lat;
    private String lng;
    private String putBudget;
    private String revShare;
    private String token;

    public CreateAdvInfoRequest(String artId, String advImg, String advIntro, String advTitle, String advUrl, String lat, String lng, String putBudget, String revShare, String token) {
        this.advImg = advImg;
        this.advIntro = advIntro;
        this.advTitle = advTitle;
        this.advUrl = advUrl;
        this.lat = lat;
        this.lng = lng;
        this.putBudget = putBudget;
        this.revShare = revShare;
        this.token = token;
        this.artId = artId;
    }

    private String getArtId() {
        return artId;
    }

    private void setArtId(String artId) {
        this.artId = artId;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getAdvIntro() {
        return advIntro;
    }

    public void setAdvIntro(String advIntro) {
        this.advIntro = advIntro;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvUrl() {
        return advUrl;
    }

    public void setAdvUrl(String advUrl) {
        this.advUrl = advUrl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPutBudget() {
        return putBudget;
    }

    public void setPutBudget(String putBudget) {
        this.putBudget = putBudget;
    }

    public String getRevShare() {
        return revShare;
    }

    public void setRevShare(String revShare) {
        this.revShare = revShare;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
