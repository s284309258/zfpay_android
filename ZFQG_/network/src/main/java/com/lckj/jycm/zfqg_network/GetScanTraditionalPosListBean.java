package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetScanTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"scanTraditionalPosList":[{"sn":"95314618","trapos_id":"10"},{"sn":"92617921","trapos_id":"9"},{"sn":"92932752","trapos_id":"8"},{"sn":"95217293","trapos_id":"7"},{"sn":"92308252","trapos_id":"6"},{"sn":"95476182","trapos_id":"5"},{"sn":"91581156","trapos_id":"4"},{"sn":"92280318","trapos_id":"3"},{"sn":"94585127","trapos_id":"2"},{"sn":"95370909","trapos_id":"1"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ScanTraditionalPosListBean> scanTraditionalPosList;

        public List<ScanTraditionalPosListBean> getScanTraditionalPosList() {
            return scanTraditionalPosList;
        }

        public void setScanTraditionalPosList(List<ScanTraditionalPosListBean> scanTraditionalPosList) {
            this.scanTraditionalPosList = scanTraditionalPosList;
        }

        public static class ScanTraditionalPosListBean {
            /**
             * sn : 95314618
             * trapos_id : 10
             */

            private String sn;
            private String trapos_id;
            private String mpos_id;

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
