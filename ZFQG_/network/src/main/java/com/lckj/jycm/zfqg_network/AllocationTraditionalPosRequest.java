package com.lckj.jycm.zfqg_network;

public class AllocationTraditionalPosRequest {
    /**
     * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
     * acce_user_id : 4
     * card_settle_price : 0.54
     * cash_back_rate : 50
     * cloud_settle_price : 0.34
     * single_profit_rate : 50
     * sn_list : 91581156,95476182
     * weixin_settle_price : 0.34
     * zhifubao_settle_price : 0.34
     * sign : DF9C8D5B901A8647FFDAB71198555EBD
     */

    private String token;
    private String acce_user_id;
    private String card_settle_price;
    private String cash_back_rate;
    private String cloud_settle_price;
    private String single_profit_rate;
    private String sn_list;
    private String weixin_settle_price;
    private String zhifubao_settle_price;
    private String mer_cap_fee;
    private String card_settle_price_vip;
    private String cloud_settle_price_vip;
    private String weixin_settle_price_vip;
    private String zhifubao_settle_price_vip;
    private String pos_type;
    private String is_reward;

    public AllocationTraditionalPosRequest(String mer_cap_fee, String token, String acce_user_id, String card_settle_price, String cash_back_rate, String cloud_settle_price, String single_profit_rate, String sn_list, String weixin_settle_price, String zhifubao_settle_price, String card_settle_price_vip, String cloud_settle_price_vip, String weixin_settle_price_vip, String zhifubao_settle_price_vip, String pos_type,String is_reward) {
        this.token = token;
        this.acce_user_id = acce_user_id;
        this.card_settle_price = card_settle_price;
        this.cash_back_rate = cash_back_rate;
        this.cloud_settle_price = cloud_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.sn_list = sn_list;
        this.weixin_settle_price = weixin_settle_price;
        this.zhifubao_settle_price = zhifubao_settle_price;
        this.mer_cap_fee = mer_cap_fee;
        this.card_settle_price_vip = card_settle_price_vip;
        this.cloud_settle_price_vip = cloud_settle_price_vip;
        this.weixin_settle_price_vip = weixin_settle_price_vip;
        this.zhifubao_settle_price_vip = zhifubao_settle_price_vip;
        this.pos_type = pos_type;
        this.is_reward = is_reward;
    }

    public AllocationTraditionalPosRequest(String mer_cap_fee, String token, String acce_user_id, String card_settle_price, String cash_back_rate, String cloud_settle_price, String single_profit_rate, String sn_list, String weixin_settle_price, String zhifubao_settle_price, String card_settle_price_vip, String cloud_settle_price_vip, String weixin_settle_price_vip, String zhifubao_settle_price_vip) {
        this.token = token;
        this.acce_user_id = acce_user_id;
        this.card_settle_price = card_settle_price;
        this.cash_back_rate = cash_back_rate;
        this.cloud_settle_price = cloud_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.sn_list = sn_list;
        this.weixin_settle_price = weixin_settle_price;
        this.zhifubao_settle_price = zhifubao_settle_price;
        this.mer_cap_fee = mer_cap_fee;
        this.card_settle_price_vip = card_settle_price_vip;
        this.cloud_settle_price_vip = cloud_settle_price_vip;
        this.weixin_settle_price_vip = weixin_settle_price_vip;
        this.zhifubao_settle_price_vip = zhifubao_settle_price_vip;
    }

    public AllocationTraditionalPosRequest(String token, String acce_user_id, String card_settle_price, String cash_back_rate, String cloud_settle_price, String single_profit_rate, String sn_list,String is_reward) {
        this.token = token;
        this.acce_user_id = acce_user_id;
        this.card_settle_price = card_settle_price;
        this.cash_back_rate = cash_back_rate;
        this.cloud_settle_price = cloud_settle_price;
        this.single_profit_rate = single_profit_rate;
        this.sn_list = sn_list;
        this.is_reward = is_reward;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAcce_user_id() {
        return acce_user_id;
    }

    public void setAcce_user_id(String acce_user_id) {
        this.acce_user_id = acce_user_id;
    }

    public String getCard_settle_price() {
        return card_settle_price;
    }

    public void setCard_settle_price(String card_settle_price) {
        this.card_settle_price = card_settle_price;
    }

    public String getCash_back_rate() {
        return cash_back_rate;
    }

    public void setCash_back_rate(String cash_back_rate) {
        this.cash_back_rate = cash_back_rate;
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

    public String getSn_list() {
        return sn_list;
    }

    public void setSn_list(String sn_list) {
        this.sn_list = sn_list;
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
}
