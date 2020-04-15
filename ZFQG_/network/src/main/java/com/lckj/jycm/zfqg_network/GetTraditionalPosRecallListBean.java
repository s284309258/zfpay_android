package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosRecallListBean extends HttpResponse {

    /**
     * data : {"traditionalPosRecallList":[{"sn":"92280318"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosRecallListBean> traditionalPosRecallList;
        private List<TraditionalPosRecallListBean> traditionalPosUnbindList;

        public List<TraditionalPosRecallListBean> getTraditionalPosUnbindList() {
            return traditionalPosUnbindList;
        }

        public void setTraditionalPosUnbindList(List<TraditionalPosRecallListBean> traditionalPosUnbindList) {
            this.traditionalPosUnbindList = traditionalPosUnbindList;
        }

        public List<TraditionalPosRecallListBean> getTraditionalPosRecallList() {
            return traditionalPosRecallList;
        }

        public void setTraditionalPosRecallList(List<TraditionalPosRecallListBean> traditionalPosRecallList) {
            this.traditionalPosRecallList = traditionalPosRecallList;
        }

        public static class TraditionalPosRecallListBean {
            /**
             * sn : 92280318
             */

            private String sn;

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}
