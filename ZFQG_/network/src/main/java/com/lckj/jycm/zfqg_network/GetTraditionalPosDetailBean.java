package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

public class GetTraditionalPosDetailBean extends HttpResponse {

    /**
     * data : {"traditionalPosDetail":{"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","zhifubao_rate":"0.38","credit_card_rate":"0.60","weixin_rate":"0.38","weixin_settle_price":"0.32","cloud_flash_rate":"0.38","card_settle_price":"0.52","single_profit_rate":"100","act_status":"0","name":null,"cash_back_rate":"100","tel":null,"sn":"95217293"}}
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
         * traditionalPosDetail : {"zhifubao_settle_price":"0.32","cloud_settle_price":"0.30","zhifubao_rate":"0.38","credit_card_rate":"0.60","weixin_rate":"0.38","weixin_settle_price":"0.32","cloud_flash_rate":"0.38","card_settle_price":"0.52","single_profit_rate":"100","act_status":"0","name":null,"cash_back_rate":"100","tel":null,"sn":"95217293"}
         */

        private TraditionalPosDetailBean traditionalPosDetail;
        private TraditionalPosDetailBean mposDetail;

        public TraditionalPosDetailBean getMposDetail() {
            return mposDetail;
        }

        public void setMposDetail(TraditionalPosDetailBean mposDetail) {
            this.mposDetail = mposDetail;
        }

        public TraditionalPosDetailBean getTraditionalPosDetail() {
            return traditionalPosDetail;
        }

        public void setTraditionalPosDetail(TraditionalPosDetailBean traditionalPosDetail) {
            this.traditionalPosDetail = traditionalPosDetail;
        }

        public static class TraditionalPosDetailBean {
            /**
             * zhifubao_settle_price : 0.32
             * cloud_settle_price : 0.30
             * zhifubao_rate : 0.38
             * credit_card_rate : 0.60
             * weixin_rate : 0.38
             * weixin_settle_price : 0.32
             * cloud_flash_rate : 0.38
             * card_settle_price : 0.52
             * single_profit_rate : 100
             * act_status : 0
             * name : null
             * cash_back_rate : 100
             * tel : null
             * sn : 95217293
             */

            private String zhifubao_settle_price;
            private String cloud_settle_price;
            private String zhifubao_rate;
            private String credit_card_rate;
            private String weixin_rate;
            private String weixin_settle_price;
            private String cloud_flash_rate;
            private String card_settle_price;
            private String single_profit_rate;
            private String act_status;
            private String name;
            private String cash_back_rate;
            private String tel;
            private String sn;
            private String mer_cap_fee;
            private String mer_name;
            private String mer_id;
            private String num;
            private String performance;
            private String cash_back_status;
            public String card_settle_price_vip;
            public String expire_day;
            public String policy_name;
            public String act_date;

            public String getAct_date() {
                return act_date;
            }

            public void setAct_date(String act_date) {
                this.act_date = act_date;
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

            public String getCard_settle_price_vip() {
                return card_settle_price_vip;
            }

            public void setCard_settle_price_vip(String card_settle_price_vip) {
                this.card_settle_price_vip = card_settle_price_vip;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPerformance() {
                return performance;
            }

            public void setPerformance(String performance) {
                this.performance = performance;
            }

            public String getCash_back_status() {
                return cash_back_status;
            }

            public void setCash_back_status(String cash_back_status) {
                this.cash_back_status = cash_back_status;
            }

            public String getMer_name() {
                return mer_name;
            }

            public void setMer_name(String mer_name) {
                this.mer_name = mer_name;
            }

            public String getMer_id() {
                return mer_id;
            }

            public void setMer_id(String mer_id) {
                this.mer_id = mer_id;
            }

            public String getMer_cap_fee() {
                return mer_cap_fee;
            }

            public void setMer_cap_fee(String mer_cap_fee) {
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

            public String getZhifubao_rate() {
                return zhifubao_rate;
            }

            public void setZhifubao_rate(String zhifubao_rate) {
                this.zhifubao_rate = zhifubao_rate;
            }

            public String getCredit_card_rate() {
                return credit_card_rate;
            }

            public void setCredit_card_rate(String credit_card_rate) {
                this.credit_card_rate = credit_card_rate;
            }

            public String getWeixin_rate() {
                return weixin_rate;
            }

            public void setWeixin_rate(String weixin_rate) {
                this.weixin_rate = weixin_rate;
            }

            public String getWeixin_settle_price() {
                return weixin_settle_price;
            }

            public void setWeixin_settle_price(String weixin_settle_price) {
                this.weixin_settle_price = weixin_settle_price;
            }

            public String getCloud_flash_rate() {
                return cloud_flash_rate;
            }

            public void setCloud_flash_rate(String cloud_flash_rate) {
                this.cloud_flash_rate = cloud_flash_rate;
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

            public String getAct_status() {
                return act_status;
            }

            public void setAct_status(String act_status) {
                this.act_status = act_status;
            }

            public String getName() {
                if (TextUtils.isEmpty(name))
                    name = "——";
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCash_back_rate() {
                return cash_back_rate;
            }

            public void setCash_back_rate(String cash_back_rate) {
                this.cash_back_rate = cash_back_rate;
            }

            public String getTel() {
                if (TextUtils.isEmpty(tel))
                    tel = "——";
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}
