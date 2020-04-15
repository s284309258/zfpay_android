package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetAllocationTraditionalPosDetailBean extends HttpResponse {

    /**
     * data : {"allocationTraditionalPos":{"zhifubao_settle_price":"0.34","cloud_settle_price":"0.34","allocation_id":"13","weixin_settle_price":"0.34","cash_back_rate":"50","real_name":"沧海","sn":"95476182","card_settle_price":"0.54","single_profit_rate":"50"}}
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
         * allocationTraditionalPos : {"zhifubao_settle_price":"0.34","cloud_settle_price":"0.34","allocation_id":"13","weixin_settle_price":"0.34","cash_back_rate":"50","real_name":"沧海","sn":"95476182","card_settle_price":"0.54","single_profit_rate":"50"}
         */

        private AllocationTraditionalPosBean allocationTraditionalPos;
        private AllocationTraditionalPosBean allocationMpos;

        public AllocationTraditionalPosBean getAllocationMpos() {
            return allocationMpos;
        }

        public void setAllocationMpos(AllocationTraditionalPosBean allocationMpos) {
            this.allocationMpos = allocationMpos;
        }

        public AllocationTraditionalPosBean getAllocationTraditionalPos() {
            return allocationTraditionalPos;
        }

        public void setAllocationTraditionalPos(AllocationTraditionalPosBean allocationTraditionalPos) {
            this.allocationTraditionalPos = allocationTraditionalPos;
        }

        public static class AllocationTraditionalPosBean {
            /**
             * zhifubao_settle_price : 0.34
             * cloud_settle_price : 0.34
             * allocation_id : 13
             * weixin_settle_price : 0.34
             * cash_back_rate : 50
             * real_name : 沧海
             * sn : 95476182
             * card_settle_price : 0.54
             * single_profit_rate : 50
             */

            private String zhifubao_settle_price;
            private String cloud_settle_price;
            private String allocation_id;
            private String weixin_settle_price;
            private String cash_back_rate;
            private String real_name;
            private String sn;
            private String card_settle_price;
            private String single_profit_rate;
            private String mer_cap_fee;

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

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
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
