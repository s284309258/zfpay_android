package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMposActivityApplyListBean extends HttpResponse{

    /**
     * data : {"mposActivityApplyList":[{"end_date":"20190831","expenditure":"2","activity_name":"高返现活动","cre_datetime":"2019-08-22 00:00:00","apply_id":"1","order_id":"20190822101010000001","reward_money":"20","pos_num":"2","status":"09","start_date":"20190801"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MposActivityApplyListBean> mposActivityApplyList;

        public List<MposActivityApplyListBean> getMposActivityApplyList() {
            return mposActivityApplyList;
        }

        public void setMposActivityApplyList(List<MposActivityApplyListBean> mposActivityApplyList) {
            this.mposActivityApplyList = mposActivityApplyList;
        }

        public static class MposActivityApplyListBean {
            /**
             * end_date : 20190831
             * expenditure : 2
             * activity_name : 高返现活动
             * cre_datetime : 2019-08-22 00:00:00
             * apply_id : 1
             * order_id : 20190822101010000001
             * reward_money : 20
             * pos_num : 2
             * status : 09
             * start_date : 20190801
             */

            private String end_date;
            private String expenditure;
            private String activity_name;
            private String cre_datetime;
            private String apply_id;
            private String order_id;
            private String reward_money;
            private String pos_num;
            private String status;
            private String start_date;

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getExpenditure() {
                return expenditure;
            }

            public void setExpenditure(String expenditure) {
                this.expenditure = expenditure;
            }

            public String getActivity_name() {
                return activity_name;
            }

            public void setActivity_name(String activity_name) {
                this.activity_name = activity_name;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getApply_id() {
                return apply_id;
            }

            public void setApply_id(String apply_id) {
                this.apply_id = apply_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getReward_money() {
                return reward_money;
            }

            public void setReward_money(String reward_money) {
                this.reward_money = reward_money;
            }

            public String getPos_num() {
                return pos_num;
            }

            public void setPos_num(String pos_num) {
                this.pos_num = pos_num;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
