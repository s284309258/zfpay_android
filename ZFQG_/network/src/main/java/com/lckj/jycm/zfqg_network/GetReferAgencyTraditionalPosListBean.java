package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetReferAgencyTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"referAgencyTraditionalPosList":[{"performance":"0.00","name":null,"sn":"95476182","trapos_id":"13"},{"performance":"0.00","name":null,"sn":"91581156","trapos_id":"12"},{"performance":"0.00","name":null,"sn":"92280318","trapos_id":"11"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ReferAgencyTraditionalPosListBean> referAgencyTraditionalPosList;
        private List<ReferAgencyTraditionalPosListBean> referAgencyMposList;

        public List<ReferAgencyTraditionalPosListBean> getReferAgencyMposList() {
            return referAgencyMposList;
        }

        public void setReferAgencyMposList(List<ReferAgencyTraditionalPosListBean> referAgencyMposList) {
            this.referAgencyMposList = referAgencyMposList;
        }

        public List<ReferAgencyTraditionalPosListBean> getReferAgencyTraditionalPosList() {
            return referAgencyTraditionalPosList;
        }

        public void setReferAgencyTraditionalPosList(List<ReferAgencyTraditionalPosListBean> referAgencyTraditionalPosList) {
            this.referAgencyTraditionalPosList = referAgencyTraditionalPosList;
        }

        public static class ReferAgencyTraditionalPosListBean {
            /**
             * performance : 0.00
             * name : null
             * sn : 95476182
             * trapos_id : 13
             */

            private String performance;
            private String name;
            private String mer_name;
            private String sn;
            private String trapos_id;
            private String mpos_id;
            private String state_status;

            public String getState_status() {
                return state_status;
            }

            public void setState_status(String state_status) {
                this.state_status = state_status;
            }

            public String getMer_name() {
                if (TextUtils.isEmpty(mer_name)) mer_name = "——";
                return mer_name;
            }

            public void setMer_name(String mer_name) {
                this.mer_name = mer_name;
            }

            private String getMpos_id() {
                return mpos_id;
            }

            private void setMpos_id(String mpos_id) {
                this.mpos_id = mpos_id;
            }

            public String getPerformance() {
                if (TextUtils.isEmpty(performance)) performance = "0.00";
                return performance;
            }

            public void setPerformance(String performance) {
                this.performance = performance;
            }

            public String getName() {
                if (TextUtils.isEmpty(name)) name = "——";
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getTrapos_id() {
                if (TextUtils.isEmpty(trapos_id)) trapos_id = mpos_id;
                return trapos_id;
            }

            public void setTrapos_id(String trapos_id) {
                this.trapos_id = trapos_id;
            }
        }
    }
}
