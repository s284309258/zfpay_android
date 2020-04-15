package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.ArrayList;
import java.util.List;

public class GetCashRecordListBean extends HttpResponse {

    /**
     * data : {"cashRecordList":[{"cash_id":"2","order_id":"20190820203527756597528","user_id":"3","account":"123456789012345678","cash_money":"800.00","cash_actual_money":"741.00","feet_rate":"0.070","rate_feet_money":"56.00","single_feet_money":"3.00","deduct_money":"0.00","status":"00","cre_date":"2019-08-20 20:35:27","cashRecordDetailList":[{"cash_detail_id":"2","cash_id":"2","cash_status":"00","note":"申请提现","cre_date":"2019-08-20 20:35:27"}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CashRecordListBean> cashRecordList;

        public List<CashRecordListBean> getCashRecordList() {
            return cashRecordList;
        }

        public void setCashRecordList(List<CashRecordListBean> cashRecordList) {
            this.cashRecordList = cashRecordList;
        }

        public static class CashRecordListBean {
            /**
             * cash_id : 2
             * order_id : 20190820203527756597528
             * user_id : 3
             * account : 123456789012345678
             * cash_money : 800.00
             * cash_actual_money : 741.00
             * feet_rate : 0.070
             * rate_feet_money : 56.00
             * single_feet_money : 3.00
             * deduct_money : 0.00
             * status : 00
             * cre_date : 2019-08-20 20:35:27
             * cashRecordDetailList : [{"cash_detail_id":"2","cash_id":"2","cash_status":"00","note":"申请提现","cre_date":"2019-08-20 20:35:27"}]
             */

            private String cash_id;
            private String order_id;
            private String user_id;
            private String account;
            private String cash_money;
            private String cash_actual_money;
            private String feet_rate;
            private String rate_feet_money;
            private String single_feet_money;
            private String deduct_money;
            private String status;
            private String cre_date;
            private List<CashRecordDetailListBean> cashRecordDetailList;

            public String getCash_id() {
                return cash_id;
            }

            public void setCash_id(String cash_id) {
                this.cash_id = cash_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getCash_money() {
                return cash_money;
            }

            public void setCash_money(String cash_money) {
                this.cash_money = cash_money;
            }

            public String getCash_actual_money() {
                return cash_actual_money;
            }

            public void setCash_actual_money(String cash_actual_money) {
                this.cash_actual_money = cash_actual_money;
            }

            public String getFeet_rate() {
                return feet_rate;
            }

            public void setFeet_rate(String feet_rate) {
                this.feet_rate = feet_rate;
            }

            public String getRate_feet_money() {
                return rate_feet_money;
            }

            public void setRate_feet_money(String rate_feet_money) {
                this.rate_feet_money = rate_feet_money;
            }

            public String getSingle_feet_money() {
                return single_feet_money;
            }

            public void setSingle_feet_money(String single_feet_money) {
                this.single_feet_money = single_feet_money;
            }

            public String getDeduct_money() {
                return deduct_money;
            }

            public void setDeduct_money(String deduct_money) {
                this.deduct_money = deduct_money;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCre_date() {
                return cre_date;
            }

            public void setCre_date(String cre_date) {
                this.cre_date = cre_date;
            }

            public List<CashRecordDetailListBean> getCashRecordDetailList() {
                if (cashRecordDetailList == null) cashRecordDetailList = new ArrayList<>();
                return cashRecordDetailList;
            }

            public void setCashRecordDetailList(List<CashRecordDetailListBean> cashRecordDetailList) {
                this.cashRecordDetailList = cashRecordDetailList;
            }

            public static class CashRecordDetailListBean {
                /**
                 * cash_detail_id : 2
                 * cash_id : 2
                 * cash_status : 00
                 * note : 申请提现
                 * cre_date : 2019-08-20 20:35:27
                 */

                private String cash_detail_id;
                private String cash_id;
                private String cash_status;
                private String note;
                private String cre_date;

                public String getCash_detail_id() {
                    return cash_detail_id;
                }

                public void setCash_detail_id(String cash_detail_id) {
                    this.cash_detail_id = cash_detail_id;
                }

                public String getCash_id() {
                    return cash_id;
                }

                public void setCash_id(String cash_id) {
                    this.cash_id = cash_id;
                }

                public String getCash_status() {
                    return cash_status;
                }

                public void setCash_status(String cash_status) {
                    this.cash_status = cash_status;
                }

                public String getNote() {
                    return note;
                }

                public void setNote(String note) {
                    this.note = note;
                }

                public String getCre_date() {
                    return cre_date;
                }

                public void setCre_date(String cre_date) {
                    this.cre_date = cre_date;
                }
            }
        }
    }
}
