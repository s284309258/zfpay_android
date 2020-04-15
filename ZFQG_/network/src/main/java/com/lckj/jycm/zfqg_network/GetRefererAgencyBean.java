package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetRefererAgencyBean extends HttpResponse {

    /**
     * data : {"refererAgencyList":[{"user_id":"4","user_tel":"18772101525","real_name":"沧海","head_photo":null}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RefererAgencyListBean> refererAgencyList;

        public List<RefererAgencyListBean> getRefererAgencyList() {
            return refererAgencyList;
        }

        public void setRefererAgencyList(List<RefererAgencyListBean> refererAgencyList) {
            this.refererAgencyList = refererAgencyList;
        }

        public static class RefererAgencyListBean {
            /**
             * user_id : 4
             * user_tel : 18772101525
             * real_name : 沧海
             * head_photo : null
             */

            private String user_id;
            private String user_tel;
            private String real_name;
            private Object head_photo;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_tel() {
                return user_tel;
            }

            public void setUser_tel(String user_tel) {
                this.user_tel = user_tel;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public Object getHead_photo() {
                return head_photo;
            }

            public void setHead_photo(Object head_photo) {
                this.head_photo = head_photo;
            }
        }
    }
}
