package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetUserNewInfoBean extends HttpResponse {

    /**
     * data : {"userNewInfo":{"single_rate_feet_money":null,"money":0,"today_benefit":0,"deduct_money":0,"settle_money":0,"total_benefit":0,"settle_single_feet_money":0}}
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
         * userNewInfo : {"single_rate_feet_money":null,"money":0,"today_benefit":0,"deduct_money":0,"settle_money":0,"total_benefit":0,"settle_single_feet_money":0}
         */

        private UserNewInfoBean userNewInfo;

        public UserNewInfoBean getUserNewInfo() {
            return userNewInfo;
        }

        public void setUserNewInfo(UserNewInfoBean userNewInfo) {
            this.userNewInfo = userNewInfo;
        }

        public static class UserNewInfoBean {
            /**
             * single_rate_feet_money : null
             * money : 0.0
             * today_benefit : 0.0
             * deduct_money : 0.0
             * settle_money : 0.0
             * total_benefit : 0.0
             * settle_single_feet_money : 0.0
             */

            private Object single_rate_feet_money;
            private double money;
            private double today_benefit;
            private double deduct_money;
            private double settle_money;
            private double total_benefit;
            private double settle_single_feet_money;

            public Object getSingle_rate_feet_money() {
                return single_rate_feet_money;
            }

            public void setSingle_rate_feet_money(Object single_rate_feet_money) {
                this.single_rate_feet_money = single_rate_feet_money;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getToday_benefit() {
                return today_benefit;
            }

            public void setToday_benefit(double today_benefit) {
                this.today_benefit = today_benefit;
            }

            public double getDeduct_money() {
                return deduct_money;
            }

            public void setDeduct_money(double deduct_money) {
                this.deduct_money = deduct_money;
            }

            public double getSettle_money() {
                return settle_money;
            }

            public void setSettle_money(double settle_money) {
                this.settle_money = settle_money;
            }

            public double getTotal_benefit() {
                return total_benefit;
            }

            public void setTotal_benefit(double total_benefit) {
                this.total_benefit = total_benefit;
            }

            public double getSettle_single_feet_money() {
                return settle_single_feet_money;
            }

            public void setSettle_single_feet_money(double settle_single_feet_money) {
                this.settle_single_feet_money = settle_single_feet_money;
            }
        }
    }
}
