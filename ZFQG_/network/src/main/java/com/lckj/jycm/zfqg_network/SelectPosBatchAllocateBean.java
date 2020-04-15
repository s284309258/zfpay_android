package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class SelectPosBatchAllocateBean extends HttpResponse {

    /**
     * data : {"allocateList":[{"min_sn":"3","batch_no":"1828_20191027183642","user_id":"1829","max_sn":"3","allocate_date":"2019-10-27 18:36:43","real_name":"邱航","allocate_by":"1828"},{"min_sn":"10","batch_no":"1828_20191027183956","user_id":"1829","max_sn":"7","allocate_date":"2019-10-27 18:39:57","real_name":"邱航","allocate_by":"1828"},{"min_sn":"190","batch_no":"1828_20191028180403","user_id":"1829","max_sn":"192","allocate_date":"2019-10-28 18:04:03","real_name":"邱航","allocate_by":"1828"},{"min_sn":"193","batch_no":"1828_20191028180438","user_id":"1829","max_sn":"195","allocate_date":"2019-10-28 18:04:38","real_name":"邱航","allocate_by":"1828"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AllocateListBean> allocateList;

        public List<AllocateListBean> getAllocateList() {
            return allocateList;
        }

        public void setAllocateList(List<AllocateListBean> allocateList) {
            this.allocateList = allocateList;
        }

        public static class AllocateListBean {
            /**
             * min_sn : 3
             * batch_no : 1828_20191027183642
             * user_id : 1829
             * max_sn : 3
             * allocate_date : 2019-10-27 18:36:43
             * real_name : 邱航
             * allocate_by : 1828
             */

            private String min_sn;
            private String batch_no;
            private String user_id;
            private String max_sn;
            private String allocate_date;
            private String real_name;
            private String allocate_by;
            private String id;
            private String cnt;

            public String getCnt() {
                return cnt;
            }

            public void setCnt(String cnt) {
                this.cnt = cnt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMin_sn() {
                return min_sn;
            }

            public void setMin_sn(String min_sn) {
                this.min_sn = min_sn;
            }

            public String getBatch_no() {
                return batch_no;
            }

            public void setBatch_no(String batch_no) {
                this.batch_no = batch_no;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getMax_sn() {
                return max_sn;
            }

            public void setMax_sn(String max_sn) {
                this.max_sn = max_sn;
            }

            public String getAllocate_date() {
                return allocate_date;
            }

            public void setAllocate_date(String allocate_date) {
                this.allocate_date = allocate_date;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getAllocate_by() {
                return allocate_by;
            }

            public void setAllocate_by(String allocate_by) {
                this.allocate_by = allocate_by;
            }
        }
    }
}
