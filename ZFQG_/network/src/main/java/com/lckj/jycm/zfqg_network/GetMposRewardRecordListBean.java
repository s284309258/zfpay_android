package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class GetMposRewardRecordListBean extends HttpResponse {

    /**
     * data : {"mposRewardRecordList":[{"end_date":"20190831","record_id":"1","activity_name":"高返现活动","money":"20","cre_datetime":"2019-08-22 00:00:00","sn":"95314100","order_id":"2019082210101000001","start_date":"20190801"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MposRewardRecordListBean> mposRewardRecordList;
        private List<MposRewardRecordListBean> traditionalPosRewardRecordList;

        public List<MposRewardRecordListBean> getMposRewardRecordList() {
            if (mposRewardRecordList == null) {
                mposRewardRecordList = new ArrayList<>();
            }
            if (traditionalPosRewardRecordList == null) {
                traditionalPosRewardRecordList = new ArrayList<>();
            }
            if (mposRewardRecordList.size() != 0)
                return mposRewardRecordList;
            return traditionalPosRewardRecordList;
        }

        public void setMposRewardRecordList(List<MposRewardRecordListBean> mposRewardRecordList) {
            this.mposRewardRecordList = mposRewardRecordList;
        }

        public static class MposRewardRecordListBean {
            /**
             * end_date : 20190831
             * record_id : 1
             * activity_name : 高返现活动
             * money : 20
             * cre_datetime : 2019-08-22 00:00:00
             * sn : 95314100
             * order_id : 2019082210101000001
             * start_date : 20190801
             */

            private String end_date;
            private String record_id;
            private String activity_name;
            private String money;
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

            public String getActivity_name() {
                return activity_name;
            }

            public void setActivity_name(String activity_name) {
                this.activity_name = activity_name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
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
