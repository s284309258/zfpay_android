package com.lckj.jycm.zfqg_network;

public class EditAllocationMPosBatchRequest {

    /**
     * sign : 6218EDB61220CC23DAFA2B4A7DD4A01F
     * token : 1828|TGBQFLC3BBFKNOKLE5VYKD405YANUD5B
     * batch_no : 1828_20191027183956
     * card_settle_price : 0.61
     * cloud_settle_price : 0.35
     * single_profit_rate : 0
     * cash_back_rate : 0
     */

    private String token;
    private String batch_no;
    private String card_settle_price;
    private String cloud_settle_price;
    private String single_profit_rate;
    private String cash_back_rate;
    /**
     * sign : A15C0744F64337C22AAFD75FA4FBAF52
     * weixin_settle_price : 0.55
     * zhifubao_settle_price : 0.35
     * mer_cap_fee : 25
     */

    private String weixin_settle_price;
    private String zhifubao_settle_price;
    private String mer_cap_fee;
    private String card_settle_price_vip;
    private String cloud_settle_price_vip;
    private String weixin_settle_price_vip;
    private String zhifubao_settle_price_vip;

    public EditAllocationMPosBatchRequest(String mer_cap_fee, String zhifubao_settle_price, String cloud_settle_price, String batch_no, String weixin_settle_price, String cash_back_rate, String card_settle_price, String single_profit_rate, String token, String card_settle_price_vip, String cloud_settle_price_vip, String weixin_settle_price_vip, String zhifubao_settle_price_vip) {
        this.token = token;
        this.batch_no = batch_no;
        this.card_settle_price = card_settle_price;
        this.cloud_settle_price = cloud_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.cash_back_rate = cash_back_rate;
        this.weixin_settle_price = weixin_settle_price;
        this.zhifubao_settle_price = zhifubao_settle_price;
        this.mer_cap_fee = mer_cap_fee;
        this.card_settle_price_vip = card_settle_price_vip;
        this.cloud_settle_price_vip = cloud_settle_price_vip;
        this.weixin_settle_price_vip = weixin_settle_price_vip;
        this.zhifubao_settle_price_vip = zhifubao_settle_price_vip;
    }

    public EditAllocationMPosBatchRequest(String cloud_settle_price, String batch_no, String cash_back_rate, String card_settle_price, String single_profit_rate, String token) {
        this.token = token;
        this.batch_no = batch_no;
        this.card_settle_price = card_settle_price;
        this.cloud_settle_price = cloud_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.cash_back_rate = cash_back_rate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getCard_settle_price() {
        return card_settle_price;
    }

    public void setCard_settle_price(String card_settle_price) {
        this.card_settle_price = card_settle_price;
    }

    public String getCloud_settle_price() {
        return cloud_settle_price;
    }

    public void setCloud_settle_price(String cloud_settle_price) {
        this.cloud_settle_price = cloud_settle_price;
    }

    public String getSingle_profit_rate() {
        return single_profit_rate;
    }

    public void setSingle_profit_rate(String single_profit_rate) {
        this.single_profit_rate = single_profit_rate;
    }

    public String getCash_back_rate() {
        return cash_back_rate;
    }

    public void setCash_back_rate(String cash_back_rate) {
        this.cash_back_rate = cash_back_rate;
    }

    public String getWeixin_settle_price() {
        return weixin_settle_price;
    }

    public void setWeixin_settle_price(String weixin_settle_price) {
        this.weixin_settle_price = weixin_settle_price;
    }

    public String getZhifubao_settle_price() {
        return zhifubao_settle_price;
    }

    public void setZhifubao_settle_price(String zhifubao_settle_price) {
        this.zhifubao_settle_price = zhifubao_settle_price;
    }

    public String getMer_cap_fee() {
        return mer_cap_fee;
    }

    public void setMer_cap_fee(String mer_cap_fee) {
        this.mer_cap_fee = mer_cap_fee;
    }
}
