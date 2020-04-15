package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class PersonInfoBean extends HttpResponse {
    /**
     * data : {"userId":11,"userName":"18682031914","userTel":"18682031914","payPassword":null,"loginPassword":"","userBalance":null,"headPhoto":"lcwlAvatarlogo.png","refererId":null,"userStatus":null,"completeRate":null,"occupation":null,"education":null,"profile":null,"age":null,"sex":null,"wxId":null,"areaId":null,"userRegtime":"1554704781344","orderNo":null,"benefitType":null,"updateTime":null,"invitationCode":"ASONTLZKQOPG7","userRegtimes":"2019-04-08 14:26:21","accountId":6,"lng":null,"lat":null,"realVerification":null,"frontUserAccountDO":{"id":6,"gold":10,"amount":0,"sumAmount":0,"createBy":null,"createDate":"2019-04-08 14:26:21","updateBy":null,"updateDate":null,"teamMember":0}}
     * token : 11|CJEO4KQ6Z47Z285NTUSA1JX361HIYY3R
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
         * userId : 11
         * userName : 18682031914
         * userTel : 18682031914
         * payPassword : null
         * loginPassword :
         * userBalance : null
         * headPhoto : lcwlAvatarlogo.png
         * refererId : null
         * userStatus : null
         * completeRate : null
         * occupation : null
         * education : null
         * profile : null
         * age : null
         * sex : null
         * wxId : null
         * areaId : null
         * userRegtime : 1554704781344
         * orderNo : null
         * benefitType : null
         * updateTime : null
         * invitationCode : ASONTLZKQOPG7
         * userRegtimes : 2019-04-08 14:26:21
         * accountId : 6
         * lng : null
         * lat : null
         * realVerification : null
         * frontUserAccountDO : {"id":6,"gold":10,"amount":0,"sumAmount":0,"createBy":null,"createDate":"2019-04-08 14:26:21","updateBy":null,"updateDate":null,"teamMember":0}
         */

        private int userId;
        private String userName;
        private String userTel;
        private Object payPassword;
        private String loginPassword;
        private Object userBalance;
        private String headPhoto;
        private Object refererId;
        private Object userStatus;
        private Object completeRate;
        private Object occupation;
        private Object education;
        private Object profile;
        private Object age;
        private Object sex;
        private Object wxId;
        private Object areaId;
        private String userRegtime;
        private Object orderNo;
        private Object benefitType;
        private Object updateTime;
        private String invitationCode;
        private String userRegtimes;
        private int accountId;
        private Object lng;
        private Object lat;
        private Object realVerification;
        private FrontUserAccountDOBean frontUserAccountDO;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        public Object getPayPassword() {
            return payPassword;
        }

        public void setPayPassword(Object payPassword) {
            this.payPassword = payPassword;
        }

        public String getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
            this.loginPassword = loginPassword;
        }

        public Object getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(Object userBalance) {
            this.userBalance = userBalance;
        }

        public String getHeadPhoto() {
            return headPhoto;
        }

        public void setHeadPhoto(String headPhoto) {
            this.headPhoto = headPhoto;
        }

        public Object getRefererId() {
            return refererId;
        }

        public void setRefererId(Object refererId) {
            this.refererId = refererId;
        }

        public Object getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(Object userStatus) {
            this.userStatus = userStatus;
        }

        public Object getCompleteRate() {
            return completeRate;
        }

        public void setCompleteRate(Object completeRate) {
            this.completeRate = completeRate;
        }

        public Object getOccupation() {
            return occupation;
        }

        public void setOccupation(Object occupation) {
            this.occupation = occupation;
        }

        public Object getEducation() {
            return education;
        }

        public void setEducation(Object education) {
            this.education = education;
        }

        public Object getProfile() {
            return profile;
        }

        public void setProfile(Object profile) {
            this.profile = profile;
        }

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getWxId() {
            return wxId;
        }

        public void setWxId(Object wxId) {
            this.wxId = wxId;
        }

        public Object getAreaId() {
            return areaId;
        }

        public void setAreaId(Object areaId) {
            this.areaId = areaId;
        }

        public String getUserRegtime() {
            return userRegtime;
        }

        public void setUserRegtime(String userRegtime) {
            this.userRegtime = userRegtime;
        }

        public Object getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(Object orderNo) {
            this.orderNo = orderNo;
        }

        public Object getBenefitType() {
            return benefitType;
        }

        public void setBenefitType(Object benefitType) {
            this.benefitType = benefitType;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public String getUserRegtimes() {
            return userRegtimes;
        }

        public void setUserRegtimes(String userRegtimes) {
            this.userRegtimes = userRegtimes;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getRealVerification() {
            return realVerification;
        }

        public void setRealVerification(Object realVerification) {
            this.realVerification = realVerification;
        }

        public FrontUserAccountDOBean getFrontUserAccountDO() {
            return frontUserAccountDO;
        }

        public void setFrontUserAccountDO(FrontUserAccountDOBean frontUserAccountDO) {
            this.frontUserAccountDO = frontUserAccountDO;
        }

        public static class FrontUserAccountDOBean {
            /**
             * id : 6
             * gold : 10
             * amount : 0
             * sumAmount : 0
             * createBy : null
             * createDate : 2019-04-08 14:26:21
             * updateBy : null
             * updateDate : null
             * teamMember : 0
             */

            private int id;
            private String gold;
            private String amount;
            private String sumAmount;
            private Object createBy;
            private String createDate;
            private Object updateBy;
            private Object updateDate;
            private int teamMember;

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

            public Object getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(Object updateDate) {
                this.updateDate = updateDate;
            }

            public int getTeamMember() {
                return teamMember;
            }

            public void setTeamMember(int teamMember) {
                this.teamMember = teamMember;
            }
        }
    }
}
