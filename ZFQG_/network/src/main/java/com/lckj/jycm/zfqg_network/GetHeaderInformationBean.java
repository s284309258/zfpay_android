package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetHeaderInformationBean extends HttpResponse {

    /**
     * data : {"headerInfo":{"withdraw_money":"100.00","today_benefit":"100.00","settle_money":"0.00","total_benefit":"0.00"}}
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
         * headerInfo : {"withdraw_money":"100.00","today_benefit":"100.00","settle_money":"0.00","total_benefit":"0.00"}
         */

        private HeaderInfoBean headerInfo;

        public HeaderInfoBean getHeaderInfo() {
            return headerInfo;
        }

        public void setHeaderInfo(HeaderInfoBean headerInfo) {
            this.headerInfo = headerInfo;
        }

        public static class HeaderInfoBean {
            /**
             * withdraw_money : 100.00
             * today_benefit : 100.00
             * settle_money : 0.00
             * total_benefit : 0.00
             */

            private String withdraw_money;
            private String today_benefit;
            private String settle_money;
            private String total_benefit;

            public String getWithdraw_money() {
                return withdraw_money;
            }

            public void setWithdraw_money(String withdraw_money) {
                this.withdraw_money = withdraw_money;
            }

            public String getToday_benefit() {
                return today_benefit;
            }

            public void setToday_benefit(String today_benefit) {
                this.today_benefit = today_benefit;
            }

            public String getSettle_money() {
                return settle_money;
            }

            public void setSettle_money(String settle_money) {
                this.settle_money = settle_money;
            }

            public String getTotal_benefit() {
                return total_benefit;
            }

            public void setTotal_benefit(String total_benefit) {
                this.total_benefit = total_benefit;
            }
        }
    }
}
