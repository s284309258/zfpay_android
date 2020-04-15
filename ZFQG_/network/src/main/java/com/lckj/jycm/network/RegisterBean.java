package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class RegisterBean extends HttpResponse {
    /**
     * msg : 登陆成功
     * success : true
     * data : {"userId":2,"userName":"13599888899","userTel":"13599888899","payPassword":null,"loginPassword":"110110","userBalance":0,"headPhoto":"lcwlAvatarlogo.png","refererId":null,"userStatus":0,"completeRate":null,"occupation":null,"education":null,"profile":null,"age":1,"sex":1,"wxId":null,"areaId":null,"userRegtime":"1553936264427","orderNo":null,"benefitType":null,"updateTime":null,"invitationCode":"RenegadeNic","userRegtimes":"2019-03-30 16:57:44"}
     * token : 2|046D5A2M33IZQBZUP73I2HOFWI1LEIHZ
     * code : null
     */

    private DataBean data;
    private String token;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * userId : 2
         * userName : 13599888899
         * userTel : 13599888899
         * payPassword : null
         * loginPassword : 110110
         * userBalance : 0
         * headPhoto : lcwlAvatarlogo.png
         * refererId : null
         * userStatus : 0
         * completeRate : null
         * occupation : null
         * education : null
         * profile : null
         * age : 1
         * sex : 1
         * wxId : null
         * areaId : null
         * userRegtime : 1553936264427
         * orderNo : null
         * benefitType : null
         * updateTime : null
         * invitationCode : RenegadeNic
         * userRegtimes : 2019-03-30 16:57:44
         */

        private int userId;
        private String userName;
        private String userTel;
        private Object payPassword;
        private String loginPassword;
        private int userBalance;
        private String headPhoto;
        private Object refererId;
        private int userStatus;
        private Object completeRate;
        private Object occupation;
        private Object education;
        private Object profile;
        private int age;
        private int sex;
        private Object wxId;
        private Object areaId;
        private String userRegtime;
        private Object orderNo;
        private Object benefitType;
        private Object updateTime;
        private String invitationCode;
        private String userRegtimes;
        private FrontUserAccountDOBean frontUserAccountDO;

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

        public int getUserBalance() {
            return userBalance;
        }

        public void setUserBalance(int userBalance) {
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

        public int getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(int userStatus) {
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
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
    }
}
