package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class SelectPosSettlePriceBySNBean extends HttpResponse {

    /**
     * data : {"allocationPos":{"sns":"190,191,192","zhifubao_settle_price":"0.30","cloud_settle_price":"0.3","trade_time":null,"weixin_settle_price":"0.3","cre_date":"20191028","del":"0","remark":"add","card_settle_price":"0.5","single_profit_rate":"0.00","trade_date":null,"create_by":"huitung","cre_time":"181358","up_date":"20191028","user_id":1828,"trade_status":"0","state_status":"0","cash_back_rate":"0.000","up_time":"181456","id":4146,"sn":"190","mer_cap_fee":"20","update_by":"","activity_status":"0","real_name":"邱航"}}
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
         * allocationPos : {"sns":"190,191,192","zhifubao_settle_price":"0.30","cloud_settle_price":"0.3","trade_time":null,"weixin_settle_price":"0.3","cre_date":"20191028","del":"0","remark":"add","card_settle_price":"0.5","single_profit_rate":"0.00","trade_date":null,"create_by":"huitung","cre_time":"181358","up_date":"20191028","user_id":1828,"trade_status":"0","state_status":"0","cash_back_rate":"0.000","up_time":"181456","id":4146,"sn":"190","mer_cap_fee":"20","update_by":"","activity_status":"0","real_name":"邱航"}
         */

        public AllocationPosBean allocationPos;

        public AllocationPosBean getAllocationPos() {
            return allocationPos;
        }

        public void setAllocationPos(AllocationPosBean allocationPos) {
            this.allocationPos = allocationPos;
        }

        public static class AllocationPosBean {
            /**
             * sns : 190,191,192
             * zhifubao_settle_price : 0.30
             * cloud_settle_price : 0.3
             * trade_time : null
             * weixin_settle_price : 0.3
             * cre_date : 20191028
             * del : 0
             * remark : add
             * card_settle_price : 0.5
             * single_profit_rate : 0.00
             * trade_date : null
             * create_by : huitung
             * cre_time : 181358
             * up_date : 20191028
             * user_id : 1828
             * trade_status : 0
             * state_status : 0
             * cash_back_rate : 0.000
             * up_time : 181456
             * id : 4146
             * sn : 190
             * mer_cap_fee : 20
             * update_by :
             * activity_status : 0
             * real_name : 邱航
             */

            public String sns;
            public String zhifubao_settle_price;
            public String cloud_settle_price;
            public Object trade_time;
            public String weixin_settle_price;
            public String cre_date;
            public String del;
            public String remark;
            public String card_settle_price;
            public String single_profit_rate;
            public Object trade_date;
            public String create_by;
            public String cre_time;
            public String up_date;
            public int user_id;
            public String trade_status;
            public String state_status;
            public String cash_back_rate;
            public String up_time;
            public int id;
            public String sn;
            public String mer_cap_fee;
            public String update_by;
            public String activity_status;
            public String real_name;
            public String card_settle_price_vip;
            public String cloud_settle_price_vip;
            public String weixin_settle_price_vip;
            public String zhifubao_settle_price_vip;
            public String policy_name;
            public String is_reward;

            public String getIs_reward() {
                return is_reward;
            }

            public void setIs_reward(String is_reward) {
                this.is_reward = is_reward;
            }

            public String getPolicy_name() {
                return policy_name;
            }

            public void setPolicy_name(String policy_name) {
                this.policy_name = policy_name;
            }

            public String getCard_settle_price_vip() {
                return card_settle_price_vip;
            }

            public void setCard_settle_price_vip(String card_settle_price_vip) {
                this.card_settle_price_vip = card_settle_price_vip;
            }

            public String getCloud_settle_price_vip() {
                return cloud_settle_price_vip;
            }

            public void setCloud_settle_price_vip(String cloud_settle_price_vip) {
                this.cloud_settle_price_vip = cloud_settle_price_vip;
            }

            public String getWeixin_settle_price_vip() {
                return weixin_settle_price_vip;
            }

            public void setWeixin_settle_price_vip(String weixin_settle_price_vip) {
                this.weixin_settle_price_vip = weixin_settle_price_vip;
            }

            public String getZhifubao_settle_price_vip() {
                return zhifubao_settle_price_vip;
            }

            public void setZhifubao_settle_price_vip(String zhifubao_settle_price_vip) {
                this.zhifubao_settle_price_vip = zhifubao_settle_price_vip;
            }

            public String getSns() {
                return sns;
            }

            public void setSns(String sns) {
                this.sns = sns;
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

            public Object getTrade_time() {
                return trade_time;
            }

            public void setTrade_time(Object trade_time) {
                this.trade_time = trade_time;
            }

            public String getWeixin_settle_price() {
                return weixin_settle_price;
            }

            public void setWeixin_settle_price(String weixin_settle_price) {
                this.weixin_settle_price = weixin_settle_price;
            }

            public String getCre_date() {
                return cre_date;
            }

            public void setCre_date(String cre_date) {
                this.cre_date = cre_date;
            }

            public String getDel() {
                return del;
            }

            public void setDel(String del) {
                this.del = del;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
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

            public Object getTrade_date() {
                return trade_date;
            }

            public void setTrade_date(Object trade_date) {
                this.trade_date = trade_date;
            }

            public String getCreate_by() {
                return create_by;
            }

            public void setCreate_by(String create_by) {
                this.create_by = create_by;
            }

            public String getCre_time() {
                return cre_time;
            }

            public void setCre_time(String cre_time) {
                this.cre_time = cre_time;
            }

            public String getUp_date() {
                return up_date;
            }

            public void setUp_date(String up_date) {
                this.up_date = up_date;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getTrade_status() {
                return trade_status;
            }

            public void setTrade_status(String trade_status) {
                this.trade_status = trade_status;
            }

            public String getState_status() {
                return state_status;
            }

            public void setState_status(String state_status) {
                this.state_status = state_status;
            }

            public String getCash_back_rate() {
                return cash_back_rate;
            }

            public void setCash_back_rate(String cash_back_rate) {
                this.cash_back_rate = cash_back_rate;
            }

            public String getUp_time() {
                return up_time;
            }

            public void setUp_time(String up_time) {
                this.up_time = up_time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getMer_cap_fee() {
                return mer_cap_fee;
            }

            public void setMer_cap_fee(String mer_cap_fee) {
                this.mer_cap_fee = mer_cap_fee;
            }

            public String getUpdate_by() {
                return update_by;
            }

            public void setUpdate_by(String update_by) {
                this.update_by = update_by;
            }

            public String getActivity_status() {
                return activity_status;
            }

            public void setActivity_status(String activity_status) {
                this.activity_status = activity_status;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }
        }
    }
}
