package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class NewVersionInfoBean extends HttpResponse{

    private DataBean data;
    private Object token;

    public DataBean getData() {
        if (data == null)return data = new DataBean();
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * version_no : verion1.1
         * id : 1
         * version_url
         * status : 1
         */

        private String version_no;
        private int id;
        /**是否强制更新 0—否，1—是*/
        private String status;
        private String text;
        private String version_url;
        private String cre_date;

        public String getText() {
            if (text==null)return text="";
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getVersion_url() {
            return version_url;
        }

        public void setVersion_url(String version_url) {
            this.version_url = version_url;
        }

        public String getCre_date() {
            return cre_date;
        }

        public void setCre_date(String cre_date) {
            this.cre_date = cre_date;
        }

        public String getCre_time() {
            return cre_time;
        }

        public void setCre_time(String cre_time) {
            this.cre_time = cre_time;
        }

        private String cre_time

                ;

        public String getVersion_no() {
            if (version_no==null)return version_no = "";
            return version_no;
        }

        public void setVersion_no(String version_no) {
            this.version_no = version_no;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
