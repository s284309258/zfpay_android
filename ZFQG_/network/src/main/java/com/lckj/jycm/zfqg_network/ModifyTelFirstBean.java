package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class ModifyTelFirstBean extends HttpResponse {

    /**
     * data : {"valid_flag":"20190820174641114642743"}
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
         * valid_flag : 20190820174641114642743
         */

        private String valid_flag;

        public String getValid_flag() {
            return valid_flag;
        }

        public void setValid_flag(String valid_flag) {
            this.valid_flag = valid_flag;
        }
    }
}
