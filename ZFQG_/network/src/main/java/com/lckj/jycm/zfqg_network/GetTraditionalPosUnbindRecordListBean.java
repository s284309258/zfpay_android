package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosUnbindRecordListBean extends HttpResponse {

    /**
     * data : {"traditionalPosUnbindRecordList":[{"unbind_id":"1","cre_datetime":"2019-08-24 18:13:00","sn":"95217293","status":"00"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosUnbindRecordListBean> traditionalPosUnbindRecordList;
        private List<TraditionalPosUnbindRecordListBean> mposUnbindRecordList;

        public List<TraditionalPosUnbindRecordListBean> getMposUnbindRecordList() {
            return mposUnbindRecordList;
        }

        public void setMposUnbindRecordList(List<TraditionalPosUnbindRecordListBean> mposUnbindRecordList) {
            this.mposUnbindRecordList = mposUnbindRecordList;
        }

        public List<TraditionalPosUnbindRecordListBean> getTraditionalPosUnbindRecordList() {
            return traditionalPosUnbindRecordList;
        }

        public void setTraditionalPosUnbindRecordList(List<TraditionalPosUnbindRecordListBean> traditionalPosUnbindRecordList) {
            this.traditionalPosUnbindRecordList = traditionalPosUnbindRecordList;
        }

        public static class TraditionalPosUnbindRecordListBean {
            /**
             * unbind_id : 1
             * cre_datetime : 2019-08-24 18:13:00
             * sn : 95217293
             * status : 00
             */

            private String unbind_id;
            private String cre_datetime;
            private String sn;
            private String status;

            public String getUnbind_id() {
                return unbind_id;
            }

            public void setUnbind_id(String unbind_id) {
                this.unbind_id = unbind_id;
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
