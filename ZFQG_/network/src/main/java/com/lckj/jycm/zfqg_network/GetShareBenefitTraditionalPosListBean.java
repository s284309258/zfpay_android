package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetShareBenefitTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"shareBenefitTraditionalPosList":[{"record_id":"4","benefit_type":"02","single_amount":"3","state_type":"1","cre_datetime":"2019-08-25 00:00:00","benefit_money":"1","sn":"95217293","trans_datetime":"2019-08-25 10:20:20","trans_amount":"20000","card_type":"1","order_id":"2019082513456512343211100004","trans_type":"1"},{"record_id":"3","benefit_type":"02","single_amount":"3","state_type":"1","cre_datetime":"2019-08-25 00:00:00","benefit_money":"1","sn":"95217293","trans_datetime":"2019-08-25 10:20:20","trans_amount":"20000","card_type":"1","order_id":"2019082513456512343211100003","trans_type":"1"},{"record_id":"2","benefit_type":"01","single_amount":"3","state_type":"1","cre_datetime":"2019-08-25 00:00:00","benefit_money":"10","sn":"95217293","trans_datetime":"2019-08-25 10:20:20","trans_amount":"20000","card_type":"1","order_id":"2019082513456512343211100002","trans_type":"1"},{"record_id":"1","benefit_type":"01","single_amount":"3","state_type":"1","cre_datetime":"2019-08-25 00:00:00","benefit_money":"10","sn":"95217293","trans_datetime":"2019-08-25 10:20:20","trans_amount":"20000","card_type":"1","order_id":"2019082513456512343211100001","trans_type":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ShareBenefitTraditionalPosListBean> shareBenefitTraditionalPosList;
        private List<ShareBenefitTraditionalPosListBean> shareBenefitMposList;

        public List<ShareBenefitTraditionalPosListBean> getShareBenefitMposList() {
            return shareBenefitMposList;
        }

        public void setShareBenefitMposList(List<ShareBenefitTraditionalPosListBean> shareBenefitMposList) {
            this.shareBenefitMposList = shareBenefitMposList;
        }

        public List<ShareBenefitTraditionalPosListBean> getShareBenefitTraditionalPosList() {
            return shareBenefitTraditionalPosList;
        }

        public void setShareBenefitTraditionalPosList(List<ShareBenefitTraditionalPosListBean> shareBenefitTraditionalPosList) {
            this.shareBenefitTraditionalPosList = shareBenefitTraditionalPosList;
        }

        public static class ShareBenefitTraditionalPosListBean {
            /**
             * record_id : 4
             * benefit_type : 02
             * single_amount : 3
             * state_type : 1
             * cre_datetime : 2019-08-25 00:00:00
             * benefit_money : 1
             * sn : 95217293
             * trans_datetime : 2019-08-25 10:20:20
             * trans_amount : 20000
             * card_type : 1
             * order_id : 2019082513456512343211100004
             * trans_type : 1
             */

            private String record_id;
            private String benefit_type;
            private String single_amount;
            private String state_type;
            private String cre_datetime;
            private String benefit_money;
            private String sn;
            private String trans_datetime;
            private String trans_amount;
            private String card_type;
            private String order_id;
            private String trans_type;
            private String trans_product;

            public String getTrans_product() {
                return trans_product;
            }

            public void setTrans_product(String trans_product) {
                this.trans_product = trans_product;
            }

            public String getRecord_id() {
                return record_id;
            }

            public void setRecord_id(String record_id) {
                this.record_id = record_id;
            }

            public String getBenefit_type() {
                return benefit_type;
            }

            public void setBenefit_type(String benefit_type) {
                this.benefit_type = benefit_type;
            }

            public String getSingle_amount() {
                return single_amount;
            }

            public void setSingle_amount(String single_amount) {
                this.single_amount = single_amount;
            }

            public String getState_type() {
                return state_type;
            }

            public void setState_type(String state_type) {
                this.state_type = state_type;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getBenefit_money() {
                return benefit_money;
            }

            public void setBenefit_money(String benefit_money) {
                this.benefit_money = benefit_money;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getTrans_datetime() {
                return trans_datetime;
            }

            public void setTrans_datetime(String trans_datetime) {
                this.trans_datetime = trans_datetime;
            }

            public String getTrans_amount() {
                return trans_amount;
            }

            public void setTrans_amount(String trans_amount) {
                this.trans_amount = trans_amount;
            }

            public String getCard_type() {
                return card_type;
            }

            public void setCard_type(String card_type) {
                this.card_type = card_type;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getTrans_type() {
                return trans_type;
            }

            public void setTrans_type(String trans_type) {
                this.trans_type = trans_type;
            }
        }
    }
}
