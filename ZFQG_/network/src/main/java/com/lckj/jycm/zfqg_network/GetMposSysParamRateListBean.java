package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMposSysParamRateListBean extends HttpResponse {

    /**
     * data : {"mposSysParamRate":{"single_profit_rate_list":["0.000","100.00","25.00","50.00"],"cloud_settle_price_list":["0.310","0.320","0.330","0.340"],"cash_back_rate_list":["0.000","100.00","25.00","50.00"],"card_settle_price_list":["0.520","0.530","0.540","0.550"]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mposSysParamRate : {"single_profit_rate_list":["0.000","100.00","25.00","50.00"],"cloud_settle_price_list":["0.310","0.320","0.330","0.340"],"cash_back_rate_list":["0.000","100.00","25.00","50.00"],"card_settle_price_list":["0.520","0.530","0.540","0.550"]}
         */

        private MposSysParamRateBean mposSysParamRate;

        public MposSysParamRateBean getMposSysParamRate() {
            return mposSysParamRate;
        }

        public void setMposSysParamRate(MposSysParamRateBean mposSysParamRate) {
            this.mposSysParamRate = mposSysParamRate;
        }

        public static class MposSysParamRateBean {
            private List<String> single_profit_rate_list;
            private List<String> cloud_settle_price_list;
            private List<String> cash_back_rate_list;
            private List<String> card_settle_price_list;

            public List<String> getSingle_profit_rate_list() {
                return single_profit_rate_list;
            }

            public void setSingle_profit_rate_list(List<String> single_profit_rate_list) {
                this.single_profit_rate_list = single_profit_rate_list;
            }

            public List<String> getCloud_settle_price_list() {
                return cloud_settle_price_list;
            }

            public void setCloud_settle_price_list(List<String> cloud_settle_price_list) {
                this.cloud_settle_price_list = cloud_settle_price_list;
            }

            public List<String> getCash_back_rate_list() {
                return cash_back_rate_list;
            }

            public void setCash_back_rate_list(List<String> cash_back_rate_list) {
                this.cash_back_rate_list = cash_back_rate_list;
            }

            public List<String> getCard_settle_price_list() {
                return card_settle_price_list;
            }

            public void setCard_settle_price_list(List<String> card_settle_price_list) {
                this.card_settle_price_list = card_settle_price_list;
            }
        }
    }
}
