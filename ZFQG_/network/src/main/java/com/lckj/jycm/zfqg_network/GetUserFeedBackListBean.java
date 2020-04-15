package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.io.Serializable;
import java.util.List;

public class GetUserFeedBackListBean extends HttpResponse {

    /**
     * data : {"userFeedBackList":[{"feedback_content":"系统卡顿","cre_time":"2019-06-13 00:00:00","user_id":1,"contact_way":"1111111","feedback_title":"反馈啦","up_time":"2019-06-13 00:00:00","feedback_answer":"已解决","id":1,"feedback_img":null}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        private List<UserFeedBackListBean> userFeedBackList;

        public List<UserFeedBackListBean> getUserFeedBackList() {
            return userFeedBackList;
        }

        public void setUserFeedBackList(List<UserFeedBackListBean> userFeedBackList) {
            this.userFeedBackList = userFeedBackList;
        }

        public static class UserFeedBackListBean  implements Serializable {
            /**
             * feedback_content : 系统卡顿
             * cre_time : 2019-06-13 00:00:00
             * user_id : 1
             * contact_way : 1111111
             * feedback_title : 反馈啦
             * up_time : 2019-06-13 00:00:00
             * feedback_answer : 已解决
             * id : 1
             * feedback_img : null
             */

            private String feedback_content;
            private String cre_time;
            private String user_id;
            private String contact_way;
            private String feedback_title;
            private String up_time;
            private String feedback_answer;
            private String id;
            private String feedback_img;

            public String getFeedback_content() {
                return feedback_content;
            }

            public void setFeedback_content(String feedback_content) {
                this.feedback_content = feedback_content;
            }

            public String getCre_time() {
                return cre_time;
            }

            public void setCre_time(String cre_time) {
                this.cre_time = cre_time;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getContact_way() {
                return contact_way;
            }

            public void setContact_way(String contact_way) {
                this.contact_way = contact_way;
            }

            public String getFeedback_title() {
                return feedback_title;
            }

            public void setFeedback_title(String feedback_title) {
                this.feedback_title = feedback_title;
            }

            public String getUp_time() {
                return up_time;
            }

            public void setUp_time(String up_time) {
                this.up_time = up_time;
            }

            public String getFeedback_answer() {
                return feedback_answer;
            }

            public void setFeedback_answer(String feedback_answer) {
                this.feedback_answer = feedback_answer;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFeedback_img() {
                return feedback_img;
            }

            public void setFeedback_img(String feedback_img) {
                this.feedback_img = feedback_img;
            }
        }
    }
}
