package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class CreateAdvInfoBean extends HttpResponse {

    /**
     * data : {"id":6,"gold":1280,"amount":0,"sumAmount":0,"createBy":null,"createDate":"2019-04-08 14:26:21","updateBy":null,"updateDate":"2019-04-17 10:40:34","teamMember":null}
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
         * id : 6
         * gold : 1280
         * amount : 0
         * sumAmount : 0
         * createBy : null
         * createDate : 2019-04-08 14:26:21
         * updateBy : null
         * updateDate : 2019-04-17 10:40:34
         * teamMember : null
         */

        private int id;
        private String gold;
        private String amount;
        private String sumAmount;
        private String shareReward;
        private Object createBy;
        private String createDate;
        private Object updateBy;
        private String updateDate;
        private Object teamMember;

        public String getShareReward() {
            return shareReward;
        }

        public void setShareReward(String shareReward) {
            this.shareReward = shareReward;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getSumAmount() {
            return sumAmount;
        }

        public void setSumAmount(String sumAmount) {
            this.sumAmount = sumAmount;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public Object getTeamMember() {
            return teamMember;
        }

        public void setTeamMember(Object teamMember) {
            this.teamMember = teamMember;
        }
    }
}
