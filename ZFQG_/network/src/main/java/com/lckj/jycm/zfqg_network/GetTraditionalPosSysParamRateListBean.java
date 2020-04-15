package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class GetTraditionalPosSysParamRateListBean extends HttpResponse {

    /**
     * data : {"traditionalPosSysParamRate":{"weixin_settle_price_list":["0.310","0.320","0.330","0.340"],"single_profit_rate_list":["0.000","100.00","25.00","50.00"],"cloud_settle_price_list":["0.310","0.320","0.330","0.340"],"cash_back_rate_list":["0.000","100.00","25.00","50.00"],"zhifubao_settle_price_list":["0.310","0.320","0.330","0.340"],"card_settle_price_list":["0.520","0.530","0.540","0.550"]}}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * traditionalPosSysParamRate : {"weixin_settle_price_list":["0.310","0.320","0.330","0.340"],"single_profit_rate_list":["0.000","100.00","25.00","50.00"],"cloud_settle_price_list":["0.310","0.320","0.330","0.340"],"cash_back_rate_list":["0.000","100.00","25.00","50.00"],"zhifubao_settle_price_list":["0.310","0.320","0.330","0.340"],"card_settle_price_list":["0.520","0.530","0.540","0.550"]}
         */

        public TraditionalPosSysParamRateBean traditionalPosSysParamRate;
        public TraditionalPosSysParamRateBean mposSysParamRate;

        public TraditionalPosSysParamRateBean getMposSysParamRate() {
            return mposSysParamRate;
        }

        public void setMposSysParamRate(TraditionalPosSysParamRateBean mposSysParamRate) {
            this.mposSysParamRate = mposSysParamRate;
        }

        public TraditionalPosSysParamRateBean getTraditionalPosSysParamRate() {
            return traditionalPosSysParamRate;
        }

        public void setTraditionalPosSysParamRate(TraditionalPosSysParamRateBean traditionalPosSysParamRate) {
            this.traditionalPosSysParamRate = traditionalPosSysParamRate;
        }

        public static class TraditionalPosSysParamRateBean {
            public List<String> weixin_settle_price_list;
            public List<String> single_profit_rate_list;
            public List<String> cloud_settle_price_list;
            public List<String> cash_back_rate_list;
            public List<String> zhifubao_settle_price_list;
            public List<String> card_settle_price_list;
            public List<String> mer_cap_fee_list;
            public List<String> card_settle_price_vip_list;
            public List<String> weixin_settle_price_vip_list;
            public List<String> zhifubao_settle_price_vip_list;
            public List<String> cloud_settle_price_vip_list;
            public String is_reward;

            public String getIs_reward() {
                return is_reward;
            }

            public void setIs_reward(String is_reward) {
                this.is_reward = is_reward;
            }

            public List<String> getCard_settle_price_vip_list() {
                return card_settle_price_vip_list;
            }

            public void setCard_settle_price_vip_list(List<String> card_settle_price_vip_list) {
                this.card_settle_price_vip_list = card_settle_price_vip_list;
            }

            public List<String> getWeixin_settle_price_vip_list() {
                return weixin_settle_price_vip_list;
            }

            public void setWeixin_settle_price_vip_list(List<String> weixin_settle_price_vip_list) {
                this.weixin_settle_price_vip_list = weixin_settle_price_vip_list;
            }

            public List<String> getZhifubao_settle_price_vip_list() {
                return zhifubao_settle_price_vip_list;
            }

            public void setZhifubao_settle_price_vip_list(List<String> zhifubao_settle_price_vip_list) {
                this.zhifubao_settle_price_vip_list = zhifubao_settle_price_vip_list;
            }

            public List<String> getCloud_settle_price_vip_list() {
                return cloud_settle_price_vip_list;
            }

            public void setCloud_settle_price_vip_list(List<String> cloud_settle_price_vip_list) {
                this.cloud_settle_price_vip_list = cloud_settle_price_vip_list;
            }

            public List<String> getMer_cap_fee_list() {
                return mer_cap_fee_list;
            }

            public void setMer_cap_fee_list(List<String> mer_cap_fee_list) {
                this.mer_cap_fee_list = mer_cap_fee_list;
            }

            public List<String> getWeixin_settle_price_list() {
                if (weixin_settle_price_list == null)
                    weixin_settle_price_list = new ArrayList<>();
                return weixin_settle_price_list;
            }

            public void setWeixin_settle_price_list(List<String> weixin_settle_price_list) {
                this.weixin_settle_price_list = weixin_settle_price_list;
            }

            public List<String> getSingle_profit_rate_list() {
                if (single_profit_rate_list == null)
                    single_profit_rate_list = new ArrayList<>();
                return single_profit_rate_list;
            }

            public void setSingle_profit_rate_list(List<String> single_profit_rate_list) {
                this.single_profit_rate_list = single_profit_rate_list;
            }

            public List<String> getCloud_settle_price_list() {
                if (cloud_settle_price_list == null)
                    cloud_settle_price_list = new ArrayList<>();
                return cloud_settle_price_list;
            }

            public void setCloud_settle_price_list(List<String> cloud_settle_price_list) {
                this.cloud_settle_price_list = cloud_settle_price_list;
            }

            public List<String> getCash_back_rate_list() {
                if (cash_back_rate_list == null)
                    cash_back_rate_list = new ArrayList<>();
                return cash_back_rate_list;
            }

            public void setCash_back_rate_list(List<String> cash_back_rate_list) {
                this.cash_back_rate_list = cash_back_rate_list;
            }

            public List<String> getZhifubao_settle_price_list() {
                if (zhifubao_settle_price_list == null)
                    zhifubao_settle_price_list = new ArrayList<>();
                return zhifubao_settle_price_list;
            }

            public void setZhifubao_settle_price_list(List<String> zhifubao_settle_price_list) {
                this.zhifubao_settle_price_list = zhifubao_settle_price_list;
            }

            public List<String> getCard_settle_price_list() {
                if (card_settle_price_list == null)
                    card_settle_price_list = new ArrayList<>();
                return card_settle_price_list;
            }

            public void setCard_settle_price_list(List<String> card_settle_price_list) {
                this.card_settle_price_list = card_settle_price_list;
            }
        }
    }
}
