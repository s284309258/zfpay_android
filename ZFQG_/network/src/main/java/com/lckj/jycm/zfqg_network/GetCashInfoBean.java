package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetCashInfoBean extends HttpResponse {

    /**
     * data : {"cashInfo":{"cashFeetRate":"0.07","can_cash_money":900,"cashMinNum":"10","cashSingleFeet":"3","deduct_money":"200.00"}}
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
         * cashInfo : {"cashFeetRate":"0.07","can_cash_money":900,"cashMinNum":"10","cashSingleFeet":"3","deduct_money":"200.00"}
         */

        private CashInfoBean cashInfo;

        public CashInfoBean getCashInfo() {
            return cashInfo;
        }

        public void setCashInfo(CashInfoBean cashInfo) {
            this.cashInfo = cashInfo;
        }

        public static class CashInfoBean {
            /**
             * cashFeetRate : 0.07
             * can_cash_money : 900.0
             * cashMinNum : 10
             * cashSingleFeet : 3
             * deduct_money : 200.00
             */

            private String cashFeetRate;
            private double can_cash_money;
            private String cashMinNum;
            private String cashSingleFeet;
            private String deduct_money;

            public String getCashFeetRate() {
                return cashFeetRate;
            }

            public void setCashFeetRate(String cashFeetRate) {
                this.cashFeetRate = cashFeetRate;
            }

            public double getCan_cash_money() {
                return can_cash_money;
            }

            public void setCan_cash_money(double can_cash_money) {
                this.can_cash_money = can_cash_money;
            }

            public String getCashMinNum() {
                return cashMinNum;
            }

            public void setCashMinNum(String cashMinNum) {
                this.cashMinNum = cashMinNum;
            }

            public String getCashSingleFeet() {
                return cashSingleFeet;
            }

            public void setCashSingleFeet(String cashSingleFeet) {
                this.cashSingleFeet = cashSingleFeet;
            }

            public String getDeduct_money() {
                return deduct_money;
            }

            public void setDeduct_money(String deduct_money) {
                this.deduct_money = deduct_money;
            }
        }
    }
}
