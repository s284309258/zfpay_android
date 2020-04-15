package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetCreditCardRateListBean extends HttpResponse {

    /**
     * data : {"creditCardRateList":["0.620","0.610","0.600","0.590","0.580"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> creditCardRateList;

        public List<String> getCreditCardRateList() {
            return creditCardRateList;
        }

        public void setCreditCardRateList(List<String> creditCardRateList) {
            this.creditCardRateList = creditCardRateList;
        }
    }
}
