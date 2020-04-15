package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMposRecallListBean extends HttpResponse {

    /**
     * data : {"mposRecallList":[{"sn":"95314102"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MposRecallListBean> mposRecallList;
        private List<MposRecallListBean> mposUnbindList;

        public List<MposRecallListBean> getMposUnbindList() {
            return mposUnbindList;
        }

        public void setMposUnbindList(List<MposRecallListBean> mposUnbindList) {
            this.mposUnbindList = mposUnbindList;
        }

        public List<MposRecallListBean> getMposRecallList() {
            return mposRecallList;
        }

        public void setMposRecallList(List<MposRecallListBean> mposRecallList) {
            this.mposRecallList = mposRecallList;
        }

        public static class MposRecallListBean {
            /**
             * sn : 95314102
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
