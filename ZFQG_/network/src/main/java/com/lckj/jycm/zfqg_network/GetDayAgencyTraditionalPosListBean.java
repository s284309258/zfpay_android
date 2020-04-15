package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetDayAgencyTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"dayAgencyMposList":[{"date":"20190819","user_num":"0","agency_trade_num":"0","performance":"0.00"},{"date":"20190820","user_num":"0","agency_trade_num":"0","performance":"0.00"},{"date":"20190821","user_num":"0","agency_trade_num":"0","performance":"0.00"},{"date":"20190822","user_num":"0","agency_trade_num":"0","performance":"0.00"},{"date":"20190823","user_num":"0","agency_trade_num":"0","performance":"0.00"},{"date":"20190824","user_num":"0","agency_trade_num":"0","performance":"0.00"},{"date":"20190825","user_num":"0","agency_trade_num":"0","performance":"0.00"}]}
     */

    public DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public List<DayAgencyMposListBean> dayAgencyMposList;
        public List<DayAgencyMposListBean> monthAgencyMposList;
        public List<DayAgencyMposListBean> dayMerchantMposList;
        public List<DayAgencyMposListBean> monthMerchantMposList;
        public List<DayAgencyMposListBean> dayAgencyTraditionalPosList;
        public List<DayAgencyMposListBean> monthAgencyTraditionalPosList;
        public List<DayAgencyMposListBean> dayMerchantTraditionalPosList;
        public List<DayAgencyMposListBean> monthMerchantTraditionalPosList;

        public List<DayAgencyMposListBean> getDayAgencyTraditionalPosList() {
            return dayAgencyTraditionalPosList;
        }

        public void setDayAgencyTraditionalPosList(List<DayAgencyMposListBean> dayAgencyTraditionalPosList) {
            this.dayAgencyTraditionalPosList = dayAgencyTraditionalPosList;
        }

        public List<DayAgencyMposListBean> getMonthAgencyTraditionalPosList() {
            return monthAgencyTraditionalPosList;
        }

        public void setMonthAgencyTraditionalPosList(List<DayAgencyMposListBean> monthAgencyTraditionalPosList) {
            this.monthAgencyTraditionalPosList = monthAgencyTraditionalPosList;
        }

        public List<DayAgencyMposListBean> getDayMerchantTraditionalPosList() {
            return dayMerchantTraditionalPosList;
        }

        public void setDayMerchantTraditionalPosList(List<DayAgencyMposListBean> dayMerchantTraditionalPosList) {
            this.dayMerchantTraditionalPosList = dayMerchantTraditionalPosList;
        }

        public List<DayAgencyMposListBean> getMonthMerchantTraditionalPosList() {
            return monthMerchantTraditionalPosList;
        }

        public void setMonthMerchantTraditionalPosList(List<DayAgencyMposListBean> monthMerchantTraditionalPosList) {
            this.monthMerchantTraditionalPosList = monthMerchantTraditionalPosList;
        }

        public List<DayAgencyMposListBean> getMonthAgencyMposList() {
            return monthAgencyMposList;
        }

        public void setMonthAgencyMposList(List<DayAgencyMposListBean> monthAgencyMposList) {
            this.monthAgencyMposList = monthAgencyMposList;
        }

        public List<DayAgencyMposListBean> getDayMerchantMposList() {
            return dayMerchantMposList;
        }

        public void setDayMerchantMposList(List<DayAgencyMposListBean> dayMerchantMposList) {
            this.dayMerchantMposList = dayMerchantMposList;
        }

        public List<DayAgencyMposListBean> getMonthMerchantMposList() {
            return monthMerchantMposList;
        }

        public void setMonthMerchantMposList(List<DayAgencyMposListBean> monthMerchantMposList) {
            this.monthMerchantMposList = monthMerchantMposList;
        }

        public List<DayAgencyMposListBean> getDayAgencyMposList() {
            return dayAgencyMposList;
        }

        public void setDayAgencyMposList(List<DayAgencyMposListBean> dayAgencyMposList) {
            this.dayAgencyMposList = dayAgencyMposList;
        }

        public static class DayAgencyMposListBean {
            /**
             * date : 20190819
             * user_num : 0
             * agency_trade_num : 0
             * performance : 0.00
             */

            public String date;
            public String user_num;
            public String act_num;
            public String performance;

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
