package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosAllocationListBean extends HttpResponse {

    /**
     * data : {"traditionalPosAllocationList":[{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"91581156","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"92280318","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"92308252","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"92617921","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"92932752","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"95217293","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"95314618","card_settle_price":"0.52","single_profit_rate":"100"},{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","weixin_settle_price":"0.32","cash_back_rate":"100","sn":"95476182","card_settle_price":"0.52","single_profit_rate":"100"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosAllocationListBean> traditionalPosAllocationList;

        public List<TraditionalPosAllocationListBean> getTraditionalPosAllocationList() {
            return traditionalPosAllocationList;
        }

        public void setTraditionalPosAllocationList(List<TraditionalPosAllocationListBean> traditionalPosAllocationList) {
            this.traditionalPosAllocationList = traditionalPosAllocationList;
        }

        public static class TraditionalPosAllocationListBean{
            /**
             * zhifubao_settle_price : 0.32
             * cloud_settle_price : 0.30
             * weixin_settle_price : 0.32
             * cash_back_rate : 100
             * sn : 91581156
             * card_settle_price : 0.52
             * single_profit_rate : 100
             */

            private String zhifubao_settle_price;
            private String cloud_settle_price;
            private String weixin_settle_price;
            private String cash_back_rate;
            private String sn;
            private String card_settle_price;
            private String single_profit_rate;
            public String expire_day;
            public String policy_name;
            public String card_settle_price_vip;

            public String getCard_settle_price_vip() {
                return card_settle_price_vip;
            }

            public void setCard_settle_price_vip(String card_settle_price_vip) {
                this.card_settle_price_vip = card_settle_price_vip;
            }

            public String getPolicy_name() {
                return policy_name;
            }

            public void setPolicy_name(String policy_name) {
                this.policy_name = policy_name;
            }

            public String getExpire_day() {
                return expire_day;
            }

            public void setExpire_day(String expire_day) {
                this.expire_day = expire_day;
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

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
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
        }
    }
}
