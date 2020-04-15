package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosOnlineActivityListBean extends HttpResponse{

    /**
     * data : {"traditionalPosOnlineActivityList":[{"end_date":"20190831","activity_name":"高返现活动","cover_url":"banners_1.jpg","activity_id":"1","start_date":"20190801"},{"end_date":"20190831","activity_name":"交易额活动","cover_url":"banners_1.jpg","activity_id":"2","start_date":"20190801"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosOnlineActivityListBean> traditionalPosOnlineActivityList;

        public List<TraditionalPosOnlineActivityListBean> getTraditionalPosOnlineActivityList() {
            return traditionalPosOnlineActivityList;
        }

        public void setTraditionalPosOnlineActivityList(List<TraditionalPosOnlineActivityListBean> traditionalPosOnlineActivityList) {
            this.traditionalPosOnlineActivityList = traditionalPosOnlineActivityList;
        }

        public static class TraditionalPosOnlineActivityListBean {
            /**
             * end_date : 20190831
             * activity_name : 高返现活动
             * cover_url : banners_1.jpg
             * activity_id : 1
             * start_date : 20190801
             */

            private String end_date;
            private String activity_name;
            private String cover_url;
            private String activity_id;
            private String start_date;

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getActivity_name() {
                return activity_name;
            }

            public void setActivity_name(String activity_name) {
                this.activity_name = activity_name;
            }

            public String getCover_url() {
                return cover_url;
            }

            public void setCover_url(String cover_url) {
                this.cover_url = cover_url;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
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
