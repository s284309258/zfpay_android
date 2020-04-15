package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTrafficCardAllocationListBean extends HttpResponse {

    /**
     * data : {"trafficCardAllocationList":[{"card_no":"100001"},{"card_no":"100002"},{"card_no":"100003"},{"card_no":"100004"},{"card_no":"100005"},{"card_no":"100006"},{"card_no":"100007"},{"card_no":"100008"},{"card_no":"100009"},{"card_no":"100010"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TrafficCardAllocationListBean> trafficCardAllocationList;

        public List<TrafficCardAllocationListBean> getTrafficCardAllocationList() {
            return trafficCardAllocationList;
        }

        public void setTrafficCardAllocationList(List<TrafficCardAllocationListBean> trafficCardAllocationList) {
            this.trafficCardAllocationList = trafficCardAllocationList;
        }

        public static class TrafficCardAllocationListBean {
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
