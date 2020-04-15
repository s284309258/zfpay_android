package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMposAllocationListBean extends HttpResponse {

    /**
     * data : {"mposAllocationList":[{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314102","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314103","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314104","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314105","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314106","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314107","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314108","card_settle_price":"0.52","single_profit_rate":"100"},{"cloud_settle_price":"0.30","cash_back_rate":"100","sn":"95314109","card_settle_price":"0.52","single_profit_rate":"100"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MposAllocationListBean> mposAllocationList;

        public List<MposAllocationListBean> getMposAllocationList() {
            return mposAllocationList;
        }

        public void setMposAllocationList(List<MposAllocationListBean> mposAllocationList) {
            this.mposAllocationList = mposAllocationList;
        }

        public static class MposAllocationListBean {
            /**
             * cloud_settle_price : 0.30
             * cash_back_rate : 100
             * sn : 95314102
             * card_settle_price : 0.52
             * single_profit_rate : 100
             */

            private String cloud_settle_price;
            private String cash_back_rate;
            private String sn;
            private String card_settle_price;
            private String single_profit_rate;
            public String expire_day;
            public String policy_name;

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

            public String getCloud_settle_price() {
                return cloud_settle_price;
            }

            public void setCloud_settle_price(String cloud_settle_price) {
                this.cloud_settle_price = cloud_settle_price;
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
