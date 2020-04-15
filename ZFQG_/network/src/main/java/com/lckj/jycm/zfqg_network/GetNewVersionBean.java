package com.lckj.jycm.zfqg_network;

import com.lckj.framework.network.HttpResponse;

public class GetNewVersionBean extends HttpResponse {

    /**
     * data : {"versionInfo":{"version_no":"1.0.2","note":"APP初始版本上线\t\t\t\t","device_type":"android","id":27,"version_url":"https://www.021163.cn/jjstapp","status":"1"}}
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
         * versionInfo : {"version_no":"1.0.2","note":"APP初始版本上线\t\t\t\t","device_type":"android","id":27,"version_url":"https://www.021163.cn/jjstapp","status":"1"}
         */

        private VersionInfoBean versionInfo;

        public VersionInfoBean getVersionInfo() {
            return versionInfo;
        }

        public void setVersionInfo(VersionInfoBean versionInfo) {
            this.versionInfo = versionInfo;
        }

        public static class VersionInfoBean {
            /**
             * version_no : 1.0.2
             * note : APP初始版本上线
             * device_type : android
             * id : 27
             * version_url : https://www.021163.cn/jjstapp
             * status : 1
             */

            private String version_no;
            private String note;
            private String device_type;
            private int id;
            private String version_url;
            private String status;

            public String getVersion_no() {
                return version_no;
            }

            public void setVersion_no(String version_no) {
                this.version_no = version_no;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVersion_url() {
                return version_url;
            }

            public void setVersion_url(String version_url) {
                this.version_url = version_url;
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
