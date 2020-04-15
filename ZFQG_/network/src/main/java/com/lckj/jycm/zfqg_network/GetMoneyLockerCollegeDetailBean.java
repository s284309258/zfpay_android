package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetMoneyLockerCollegeDetailBean extends HttpResponse {
    /**
     * data : {"moneyLockerCollege":{"money_locker_title":"钱柜宝典-MPOS未到账处理流程","cre_datetime":"2019-08-23 17:51:17","money_locker_id":"2","money_locker_cover":"f8e73539-828f-44a4-90b2-ce3f96324285.jpg","money_locker_nav":"5e3ceb85-7460-4b91-a67e-ba826a63f810.mp4","money_locker_content":"f993c1ad-ff46-4792-a623-8dfaa3308af7.jpg,d14987d5-5ecf-4b8b-a74a-1aad0a4e7c2e.png,eae393d2-cb79-4327-983a-052a6f7edcb2.png"}}
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
         * moneyLockerCollege : {"money_locker_title":"钱柜宝典-MPOS未到账处理流程","cre_datetime":"2019-08-23 17:51:17","money_locker_id":"2","money_locker_cover":"f8e73539-828f-44a4-90b2-ce3f96324285.jpg","money_locker_nav":"5e3ceb85-7460-4b91-a67e-ba826a63f810.mp4","money_locker_content":"f993c1ad-ff46-4792-a623-8dfaa3308af7.jpg,d14987d5-5ecf-4b8b-a74a-1aad0a4e7c2e.png,eae393d2-cb79-4327-983a-052a6f7edcb2.png"}
         */

        private MoneyLockerCollegeBean moneyLockerCollege;

        public MoneyLockerCollegeBean getMoneyLockerCollege() {
            return moneyLockerCollege;
        }

        public void setMoneyLockerCollege(MoneyLockerCollegeBean moneyLockerCollege) {
            this.moneyLockerCollege = moneyLockerCollege;
        }

        public static class MoneyLockerCollegeBean {
            /**
             * money_locker_title : 钱柜宝典-MPOS未到账处理流程
             * cre_datetime : 2019-08-23 17:51:17
             * money_locker_id : 2
             * money_locker_cover : f8e73539-828f-44a4-90b2-ce3f96324285.jpg
             * money_locker_nav : 5e3ceb85-7460-4b91-a67e-ba826a63f810.mp4
             * money_locker_content : f993c1ad-ff46-4792-a623-8dfaa3308af7.jpg,d14987d5-5ecf-4b8b-a74a-1aad0a4e7c2e.png,eae393d2-cb79-4327-983a-052a6f7edcb2.png
             */

            private String money_locker_title;
            private String cre_datetime;
            private String money_locker_id;
            private String money_locker_cover;
            private String money_locker_nav;
            private String money_locker_content;

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

            public String getMoney_locker_nav() {
                return money_locker_nav;
            }

            public void setMoney_locker_nav(String money_locker_nav) {
                this.money_locker_nav = money_locker_nav;
            }

            public String getMoney_locker_content() {
                return money_locker_content;
            }

            public void setMoney_locker_content(String money_locker_content) {
                this.money_locker_content = money_locker_content;
            }
        }
    }
}
