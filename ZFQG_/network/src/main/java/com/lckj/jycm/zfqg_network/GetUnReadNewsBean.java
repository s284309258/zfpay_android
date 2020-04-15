package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetUnReadNewsBean extends HttpResponse {
    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public String collegeFlag;
        public String applyRateFlag;
        public String recallFlag;
        public String cardFlag;
        public String appImgFlag;

        public String getAppImgFlag() {
            return appImgFlag;
        }

        public void setAppImgFlag(String appImgFlag) {
            this.appImgFlag = appImgFlag;
        }

        public String getCardFlag() {
            return cardFlag;
        }

        public void setCardFlag(String cardFlag) {
            this.cardFlag = cardFlag;
        }

        public String getCollegeFlag() {
            return collegeFlag;
        }

        public void setCollegeFlag(String collegeFlag) {
            this.collegeFlag = collegeFlag;
        }

        public String getApplyRateFlag() {
            return applyRateFlag;
        }

        public void setApplyRateFlag(String applyRateFlag) {
            this.applyRateFlag = applyRateFlag;
        }

        public String getRecallFlag() {
            return recallFlag;
        }

        public void setRecallFlag(String recallFlag) {
            this.recallFlag = recallFlag;
        }
    }
}
