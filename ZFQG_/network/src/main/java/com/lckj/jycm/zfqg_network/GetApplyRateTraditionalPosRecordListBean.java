package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetApplyRateTraditionalPosRecordListBean extends HttpResponse {
    /**
     * data : {"applyRateTraditionalPosRecordList":[{"credit_card_rate_old":"0.60","cre_datetime":"2019-08-24 21:22:21","apply_id":"5","sn":"92308252","credit_card_rate_new":"0.62","status":"00"},{"credit_card_rate_old":"0.60","cre_datetime":"2019-08-24 21:22:21","apply_id":"4","sn":"95476182","credit_card_rate_new":"0.62","status":"00"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ApplyRateTraditionalPosRecordListBean> applyRateTraditionalPosRecordList;
        private List<ApplyRateTraditionalPosRecordListBean> applyRateMposRecordList;

        public List<ApplyRateTraditionalPosRecordListBean> getApplyRateMposRecordList() {
            return applyRateMposRecordList;
        }

        public void setApplyRateMposRecordList(List<ApplyRateTraditionalPosRecordListBean> applyRateMposRecordList) {
            this.applyRateMposRecordList = applyRateMposRecordList;
        }

        public List<ApplyRateTraditionalPosRecordListBean> getApplyRateTraditionalPosRecordList() {
            return applyRateTraditionalPosRecordList;
        }

        public void setApplyRateTraditionalPosRecordList(List<ApplyRateTraditionalPosRecordListBean> applyRateTraditionalPosRecordList) {
            this.applyRateTraditionalPosRecordList = applyRateTraditionalPosRecordList;
        }

        public static class ApplyRateTraditionalPosRecordListBean {
            /**
             * credit_card_rate_old : 0.60
             * cre_datetime : 2019-08-24 21:22:21
             * apply_id : 5
             * sn : 92308252
             * credit_card_rate_new : 0.62
             * status : 00
             */

            private String credit_card_rate_old;
            private String cre_datetime;
            private String apply_id;
            private String sn;
            private String credit_card_rate_new;
            private String status;
            private String remark;

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCredit_card_rate_old() {
                return credit_card_rate_old;
            }

            public void setCredit_card_rate_old(String credit_card_rate_old) {
                this.credit_card_rate_old = credit_card_rate_old;
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

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getCredit_card_rate_new() {
                return credit_card_rate_new;
            }

            public void setCredit_card_rate_new(String credit_card_rate_new) {
                this.credit_card_rate_new = credit_card_rate_new;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
