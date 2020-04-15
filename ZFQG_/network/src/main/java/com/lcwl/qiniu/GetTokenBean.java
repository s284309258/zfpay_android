package com.lcwl.qiniu;

import com.lckj.framework.network.HttpResponse;

public class GetTokenBean extends HttpResponse{


    /**
     * data : {"qiniu_domain":"http://wjdhtop.com/","qiniu_token":"vO5_iAyEm8445U3ysWEZEHyg2HQ2qO9bQpEcna48:aCU4zU9Cdykhqix8GtultYqhvBo=:eyJzY29wZSI6ImltYWdlcyIsImRlYWRsaW5lIjoxNTQ3MTE2MDU2fQ=="}
     * token :
     * code :
     */

    private DataBean data;
    private String token;

    public DataBean getData() {
        if (data==null)return data = new DataBean();
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public static class DataBean {
        /**
         * qiniu_domain : http://wjdhtop.com/
         * qiniu_token : vO5_iAyEm8445U3ysWEZEHyg2HQ2qO9bQpEcna48:aCU4zU9Cdykhqix8GtultYqhvBo=:eyJzY29wZSI6ImltYWdlcyIsImRlYWRsaW5lIjoxNTQ3MTE2MDU2fQ==
         */

        private String qiniu_domain;
        private String qiniu_token;

        public String getQiniu_domain() {
            return qiniu_domain;
        }

        public void setQiniu_domain(String qiniu_domain) {
            this.qiniu_domain = qiniu_domain;
        }

        public String getQiniu_token() {
            return qiniu_token;
        }

        public void setQiniu_token(String qiniu_token) {
            this.qiniu_token = qiniu_token;
        }
    }
}
