package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class MerchantInfoBean extends HttpResponse {

    /**
     * msg : 请求成功
     * success : true
     * data : {"id":2,"frontUserId":11,"merName":"展涛大厦八元里自选快餐","userName":"联系人","userTel":"13802211221","industry":"1","localtion":"民治大道223号","lng":"113.93029","lat":"22.53291","showImg":"1234abc.img,234cac.img","busLic":"12344321abc.img","status":1,"remark":"\u201c\u201d","createTime":"\u201c\u201d","createBy":"18682031914","createDate":"2019-04-10 16:44:08","updateBy":"\u201c\u201d","updateDate":"\u201c\u201d","distance":"\u201c\u201d"}
     * token :
     * code :
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
         * id : 2
         * frontUserId : 11
         * merName : 展涛大厦八元里自选快餐
         * userName : 联系人
         * userTel : 13802211221
         * industry : 1
         * localtion : 民治大道223号
         * lng : 113.93029
         * lat : 22.53291
         * showImg : 1234abc.img,234cac.img
         * busLic : 12344321abc.img
         * status : 1
         * remark : “”
         * createTime : “”
         * createBy : 18682031914
         * createDate : 2019-04-10 16:44:08
         * updateBy : “”
         * updateDate : “”
         * distance : “”
         */

        private int id;
        private int frontUserId;
        private String merName;
        private String userName;
        private String userTel;
        private String industry;
        private String localtion;
        private String lng;
        private String lat;
        private String showImg;
        private String busLic;
        private int status;
        private String remark;
        private String createTime;
        private String createBy;
        private String createDate;
        private String updateBy;
        private String updateDate;
        private String distance;

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

        public String getMerName() {
            return merName;
        }

        public void setMerName(String merName) {
            this.merName = merName;
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

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getLocaltion() {
            return localtion;
        }

        public void setLocaltion(String localtion) {
            this.localtion = localtion;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getShowImg() {
            return showImg;
        }

        public void setShowImg(String showImg) {
            this.showImg = showImg;
        }

        public String getBusLic() {
            return busLic;
        }

        public void setBusLic(String busLic) {
            this.busLic = busLic;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }
    }
}
