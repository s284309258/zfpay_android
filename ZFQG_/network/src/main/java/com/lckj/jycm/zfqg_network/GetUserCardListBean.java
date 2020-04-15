package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetUserCardListBean extends HttpResponse {

    /**
     * data : {"userCardList":[{"bank_code":"103100021172","card_photo":"defaultAvatar.png,defaultAvatar.png","user_id":"3","account_name":"沧海","bank_name":"中国农业银行股份有限公司北京金融大街丰盛支行","remark":null,"is_default":"1","card_id":1452,"account":"123456789012345678","status":"09"},{"bank_code":"103100021172","card_photo":"defaultAvatar.png,defaultAvatar.png","user_id":"3","account_name":"沧海","bank_name":"中国农业银行股份有限公司北京金融大街丰盛支行","remark":null,"is_default":"0","card_id":1451,"account":"123456789012345678","status":"09"},{"bank_code":"103100021172","card_photo":"defaultAvatar.png,defaultAvatar.png","user_id":"3","account_name":"沧海","bank_name":"中国农业银行股份有限公司北京金融大街丰盛支行","remark":null,"is_default":"0","card_id":1450,"account":"123456789012345678","status":"09"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<UserCardListBean> userCardList;

        public List<UserCardListBean> getUserCardList() {
            return userCardList;
        }

        public void setUserCardList(List<UserCardListBean> userCardList) {
            this.userCardList = userCardList;
        }

        public static class UserCardListBean {
            /**
             * bank_code : 103100021172
             * card_photo : defaultAvatar.png,defaultAvatar.png
             * user_id : 3
             * account_name : 沧海
             * bank_name : 中国农业银行股份有限公司北京金融大街丰盛支行
             * remark : null
             * is_default : 1
             * card_id : 1452
             * account : 123456789012345678
             * status : 09
             */

            private String bank_code;
            private String card_photo;
            private String user_id;
            private String account_name;
            private String bank_name;
            private String remark;
            private String is_default;
            private String card_id;
            private String account;
            private String status;

            public String getBank_code() {
                return bank_code;
            }

            public void setBank_code(String bank_code) {
                this.bank_code = bank_code;
            }

            public String getCard_photo() {
                return card_photo;
            }

            public void setCard_photo(String card_photo) {
                this.card_photo = card_photo;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getAccount_name() {
                return account_name;
            }

            public void setAccount_name(String account_name) {
                this.account_name = account_name;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
                this.is_default = is_default;
            }

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
