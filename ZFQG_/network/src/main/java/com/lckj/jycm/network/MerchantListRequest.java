package com.lckj.jycm.network;

public class MerchantListRequest {


    private String dis;
    /**
     * currentPageNo : 1
     * industry : 1
     * lat : 22.547
     * lng : 114.085947
     * queryKey : 展涛
     * sign : 675F9C3087B322C1337AA3499DB9C8E7
     * token : 11|EVA6O4KZ5CKFV2IC3QRJSTCTFOGN4XDA
     */


    private String token;
    private String queryKey;
    private String industry;
    private String lat;
    private String lng;
    /***/
    private int currentPageNo;
    private String city;
    private String area;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public MerchantListRequest(String token, String queryKey, String industry, String dis, String lat, String lng, String city,String area,int currentPageNo) {
        this.token = token;
        this.queryKey = queryKey;
        this.industry = industry;
        this.dis = dis;
        this.lat = lat;
        this.lng = lng;
        this.currentPageNo = currentPageNo;
        this.city = city;
        this.area = area;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
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

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
