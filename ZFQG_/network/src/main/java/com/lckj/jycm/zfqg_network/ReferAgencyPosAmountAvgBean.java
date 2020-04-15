package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.math.BigDecimal;

public class ReferAgencyPosAmountAvgBean extends HttpResponse {

    /**
     * data : {"merchant_performance":"9190875.61","avg_performance":"31261.48"}
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
         * merchant_performance : 9190875.61
         * avg_performance : 31261.48
         */

        private String merchant_performance;
        private String avg_performance;

        public String getMerchant_performance() {
            return merchant_performance;
        }

        public void setMerchant_performance(String merchant_performance) {
            this.merchant_performance = merchant_performance;
        }

        public String getAvg_performance() {
            return avg_performance;
        }

        public void setAvg_performance(String avg_performance) {
            this.avg_performance = avg_performance;
        }
    }
}
