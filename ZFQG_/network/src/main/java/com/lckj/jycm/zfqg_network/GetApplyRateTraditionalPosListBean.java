package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetApplyRateTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"applyRateTraditionalPosList":[{"credit_card_rate":"0.60","sn":"95314618"},{"credit_card_rate":"0.60","sn":"92617921"},{"credit_card_rate":"0.60","sn":"92932752"},{"credit_card_rate":"0.60","sn":"95217293"},{"credit_card_rate":"0.60","sn":"92308252"},{"credit_card_rate":"0.60","sn":"95476182"},{"credit_card_rate":"0.60","sn":"91581156"},{"credit_card_rate":"0.60","sn":"94585127"},{"credit_card_rate":"0.60","sn":"95370909"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ApplyRateTraditionalPosListBean> applyRateTraditionalPosList;
        private List<ApplyRateTraditionalPosListBean> applyRateMposList;

        public List<ApplyRateTraditionalPosListBean> getApplyRateMposList() {
            return applyRateMposList;
        }

        public void setApplyRateMposList(List<ApplyRateTraditionalPosListBean> applyRateMposList) {
            this.applyRateMposList = applyRateMposList;
        }

        public List<ApplyRateTraditionalPosListBean> getApplyRateTraditionalPosList() {
            return applyRateTraditionalPosList;
        }

        public void setApplyRateTraditionalPosList(List<ApplyRateTraditionalPosListBean> applyRateTraditionalPosList) {
            this.applyRateTraditionalPosList = applyRateTraditionalPosList;
        }

        public static class ApplyRateTraditionalPosListBean {
            /**
             * credit_card_rate : 0.60
             * sn : 95314618
             */

            private String credit_card_rate;
            private String sn;

            public String getCredit_card_rate() {
                if(TextUtils.isEmpty(credit_card_rate))credit_card_rate="";
                return credit_card_rate;
            }

            public void setCredit_card_rate(String credit_card_rate) {
                this.credit_card_rate = credit_card_rate;
            }

            public String getSn() {
                if(TextUtils.isEmpty(sn))sn="";
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}
