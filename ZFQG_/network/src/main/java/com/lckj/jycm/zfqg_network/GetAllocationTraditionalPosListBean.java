package com.lckj.jycm.zfqg_network;

import android.text.TextUtils;

import com.lckj.framework.network.HttpResponse;

import java.util.List;

public class GetAllocationTraditionalPosListBean extends HttpResponse {

    /**
     * data : {"allocationTraditionalPosList":[{"cre_datetime":"2019-08-24 17:20:11","allocation_id":"13","real_name":"沧海","sn":"95476182"},{"cre_datetime":"2019-08-24 17:20:11","allocation_id":"12","real_name":"沧海","sn":"91581156"},{"cre_datetime":"2019-08-24 00:00:00","allocation_id":"11","real_name":"沧海","sn":"92280318"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<AllocationTraditionalPosListBean> allocationTraditionalPosList;
        private List<AllocationTraditionalPosListBean> allocationTrafficCardList;
        private List<AllocationTraditionalPosListBean> allocationMposList;

        public List<AllocationTraditionalPosListBean> getAllocationTrafficCardList() {
            return allocationTrafficCardList;
        }

        public void setAllocationTrafficCardList(List<AllocationTraditionalPosListBean> allocationTrafficCardList) {
            this.allocationTrafficCardList = allocationTrafficCardList;
        }

        public List<AllocationTraditionalPosListBean> getAllocationMposList() {
            return allocationMposList;
        }

        public void setAllocationMposList(List<AllocationTraditionalPosListBean> allocationMposList) {
            this.allocationMposList = allocationMposList;
        }

        public List<AllocationTraditionalPosListBean> getAllocationTraditionalPosList() {
            return allocationTraditionalPosList;
        }

        public void setAllocationTraditionalPosList(List<AllocationTraditionalPosListBean> allocationTraditionalPosList) {
            this.allocationTraditionalPosList = allocationTraditionalPosList;
        }

        public static class AllocationTraditionalPosListBean {
            /**
             * cre_datetime : 2019-08-24 17:20:11
             * allocation_id : 13
             * real_name : 沧海
             * sn : 95476182
             */

            private String cre_datetime;
            private String allocation_id;
            private String real_name;
            private String sn;
            private String card_no;

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getCre_datetime() {
                return cre_datetime;
            }

            public void setCre_datetime(String cre_datetime) {
                this.cre_datetime = cre_datetime;
            }

            public String getAllocation_id() {
                return allocation_id;
            }

            public void setAllocation_id(String allocation_id) {
                this.allocation_id = allocation_id;
            }

            public String getReal_name() {
                if (TextUtils.isEmpty(real_name)) real_name = "——";
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}
