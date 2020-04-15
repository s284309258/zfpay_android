package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetUserAuthStatusBean extends HttpResponse {


    /**
     * data : {"userAuthStatus":{"card_photo":"FjhQb53TC4qf14eNKSBGC-__oexh,Fttq_EtUgznQZ0nvoXe7srGewJbZ,FvniB1zRtwdXFeF129-E8zw39HSD","tradeAmountAll":"104532321.32","tradeAmountDay":"0.00","id_card":"352228198203282016","auth_remark":null,"real_name":"李贤耀","auth_status":"09"}}
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
         * userAuthStatus : {"card_photo":"FjhQb53TC4qf14eNKSBGC-__oexh,Fttq_EtUgznQZ0nvoXe7srGewJbZ,FvniB1zRtwdXFeF129-E8zw39HSD","tradeAmountAll":"104532321.32","tradeAmountDay":"0.00","id_card":"352228198203282016","auth_remark":null,"real_name":"李贤耀","auth_status":"09"}
         */

        private UserAuthStatusBean userAuthStatus;

        public UserAuthStatusBean getUserAuthStatus() {
            return userAuthStatus;
        }

        public void setUserAuthStatus(UserAuthStatusBean userAuthStatus) {
            this.userAuthStatus = userAuthStatus;
        }

        public static class UserAuthStatusBean {
            /**
             * card_photo : FjhQb53TC4qf14eNKSBGC-__oexh,Fttq_EtUgznQZ0nvoXe7srGewJbZ,FvniB1zRtwdXFeF129-E8zw39HSD
             * tradeAmountAll : 104532321.32
             * tradeAmountDay : 0.00
             * id_card : 352228198203282016
             * auth_remark : null
             * real_name : 李贤耀
             * auth_status : 09
             */

            private String card_photo;
            private String tradeAmountAll;
            private String tradeAmountDay;
            private String id_card;
            private String auth_remark;
            private String real_name;
            private String auth_status;

            public String getCard_photo() {
                return card_photo;
            }

            public void setCard_photo(String card_photo) {
                this.card_photo = card_photo;
            }

            public String getTradeAmountAll() {
                return tradeAmountAll;
            }

            public void setTradeAmountAll(String tradeAmountAll) {
                this.tradeAmountAll = tradeAmountAll;
            }

            public String getTradeAmountDay() {
                return tradeAmountDay;
            }

            public void setTradeAmountDay(String tradeAmountDay) {
                this.tradeAmountDay = tradeAmountDay;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getAuth_remark() {
                return auth_remark;
            }

            public void setAuth_remark(String auth_remark) {
                this.auth_remark = auth_remark;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getAuth_status() {
                return auth_status;
            }

            public void setAuth_status(String auth_status) {
                this.auth_status = auth_status;
            }
        }
    }
}
