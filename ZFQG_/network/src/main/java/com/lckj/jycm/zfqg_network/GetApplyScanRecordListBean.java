package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetApplyScanRecordListBean extends HttpResponse {

    /**
     * data : {"applyScanRecordList":[{"record_id":"2","cre_datetime":"2019-08-21 20:26:36","sn":"94585127","status":"00"},{"record_id":"1","cre_datetime":"2019-08-21 20:26:36","sn":"95370909","status":"00"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ApplyScanRecordListBean> applyScanRecordList;

        public List<ApplyScanRecordListBean> getApplyScanRecordList() {
            return applyScanRecordList;
        }

        public void setApplyScanRecordList(List<ApplyScanRecordListBean> applyScanRecordList) {
            this.applyScanRecordList = applyScanRecordList;
        }

        public static class ApplyScanRecordListBean {
            /**
             * record_id : 2
             * cre_datetime : 2019-08-21 20:26:36
             * sn : 94585127
             * status : 00
             */

            private String record_id;
            private String cre_datetime;
            private String sn;
            private String status;

            public String getRecord_id() {
                return record_id;
            }

            public void setRecord_id(String record_id) {
                this.record_id = record_id;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
