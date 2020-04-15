package com.lckj.jycm.zfqg_network;

public class EditAllocationTraditionalPosRequest {
    /**
     * zhifubao_settle_price : 0.33
     * cloud_settle_price : 0.33
     * allocation_id : 11
     * weixin_settle_price : 0.33
     * sign : FC797A3A7589814CDBCD4DD24F580D56
     * cash_back_rate : 25
     * card_settle_price : 0.53
     * single_profit_rate : 25
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     */

    private String zhifubao_settle_price;
    private String cloud_settle_price;
    private String allocation_id;
    private String weixin_settle_price;
    private String cash_back_rate;
    private String card_settle_price;
    private String single_profit_rate;
    private String token;
    private String mer_cap_fee;

    public EditAllocationTraditionalPosRequest(String cloud_settle_price, String allocation_id, String cash_back_rate, String card_settle_price, String single_profit_rate, String token) {
        this.cloud_settle_price = cloud_settle_price;
        this.allocation_id = allocation_id;
        this.cash_back_rate = cash_back_rate;
        this.card_settle_price = card_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.token = token;
    }

    public EditAllocationTraditionalPosRequest(String mer_cap_fee,String zhifubao_settle_price, String cloud_settle_price, String allocation_id, String weixin_settle_price, String cash_back_rate, String card_settle_price, String single_profit_rate, String token) {
        this.zhifubao_settle_price = zhifubao_settle_price;
        this.cloud_settle_price = cloud_settle_price;
        this.allocation_id = allocation_id;
        this.weixin_settle_price = weixin_settle_price;
        this.cash_back_rate = cash_back_rate;
        this.card_settle_price = card_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.token = token;
        this.mer_cap_fee = mer_cap_fee;
    }

    public String getZhifubao_settle_price() {
        return zhifubao_settle_price;
    }

    public void setZhifubao_settle_price(String zhifubao_settle_price) {
        this.zhifubao_settle_price = zhifubao_settle_price;
    }

    public String getCloud_settle_price() {
        return cloud_settle_price;
    }

    public void setCloud_settle_price(String cloud_settle_price) {
        this.cloud_settle_price = cloud_settle_price;
    }

    public String getAllocation_id() {
        return allocation_id;
    }

    public void setAllocation_id(String allocation_id) {
        this.allocation_id = allocation_id;
    }

    public String getWeixin_settle_price() {
        return weixin_settle_price;
    }

    public void setWeixin_settle_price(String weixin_settle_price) {
        this.weixin_settle_price = weixin_settle_price;
    }

    public String getCash_back_rate() {
        return cash_back_rate;
    }

    public void setCash_back_rate(String cash_back_rate) {
        this.cash_back_rate = cash_back_rate;
    }

    public String getCard_settle_price() {
        return card_settle_price;
    }

    public void setCard_settle_price(String card_settle_price) {
        this.card_settle_price = card_settle_price;
    }

    public String getSingle_profit_rate() {
        return single_profit_rate;
    }

    public void setSingle_profit_rate(String single_profit_rate) {
        this.single_profit_rate = single_profit_rate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
