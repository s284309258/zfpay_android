package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetSummaryTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"excellent_merchant":2,"dormant_merchant":2,"all_merchant":7,"active_merchant":2}
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
         * excellent_merchant : 2
         * dormant_merchant : 2
         * all_merchant : 7
         * active_merchant : 2
         */

        private String excellent_merchant;
        private String dormant_merchant;
        private String all_merchant;
        private String active_merchant;
        private String standard_merchant;

        public String getStandard_merchant() {
            return standard_merchant;
        }

        public void setStandard_merchant(String standard_merchant) {
            this.standard_merchant = standard_merchant;
        }

        public String getExcellent_merchant() {
            return excellent_merchant;
        }

        public void setExcellent_merchant(String excellent_merchant) {
            this.excellent_merchant = excellent_merchant;
        }

        public String getDormant_merchant() {
            return dormant_merchant;
        }

        public void setDormant_merchant(String dormant_merchant) {
            this.dormant_merchant = dormant_merchant;
        }

        public String getAll_merchant() {
            return all_merchant;
        }

        public void setAll_merchant(String all_merchant) {
            this.all_merchant = all_merchant;
        }

        public String getActive_merchant() {
            return active_merchant;
        }

        public void setActive_merchant(String active_merchant) {
            this.active_merchant = active_merchant;
        }
    }
}
