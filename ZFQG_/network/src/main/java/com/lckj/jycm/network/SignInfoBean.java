package com.lckj.jycm.network;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class SignInfoBean extends HttpResponse {
    /**
     * data : {"signList":[{"id":6,"signDays":6,"gold":98,"addGold":0,"createBy":"admin","createDate":"2019-04-05 17:36:05","updateBy":null,"updateDate":null},{"id":7,"signDays":7,"gold":193,"addGold":100,"createBy":"admin","createDate":"2019-04-05 17:36:25","updateBy":null,"updateDate":null}],"signGold":293,"signSum":7}
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
         * signList : [{"id":6,"signDays":6,"gold":98,"addGold":0,"createBy":"admin","createDate":"2019-04-05 17:36:05","updateBy":null,"updateDate":null},{"id":7,"signDays":7,"gold":193,"addGold":100,"createBy":"admin","createDate":"2019-04-05 17:36:25","updateBy":null,"updateDate":null}]
         * signGold : 293
         * signSum : 7
         */

        private int signGold;
        private int signSum;
        private List<SignListBean> signList;

        public int getSignGold() {
            return signGold;
        }

        public void setSignGold(int signGold) {
            this.signGold = signGold;
        }

        public int getSignSum() {
            return signSum;
        }

        public void setSignSum(int signSum) {
            this.signSum = signSum;
        }

        public List<SignListBean> getSignList() {
            return signList;
        }

        public void setSignList(List<SignListBean> signList) {
            this.signList = signList;
        }

        public static class SignListBean {
            /**
             * id : 6
             * signDays : 6
             * gold : 98
             * addGold : 0
             * createBy : admin
             * createDate : 2019-04-05 17:36:05
             * updateBy : null
             * updateDate : null
             */

            private int id;
            private int signDays;
            private String gold;
            private String addGold;
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

            public int getSignDays() {
                return signDays;
            }

            public void setSignDays(int signDays) {
                this.signDays = signDays;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getAddGold() {
                return addGold;
            }

            public void setAddGold(String addGold) {
                this.addGold = addGold;
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
}
