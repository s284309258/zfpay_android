package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class UserLoginBean extends HttpResponse {

    /**
     * data : {"user_id":"3","qiniu_domain":"http://cdn.yhswl.com","user_tel":"18772101525","pay_password":"99999999999999","sys_time":1566290195494,"real_name":"","auth_status":"00","head_photo":"defaultAvatar.png","qr_code_url":"http://www.proecosystem.com/html/rst.html?account=18772101525","token":"3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076"}
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
         * user_id : 3
         * qiniu_domain : http://cdn.yhswl.com
         * user_tel : 18772101525
         * pay_password : 99999999999999
         * sys_time : 1566290195494
         * real_name :
         * auth_status : 00
         * head_photo : defaultAvatar.png
         * qr_code_url : http://www.proecosystem.com/html/rst.html?account=18772101525
         * token : 3|CKORI742CK8UOYXZ0I3H8XIIHKV9F076
         */

        private String user_id;
        private String qiniu_domain;
        private String user_tel;
        private String pay_password;
        private long sys_time;
        private String real_name;
        private String auth_status;
        private String head_photo;
        private String qr_code_url;
        private String token;
        private String algebra;
        private String app_id;

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getAlgebra() {
            return algebra;
        }

        public void setAlgebra(String algebra) {
            this.algebra = algebra;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getQiniu_domain() {
            return qiniu_domain;
        }

        public void setQiniu_domain(String qiniu_domain) {
            this.qiniu_domain = qiniu_domain;
        }

        public String getUser_tel() {
            return user_tel;
        }

        public void setUser_tel(String user_tel) {
            this.user_tel = user_tel;
        }

        public String getPay_password() {
            return pay_password;
        }

        public void setPay_password(String pay_password) {
            this.pay_password = pay_password;
        }

        public long getSys_time() {
            return sys_time;
        }

        public void setSys_time(long sys_time) {
            this.sys_time = sys_time;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getAuth_status() {
            return auth_status;
        }

        public void setAuth_status(String auth_status) {
            this.auth_status = auth_status;
        }

        public String getHead_photo() {
            return head_photo;
        }

        public void setHead_photo(String head_photo) {
            this.head_photo = head_photo;
        }

        public String getQr_code_url() {
            return qr_code_url;
        }

        public void setQr_code_url(String qr_code_url) {
            this.qr_code_url = qr_code_url;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
