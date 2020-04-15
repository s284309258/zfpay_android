package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class UpdateUserCardBean extends HttpResponse {

    /**
     * data : {"card_id":"1452"}
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
         * card_id : 1452
         */

        private String card_id;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }
    }
}
