package com.lckj.jycm.zfqg_network;

public class AddApplyScanRecordRequest {

    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * business_license : banners_1.jpg
     * store_interior : banners_1.jpg
     * shop_head : banners_1.jpg
     * cashier_desk : banners_1.jpg
     * sn_list : 95370909,94585127
     * sign : 0C893CAA7BD7F09D982F4A48627116A6
     */

    private String token;
    private String business_license;
    private String store_interior;
    private String shop_head;
    private String cashier_desk;
    private String sn_list;

    public AddApplyScanRecordRequest(String token, String business_license, String store_interior, String shop_head, String cashier_desk, String sn_list) {
        this.token = token;
        this.business_license = business_license;
        this.store_interior = store_interior;
        this.shop_head = shop_head;
        this.cashier_desk = cashier_desk;
        this.sn_list = sn_list;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getStore_interior() {
        return store_interior;
    }

    public void setStore_interior(String store_interior) {
        this.store_interior = store_interior;
    }

    public String getShop_head() {
        return shop_head;
    }

    public void setShop_head(String shop_head) {
        this.shop_head = shop_head;
    }

    public String getCashier_desk() {
        return cashier_desk;
    }

    public void setCashier_desk(String cashier_desk) {
        this.cashier_desk = cashier_desk;
    }

    public String getSn_list() {
        return sn_list;
    }

    public void setSn_list(String sn_list) {
        this.sn_list = sn_list;
    }
}
