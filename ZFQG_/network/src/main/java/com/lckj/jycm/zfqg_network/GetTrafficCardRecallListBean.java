package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTrafficCardRecallListBean extends HttpResponse {

    /**
     * data : {"trafficCardRecallList":[{"card_no":"100001"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TrafficCardRecallListBean> trafficCardRecallList;

        public List<TrafficCardRecallListBean> getTrafficCardRecallList() {
            return trafficCardRecallList;
        }

        public void setTrafficCardRecallList(List<TrafficCardRecallListBean> trafficCardRecallList) {
            this.trafficCardRecallList = trafficCardRecallList;
        }

        public static class TrafficCardRecallListBean {
            /**
             * card_no : 100001
             */

            private String card_no;

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }
        }
    }
}
