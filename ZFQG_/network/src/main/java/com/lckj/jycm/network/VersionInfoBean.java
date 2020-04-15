package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

public class VersionInfoBean extends HttpResponse {
    /**
     * data : {"id":1,"versionNo":"1.0.1","versionUrl":"www.baidu.com","status":1,"deviceType":1,"text":"版本首发 创建文章 创建广告 分享广告","operId":1234,"createBy":"admin","createDate":"2019-04-22 21:15:25","updateBy":null,"updateDate":null}
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
         * versionNo : 1.0.1
         * versionUrl : www.baidu.com
         * status : 1
         * deviceType : 1
         * text : 版本首发 创建文章 创建广告 分享广告
         * operId : 1234
         * createBy : admin
         * createDate : 2019-04-22 21:15:25
         * updateBy : null
         * updateDate : null
         */

        private int id;
        private String versionNo;
        private String versionUrl;
        private int status;
        private int deviceType;
        private String text;
        private int operId;
        private String createBy;
        private String createDate;
        private Object updateBy;
        private Object updateDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getVersionUrl() {
            return versionUrl;
        }

        public void setVersionUrl(String versionUrl) {
            this.versionUrl = versionUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getOperId() {
            return operId;
        }

        public void setOperId(int operId) {
            this.operId = operId;
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
    }
}
