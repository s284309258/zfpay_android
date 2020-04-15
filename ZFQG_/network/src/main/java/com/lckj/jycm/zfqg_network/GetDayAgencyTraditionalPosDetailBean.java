package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetDayAgencyTraditionalPosDetailBean extends HttpResponse {

    /**
     * data : {"dayAgencyTraditionalPosDetail":{"user_num":"0","act_num":"0","performance":"0.00"}}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dayAgencyTraditionalPosDetail : {"user_num":"0","act_num":"0","performance":"0.00"}
         */

        public DayAgencyTraditionalPosDetailBean dayAgencyTraditionalPosDetail;
        public DayAgencyTraditionalPosDetailBean monthAgencyTraditionalPosDetail;
        public DayAgencyTraditionalPosDetailBean dayMerchantTraditionalPosDetail;
        public DayAgencyTraditionalPosDetailBean monthMerchantTraditionalPosDetail;
        public DayAgencyTraditionalPosDetailBean dayAgencyMposDetail;
        public DayAgencyTraditionalPosDetailBean monthAgencyMposDetail;
        public DayAgencyTraditionalPosDetailBean dayMerchantMposDetail;
        public DayAgencyTraditionalPosDetailBean monthMerchantMposDetail;

        public DayAgencyTraditionalPosDetailBean getMonthAgencyTraditionalPosDetail() {
            return monthAgencyTraditionalPosDetail;
        }

        public void setMonthAgencyTraditionalPosDetail(DayAgencyTraditionalPosDetailBean monthAgencyTraditionalPosDetail) {
            this.monthAgencyTraditionalPosDetail = monthAgencyTraditionalPosDetail;
        }

        public DayAgencyTraditionalPosDetailBean getDayMerchantTraditionalPosDetail() {
            return dayMerchantTraditionalPosDetail;
        }

        public void setDayMerchantTraditionalPosDetail(DayAgencyTraditionalPosDetailBean dayMerchantTraditionalPosDetail) {
            this.dayMerchantTraditionalPosDetail = dayMerchantTraditionalPosDetail;
        }

        public DayAgencyTraditionalPosDetailBean getMonthMerchantTraditionalPosDetail() {
            return monthMerchantTraditionalPosDetail;
        }

        public void setMonthMerchantTraditionalPosDetail(DayAgencyTraditionalPosDetailBean monthMerchantTraditionalPosDetail) {
            this.monthMerchantTraditionalPosDetail = monthMerchantTraditionalPosDetail;
        }

        public DayAgencyTraditionalPosDetailBean getDayAgencyMposDetail() {
            return dayAgencyMposDetail;
        }

        public void setDayAgencyMposDetail(DayAgencyTraditionalPosDetailBean dayAgencyMposDetail) {
            this.dayAgencyMposDetail = dayAgencyMposDetail;
        }

        public DayAgencyTraditionalPosDetailBean getMonthAgencyMposDetail() {
            return monthAgencyMposDetail;
        }

        public void setMonthAgencyMposDetail(DayAgencyTraditionalPosDetailBean monthAgencyMposDetail) {
            this.monthAgencyMposDetail = monthAgencyMposDetail;
        }

        public DayAgencyTraditionalPosDetailBean getDayMerchantMposDetail() {
            return dayMerchantMposDetail;
        }

        public void setDayMerchantMposDetail(DayAgencyTraditionalPosDetailBean dayMerchantMposDetail) {
            this.dayMerchantMposDetail = dayMerchantMposDetail;
        }

        public DayAgencyTraditionalPosDetailBean getMonthMerchantMposDetail() {
            return monthMerchantMposDetail;
        }

        public void setMonthMerchantMposDetail(DayAgencyTraditionalPosDetailBean monthMerchantMposDetail) {
            this.monthMerchantMposDetail = monthMerchantMposDetail;
        }

        public DayAgencyTraditionalPosDetailBean getDayAgencyTraditionalPosDetail() {
            return dayAgencyTraditionalPosDetail;
        }

        public void setDayAgencyTraditionalPosDetail(DayAgencyTraditionalPosDetailBean dayAgencyTraditionalPosDetail) {
            this.dayAgencyTraditionalPosDetail = dayAgencyTraditionalPosDetail;
        }

        public static class DayAgencyTraditionalPosDetailBean {
            /**
             * user_num : 0
             * act_num : 0
             * performance : 0.00
             */

            public String user_num;
            public String act_num;
            public String performance;
            public String date;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getUser_num() {
                return user_num;
            }

            public void setUser_num(String user_num) {
                this.user_num = user_num;
            }

            public String getAct_num() {
                return act_num;
            }

            public void setAct_num(String act_num) {
                this.act_num = act_num;
            }

            public String getPerformance() {
                return performance;
            }

            public void setPerformance(String performance) {
                this.performance = performance;
            }
        }
    }
}
