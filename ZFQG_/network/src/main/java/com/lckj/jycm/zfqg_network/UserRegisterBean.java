package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class UserRegisterBean extends HttpResponse {

    /**
     * data : {"token":"3|7KBG2J44WRY0JMHJYD7VOTHQCM4LBKQL"}
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
         * token : 3|7KBG2J44WRY0JMHJYD7VOTHQCM4LBKQL
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
