package com.lckj.jycm.network;

public class AddMerchantInfoRequest {

    private String proCity;
    private String token;
    /**
     * busLic : 12344321abc.img
     * industry : 1
     * lat : 23.22
     * lng : 123.111
     * localtion : 民治大道223号
     * merName : 展涛大厦八元里自选快餐
     * showImg : 1234abc.img,234cac.img
     * sign : DBDDF4377BBA418B88EBE081EBBC6A95
     * token : 11|2YD7KQ73PCYDWPWR84JZ5K1O3WRSI4AR
     * userName : 联系人
     * userTel : 13802211221
     */
    private String merName;
    private String system_type = "addMerchantInfo";
    private String userName;
    private String userTel;
    private String industry;
    private String localtion;
    private String lat;
    private String lng;
    private String showImg;
    /**执照*/
    private String busLic;

    public AddMerchantInfoRequest(String token, String merName, String userName, String userTel, String industry,String proCity, String localtion, String lat, String lng, String showImg, String busLic) {
        this.token = token;
        this.merName = merName;
        this.userName = userName;
        this.userTel = userTel;
        this.industry = industry;
        this.localtion = localtion;
        this.lat = lat;
        this.lng = lng;
        this.showImg = showImg;
        this.busLic = busLic;
        this.proCity = proCity;
    }

    public String getBusLic() {
        return busLic;
    }

    public void setBusLic(String busLic) {
        this.busLic = busLic;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}
