package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetMoneyLockerCollegeListBean extends HttpResponse {

    /**
     * data : {"moneyLockerCollegeList":[{"money_locker_title":"钱柜宝典-MPOS未到账处理流程","cre_datetime":"2019-08-23 17:51:17","money_locker_id":"2","money_locker_cover":"f8e73539-828f-44a4-90b2-ce3f96324285.jpg"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MoneyLockerCollegeListBean> moneyLockerCollegeList;

        public List<MoneyLockerCollegeListBean> getMoneyLockerCollegeList() {
            return moneyLockerCollegeList;
        }

        public void setMoneyLockerCollegeList(List<MoneyLockerCollegeListBean> moneyLockerCollegeList) {
            this.moneyLockerCollegeList = moneyLockerCollegeList;
        }

        public static class MoneyLockerCollegeListBean {
            /**
             * money_locker_title : 钱柜宝典-MPOS未到账处理流程
             * cre_datetime : 2019-08-23 17:51:17
             * money_locker_id : 2
             * money_locker_cover : f8e73539-828f-44a4-90b2-ce3f96324285.jpg
             */

            private String money_locker_title;
            private String cre_datetime;
            private String money_locker_id;
            private String money_locker_cover;

            public String getMoney_locker_title() {
                return money_locker_title;
            }

            public void setMoney_locker_title(String money_locker_title) {
                this.money_locker_title = money_locker_title;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getMoney_locker_id() {
                return money_locker_id;
            }

            public void setMoney_locker_id(String money_locker_id) {
                this.money_locker_id = money_locker_id;
            }

            public String getMoney_locker_cover() {
                return money_locker_cover;
            }

            public void setMoney_locker_cover(String money_locker_cover) {
                this.money_locker_cover = money_locker_cover;
            }
        }
    }
}
