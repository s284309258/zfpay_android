package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class UserExpandInfoBean extends HttpResponse {

    /**
     * data : {"id":1,"frontUserId":11,"companyName":"蓝波测试公司","userName":"兰博","idCard":"430410203202114301","cardFrontImg":"http://123432.jpg","cardBackImg":"http://123432.jpg","createBy":"18682031914","createDate":"2019-04-15 11:38:27","updateBy":null,"updateDate":null,"status":1,"remark":null}
     * token : null
     * code : null
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
         * id : 1
         * frontUserId : 11
         * companyName : 蓝波测试公司
         * userName : 兰博
         * idCard : 430410203202114301
         * cardFrontImg : http://123432.jpg
         * cardBackImg : http://123432.jpg
         * createBy : 18682031914
         * createDate : 2019-04-15 11:38:27
         * updateBy : null
         * updateDate : null
         * status : 1
         * remark : null
         */

        private int id;
        private int frontUserId;
        private String companyName;
        private String userName;
        private String idCard;
        private String cardFrontImg;
        private String cardBackImg;
        private String createBy;
        private String createDate;
        private Object updateBy;
        private Object updateDate;
        private int status;
        private Object remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFrontUserId() {
            return frontUserId;
        }

        public void setFrontUserId(int frontUserId) {
            this.frontUserId = frontUserId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getCardFrontImg() {
            return cardFrontImg;
        }

        public void setCardFrontImg(String cardFrontImg) {
            this.cardFrontImg = cardFrontImg;
        }

        public String getCardBackImg() {
            return cardBackImg;
        }

        public void setCardBackImg(String cardBackImg) {
            this.cardBackImg = cardBackImg;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }
    }
}
