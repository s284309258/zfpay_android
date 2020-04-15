package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetDeductTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"deductTraditionalPosList":[{"end_date":"20190831","record_id":"1","money":"20","assess_name":"交易额达标活动","cre_datetime":"2019-08-22 00:00:00","sn":"92932752","order_id":"2019082513456512343211500003","start_date":"20190801"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<DeductTraditionalPosListBean> deductTraditionalPosList;
        private List<DeductTraditionalPosListBean> deductMposList;

        public List<DeductTraditionalPosListBean> getDeductMposList() {
            return deductMposList;
        }

        public void setDeductMposList(List<DeductTraditionalPosListBean> deductMposList) {
            this.deductMposList = deductMposList;
        }

        public List<DeductTraditionalPosListBean> getDeductTraditionalPosList() {
            return deductTraditionalPosList;
        }

        public void setDeductTraditionalPosList(List<DeductTraditionalPosListBean> deductTraditionalPosList) {
            this.deductTraditionalPosList = deductTraditionalPosList;
        }

        public static class DeductTraditionalPosListBean {
            /**
             * end_date : 20190831
             * record_id : 1
             * money : 20
             * assess_name : 交易额达标活动
             * cre_datetime : 2019-08-22 00:00:00
             * sn : 92932752
             * order_id : 2019082513456512343211500003
             * start_date : 20190801
             */

            private String end_date;
            private String record_id;
            private String money;
            private String assess_name;
            private String cre_datetime;
            private String sn;
            private String order_id;
            private String start_date;

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getRecord_id() {
                return record_id;
            }

            public void setRecord_id(String record_id) {
                this.record_id = record_id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getAssess_name() {
                return assess_name;
            }

            public void setAssess_name(String assess_name) {
                this.assess_name = assess_name;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }
        }
    }
}
