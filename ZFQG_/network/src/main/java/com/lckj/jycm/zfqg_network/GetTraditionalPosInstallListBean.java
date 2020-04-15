package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetTraditionalPosInstallListBean extends HttpResponse {

    /**
     * data : {"traditionalPosInstallList":[{"install_id":"1","mer_code":"847584453990324","biz_msg":"装机完成","cre_datetime":"2019-09-16 10:45:04","merchant_name":"深圳市万和宝百货"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TraditionalPosInstallListBean> traditionalPosInstallList;

        public List<TraditionalPosInstallListBean> getTraditionalPosInstallList() {
            return traditionalPosInstallList;
        }

        public void setTraditionalPosInstallList(List<TraditionalPosInstallListBean> traditionalPosInstallList) {
            this.traditionalPosInstallList = traditionalPosInstallList;
        }

        public static class TraditionalPosInstallListBean {
            /**
             * install_id : 1
             * mer_code : 847584453990324
             * biz_msg : 装机完成
             * cre_datetime : 2019-09-16 10:45:04
             * merchant_name : 深圳市万和宝百货
             */

            private String install_id;
            private String mer_code;
            private String biz_msg;
            private String cre_datetime;
            private String merchant_name;

            public String getInstall_id() {
                return install_id;
            }

            public void setInstall_id(String install_id) {
                this.install_id = install_id;
            }

            public String getMer_code() {
                return mer_code;
            }

            public void setMer_code(String mer_code) {
                this.mer_code = mer_code;
            }

            public String getBiz_msg() {
                return biz_msg;
            }

            public void setBiz_msg(String biz_msg) {
                this.biz_msg = biz_msg;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getMerchant_name() {
                return merchant_name;
            }

            public void setMerchant_name(String merchant_name) {
                this.merchant_name = merchant_name;
            }
        }
    }
}
