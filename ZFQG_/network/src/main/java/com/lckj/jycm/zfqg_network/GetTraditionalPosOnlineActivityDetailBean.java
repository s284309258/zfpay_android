package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetTraditionalPosOnlineActivityDetailBean extends HttpResponse {

    /**
     * data : {"traditionalPosOnlineActivity":{"end_date":"20190831","activity_name":"高返现活动","detail_url":"banners_1.jpg","activity_id":"1","start_date":"20190801"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * traditionalPosOnlineActivity : {"end_date":"20190831","activity_name":"高返现活动","detail_url":"banners_1.jpg","activity_id":"1","start_date":"20190801"}
         */

        private TraditionalPosOnlineActivityBean traditionalPosOnlineActivity;
        private TraditionalPosOnlineActivityBean mposOnlineActivity;

        public TraditionalPosOnlineActivityBean getMposOnlineActivity() {
            return mposOnlineActivity;
        }

        public void setMposOnlineActivity(TraditionalPosOnlineActivityBean mposOnlineActivity) {
            this.mposOnlineActivity = mposOnlineActivity;
        }

        public TraditionalPosOnlineActivityBean getTraditionalPosOnlineActivity() {
            return traditionalPosOnlineActivity;
        }

        public void setTraditionalPosOnlineActivity(TraditionalPosOnlineActivityBean traditionalPosOnlineActivity) {
            this.traditionalPosOnlineActivity = traditionalPosOnlineActivity;
        }

        public static class TraditionalPosOnlineActivityBean {
            /**
             * end_date : 20190831
             * activity_name : 高返现活动
             * detail_url : banners_1.jpg
             * activity_id : 1
             * start_date : 20190801
             */

            private String end_date;
            private String activity_name;
            private String detail_url;
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

            public String getDetail_url() {
                return detail_url;
            }

            public void setDetail_url(String detail_url) {
                this.detail_url = detail_url;
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
